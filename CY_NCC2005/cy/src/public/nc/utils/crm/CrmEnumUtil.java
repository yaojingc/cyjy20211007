package nc.utils.crm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nc.api.cy.rest.entity.crm.basepojo.CrmBaseTag;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.enums.Ncc_Area;
import nc.api.cy.rest.enums.Ncc_BillStatus;
import nc.api.cy.rest.enums.Ncc_Busilevel;
import nc.api.cy.rest.enums.Ncc_Company;
import nc.api.cy.rest.enums.Ncc_Developsituation;
import nc.api.cy.rest.enums.Ncc_Grade;
import nc.api.cy.rest.enums.Ncc_Intedegree;
import nc.api.cy.rest.enums.Ncc_Neworoldschool;
import nc.api.cy.rest.enums.Ncc_Sex;
import nc.api.cy.rest.enums.Ncc_Stablelevel;
import nc.api.cy.rest.enums.Ncc_Studenttype;
import nc.api.cy.rest.enums.Ncc_Teacherapply;
import nc.api.cy.rest.enums.Ncc_Teachertype;
import nc.api.cy.rest.enums.Ncc_Ufboolean;
import nc.api.cy.rest.enums.Ncc_Urgency;

/**
 * 营销管理专用枚举封装
 * @author csh
 *
 */
public class CrmEnumUtil {
	
	/**
         * 获取枚举值
     * @param tag 标记值
     * @return
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
     */
	@SuppressWarnings({"all"})
    public static List<RefPOJO> getEnmu(String tag) throws Exception{
        List<RefPOJO> list = new ArrayList<RefPOJO>();
        
		String enumClazzName = null;
        switch (tag) {
			case CrmBaseTag.ENUM_UFBOOLEAN:
				enumClazzName = Ncc_Ufboolean.class.getName();
				break;
			case CrmBaseTag.ENUM_BUSILEVEL:
				enumClazzName = Ncc_Busilevel.class.getName();
				break;
			case CrmBaseTag.ENUM_STABLELEVEL:
				enumClazzName = Ncc_Stablelevel.class.getName();
				break;
			case CrmBaseTag.ENUM_STUDENTTYPE:
				enumClazzName = Ncc_Studenttype.class.getName();
				break;
			case CrmBaseTag.ENUM_TEACHERTYPE:
				enumClazzName = Ncc_Teachertype.class.getName();
				break;
			case CrmBaseTag.ENUM_GRADE:
				enumClazzName = Ncc_Grade.class.getName();
				break;
			case CrmBaseTag.ENUM_BILLSTATUS:
				enumClazzName = Ncc_BillStatus.class.getName();
				break;
			case CrmBaseTag.ENUM_TEACHERAPPLY:
				enumClazzName = Ncc_Teacherapply.class.getName();
				break;
			case CrmBaseTag.ENUM_INTEDEGREE:
				enumClazzName = Ncc_Intedegree.class.getName();
				break;
			case CrmBaseTag.ENUM_COMPANY:
				enumClazzName = Ncc_Company.class.getName();
				break;
			case CrmBaseTag.ENUM_AREA:
				enumClazzName = Ncc_Area.class.getName();
				break;
			case CrmBaseTag.ENUM_DEVELOPSITUATION:
				enumClazzName = Ncc_Developsituation.class.getName();
				break;
			case CrmBaseTag.ENUM_URGENCY:
				enumClazzName = Ncc_Urgency.class.getName();
				break;
			case CrmBaseTag.ENUM_NEWOROLDSCHOOL:
				enumClazzName = Ncc_Neworoldschool.class.getName();
				break;
			case CrmBaseTag.ENUM_SEX:
				enumClazzName = Ncc_Sex.class.getName();
				break;
			default:
				break;
		}
        
        if(enumClazzName!=null) {
        	Class enumClazz = Class.forName(enumClazzName);
        	Object[] objects = enumClazz.getEnumConstants();
        	Method getName = enumClazz.getMethod("getName");
            Method getValue = enumClazz.getMethod("getValue");
            for (Object obj : objects) {
            	RefPOJO pojo = new RefPOJO();
            	pojo.setLabel(getName.invoke(obj).toString());
            	pojo.setValue(getValue.invoke(obj).toString());
                list.add(pojo);
            }
        }
        return list;
    }
}
