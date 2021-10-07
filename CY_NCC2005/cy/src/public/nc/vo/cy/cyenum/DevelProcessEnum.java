package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2021-7-28
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class DevelProcessEnum extends MDEnum{
	public DevelProcessEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final DevelProcessEnum N = MDEnum.valueOf(DevelProcessEnum.class, Integer.valueOf(0));
	
	
	public static final DevelProcessEnum Y = MDEnum.valueOf(DevelProcessEnum.class, Integer.valueOf(1));
	
	
	public static final DevelProcessEnum IN = MDEnum.valueOf(DevelProcessEnum.class, Integer.valueOf(2));
	

}