package nc.utils.commonplugin;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import nc.vo.cy.pojo.DefdocPOJO;
import nc.vo.cy.pojo.SingleReceivablePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

public class SingleReceivableXmlUtils {

	public static String SingleXML(SingleReceivablePOJO singlepojo, DefdocPOJO defdocpojo) {
		String xml = "";
		String groupcode = "CYJY";
		String account = "CYJY";
		String sender = "CY01";
		String orgcode = "";
		String datestr = new UFDate().toString();
		String custcode = "";
		try {
			String orgid = singlepojo.getPk_org();
			orgcode = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryOrgCodeById(orgid);
			String customerid = singlepojo.getCustomer();
			custcode = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryCustCodeById(customerid);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		/*
		 * Xml 拼接Start
		 */
		Document document = DocumentHelper.createDocument();
		Element ufinterface = document.addElement("ufinterface");
		ufinterface.addAttribute("account", account);
		ufinterface.addAttribute("billtype", "F0");
		ufinterface.addAttribute("filename", "");
		ufinterface.addAttribute("groupcode", groupcode);
		ufinterface.addAttribute("orgcode", "");
		ufinterface.addAttribute("isexchange", "Y");
		ufinterface.addAttribute("replace", "Y");
		ufinterface.addAttribute("roottag", "");
		ufinterface.addAttribute("sender", sender);
		Element bill = ufinterface.addElement("bill");
		bill.addAttribute("id", "");

		Element billhead = bill.addElement("billhead");
		Element pk_group = billhead.addElement("pk_group");
		pk_group.setText(groupcode);
		Element pk_org = billhead.addElement("pk_org");
		pk_org.setText(orgcode);
		Element pk_org_v = billhead.addElement("pk_org_v");
		pk_org_v.setText(orgcode);
		Element isreded = billhead.addElement("isreded");
		isreded.setText("N");
		Element officialprintdate = billhead.addElement("officialprintdate");
		officialprintdate.setText(datestr);
		Element modifiedtime = billhead.addElement("modifiedtime");
		modifiedtime.setText(datestr);
		Element creationtime = billhead.addElement("creationtime");
		creationtime.setText(datestr);
		Element pk_billtype = billhead.addElement("pk_billtype");
		pk_billtype.setText("F0");
		Element pk_tradetype = billhead.addElement("pk_tradetype");
		pk_tradetype.setText("D0");
		Element billclass = billhead.addElement("billclass");
		billclass.setText("ys");
		Element accessorynum = billhead.addElement("accessorynum");
		accessorynum.setText("0");
		Element isflowbill = billhead.addElement("isflowbill");
		isflowbill.setText("N");
		Element isinit = billhead.addElement("isinit");
		isinit.setText("N");
		Element billdate = billhead.addElement("billdate");
		billdate.setText(datestr);
		Element syscode = billhead.addElement("syscode");
		syscode.setText("0");
		Element src_syscode = billhead.addElement("src_syscode");
		src_syscode.setText("0");
		Element billstatus = billhead.addElement("billstatus");
		billstatus.setText("0");
		Element billmaker = billhead.addElement("billmaker");
		billmaker.setText("ufrl");
		Element approver = billhead.addElement("approver");
		approver.setText("ufrl");
		Element approvedate = billhead.addElement("approvedate");
		approvedate.setText(datestr);
		Element lastadjustuser = billhead.addElement("lastadjustuser");
		lastadjustuser.setText("ufrl");
		Element money = billhead.addElement("money");
		money.setText(singlepojo.getHsje());
		Element local_money = billhead.addElement("local_money");
		local_money.setText(singlepojo.getHsje());
		Element rate = billhead.addElement("rate");
		rate.setText("1");
		Element effectstatus = billhead.addElement("effectstatus");
		effectstatus.setText("0");
		Element coordflag = billhead.addElement("coordflag");
		coordflag.setText("0");
		Element inner_effect_date = billhead.addElement("inner_effect_date");
		inner_effect_date.setText(datestr);
		Element busidate = billhead.addElement("busidate");
		busidate.setText(datestr);
		if (CyCommonUtils.isNotEmpty(defdocpojo.getDqcode())) {
			Element def1 = billhead.addElement("def1");
			def1.setText(defdocpojo.getDqcode());
		}
		if (CyCommonUtils.isNotEmpty(defdocpojo.getSfcode())) {
			Element def2 = billhead.addElement("def2");
			def2.setText(defdocpojo.getSfcode());
		}
		if (CyCommonUtils.isNotEmpty(defdocpojo.getXxcode())) {
			Element def3 = billhead.addElement("def3");
			def3.setText(defdocpojo.getXxcode());
		}

		Element bodys = billhead.addElement("bodys");
		Element item = bodys.addElement("item");

		/*
		 * item
		 */
		Element pk_orgb = item.addElement("pk_org");
		pk_orgb.setText(orgcode);
		Element pk_org_vb = item.addElement("pk_org_v");
		pk_org_vb.setText(orgcode);
		Element customer = item.addElement("customer");
		customer.setText(custcode);
		Element material = item.addElement("material");
		material.setText("01001");// 培训费
		Element price = item.addElement("price");
		price.setText(singlepojo.getBhsje());// 单价
		Element taxprice = item.addElement("taxprice");
		taxprice.setText(singlepojo.getHsje());// 含税单价
		Element coordflagb = item.addElement("coordflag");
		coordflagb.setText("0");
		Element pausetransact = item.addElement("pausetransact");
		pausetransact.setText("N");
		Element billdateb = item.addElement("billdate");
		billdateb.setText(datestr);
		Element pk_groupb = item.addElement("pk_group");
		pk_groupb.setText(groupcode);
		Element pk_billtypeb = item.addElement("pk_billtype");
		pk_billtypeb.setText("F0");
		Element billclassb = item.addElement("billclass");
		billclassb.setText("ys");
		Element pk_tradetypeb = item.addElement("pk_tradetype");
		pk_tradetypeb.setText("D0");
		Element busidateb = item.addElement("busidate");
		busidateb.setText(datestr);
		Element objtype = item.addElement("objtype");
		objtype.setText("0");
		Element rowno = item.addElement("rowno");
		rowno.setText("0");
		Element rowtype = item.addElement("rowtype");
		rowtype.setText("0");
		Element direction = item.addElement("direction");
		direction.setText("0");
		Element pk_currtype = item.addElement("pk_currtype");
		pk_currtype.setText("CNY");
		Element rateb = item.addElement("rate");
		rateb.setText("1");
		Element money_de = item.addElement("money_de");
		money_de.setText(singlepojo.getHsje());
		Element local_money_de = item.addElement("local_money_de");
		local_money_de.setText(singlepojo.getHsje());
//		Element quantity_de = item.addElement("quantity_de");
//		quantity_de.setText(singlepojo.getBhsje());
//		Element money_bal = item.addElement("money_bal");
//		money_bal.setText(singlepojo.getBhsje());
//		Element local_money_bal = item.addElement("local_money_bal");
//		local_money_bal.setText(singlepojo.getBhsje());
//		Element quantity_bal = item.addElement("quantity_bal");
//		quantity_bal.setText(singlepojo.getBhsje());
		Element local_tax_de = item.addElement("local_tax_de");
		local_tax_de.setText(singlepojo.getSe());
		Element notax_de = item.addElement("notax_de");
		notax_de.setText(singlepojo.getBhsje());
		Element local_notax_de = item.addElement("local_notax_de");
		local_notax_de.setText(singlepojo.getBhsje());
//		Element price = item.addElement("price");
//		price.setText(singlepojo.getBhsje());
//		Element taxprice = item.addElement("taxprice");
//		taxprice.setText(singlepojo.getBhsje());
		Element taxrate = item.addElement("taxrate");
		taxrate.setText(singlepojo.getNtaxrate());
		Element grouprate = item.addElement("grouprate");
		grouprate.setText("1");
		Element globalrate = item.addElement("globalrate");
		globalrate.setText("1");
		Element groupdebit = item.addElement("groupdebit");
		groupdebit.setText(singlepojo.getHsje());
		Element globaldebit = item.addElement("globaldebit");
		globaldebit.setText(singlepojo.getHsje());
		Element groupbalance = item.addElement("groupbalance");
		groupbalance.setText("1");
//		Element globalbalance = item.addElement("globalbalance");
//		globalbalance.setText(singlepojo.getBhsje());
		Element groupnotax_de = item.addElement("groupnotax_de");
		groupnotax_de.setText(singlepojo.getBhsje());
		Element globalnotax_de = item.addElement("globalnotax_de");
		globalnotax_de.setText(singlepojo.getBhsje());
//		Element occupationmny = item.addElement("occupationmny");
//		occupationmny.setText(singlepojo.getBhsje());
		Element buysellflag = item.addElement("buysellflag");
		buysellflag.setText("1");
		Element caltaxmny = item.addElement("caltaxmny");
		caltaxmny.setText(singlepojo.getHsje());

		XmlParsers parse = new XmlParsers();
		xml = parse.formatXML(document, "UTF-8");
		return xml;
	}
}
