
package nccloud.cy.cy.teacherfilehvo.action;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.md.model.MetaDataException;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.vo.cy.teacherfile.AggTeacherfileHVO;
import nc.vo.cy.teacherfile.TeacherfileHVO;
import nc.vo.cy.teacherfile.TeacherfileDetailVO;

import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.billcard.BillCardOperator;
import nccloud.cy.cy.teacherfilehvo.cy.bean.PageQueryInfo;
import nccloud.cy.cy.teacherfilehvo.cy.util.CommonUtil;

/**
* 卡片查询（查询方案）操作
* @version  @since v3.5.6-1903
*/
public class TeacherfileHVOQueryCardAction implements ICommonAction {

@Override
public Object doAction(IRequest paramIRequest) {
PageQueryInfo queryParam = this.getQueryParam(paramIRequest);
try {
// 查询数据
Collection<AggTeacherfileHVO> bills = this.queryBills(queryParam);


List<AggTeacherfileHVO> billsList = new ArrayList<>(bills);
if (!billsList.isEmpty()) {
for (AggTeacherfileHVO vo : billsList) {
if (vo.getChildren(TeacherfileDetailVO.class) == null) {
String con = "pk_teachfile='" + vo.getParentVO().getPk_teachfile() + "'";
Collection<TeacherfileDetailVO> result = new BaseDAO().retrieveByClause(TeacherfileDetailVO.class, con);
List<TeacherfileDetailVO> childs = new ArrayList<>(result);
if (!childs.isEmpty()) {
TeacherfileDetailVO[] childArray = new TeacherfileDetailVO[childs.size()];
childs.toArray(childArray);
vo.setChildren(TeacherfileDetailVO.class, childArray);
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
private Collection<AggTeacherfileHVO> queryBills(PageQueryInfo queryParam)
throws MetaDataException {
IMDPersistenceQueryService service = CommonUtil.getMDPersistenceQueryService();
// 注意：业务提供查询方法（元数据查询默认查询到dr=1的数据了）
String wheresql =
"pk_teachfile" + "='" + queryParam.getPk() + "'";
Collection<AggTeacherfileHVO> bills =
service.queryBillOfVOByCond(AggTeacherfileHVO.class, wheresql, true,
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
Collection<AggTeacherfileHVO> bills) {
if (bills == null) {
return null;
}
AggTeacherfileHVO bill = bills.toArray(new AggTeacherfileHVO[0])[0];
BillCardOperator operator = new BillCardOperator(queryParam.getPagecode());
return operator.toCard(bill);
}
}
