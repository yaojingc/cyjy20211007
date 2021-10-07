package nccloud.impl.rule;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.itf.fct.ar.IArApprove;
import nc.jdbc.framework.generator.SequenceGenerator;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.fct.ar.entity.AggCtArVO;
import nc.vo.fct.ar.entity.CtArBVO;
import nc.vo.fct.ar.entity.CtArChangeVO;
import nc.vo.fct.ar.entity.CtArPlanVO;
import nc.vo.fct.ar.entity.CtArVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.itf.cy.cy.ICreditcontracthvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 学费收款合同 新增修改单据校验 处理单据变更业务
 * 
 * @author tanrg && yaojing
 *
 * @param <T>
 */
public class CreditcontractRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (MMValueCheck.isNotEmpty(paramArrayOfE)) {
			try {
				billCheck(paramArrayOfE);

				// 处理变更的业务
				dealModify(paramArrayOfE);

			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

	/**
	 * Add By yaojing 处理单据变更业务，用户点击变更时复制出一张新单据做变更 走系统的新增保存，在这里加上相关的处理
	 * 
	 * 1. 后规则中我们添加版本号的生成，表体合计的生成，以及表体合计的金额与表头总金额的校验
	 * 2.如果改学费收款合同已经生成了正式收款合同则需要同步变更正式收款合同 3.保存时需要回写学费减免申请为 已被消费
	 * 
	 * 学费收款合同： def2 学生 def6 虚拟合同版本号
	 * 
	 * 
	 * @param paramArrayOfE
	 * @throws BusinessException
	 */
	private void dealModify(T[] paramArrayOfE) throws BusinessException {

		if (paramArrayOfE.length == 1 && paramArrayOfE[0] instanceof AggCreditcontractHVO) {
			AggCreditcontractHVO aggvo = (AggCreditcontractHVO) paramArrayOfE[0];
			String xfjmpk = aggvo.getParentVO().getDef7();
			if (CyCommonUtils.isNotEmpty(xfjmpk)) {
				String pk_student = aggvo.getParentVO().getDef2();
				String idcard = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryIdCardByStudent(pk_student);
				/**
				 * 判断是否已经生成了正式的 收款合同 1. 若已生成，则需要同步变更 正式收款合同 2. 若未生成，则只需要将学费收款合同保存为最新的版本号+1
				 */
				// 已经生成合同,根据身份证查询合同查询单据主键vdef4
				String pk_rt = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).queryFormalContract(idcard);
				if (CyCommonUtils.isNotEmpty(pk_rt)) {
					AggCtArVO aggctarvo = (AggCtArVO) CyCommonUtils.getInstance(ICrmService.class).queryDeatil(pk_rt,
							AggCtArVO.class);
					AggCtArVO newaggvo = this.setChangeVOWhenModify(aggctarvo, aggvo);
					IArApprove IArApprove = NCLocator.getInstance().lookup(IArApprove.class);
					AggCtArVO[] modify = IArApprove.modify(new AggCtArVO[] { newaggvo }, null);
					CtArPlanVO[] ctArPlanVO = modify[0].getCtArPlanVO();
					for (CtArPlanVO ctArPlanVO2 : ctArPlanVO) {
						CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).updateFctplan(
								ctArPlanVO2.getPk_fct_ar_plan(), ctArPlanVO2.getPlanmoney().toString(),
								ctArPlanVO2.getPlanrate().toString());
					}
					CtArBVO[] ctArBVO = modify[0].getCtArBVO();
					CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).updateFctbasic(
							ctArBVO[0].getPk_fct_ar_b(), ctArBVO[0].getNorigtaxmny().toString(),
							ctArBVO[0].getNorigmny().toString(), ctArBVO[0].getNtax().toString());
					String pk_fct_ar = modify[0].getParentVO().getPk_fct_ar();
					String ljjm = modify[0].getParentVO().getVdef6();
					CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class).updateFctLjjm(pk_fct_ar, ljjm);
					// 学费收款合同版本号更新
					Integer version = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class)
							.queryLatestVersion(pk_student);
					aggvo.getParentVO().setDef6(version.toString());
					String scjmhje = aggvo.getParentVO().getDef10();
					String jmje = aggvo.getParentVO().getDef8();
					if (CyCommonUtils.isNotEmpty(scjmhje)) {
						scjmhje = new UFDouble(scjmhje).sub(new UFDouble(jmje)).toString();
						aggvo.getParentVO().setDef10(scjmhje);
					}
					CreditcontractDetailVO[] childrenVOs = (CreditcontractDetailVO[]) aggvo.getChildrenVO();
					if (CyCommonUtils.isNotEmpty(childrenVOs)) {
						for (CreditcontractDetailVO creditcontractDetailVO : childrenVOs) {
							creditcontractDetailVO.setDef6(version.toString());// 表体回写版本号
						}
					}
					// 修改学费减免单的核销状态
					CyCommonUtils.getInstance(ICyCommonSqlUtil.class).updateXfjmStatus(xfjmpk);
				} else {// 还未生成合同
					// 先得到同一个学生的合同的最新的版本号
					Integer version = CyCommonUtils.getInstance(ICreditcontracthvoMaintain.class)
							.queryLatestVersion(pk_student);
					aggvo.getParentVO().setDef6(version.toString());
					CreditcontractDetailVO[] childrenVOs = (CreditcontractDetailVO[]) aggvo.getChildrenVO();
					if (CyCommonUtils.isNotEmpty(childrenVOs)) {
						for (CreditcontractDetailVO creditcontractDetailVO : childrenVOs) {
							creditcontractDetailVO.setDef6(version.toString());// 表体回写版本号
						}
					}
				}
			}
			String def6 = aggvo.getParentVO().getDef6();
			if (CyCommonUtils.isNotEmpty(def6) && def6.equals("1")) {
				CreditcontractDetailVO[] childrenVOs = (CreditcontractDetailVO[]) aggvo.getChildrenVO();
				if (CyCommonUtils.isNotEmpty(childrenVOs)) {
					for (CreditcontractDetailVO creditcontractDetailVO : childrenVOs) {
						String pk_detail2 = creditcontractDetailVO.getPk_detail();
						if (CyCommonUtils.isEmpty(pk_detail2)) {
							String pk_detail = this.getTablePK();
							creditcontractDetailVO.setPk_detail(pk_detail);
							creditcontractDetailVO.setDef10(pk_detail);
						} else {
							creditcontractDetailVO.setDef10(pk_detail2);
						}
					}
				}
			}
		}
	}

	/**
	 * 主键生成，获取主键pk
	 * 
	 * @param flevel 层级
	 * @return
	 */
	private String getTablePK() {// int flevel
		SequenceGenerator generator = new SequenceGenerator("cyline");
		return generator.generate();
	}

	private AggCtArVO setChangeVOWhenModify(AggCtArVO ctVo, AggCreditcontractHVO aggvo) {
		try {
			CtArVO ctHeadVo = ctVo.getParentVO();
			UFDouble newVersion = ctHeadVo.getVersion().add(UFDouble.ONE_DBL);
			ctHeadVo.setAttributeValue("version", newVersion);
			CtArChangeVO[] ctArChangeVOs = ctVo.getCtArChangeVO();
			List<CtArChangeVO> ctarclist = new ArrayList<CtArChangeVO>();
			if (CyCommonUtils.isNotEmpty(ctArChangeVOs)) {
				for (CtArChangeVO ctArChangeVO : ctArChangeVOs) {
					ctarclist.add(ctArChangeVO);
				}
			}
			/*
			 * 合同历史Start
			 */
			CtArChangeVO ctarcvp = new CtArChangeVO();
			ctarcvp.setAttributeValue("pk_group", ctHeadVo.getPk_group());
			ctarcvp.setAttributeValue("pk_org", ctHeadVo.getPk_org());
			ctarcvp.setAttributeValue("pk_org_v", ctHeadVo.getPk_org_v());
			ctarcvp.setAttributeValue("pk_fct_ar", ctHeadVo.getPk_fct_ar());
			ctarcvp.setAttributeValue("vchangecode", newVersion);
			ctarcvp.setAttributeValue("vchgdate", AppContext.getInstance().getBusiDate());
			String userId = InvocationInfoProxy.getInstance().getUserId();
			ctarcvp.setAttributeValue("vchgpsn", userId);
			/*
			 * End
			 */
			/*
			 * 收款计划金额修改Start
			 */
			CtArPlanVO[] ctArPlanVOs = ctVo.getCtArPlanVO();
			if (CyCommonUtils.isNotEmpty(ctArPlanVOs)) {
				for (CtArPlanVO ctArPlanVO : ctArPlanVOs) {
					String pk_detailxf = ctArPlanVO.getVdef1();// 学费收款合同主键
					CreditcontractDetailVO[] CreditcontractDetailVOs = (CreditcontractDetailVO[]) aggvo.getChildrenVO();
					for (CreditcontractDetailVO detailvo : CreditcontractDetailVOs) {
						String pk_detail = detailvo.getDef10();
						if (CyCommonUtils.isNotEmpty(pk_detail) && pk_detail.equals(pk_detailxf)) {
							ctArPlanVO.setPlanmoney(detailvo.getPlanamount().setScale(2, 5));// 计划金额
							ctArPlanVO.setOrgmoney(detailvo.getPlanamount().setScale(2, 5));
							ctArPlanVO.setGroupmoney(detailvo.getPlanamount().setScale(2, 5));
							ctArPlanVO.setGlomoney(detailvo.getPlanamount().setScale(2, 5));
							ctArPlanVO.setPlanrate(new UFDouble(detailvo.getProplan()));// 计划比例
						}
					}
				}
			}
			/*
			 * End
			 */
			/*
			 * 合同基本Start
			 */
			CtArBVO[] ctArBVOs = ctVo.getCtArBVO();
			String jmhje = aggvo.getParentVO().getDef9();// 减免后金额
			UFDouble ntaxrate = ctArBVOs[0].getNtaxrate();// 根据物料查询税率赋值
			UFDouble divTax = ntaxrate.div(100).add(new UFDouble(1.0));
			if (CyCommonUtils.isNotEmpty(jmhje)) {
				ctArBVOs[0].setNorigtaxmny(new UFDouble(jmhje));// 原币价税合计
				ctArBVOs[0].setNtaxmny(new UFDouble(jmhje));// 本币价税合计
				UFDouble bhsje = new UFDouble(jmhje).div(divTax).setScale(2, 5);
				ctArBVOs[0].setNorigmny(bhsje);// 无税金额
				ctArBVOs[0].setNmny(bhsje);// 本币无税金额
				ctArBVOs[0].setNtax(new UFDouble(jmhje).sub(bhsje));// 税额
			}

			String yhtje = ctHeadVo.getVdef7();// 原合同金额
			String ljjm = ctHeadVo.getVdef6();// 累计减免金额
			if (CyCommonUtils.isNotEmpty(ljjm)) {
				ctHeadVo.setVdef6(new UFDouble(ljjm).add(new UFDouble(yhtje).sub(new UFDouble(jmhje))).toString());// 累计减免金额
			} else {
				ctHeadVo.setVdef6(new UFDouble(yhtje).sub(new UFDouble(jmhje)).toString());
			}
			ctarclist.add(ctarcvp);
			ctVo.setChildrenVO(ctarclist.toArray(new CtArChangeVO[ctarclist.size()]));
			ctVo.setParentVO(ctHeadVo);
			return ctVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private void billCheck(T[] paramArrayOfE) throws BusinessException {
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggCreditcontractHVO) {
				AggCreditcontractHVO aggvo = (AggCreditcontractHVO) paramArrayOfE[i];

				CreditcontractHVO parentVO = aggvo.getParentVO();
				String pk_student = parentVO.getDef2();// 学生
				UFDouble amountBefore = parentVO.getAmount();// 合同金额
				UFDouble amount = new UFDouble(CyCommonUtils.isNotEmpty(parentVO.getDef9()) ? parentVO.getDef9() : "0");// 学费减免后的总金额

				if (CyCommonUtils.isEmpty(pk_student)) {
					throw new BusinessException("学费收款合同主表学生不能为空");
				}

				if (MMValueCheck.isEmpty(amount)) {
					throw new BusinessException("学费收款合同主表合同金额不能为空");
				}

				if (amountBefore.compareTo(amount) < 0) {
					throw new BusinessException(" 学费减免后的总金额必须小于原始合同总金额 ！ ");
				}

				UFDateTime hstartdate = parentVO.getStartdate();
				UFDateTime henddate = parentVO.getEnddate();
				if (MMValueCheck.isEmpty(hstartdate) || MMValueCheck.isEmpty(henddate)) {
					throw new BusinessException("学费收款合同主表合同生效日期或合同失效日期为空");
				}
				if (!hstartdate.before(henddate)) {
					throw new BusinessException("学费收款合同主表合同生效日期" + hstartdate + "大于等于合同失效日期" + henddate);
				}

				CreditcontractDetailVO[] childrenVO = (CreditcontractDetailVO[]) aggvo.getChildrenVO();
				if (MMValueCheck.isEmpty(childrenVO)) {
					throw new BusinessException("学费收款合同子表数据不能为空");
				}
				UFDouble bAmountAll = new UFDouble(0);
				int porplanAll = 0;
				for (int j = 0; j < childrenVO.length; j++) {
					CreditcontractDetailVO ccdvo = childrenVO[j];
					String rowno = ccdvo.getRowno();
					UFDateTime startdate = ccdvo.getStartdate();
					UFDateTime enddate = ccdvo.getEnddate();
					if (MMValueCheck.isEmpty(startdate) || MMValueCheck.isEmpty(enddate)) {
						throw new BusinessException("学费收款合同子表第" + rowno + "行起算日期或计划到期日为空");
					}
					if (!startdate.before(enddate)) {
						throw new BusinessException("学费收款合同子表第" + rowno + "行起算日期大于等于计划到期日");
					}
					UFDouble planamount = ccdvo.getPlanamount();
					Integer proplan = ccdvo.getProplan();
					if (MMValueCheck.isEmpty(proplan)
							|| (MMValueCheck.isNotEmpty(proplan) && proplan.intValue() <= 0)) {
						throw new BusinessException("学费收款合同子表第" + rowno + "行计划比例异常");
					}
					if (MMValueCheck.isEmpty(planamount) || MMValueCheck.isEmpty(planamount.doubleValue())
							|| planamount.doubleValue() <= 0) {
						throw new BusinessException("学费收款合同子表第" + rowno + "行计划金额异常");
					}

					if (VOStatus.DELETED != ccdvo.getStatus()) {
						bAmountAll = bAmountAll.add(planamount);
						porplanAll += proplan.intValue();
					}

				}
				if (amount.doubleValue() != bAmountAll.doubleValue()) {
					throw new BusinessException("学费收款合同子表计划金额之和不等于表头学费减免之后的总金额");
				}
				if (100 != porplanAll) {
					throw new BusinessException("学费收款合同子表计划比例之和不等于100");
				}
			}
		}
	}
}
