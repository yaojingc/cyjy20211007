package nccloud.impl.rule;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyDetailVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.daily.DailyHVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.workplan.WorkplanHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 
 * 工作计划 新增修改单据校验
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class WorkplanRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] arg0) {

		for (T aggvo : arg0) {
			AggWorkplanHVO tvo = (AggWorkplanHVO) aggvo;
			try {
				billCheck(tvo);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

	// 校验日期是否冲突
	private void billCheck(AggWorkplanHVO tvo) throws BusinessException {
		SqlBuilder sqlwhere1 = new SqlBuilder();// 日期条件1 开始时间已存在
		SqlBuilder sqlwhere2 = new SqlBuilder();// 日期条件2 结束时间已存在
		WorkplanHVO vo = tvo.getParentVO();
		sqlwhere1.append("and salesman", vo.getSalesman());
		sqlwhere1.append("and salesman", vo.getSalesman());
		if (CyCommonUtils.isNotEmpty(vo.getStartdate()) && CyCommonUtils.isNotEmpty(vo.getStartdate())) {
			sqlwhere1.append(" and startdate >= '" + vo.getStartdate() + "' and startdate <= '" + vo.getEnddate() + "'");
			sqlwhere2.append(" and enddate >= '" + vo.getStartdate() + "' and enddate <= '" + vo.getEnddate() + "'");
		}
		TableCountQueryVO vo1=new TableCountQueryVO("cy_workplan",sqlwhere1.toString());
		TableCountQueryVO vo2=new TableCountQueryVO("cy_workplan",sqlwhere2.toString());
		PagingPOJO r1=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo1);
		PagingPOJO r2=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo2);
//		String r1 = CyCommonUtils.queryTotal("cy_workplan", sqlwhere1.toString());
//		String r2 = CyCommonUtils.queryTotal("cy_workplan", sqlwhere2.toString());
		if (CyCommonUtils.isNotEmpty(r1)&&CyCommonUtils.isNotEmpty(r2)&&r1.getTotal()!=0&&r2.getTotal()!=0) {
			throw new BusinessException("工作计划日期冲突，请另选日期");
		}
	}
}
