package nc.utils.transfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.swiftpass.XmlUtils;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nccloud.impl.dao.CreditContractBaseDao;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/**
 * 缴费记录接口数据转换成系统vo
 * 
 * @author rongleia
 *
 */
public class SchoolPaymentToBankVO {

	public String transResptoVo(String respxml) throws BusinessException {
		try {
//			CyCommonUtils.login();
			List<AggBankcollflowHVO> agglist = new ArrayList<AggBankcollflowHVO>();
			Map<String, String> map = XmlUtils.toMap(respxml.getBytes(), "utf-8");
			if (CyCommonUtils.isNotEmpty(map)) {
				String payNo = map.get("out_trade_no").trim();// 缴费单号
				Boolean queryFlag = CyCommonUtils.getInstance(IBankcollflowhvoMaintain.class).queryVoucher(payNo);
				if (queryFlag) {
					String sname = "";
					String idcard = "";
					String phonenum = "";
					String grade = "";
					String school = "";
					String pk_detail = "";
					AggBankcollflowHVO aggvo = new AggBankcollflowHVO();
					BankcollflowHVO hvo = new BankcollflowHVO();
					String payAmount = map.get("total_fee").trim();// 缴费金额
					String msgstr = map.get("attach").trim();
					String[] split = msgstr.split("#");
					for (String string : split) {
						String[] split2 = string.trim().split("=");
						if (split2[0].equals("sname")) {
							sname = split2[1];
						} else if (split2[0].equals("schoolname")) {
							school = split2[1];
						} else if (split2[0].equals("clasname")) {
							grade = split2[1];
						} else if (split2[0].equals("idcard")) {
							idcard = split2[1];
						} else if (split2[0].equals("parentphone")) {
							phonenum = split2[1];
						} else if (split2[0].equals("pk_detail")) {
							// 缴费单号回写学费收款合同
							pk_detail = split2[1];
							if (CyCommonUtils.isNotEmpty(pk_detail)) {
								CyCommonUtils.getInstance(ICyCommonSqlUtil.class).updateContract(pk_detail, payNo);
							}
						}
					}
					hvo.setSchool(school);// 学校
					hvo.setSname(sname);// 学生名字
					hvo.setGrade(grade);// 班级
					hvo.setIdcard(idcard);// 身份证号
					hvo.setPhonenum(phonenum);// 电话号码
					if (CyCommonUtils.isNotEmpty(payAmount)) {
						hvo.setPayamount((new UFDouble(payAmount).div(new UFDouble(100)))
								.setScale(2, UFDouble.ROUND_HALF_UP).toString());// 缴费金额
					} else {
						hvo.setPayamount("0.0");
					}
					hvo.setDbilldate(new UFDate());// 单据时间
					hvo.setAcquiescent("0");// 默认
					hvo.setPaydate(new UFDateTime());// 缴费时间
					hvo.setPaycode(payNo);// 单据号
					hvo.setPaytype("家长端");// 缴费方式
					hvo.setBankaccount("1001L110000000004R9P");// 银行账户
					hvo.setBillstatus(-1);
					hvo.setApprovestatus(-1);
					hvo.setPk_group(AppletsParams.Pk_Group);
					hvo.setPk_org(AppletsParams.Pk_Org);
					aggvo.setParentVO(hvo);
					agglist.add(aggvo);
					AggBankcollflowHVO[] array = agglist.toArray(new AggBankcollflowHVO[agglist.size()]);
					IBankcollflowhvoMaintain bfinfo = NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
					AggBankcollflowHVO[] insert = bfinfo.insert(array, null);
					
//					CreditContractBaseDao.updateStatus2(idcard);// 修改学费收款合同已生效状态
					return idcard;
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}
}
