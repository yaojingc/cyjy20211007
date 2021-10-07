package nc.vo.cy.cyenum;

import nc.md.model.IEnumValue;
import nc.md.model.impl.MDEnum;

/**
 * <b>�˴���Ҫ������ö�ٵĹ��� </b>
 * <p>
 * �˴���Ӹ�ö�ٵ�������Ϣ
 * </p>
 * ��������:2021-8-6
 * 
 * @author yonyouBQ
 * @version NCPrj ??
 */
public class FacultyEnum extends MDEnum {
	public FacultyEnum(IEnumValue enumvalue) {
		super(enumvalue);
	}

	public static final FacultyEnum a = MDEnum.valueOf(FacultyEnum.class, Integer.valueOf(0));

	public static final FacultyEnum b = MDEnum.valueOf(FacultyEnum.class, Integer.valueOf(1));

	public static final FacultyEnum c = MDEnum.valueOf(FacultyEnum.class, Integer.valueOf(2));

	public static final FacultyEnum d = MDEnum.valueOf(FacultyEnum.class, Integer.valueOf(3));

}