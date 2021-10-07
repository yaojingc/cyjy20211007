
package nccloud.cy.cy.classcyhvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.classcyhvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.classcyhvo.cy.util.CommonUtil;
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
public class ClasscyHVOQueryCardAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggClasscyHVO> bills = this.queryBills(queryParam);

			List<AggClasscyHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggClasscyHVO vo : billsList) {
					if (vo.getChildren(ClasscyDetailVO.class) == null) {
						String con = "pk_classcy='" + vo.getParentVO().getPk_classcy() + "'";
						Collection<ClasscyDetailVO> result = new BaseDAO().retrieveByClause(ClasscyDetailVO.class, con);
						List<ClasscyDetailVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							ClasscyDetailVO[] childArray = new ClasscyDetailVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(ClasscyDetailVO.class, childArray);
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
	private Collection<AggClasscyHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_classcy" + "='" + queryParam.getPk() + "'";
		Collection<AggClasscyHVO> bills = service.queryBillOfVOByCond(AggClasscyHVO.class, wheresql, true, false);
		return bills;
	}

	/**
	 * VO 转换
	 *
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	private BillCard transBillCard(PageQueryInfo queryParam, Collection<AggClasscyHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggClasscyHVO bill = bills.toArray(new AggClasscyHVO[0])[0];
		BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
		BillCard billCard = operator.toCard(bill);
		// 自定义参照翻译
		return CommonUtil.displayCardManager(billCard, bill);
	}

}
