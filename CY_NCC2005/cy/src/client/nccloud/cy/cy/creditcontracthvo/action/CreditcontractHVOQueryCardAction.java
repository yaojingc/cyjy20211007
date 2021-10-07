
package nccloud.cy.cy.creditcontracthvo.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.creditcontract.AggCreditcontractHVO;
import nc.vo.cy.creditcontract.CreditcontractDetailVO;
import nc.vo.cy.creditcontract.CreditcontractHVO;
import nc.vo.pub.BusinessException;
import nccloud.cy.cy.creditcontracthvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.creditcontracthvo.cy.util.CommonUtil;
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
import nccloud.itf.cy.cy.IStudenthvoMaintain;

/**
 * 卡片查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class CreditcontractHVOQueryCardAction implements ICommonAction {
	
	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
		try {
			// 查询数据
			Collection<AggCreditcontractHVO> bills = this.queryBills(queryParam);

			List<AggCreditcontractHVO> billsList = new ArrayList<>(bills);
			if (!billsList.isEmpty()) {
				for (AggCreditcontractHVO vo : billsList) {
					if (vo.getChildren(CreditcontractDetailVO.class) == null) {
						String con = "pk_contract='" + vo.getParentVO().getPk_contract() + "'";
						Collection<CreditcontractDetailVO> result = new BaseDAO()
								.retrieveByClause(CreditcontractDetailVO.class, con);
						List<CreditcontractDetailVO> childs = new ArrayList<>(result);
						if (!childs.isEmpty()) {
							CreditcontractDetailVO[] childArray = new CreditcontractDetailVO[childs.size()];
							childs.toArray(childArray);
							vo.setChildren(CreditcontractDetailVO.class, childArray);
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
	private Collection<AggCreditcontractHVO> queryBills(PageQueryInfo queryParam) throws MetaDataException {
		IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
		// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
		String wheresql = "pk_contract" + "='" + queryParam.getPk() + "'";
		Collection<AggCreditcontractHVO> bills = service.queryBillOfVOByCond(AggCreditcontractHVO.class, wheresql, true,
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
	private BillCard transBillCard(PageQueryInfo queryParam, Collection<AggCreditcontractHVO> bills) {
		if (bills == null) {
			return null;
		}
		AggCreditcontractHVO bill = bills.toArray(new AggCreditcontractHVO[0])[0];
		BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
		BillCard billCard = operator.toCard(bill);
		// 自定义参照翻译
		return CommonUtil.displayCardManager(billCard, bill);
	}

	// /**
	// * 自定义参照翻译
	// * @param queryParam
	// * @param bill
	// * @param hvo
	// * @return
	// */
	// private BillCard displayManager(PageQueryInfo queryParam,
	// AggCreditcontractHVO bill) {
	// CreditcontractHVO chvo = bill.getParentVO();
	// // def1：班级，def2：学生，def5：学校
	// String def1 = chvo.getDef1();
	// String def2 = chvo.getDef2();
	// String def5 = chvo.getDef5();
	// ISchoolbasicshvoMaintain schoolMaintain =
	// NCLocator.getInstance().lookup(ISchoolbasicshvoMaintain.class);
	// IClasscyhvoMaintain classcyMaintain =
	// NCLocator.getInstance().lookup(IClasscyhvoMaintain.class);
	// IStudenthvoMaintain studentMaintain =
	// NCLocator.getInstance().lookup(IStudenthvoMaintain.class);
	// Map<String, String> classcyMap = classcyMaintain.classcyDataQuery(def1);
	// Map<String, String> student = studentMaintain.studentDataQuery(def2);
	// Map<String, String> schoolMap = schoolMaintain.schoolDataQuery(def5);
	//
	// BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
	// BillCard billCard = operator.toCard(bill);
	// Row[] rows = billCard.getHead().getModel().getRows();
	// if (rows != null && rows.length > 0) {
	// for (int i = 0; i < rows.length; i++) {
	// Cell cellDef1 = rows[i].getCell("def1");
	// cellDef1.setDisplay(classcyMap.get(def1));
	//
	// Cell cellDef2 = rows[i].getCell("def2");
	// cellDef2.setDisplay(student.get(def2));
	//
	// Cell cellDef5 = rows[i].getCell("def5");
	// cellDef5.setDisplay(schoolMap.get(def5));
	//
	// }
	// }
	// return billCard;
	// }

	
}
