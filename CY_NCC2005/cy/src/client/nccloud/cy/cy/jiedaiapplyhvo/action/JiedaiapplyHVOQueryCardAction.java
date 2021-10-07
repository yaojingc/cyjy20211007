
package nccloud.cy.cy.jiedaiapplyhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.cy.jiedaiapply.JiedaiapplykcVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.jiedaiapplyhvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.jiedaiapplyhvo.cy.util.CommonUtil;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCard;
import nccloud.framework.web.ui.pattern.extbillcard.ExtBillCardOperator;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class JiedaiapplyHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
// 查询数据
			Collection<AggJiedaiapplyHVO> bills = this.queryBills(queryParam);

			List<AggJiedaiapplyHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggJiedaiapplyHVO vo : billsList) {
					if (vo.getChildren(JiedaiapplykcVO.class) == null) {
						String con = "pk_jiedaiapply='" + vo.getParentVO().getPk_jiedaiapply() + "'";
						Collection<JiedaiapplykcVO> result = new BaseDAO().retrieveByClause(JiedaiapplykcVO.class, con);
						List<JiedaiapplykcVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							JiedaiapplykcVO[] childArray = new JiedaiapplykcVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(JiedaiapplykcVO.class, childArray);
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
	private Collection<AggJiedaiapplyHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_jiedaiapply" + "='" + queryParam.getPk() + "'";
		Collection<AggJiedaiapplyHVO> bills = service.queryBillOfVOByCond(AggJiedaiapplyHVO.class, wheresql, true,
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
	private ExtBillCard transBillCard(PageQueryInfo queryParam, Collection<AggJiedaiapplyHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggJiedaiapplyHVO bill = bills.toArray(new AggJiedaiapplyHVO[0])[0];
		ExtBillCardOperator operator = new ExtBillCardOperator(queryParam.getPagecode());
		ExtBillCard card = operator.toCard(bill);
		return CommonUtil.displayCardManager(card, bill);
	}
}
