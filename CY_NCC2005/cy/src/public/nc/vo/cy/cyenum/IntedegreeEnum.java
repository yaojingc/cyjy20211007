package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 * �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 * ��������:2021-7-21
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class IntedegreeEnum extends MDEnum {
	public IntedegreeEnum(IEnumValue enumvalue) {
		super(enumvalue);
	}

	public static final IntedegreeEnum HIGH = MDEnum.valueOf(IntedegreeEnum.class, Integer.valueOf(0));

	public static final IntedegreeEnum HIGHER = MDEnum.valueOf(IntedegreeEnum.class, Integer.valueOf(1));

	public static final IntedegreeEnum GENERAL = MDEnum.valueOf(IntedegreeEnum.class, Integer.valueOf(2));

	public static final IntedegreeEnum LOW = MDEnum.valueOf(IntedegreeEnum.class, Integer.valueOf(3));

}