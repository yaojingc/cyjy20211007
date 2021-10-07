
package nccloud.cy.cy.clsschedulehvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.clsschedule.AggClsscheduleHVO;
import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.clsschedulehvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.clsschedulehvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class ClsscheduleHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggClsscheduleHVO> bills = this.queryBills(queryParam);

			List<AggClsscheduleHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggClsscheduleHVO vo : billsList) {
					if (vo.getChildren(ClsscheduleBVO.class) == null) {
						String con = "pk_clsschedule='" + vo.getParentVO().getPk_clsschedule() + "'";
						Collection<ClsscheduleBVO> result = new BaseDAO().retrieveByClause(ClsscheduleBVO.class, con);
						List<ClsscheduleBVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							ClsscheduleBVO[] childArray = new ClsscheduleBVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(ClsscheduleBVO.class, childArray);
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
	private Collection<AggClsscheduleHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_clsschedule" + "='" + queryParam.getPk() + "'";
		Collection<AggClsscheduleHVO> bills = service.queryBillOfVOByCond(AggClsscheduleHVO.class, wheresql, true,
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
	private BillCard transBillCard(PageQueryInfo queryParam, Collection<AggClsscheduleHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggClsscheduleHVO bill = bills.toArray(new AggClsscheduleHVO[0])[0];
		BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
		BillCard card = operator.toCard(bill);
		return CommonUtil.displayCardManager(card, bill);
	}
}
