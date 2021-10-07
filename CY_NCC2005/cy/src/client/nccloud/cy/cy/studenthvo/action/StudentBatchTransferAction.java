
package nccloud.cy.cy.studenthvo.action;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonConstant.AppletsParams;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nccloud.commons.collections.MapUtils;
import nccloud.cy.cy.studenthvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nccloud.vo.cy.cy.StudentHVOConst;

/**
 * 根据学生档案批量生成学生收款合同
 * 
 * @author tanrg
 *
 */
public class StudentBatchTransferAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		BillOperatorParam queryParam = this.getQueryParam(paramIRequest);
		try {
			// 1、根据前端传递的pks查询数据,获取AGGVO
			StudentHVO[] bills = this.queryStuBills(queryParam);

			if (MMValueCheck.isEmpty(bills)) {
				throw new BusinessException("未选中数据");
			}

			Set<String> clapks = new HashSet<String>();
			for (StudentHVO stu : bills) {
				String pk_classcy = stu.getDef1();
				if (StringUtils.isNotBlank(pk_classcy)) {
					clapks.add(pk_classcy);
				} else {
					throw new BusinessException("学生：" + stu.getSname() + "/" + stu.getIdcard() + "对应的班级信息为空");
				}
			}

			// 2. 获取班级信息
			Map<String, AggClasscyHVO> clmap = null;
			if (CollectionUtils.isNotEmpty(clapks)) {
				clmap = this.queryClasscyBills(clapks);
			}
			if (MapUtils.isEmpty(clmap)) {
				throw new BusinessException("所有的学生均未匹配到对应的班级信息");
			}

			List<AggCreditcontractHVO> listAgg = new ArrayList<AggCreditcontractHVO>();
			for (StudentHVO stu : bills) {
				AggClasscyHVO aggclvo = clmap.get(stu.getDef1());
				if (MMValueCheck.isEmpty(aggclvo)) {
					throw new BusinessException("学生：" + stu.getSname() + "/" + stu.getIdcard() + "未匹配到对应的班级信息");
				}
				ClasscyHVO clvo = aggclvo.getParentVO();
				if (MMValueCheck.isEmpty(clvo)) {
					throw new BusinessException("学生：" + stu.getSname() + "/" + stu.getIdcard() + "未匹配到对应的班级信息");
				}
				ClasscyDetailVO[] cDetailVOs = (ClasscyDetailVO[]) aggclvo.getChildrenVO();
				if (MMValueCheck.isEmpty(cDetailVOs)) {
					throw new BusinessException("学生：" + stu.getSname() + "/" + stu.getIdcard() + "对应的班级子表信息为空");
				}
				// 3. 组装合同信息生成收款合同
				AggCreditcontractHVO aggCCHVO = new AggCreditcontractHVO();
				CreditcontractHVO cchvo = new CreditcontractHVO();
				UFDouble amount = clvo.getPaystand();
				if (null == amount || (amount != null && amount.doubleValue() <= 0)) {
					throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的缴费标准为空或小于0");
				}
				String oclass = clvo.getOpenclas();// 2020级 2021级
				int opeanYear = 0;
				if (StringUtils.isBlank(oclass)) {
					throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级为空");
				} else {
					try {
						opeanYear = new Integer(oclass.substring(0, 4)).intValue();
					} catch (Exception e) {
						throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级" + oclass + "格式异常!（2021级）");
					}
				}

				String contractowner = clvo.getContractowner();
				if (MMValueCheck.isEmpty(contractowner)) {
					throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的合同归属组织为空");
				}
				if (opeanYear == 0) {
					throw new BusinessException("班级编码：" + clvo.getBill_no() + "对应的开班年级" + oclass + "格式异常!（2021级）");
				}

				int currYear = new UFDateTime().getYear();
				int htnx = 3 - (currYear - opeanYear);

				if (htnx > 3 || htnx <= 0) {
					throw new BusinessException(
							"合同年限异常：" + htnx + "年!\n班级编码：" + clvo.getBill_no() + "对应的开班年级" + oclass + "格式异常!（2021级）");
				}

				String stupk = stu.getPk_student();
				cchvo.setAmount(amount);// 缴费标准
				cchvo.setSchool(stu.getDef2());
				cchvo.setClasses(clvo.getPk_classcy());
				cchvo.setStudent(stupk);
				cchvo.setDef1(clvo.getPk_classcy());
				cchvo.setDef2(stupk);
				cchvo.setDef3("否");
				cchvo.setDef4("未生效");
				cchvo.setDef5(stu.getDef2());
				cchvo.setStartdate(new UFDateTime());

				Calendar cend = Calendar.getInstance();
				cend.add(Calendar.YEAR, htnx);
				UFDateTime endUFDateTime = new UFDateTime(cend.getTimeInMillis());
				String endYear = endUFDateTime.toString().substring(0, 4);
				UFDateTime enddate = new UFDateTime(endYear + "-06-30 00:00:00");
				cchvo.setEnddate(enddate);
				cchvo.setPk_group(AppletsParams.Pk_Group);// AppletsParams.Pk_Group
				cchvo.setPk_org(contractowner);
				cchvo.setPk_material(AppletsParams.pk_material);
				cchvo.setDbilldate(new UFDate());
				cchvo.setBillstatus(-1);
				cchvo.setStatus(VOStatus.NEW);

				// 合同子表数据处理
				List<CreditcontractDetailVO> ctdArrVO = detailDataManager(clvo, cDetailVOs);

				aggCCHVO.setChildrenVO(ctdArrVO.toArray(new CreditcontractDetailVO[0]));
				aggCCHVO.setParentVO(cchvo);

				listAgg.add(aggCCHVO);

				// 学生字段更新
				stu.setDef3("是");
				stu.setStatus(VOStatus.UPDATED);
			}
			ICreditcontracthvoMaintain icreditcontr = NCLocator.getInstance().lookup(ICreditcontracthvoMaintain.class);
			AggCreditcontractHVO[] retAggArr = icreditcontr.insert(listAgg.toArray(new AggCreditcontractHVO[0]), null);
			if (ArrayUtils.isNotEmpty(bills)) {
				VOUpdate<StudentHVO> updateOnhand = new VOUpdate<StudentHVO>();
				updateOnhand.update(bills, new String[] { "def3" });
			}

		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}

	private List<CreditcontractDetailVO> detailDataManager(ClasscyHVO clvo, ClasscyDetailVO[] cDetailVOs)
			throws BusinessException {
		List<CreditcontractDetailVO> ctdArrVO = new ArrayList<CreditcontractDetailVO>();
		if (MMValueCheck.isEmpty(cDetailVOs)) {
			return ctdArrVO;
		}
		int proplanNotlast = 0;
		for (int i = 0; i < cDetailVOs.length; i++) {
			CreditcontractDetailVO cdvo = new CreditcontractDetailVO();
			UFDate startdateOrigin = cDetailVOs[i].getStartdate();
			UFDate enddateOrigin = cDetailVOs[i].getEnddate();
			if (MMValueCheck.isEmpty(startdateOrigin) || MMValueCheck.isEmpty(enddateOrigin)) {
				throw new BusinessException("开始日期或结束日期为空");
			}
			if (!startdateOrigin.before(enddateOrigin)) {
				throw new BusinessException("结束日期小于开始日期");
			}
			UFDateTime startDate = new UFDateTime(startdateOrigin.toString().substring(0, 10) + " 00:00:00");
			UFDateTime endDate = new UFDateTime(enddateOrigin.toString().substring(0, 10) + " 00:00:00");
			long between_days = (endDate.getMillis() - startDate.getMillis()) / (1000 * 3600 * 24);

			cdvo.setStartdate(startDate);
			cdvo.setEnddate(endDate);
			cdvo.setPaydays(Integer.parseInt(String.valueOf(between_days)));
			UFDouble planamount = cDetailVOs[i].getPlanamount();
			if (null == planamount || (planamount != null && planamount.doubleValue() <= 0)) {
				throw new BusinessException("子表计划金额为空或小于0");
			}
			cdvo.setPlanamount(cDetailVOs[i].getPlanamount());
			UFDouble amount = clvo.getPaystand();

			int proplan = planamount.div(amount).multiply(100).intValue();
			if (i == cDetailVOs.length - 1) {
				cdvo.setProplan(100 - proplanNotlast);
			} else {
				cdvo.setProplan(proplan);
				proplanNotlast += proplan;
			}
			cdvo.setPayno(i + 1 + "");
			cdvo.setRowno(i + 1 + "");
			cdvo.setStatus(VOStatus.NEW);

			ctdArrVO.add(cdvo);
		}
		return ctdArrVO;
	}

//	private List<CreditcontractDetailVO> detailDataManager(ClasscyHVO clvo, ClasscyDetailVO[] cDetailVOs, int htnx) {
//		List<CreditcontractDetailVO> ctdArrVO = new ArrayList<CreditcontractDetailVO>();
//		if (MMValueCheck.isEmpty(cDetailVOs)) {
//			return ctdArrVO;
//		}
//		for (int i = 0; i < cDetailVOs.length; i++) {
//			CreditcontractDetailVO cdvo = new CreditcontractDetailVO();
//			UFDateTime startDate = new UFDateTime(
//					cDetailVOs[i].getStartdate().toString().substring(0, 10) + " 00:00:00");
//			UFDateTime endDate = new UFDateTime(cDetailVOs[i].getEnddate().toString().substring(0, 10) + " 00:00:00");
//			long between_days = (endDate.getMillis()-startDate.getMillis()) / (1000 * 3600 * 24);
//
//			cdvo.setStartdate(startDate);
//			cdvo.setEnddate(endDate);
//			cdvo.setPaydays(Integer.parseInt(String.valueOf(between_days)));
//			cdvo.setPlanamount(cDetailVOs[i].getPlanamount());
//			cdvo.setPayno(cDetailVOs[i].getRowno());
//			if (htnx == 3 && i == htnx-1) {
//				cdvo.setProplan(34);
//			} else {
//				cdvo.setProplan(new UFDouble(100).div(htnx).intValue());
//			}
//			cdvo.setRowno(cDetailVOs[i].getRowno());
//			cdvo.setStatus(VOStatus.NEW);
//
//			ctdArrVO.add(cdvo);
//		}
//		return ctdArrVO;
//	}

	/**
	 * 动作编码
	 *
	 * @return
	 */
	protected String getActionCode() {
		return StudentHVOConst.CONST_ACTION_DELETE;
	}

	/**
	 * 获取查询参数
	 *
	 * @param paramIRequest
	 * @return
	 */
	private BillOperatorParam getQueryParam(IRequest paramIRequest) {
		String strRead = paramIRequest.read();
		BillOperatorParam queryParam = JsonFactory.create().fromJson(strRead, BillOperatorParam.class);
		return queryParam;
	}

	/**
	 * 查询业务数据
	 *
	 * @param queryParam
	 * @return
	 * @throws MetaDataException
	 */
	private StudentHVO[] queryStuBills(BillOperatorParam queryParam) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		Collection<StudentHVO> billvo = service.queryBillOfVOByPKs(StudentHVO.class, queryParam.getPks(), false);
		if ((billvo == null) || (billvo.size() == 0)) {
			return null;
		}
		return billvo.toArray(new StudentHVO[0]);
	}

	@SuppressWarnings("unchecked")
	private Map<String, AggClasscyHVO> queryClasscyBills(Set<String> queryParam) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		Map<String, AggClasscyHVO> mapcl = new HashMap<String, AggClasscyHVO>();

		Collection<AggClasscyHVO> billvo = service.queryBillOfVOByPKs(AggClasscyHVO.class,
				queryParam.toArray(new String[0]), false);
		if ((billvo == null) || (billvo.size() == 0)) {
			return null;
		}

		for (AggClasscyHVO cagg : billvo) {
			mapcl.put(cagg.getParentVO().getPk_classcy(), cagg);
		}
		return mapcl;
	}
}
