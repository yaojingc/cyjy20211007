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
import nc.vo.cy.schoolform.AggSchoolformHVO;
/**
 * 
  * 新发现学校申请单 电话号码和学校名称是否存在校验
 * 
 * @author huangcong
 *
 * @param <T>
 */
public class SchoolformRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] arg0) {			
		for(T aggvo : arg0){
			AggSchoolformHVO tvo=(AggSchoolformHVO) aggvo;
				try {	
					billCheck(tvo);				
				} catch (Exception e) {
					ExceptionUtils.wrappException(e);
				}
		}
	}
	
	//校验学校名称是否已存在
	private void billCheck(AggSchoolformHVO tvo) throws BusinessException {
			SqlBuilder formwhere=new SqlBuilder();
			formwhere.append("(school",tvo.getParentVO().getSchool());
			formwhere.append("or sphone",tvo.getParentVO().getSphone());
			formwhere.append(")");
			TableCountQueryVO formvo=new TableCountQueryVO("cy_schoolform",formwhere.toString());
			PagingPOJO formResult=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(formvo);
			
			SqlBuilder swhere=new SqlBuilder();
			swhere.append(" (sname",tvo.getParentVO().getSchool());
			swhere.append("or sphone",tvo.getParentVO().getSphone());
			swhere.append(")");
			TableCountQueryVO svo=new TableCountQueryVO("cy_schoolbasics",swhere.toString());
			PagingPOJO sResult=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(svo);
			
			if(formResult.getTotal()!=0||sResult.getTotal()!=0) {
				throw new BusinessException("学校名称或电话号码已存在,请检查");
			}		
	}
}
