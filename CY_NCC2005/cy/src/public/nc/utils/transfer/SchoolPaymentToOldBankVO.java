package nc.utils.transfer;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.cy.bankcollflow.pojo.CustomFieldsPOJO;
import nc.vo.cy.bankcollflow.pojo.ResponseDetailPOJO;
import nc.vo.cy.bankcollflow.pojo.ResponsePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;

public class SchoolPaymentToOldBankVO {

	public Boolean transResptoVo(ResponsePOJO resppojo) throws BusinessException {
		try {
			List<AggBankcollflowHVO> agglist = new ArrayList<AggBankcollflowHVO>();
			List<ResponseDetailPOJO> data = resppojo.getData();
			if (CyCommonUtils.isNotEmpty(data)) {
				for (ResponseDetailPOJO responseDetailPOJO : data) {
					String payNo = responseDetailPOJO.getPayNo();
					Boolean queryFlag = CyCommonUtils.getInstance(IBankcollflowhvoMaintain.class).queryVoucher(payNo);
					if (queryFlag) {
						String sname = "";
						String idcard = "";
						String phonenum = "";
						String grade = "";
						String school = "";
						AggBankcollflowHVO aggvo = new AggBankcollflowHVO();
						BankcollflowHVO hvo = new BankcollflowHVO();
						String customFields = responseDetailPOJO.getCustomFields();
						@SuppressWarnings("unchecked")
						List<CustomFieldsPOJO> customlist = (List<CustomFieldsPOJO>) JSONTool.getArrByJson(customFields,
								CustomFieldsPOJO.class);
						if (CyCommonUtils.isNotEmpty(customlist)) {
							for (CustomFieldsPOJO customfieldspojo : customlist) {
								String name = customfieldspojo.getCustomName();
								if ("学生姓名".equals(name)) {
									sname = customfieldspojo.getCustomValue();
								} else if ("家长手机号".equals(name)) {
									phonenum = customfieldspojo.getCustomValue();
								} else if ("学校".equals(name)) {
									school = customfieldspojo.getCustomValue();
								} else if ("班级".equals(name)) {
									grade = customfieldspojo.getCustomValue();
								} else if ("学生身份证".equals(name)) {
									idcard = customfieldspojo.getCustomValue();
								}
							}
						}
						hvo.setSchool(school);// 学校
						hvo.setSname(sname);// 学生名字
						hvo.setGrade(grade);// 班级
						hvo.setIdcard(idcard);// 身份证号
						hvo.setPhonenum(phonenum);// 电话号码
						hvo.setBankaccount("1001L110000000004R9P");// 银行账户
						String payAmount = responseDetailPOJO.getPayAmount();
						if (payAmount.length() > 2) {
							hvo.setPayamount(payAmount.substring(0, payAmount.length() - 2));// 缴费金额
						}
						hvo.setPaytype("威缴费");// 缴费方式
//				hvo.setAcquiescent(acquiescent);//默认
//				hvo.setMatchstate(matchstate);//匹配装填
						String billdate = responseDetailPOJO.getPayTime().substring(0, 10);
						hvo.setDbilldate(new UFDate(billdate));
						hvo.setPaydate(new UFDateTime(responseDetailPOJO.getPayTime()));// 缴费时间
//				hvo.setMakestate();// 开票状态
						hvo.setPaycode(payNo);// 单据号
						hvo.setBillstatus(-1);
						hvo.setApprovestatus(-1);
						hvo.setPk_group(AppletsParams.Pk_Group);
						hvo.setPk_org(AppletsParams.Pk_Org);
						aggvo.setParentVO(hvo);
						agglist.add(aggvo);
					}
				}
				AggBankcollflowHVO[] array = agglist.toArray(new AggBankcollflowHVO[agglist.size()]);
				IBankcollflowhvoMaintain bfinfo = NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
				AggBankcollflowHVO[] insert = bfinfo.insert(array, null);
				return true;
			}
		} catch (Exception e) {
			return false;
		}
		return false;
	}
}
