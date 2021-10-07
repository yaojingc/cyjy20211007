
package nccloud.cy.cy.teacherformhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.TeacherformDetailVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.teacherformhvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.teacherformhvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.model.row.Cell;
import nccloud.framework.web.ui.model.row.Row;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.itf.cy.cy.IClasscyhvoMaintain;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class TeacherformHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggTeacherformHVO> bills = this.queryBills(queryParam);

			List<AggTeacherformHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggTeacherformHVO vo : billsList) {
					if (vo.getChildren(TeacherformDetailVO.class) == null) {
						String con = "pk_teacherform='" + vo.getParentVO().getPk_teacherform() + "'";
						Collection<TeacherformDetailVO> result = new BaseDAO()
								.retrieveByClause(TeacherformDetailVO.class, con);
						List<TeacherformDetailVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							TeacherformDetailVO[] childArray = new TeacherformDetailVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(TeacherformDetailVO.class, childArray);
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
	private Collection<AggTeacherformHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_teacherform" + "='" + queryParam.getPk() + "'";
		Collection<AggTeacherformHVO> bills = service.queryBillOfVOByCond(AggTeacherformHVO.class, wheresql, true,
				false);
		return bills;
	}

	/**
	 * VO 转换
	 *
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	private BillCard transBillCard(PageQueryInfo queryParam, Collection<AggTeacherformHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggTeacherformHVO bill = bills.toArray(new AggTeacherformHVO[0])[0];
		BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
		BillCard billCard = operator.toCard(bill);
		return CommonUtil.displayCardManager(billCard, bill);
	}

	
}
