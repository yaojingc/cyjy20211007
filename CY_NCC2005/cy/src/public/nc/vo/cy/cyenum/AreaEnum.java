package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 *   �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 *  ��������:2021-7-22
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class AreaEnum extends MDEnum{
	public AreaEnum(IEnumValue enumvalue){
		super(enumvalue);
	}

	
	
	public static final AreaEnum EAST = MDEnum.valueOf(AreaEnum.class, Integer.valueOf(0));
	
	
	public static final AreaEnum SOUTH = MDEnum.valueOf(AreaEnum.class, Integer.valueOf(1));
	
	
	public static final AreaEnum WEST = MDEnum.valueOf(AreaEnum.class, Integer.valueOf(2));
	
	
	public static final AreaEnum NORTH = MDEnum.valueOf(AreaEnum.class, Integer.valueOf(3));
	
	
	public static final AreaEnum HUBEI = MDEnum.valueOf(AreaEnum.class, Integer.valueOf(4));
	

}