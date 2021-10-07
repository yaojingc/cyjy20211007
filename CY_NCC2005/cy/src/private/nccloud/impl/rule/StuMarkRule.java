package nccloud.impl.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.cy.stumarkdata.AggStumarkdataHVO;
import nc.vo.cy.stumarkdata.StumarkdataBVO;
import nc.vo.cy.stumarkdata.StumarkdataHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 学生成绩库单据保存校验及分数计算规则
 * 
 * @author tanrg
 *
 * @param <T>
 */
public class StuMarkRule<T extends AbstractBill> implements IRule<T> {

	@Override
	public void process(T[] paramArrayOfE) {
		if (MMValueCheck.isNotEmpty(paramArrayOfE)) {
			try {
				billCheck(paramArrayOfE);
			} catch (Exception e) {
				ExceptionUtils.wrappException(e);
			}
		}

	}

	private void billCheck(T[] paramArrayOfE) throws BusinessException {
		// 计算子表学生总分，根据表体数据行计算表头平均分项，学生总分不能大于试卷总分
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggStumarkdataHVO) {
				int bodyCount = 0;
				AggStumarkdataHVO agg = (AggStumarkdataHVO) paramArrayOfE[i];
				StumarkdataHVO markhvo = agg.getParentVO();
				StumarkdataBVO[] childrenVO = (StumarkdataBVO[]) agg.getChildrenVO();
				if (MMValueCheck.isEmpty(childrenVO)) {
					throw new BusinessException("子表数据不能为空");
				}
				UFDouble averagesub1 = new UFDouble(0);
				UFDouble averagesub2 = new UFDouble(0);
				UFDouble averagesub3 = new UFDouble(0);
				UFDouble averagesub4 = new UFDouble(0);
				UFDouble averagesub5 = new UFDouble(0);
				UFDouble averagesub6 = new UFDouble(0);
				UFDouble averagesub7 = new UFDouble(0);
				UFDouble averagesub8 = new UFDouble(0);
				UFDouble averagesub9 = new UFDouble(0);
				UFDouble averagesub10 = new UFDouble(0);
				for (int j = 0; j < childrenVO.length; j++) {
					StumarkdataBVO markbvo = childrenVO[j];
					if (VOStatus.DELETED != markbvo.getStatus()) {
						bodyCount++;
						UFDouble subscore1 = markbvo.getSubscore1() == null ? new UFDouble(0) : markbvo.getSubscore1();
						averagesub1 = averagesub1.add(subscore1);
						UFDouble subscore2 = markbvo.getSubscore2() == null ? new UFDouble(0) : markbvo.getSubscore2();
						averagesub2 = averagesub2.add(subscore2);
						UFDouble subscore3 = markbvo.getSubscore3() == null ? new UFDouble(0) : markbvo.getSubscore3();
						averagesub3 = averagesub3.add(subscore3);
						UFDouble subscore4 = markbvo.getSubscore4() == null ? new UFDouble(0) : markbvo.getSubscore4();
						averagesub4 = averagesub4.add(subscore4);
						UFDouble subscore5 = markbvo.getSubscore5() == null ? new UFDouble(0) : markbvo.getSubscore5();
						averagesub5 = averagesub5.add(subscore5);
						UFDouble subscore6 = markbvo.getSubscore6() == null ? new UFDouble(0) : markbvo.getSubscore6();
						averagesub6 = averagesub6.add(subscore6);
						UFDouble subscore7 = markbvo.getSubscore7() == null ? new UFDouble(0) : markbvo.getSubscore7();
						averagesub7 = averagesub7.add(subscore7);
						UFDouble subscore8 = markbvo.getSubscore8() == null ? new UFDouble(0) : markbvo.getSubscore8();
						averagesub8 = averagesub8.add(subscore8);
						UFDouble subscore9 = markbvo.getSubscore9() == null ? new UFDouble(0) : markbvo.getSubscore9();
						averagesub9 = averagesub9.add(subscore9);
						UFDouble subscore10 = markbvo.getSubscore10() == null ? new UFDouble(0) : markbvo.getSubscore10();
						averagesub10 = averagesub10.add(subscore10);

						UFDouble totalscore = subscore1.add(subscore2).add(subscore3).add(subscore4).add(subscore5)
								.add(subscore6).add(subscore7).add(subscore8).add(subscore9).add(subscore10);
						childrenVO[j].setTotalscore(totalscore.setScale(2, 5));// 学生总分
					}
				}
				
				if (bodyCount == 0) {
					throw new BusinessException("子表数据不能为空");
				}
				// 表头均分计算
				markhvo.setAveragesub1(averagesub1.doubleValue()==0?null:averagesub1.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub2(averagesub2.doubleValue()==0?null:averagesub2.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub3(averagesub3.doubleValue()==0?null:averagesub3.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub4(averagesub4.doubleValue()==0?null:averagesub4.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub5(averagesub5.doubleValue()==0?null:averagesub5.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub6(averagesub6.doubleValue()==0?null:averagesub6.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub7(averagesub7.doubleValue()==0?null:averagesub7.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub8(averagesub8.doubleValue()==0?null:averagesub8.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub9(averagesub9.doubleValue()==0?null:averagesub9.div(bodyCount).setScale(2, 5));
				markhvo.setAveragesub10(averagesub10.doubleValue()==0?null:averagesub10.div(bodyCount).setScale(2, 5));
			}
		}
	}

}