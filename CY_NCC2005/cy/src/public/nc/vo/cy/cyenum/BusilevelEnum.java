package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2021-8-6
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