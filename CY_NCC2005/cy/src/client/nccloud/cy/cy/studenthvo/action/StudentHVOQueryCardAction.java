
package nccloud.cy.cy.studenthvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.cy.studentfile.StudentParentVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.studenthvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.model.row.Cell;
import nccloud.framework.web.ui.model.row.Row;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class StudentHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggStudentHVO> bills = this.queryBills(queryParam);

			List<AggStudentHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggStudentHVO vo : billsList) {
					if (vo.getChildren(StudentParentVO.class) == null) {
						String con = "pk_student='" + vo.getParentVO().getPk_student() + "'";
						Collection<StudentParentVO> result = new BaseDAO().retrieveByClause(StudentParentVO.class, con);
						List<StudentParentVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							StudentParentVO[] childArray = new StudentParentVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(StudentParentVO.class, childArray);
						}
					}
				}
			}
			return this.transBillCard(queryParam, bills);
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}

	/**
	 * 获取查询参数
	 *
	 * @param paramIRequest
	 * @return
	 */
	private PageQueryInfo getQueryParam(IRequest paramIRequest) {
		String strRead = paramIRequest.read();
		PageQueryInfo queryParam = JsonFactory.create().fromJson(strRead, PageQueryInfo.class);
		return queryParam;
	}

	/**
	 * 查询业务数据
	 *
	 * @param queryParam
	 * @return
	 * @throws MetaDataException
	 */
	private Collection<AggStudentHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_student" + "='" + queryParam.getPk() + "'";
		Collection<AggStudentHVO> bills = service.queryBillOfVOByCond(AggStudentHVO.class, wheresql, true, false);
		return bills;
	}

	/**
	 * VO 转换
	 *
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	private ExtBillCard transBillCard(PageQueryInfo queryParam, Collection<AggStudentHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggStudentHVO bill = bills.toArray(new AggStudentHVO[0])[0];
		ExtBillCardOperator operator = new ExtBillCardOperator(queryParam.getPagecode());
		ExtBillCard billCard = operator.toCard(bill);
		return CommonUtil.displayCardManager(billCard, bill);
	}

	
}
