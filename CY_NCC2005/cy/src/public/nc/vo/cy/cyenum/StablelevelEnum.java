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
public class StablelevelEnum extends MDEnum {
	public StablelevelEnum(IEnumValue enumvalue) {
		super(enumvalue);
	}

	public static final StablelevelEnum LOSE = MDEnum.valueOf(StablelevelEnum.class, Integer.valueOf(0));

	public static final StablelevelEnum STABILIZE = MDEnum.valueOf(StablelevelEnum.class, Integer.valueOf(1));

	public static final StablelevelEnum INSTABILITY = MDEnum.valueOf(StablelevelEnum.class, Integer.valueOf(2));

}