package nc.bs.arap.validator;
import java.util.Arrays;
import java.util.List;
import nc.bs.arap.validator.AbstractValidator;
import nc.bs.arap.validator.OriginOTHERBillValidator;
import nc.bs.uif2.validation.ValidationFailure;
import nc.bs.uif2.validation.Validator;
import nc.vo.arap.basebill.BaseAggVO;
import nc.vo.arap.basebill.BaseBillVO;
import nc.vo.arap.pub.BillEnumCollection;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.lang.UFBoolean;

public class OriginOTHERBillValidator extends AbstractValidator implements Validator {
  private static final long serialVersionUID = 1L;
  
  static List<String> top_syscodes = Arrays.asList(new String[] { "3", "4", "16", "19", "7" });
  
  static List<String> special_billtype = Arrays.asList(new String[] { 
        "30", "35", "21", "Z2", "Z3", "Z4", "Z5", "Z1", "4B36", "4A24", 
        "4A18", "4A04", "4A27", "4A49", "4A28", "4A25", "4A23", "4A05", "2201", "5801", 
        "5811", "5720" });
  
  public ValidationFailure validate(Object obj) {
    if (UFBoolean.FALSE.equals(checkObj(obj)))
      return null; 
    for (AggregatedValueObject aggvo : (AggregatedValueObject[])obj) {
      setObj(aggvo);
      BaseBillVO billvo = (BaseBillVO)aggvo.getParentVO();
      if (!((BaseAggVO)aggvo).isFromClient())
        return null; 
      Object top_syscode = billvo.getAttributeValue("src_syscode");
      Object top_billtype = aggvo.getChildrenVO()[0].getAttributeValue("top_billtype");
      Object billtype = billvo.getAttributeValue("pk_billtype");
      if (null != top_billtype)
        if (null == top_billtype || !special_billtype.contains(top_billtype)) {
          if (null != top_syscode && top_syscodes.contains(top_syscode.toString()))
            return new ValidationFailure(NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0", "02006pub-0041")); 
          Integer srcSyscode = billvo.getSrc_syscode();
          UFBoolean isflowbill = billvo.getIsflowbill();
          if (!((BaseAggVO)aggvo).getIsOtherModuleOriginate().booleanValue())
            if (!isflowbill.booleanValue())
              if (srcSyscode.intValue() != BillEnumCollection.FromSystem.AR.VALUE.intValue() && srcSyscode.intValue() != BillEnumCollection.FromSystem.AP.VALUE.intValue() && srcSyscode
                .intValue() != BillEnumCollection.FromSystem.CMP.VALUE.intValue() && srcSyscode.intValue() != BillEnumCollection.FromSystem.WBJHPT.VALUE.intValue() && srcSyscode
                .intValue() != BillEnumCollection.FromSystem.XTDJ.VALUE.intValue()) {
            	
            	//add by csh 20210928 改造校验，如果来源单据是退费申请单则跳过此处校验
            	if(!"TFSQ".equals(top_billtype.toString().trim())) {
            		if (("F0".equals(billtype) || "F1".equals(billtype)) && "113".equals(top_syscode.toString())) {
                    	return new ValidationFailure(NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0", "02006pub-0041")); 
                    }
                    if ("36U5".equals(top_billtype) || "36UA".equals(top_billtype) || "5795".equals(top_billtype)) {
                    	return new ValidationFailure(NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0", "02006pub-0041")); 
                    }
                    if (!"FCT1".equals(top_billtype.toString().trim()) && !"FCT2".equals(top_billtype.toString().trim())) {
                    	return new ValidationFailure(NCLangRes4VoTransl.getNCLangRes().getStrByID("2006pub_0", "02006pub-0042")); 
                    }
            	}  
              }   
        }  
    } 
    return null;
  }
}
