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
public class TeacherEnum extends MDEnum {
	public TeacherEnum(IEnumValue enumvalue) {
		super(enumvalue);
	}

	public static final TeacherEnum ls = MDEnum.valueOf(TeacherEnum.class, Integer.valueOf(0));

	public static final TeacherEnum js = MDEnum.valueOf(TeacherEnum.class, Integer.valueOf(1));

}