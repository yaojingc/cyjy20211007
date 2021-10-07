package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>此处简要描述此枚举的功能 </b>
 * <p>
 *   此处添加该枚举的描述信息
 * </p>
 *  创建日期:2021-8-6
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class BusilevelEnum extends MDEnum{
	public BusilevelEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final BusilevelEnum A = MDEnum.valueOf(BusilevelEnum.class, Integer.valueOf(0));
	
	
	public static final BusilevelEnum B = MDEnum.valueOf(BusilevelEnum.class, Integer.valueOf(1));
	
	
	public static final BusilevelEnum C = MDEnum.valueOf(BusilevelEnum.class, Integer.valueOf(2));
	

}