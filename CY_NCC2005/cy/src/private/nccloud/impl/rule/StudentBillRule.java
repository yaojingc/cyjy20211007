package nccloud.impl.rule;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.IdCardUtil;
import nc.utils.commonplugin.MailUtil;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

/**
 * 
 * 学生档案 新增修改单据校验
 * 
 * @author tanrg
 *
 * @param <T>
 */
public class StudentBillRule<T extends AbstractBill> implements IRule<T> {

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

	/**
	 * 身份证唯一校验，邮箱校验
	 * @param paramArrayOfE
	 * @throws BusinessException
	 */
	private void billCheck(T[] paramArrayOfE) throws BusinessException {
		for (int i = 0; i < paramArrayOfE.length; i++) {
			if (paramArrayOfE[i] instanceof AggStudentHVO) {
				AggStudentHVO agg = (AggStudentHVO) paramArrayOfE[i];
				StudentHVO stuHVO = agg.getParentVO();
				String email = stuHVO.getEmail();
				String idcard = stuHVO.getIdcard();
				String sname = stuHVO.getSname();
				if (MMValueCheck.isEmpty(idcard)) {
					throw new BusinessException("学生"+sname+"身份证号为空");
				}else {
					if(!IdCardUtil.isValidatedAllIdcard(idcard)) {
						throw new BusinessException("学生"+sname+"身份证号不合法");
					}
				}
				if (MMValueCheck.isNotEmpty(email)) {
					if(!MailUtil.isEmail(email)) {
						throw new BusinessException("学生"+sname+"邮箱不合法");
					}
				}
			}
		}
	}

}
