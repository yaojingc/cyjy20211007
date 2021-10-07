
package nccloud.cy.cy.testpaperfilehvo.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.testpaperfile.AggTestpaperfileHVO;
import nc.vo.cy.testpaperfile.TestpaperfileHVO;
import nc.vo.cy.testpaperfile.TestpaperfileBVO;

import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.cy.cy.testpaperfilehvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.testpaperfilehvo.cy.util.CommonUtil;

/**
* 卡片查询（查询方案）操作
* @version  @since v3.5.6-1903
*/
public class TestpaperfileHVOQueryCardAction implements ICommonAction {

@Override
public Object doAction(IRequest paramIRequest) {
PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
try {
// 查询数据
Collection<AggTestpaperfileHVO> bills = this.queryBills(queryParam);


List<AggTestpaperfileHVO> billsList = new ArrayList<>(bills);
if (!billsList.isEmpty()) {
for (AggTestpaperfileHVO vo : billsList) {
if (vo.getChildren(TestpaperfileBVO.class) == null) {
String con = "pk_testpaperfile='" + vo.getParentVO().getPk_testpaperfile() + "'";
Collection<TestpaperfileBVO> result = new BaseDAO().retrieveByClause(TestpaperfileBVO.class, con);
List<TestpaperfileBVO> childs = new ArrayList<>(result);
if (!childs.isEmpty()) {
TestpaperfileBVO[] childArray = new TestpaperfileBVO[childs.size()];
childs.toArray(childArray);
vo.setChildren(TestpaperfileBVO.class, childArray);
}
}
}
}
return this.transBillCard(queryParam, bills);
}
catch (BusinessException ex) {
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
PageQueryInfo queryParam =
JsonFactory.create().fromJson(strRead, PageQueryInfo.class);
return queryParam;
}

/**
* 查询业务数据
*
* @param queryParam
* @return
* @throws MetaDataException
*/
private Collection<AggTestpaperfileHVO> queryBills(PageQueryInfo queryParam)
throws MetaDataException {
IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
String wheresql =
"pk_testpaperfile" + "='" + queryParam.getPk() + "'";
Collection<AggTestpaperfileHVO> bills =
service.queryBillOfVOByCond(AggTestpaperfileHVO.class, wheresql, true,
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
private BillCard transBillCard(PageQueryInfo queryParam,
Collection<AggTestpaperfileHVO> bills) {
if (bills == null) {
return null;
}
AggTestpaperfileHVO bill = bills.toArray(new AggTestpaperfileHVO[0])[0];
BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
return operator.toCard(bill);
}
}
