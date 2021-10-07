
package nccloud.cy.cy.classxzhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.classfilexz.AggClassxzHVO;
import nc.vo.cy.classfilexz.ClassxzDetailVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.classxzhvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.classxzhvo.cy.util.CommonUtil;
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
public class ClassxzHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggClassxzHVO> bills = this.queryBills(queryParam);

			List<AggClassxzHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggClassxzHVO vo : billsList) {
					if (vo.getChildren(ClassxzDetailVO.class) == null) {
						String con = "pk_classxz='" + vo.getParentVO().getPk_classxz() + "'";
						Collection<ClassxzDetailVO> result = new BaseDAO().retrieveByClause(ClassxzDetailVO.class, con);
						List<ClassxzDetailVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							ClassxzDetailVO[] childArray = new ClassxzDetailVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(ClassxzDetailVO.class, childArray);
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
	private Collection<AggClassxzHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_classxz" + "='" + queryParam.getPk() + "'";
		Collection<AggClassxzHVO> bills = service.queryBillOfVOByCond(AggClassxzHVO.class, wheresql, true, false);
		return bills;
	}

	/**
	 * VO 转换
	 *
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	private BillCard transBillCard(PageQueryInfo queryParam, Collection<AggClassxzHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggClassxzHVO bill = bills.toArray(new AggClassxzHVO[0])[0];
		BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
		BillCard card = operator.toCard(bill);
		return CommonUtil.displayCardManager(card, bill);
	}
}
