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
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nc.vo.cy.pojo.PagingPOJO;

/**
 * 
 * 日报 新增修改单据校验
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class DailyRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] arg0) {
		for (T aggvo : arg0) {
			AggDailyHVO tvo = (AggDailyHVO) aggvo;
			try {
				billCheck(tvo);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}
	}

	//校验当天日报是否已创建
	private void billCheck(AggDailyHVO tvo) throws BusinessException {
		SqlBuilder sqlwhere = new SqlBuilder();
		sqlwhere.append("and creator", tvo.getParentVO().getCreator());
		sqlwhere.append("and reportdate", tvo.getParentVO().getReportdate().toString());
		TableCountQueryVO vo = new TableCountQueryVO("cy_daily", sqlwhere.toString());
		PagingPOJO r = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo);
		if (r.getTotal() != 0) {
			throw new BusinessException("当天日报已存在，请另选日期");
		}
	}
}
