
package nccloud.cy.cy.plantravelhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.cy.plantravel.PlantravelHVO;
import nc.vo.cy.plantravel.PlantravelBVO;

import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;
import nccloud.cy.cy.plantravelhvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.plantravelhvo.cy.util.CommonUtil;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class PlantravelHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
// 查询数据
			Collection<AggPlantravelHVO> bills = this.queryBills(queryParam);

			List<AggPlantravelHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggPlantravelHVO vo : billsList) {
					if (vo.getChildren(PlantravelBVO.class) == null) {
						String con = "pk_plantravel='" + vo.getParentVO().getPk_plantravel() + "'";
						Collection<PlantravelBVO> result = new BaseDAO().retrieveByClause(PlantravelBVO.class, con);
						List<PlantravelBVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							PlantravelBVO[] childArray = new PlantravelBVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(PlantravelBVO.class, childArray);
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
	private Collection<AggPlantravelHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_plantravel" + "='" + queryParam.getPk() + "'";
		Collection<AggPlantravelHVO> bills = service.queryBillOfVOByCond(AggPlantravelHVO.class, wheresql, true, false);
		return bills;
	}

	/**
	 * VO 转换
	 *
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	private ExtBillCard transBillCard(PageQueryInfo queryParam, Collection<AggPlantravelHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggPlantravelHVO bill = bills.toArray(new AggPlantravelHVO[0])[0];
		ExtBillCardOperator operator = new ExtBillCardOperator(queryParam.getPagecode());
		ExtBillCard card = operator.toCard(bill);
		return CommonUtil.displayCardManager(card, bill);
	}
}
