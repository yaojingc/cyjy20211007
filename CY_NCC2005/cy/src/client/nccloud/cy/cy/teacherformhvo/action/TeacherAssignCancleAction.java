
package nccloud.cy.cy.teacherformhvo.action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.pojo.GlbdefVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.cy.cy.studenthvo.cy.bean.BillOperatorParam;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.itf.cy.cy.ITeacherformhvoMaintain;

/**
 * 老师讲师申请单审批后取消分配
 * 
 * @author tanrg
 *
 */
public class TeacherAssignCancleAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		BillOperatorParam queryParam = this.getQueryParam(paramIRequest);
		try {
			if (MMValueCheck.isEmpty(queryParam.getPks())) {
				throw new BusinessException("未选中要分配的单据");
			}
			// 根据前端传递的pks查询数据,获取单据
			TeacherformHVO thvo = this.queryStuBills(queryParam);

			if (MMValueCheck.isEmpty(thvo)) {
				throw new BusinessException("未匹配到对应的单据");
			}

			String bill_no = thvo.getBill_no();
			String pk_psndoc = thvo.getTeacher();
			Integer approvestatus = thvo.getApprovestatus();
			String def6 = thvo.getDef6();// 是否分配 Y/N
			if (MMValueCheck.isEmpty(def6) || (MMValueCheck.isNotEmpty(def6) && !"Y".equals(def6))) {
				throw new BusinessException("单据：" + bill_no + " 未分配，无需取消分配");
			}
			if (MMValueCheck.isEmpty(approvestatus)
					|| (MMValueCheck.isNotEmpty(approvestatus) && approvestatus.intValue() != 1)) {
				throw new BusinessException("单据：" + bill_no + " 不为审批态，不能取消分配");
			}
			
//			String userId = InvocationInfoProxy.getInstance().getUserId();
			// 查询人员页签数据
			List<GlbdefVO> glbdefVOs = queryPsndocGlbdef(thvo.getPk_teacherform(),pk_psndoc);
			ITeacherformhvoMaintain itService = NCLocator.getInstance().lookup(ITeacherformhvoMaintain.class);
			if (MMValueCheck.isNotEmpty(glbdefVOs)) {
				Set<String> pkSet  = new HashSet<String>();
				for (GlbdefVO glbdefVO : glbdefVOs) {
					pkSet.add(glbdefVO.getPk_psndoc());
				}
				
				if(MMValueCheck.isNotEmpty(pkSet)) {
					SqlBuilder sql = new SqlBuilder();
					sql.append(" delete from hi_psndoc_glbdef1 where pk_psndoc ",pkSet.toArray(new String[0]));
					sql.append(" and glbdef8",thvo.getPk_teacherform());
					itService.glbdefDelete(sql.toString());
				}
			}
			
			thvo.setDef6("N");// 已分配
			thvo.setStatus(VOStatus.UPDATED);
			VOUpdate<TeacherformHVO> teacherUpdate = new VOUpdate<TeacherformHVO>();
			teacherUpdate.update(new TeacherformHVO[] { thvo }, new String[] { "def6" });
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	private List<GlbdefVO> queryPsndocGlbdef(String pk_teacherfrom,String pk_psndoc) throws BusinessException {
		// pk+人员主键+学校+班级+开始时间
		IUAPQueryBS iuapSevice = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		SqlBuilder sql = new SqlBuilder();
		sql.append(" SELECT * FROM hi_psndoc_glbdef1 WHERE nvl(dr,0)=0 ");
		sql.append(" and glbdef8 is not null and glbdef8 ", pk_teacherfrom);
		if(MMValueCheck.isNotEmpty(pk_psndoc)) {
			sql.append(" and pk_psndoc ", pk_psndoc);
		}

		List<GlbdefVO> listVo = (List<GlbdefVO>) iuapSevice.executeQuery(sql.toString(), new BeanListProcessor(GlbdefVO.class));

		return listVo;
	}

	/**
	 * 查询业务数据
	 *
	 * @param queryParam
	 * @return
	 * @throws MetaDataException
	 */
	private TeacherformHVO queryStuBills(BillOperatorParam queryParam) throws MetaDataException {
		// 1、根据参数查询结果
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		List<TeacherformHVO> billvo = (List<TeacherformHVO>) service.queryBillOfVOByPKs(TeacherformHVO.class,queryParam.getPks(), false);
		if ((billvo == null) || (billvo.size() == 0)) {
			return null;
		}
		return billvo.get(0);
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
}
