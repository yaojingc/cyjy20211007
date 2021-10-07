package nccloud.impl.dao;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.vo.cy.bankcollflow.pojo.CustomFieldsPOJO;
import nc.vo.cy.bankcollflow.pojo.ResponseDetailPOJO;
import nc.vo.cy.bankcollflow.pojo.ResponsePOJO;
import nc.vo.cy.pojo.DefdocPOJO;
import nc.vo.cy.pojo.SingleReceivablePOJO;
import nc.vo.pub.BusinessException;

/**
 * 
 * @author RONGLEIA 查询收款合同自动生成应收单相关sql
 */
public class CreditContractBaseDao {

	/**
	 * Add By rongleia 查询当前表数据共有多少条
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String queryTotal() {
		try {
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			sql.append("select count(1)  from fct_ar where dr = '0' and fstatusflag  = '1'");
			Object result;
			result = queryBS.executeQuery(sql.toString(), new ColumnProcessor());
			if (result == null) {
				return "0";
			}
			return result.toString();
		} catch (BusinessException e) {
			e.printStackTrace();
			return "0";
		}
	}

	/**
	 * Add By rongleia 查询当月生效的收款合同
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SingleReceivablePOJO> queryContract(String stime, String etime, String oldnum, String newnum) {
		try {
			String start = null;
			String end;
			if ("0".equals(oldnum)) {
				start = oldnum;
			} else {
				start = oldnum + "000";
			}
			end = newnum + "000";
			IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			StringBuffer sql = new StringBuffer("");
			sql.append("select hid,pk_org, pk_org_v, customer, se, bhsje, stime, etime, status,ntaxrate\r\n"
					+ "  from (select rownum as rn,hid,\r\n" + "               pk_org,\r\n"
					+ "               pk_org_v,\r\n" + "               customer,\r\n" + "               se,\r\n"
					+ "               bhsje,\r\n" + "               stime,\r\n" + "               etime,\r\n"
					+ "               status,\r\n" + "               ntaxrate\r\n"
					+ "          from (select h.pk_fct_ar hid, h.pk_org,\r\n" + "                       h.pk_org_v,\r\n"
					+ "                       h.pk_customer customer,\r\n"
					+ "                       sum(b.ntax) se,\r\n" + "                       sum(b.norigmny) bhsje,\r\n"
					+ "                       substr(h.valdate, 1, 10) stime,\r\n"
					+ "                       substr(h.invallidate, 1, 10) etime,\r\n"
					+ "                       h.fstatusflag status,\r\n"
					+ "                       b.ntaxrate ntaxrate\r\n" + "                  from fct_ar_b b\r\n"
					+ "                 inner join fct_ar h\r\n"
					+ "                    on h.pk_fct_ar = b.pk_fct_ar\r\n" + "                 where h.dr = '0'\r\n"
					+ "                   and fstatusflag = '1'\r\n" + "                   and valdate <= '" + stime
					+ "'\r\n" + "                   and invallidate >= '" + etime + "'\r\n"
					+ "                 group by h.pk_fct_ar,\r\n" + "                          h.valdate,\r\n"
					+ "                          h.invallidate,\r\n" + "                          h.fstatusflag,\r\n"
					+ "                          h.pk_org,\r\n" + "                          h.pk_org_v,\r\n"
					+ "                          h.pk_fct_ar,h.pk_customer,\r\n"
					+ "                          b.ntaxrate)\r\n" + "         where rownum <= '" + end + "')\r\n"
					+ " where 1 = 1\r\n" + "   and rn >= '" + start + "'");
			// 组装查询SQL语句并执行
			BaseDAO dao = new BaseDAO();
			// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
			List<SingleReceivablePOJO> listvos = new ArrayList<SingleReceivablePOJO>();
			listvos = (List<SingleReceivablePOJO>) dao.executeQuery(sql.toString(),
					new BeanListProcessor(SingleReceivablePOJO.class));
			if (CyCommonUtils.isNotEmpty(listvos)) {
				return listvos;
			}
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	/**
	 * Add By rongleia 查询当前表数据共有多少条
	 * 
	 * @param resp
	 * 
	 * @param rolecode
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static String updateStatus(ResponsePOJO resp) {
		try {
			List<ResponseDetailPOJO> data = resp.getData();

			for (ResponseDetailPOJO responseDetailPOJO : data) {
				String customFields = responseDetailPOJO.getCustomFields();
				List<CustomFieldsPOJO> customlist = (List<CustomFieldsPOJO>) JSONTool.getArrByJson(customFields,
						CustomFieldsPOJO.class);
				for (CustomFieldsPOJO CustomFieldsPOJO : customlist) {
					String name = CustomFieldsPOJO.getCustomName();
					if ("学生身份证".equals(name)) {
						String idcard = CustomFieldsPOJO.getCustomValue();
						BaseDAO dao = new BaseDAO();
						StringBuffer sql = new StringBuffer("");
						sql.append(
								"update cy_contract set def4 = '已生效' where def2 = (select pk_student from cy_student where idcard = '"
										+ idcard + "' and dr = '0')");

						int executeUpdate = dao.executeUpdate(sql.toString());
					}
				}
			}
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * 根据合同主键修改金额
	 */
	public static void updatePrice(String hid, String hsje) {
		try {
			BaseDAO dao = new BaseDAO();
			StringBuffer sql = new StringBuffer("");
			sql.append(// norigpshamount
					"update fct_ar_b set "
							+ " noricopegpmny = ((select sum(noricopegpmny) from fct_ar_b where pk_fct_ar = '" + hid
							+ "')+" + hsje + ") where pk_fct_ar = '" + hid + "'");
			BaseDAO dao2 = new BaseDAO();
			StringBuffer sql2 = new StringBuffer("");
			sql2.append(// norigpshamount
					"update fct_ar set norigcopamount = ((select sum(noricopegpmny) from fct_ar_b where pk_fct_ar = '"
							+ hid + "')+" + hsje + ") where pk_fct_ar = '" + hid + "'");
			int executeUpdate2 = dao2.executeUpdate(sql2.toString());
			int executeUpdate = dao.executeUpdate(sql.toString());
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Add By rongleia 根据身份证号修改生效状态
	 * 
	 * @param resp
	 * 
	 * @param rolecode
	 * @return
	 */
	public static String updateStatus2(String idcard) {
		try {
			BaseDAO dao = new BaseDAO();
			StringBuffer sql = new StringBuffer("");
			sql.append(
					"update cy_contract set def4 = '已生效' where def2 = (select pk_student from cy_student where idcard = '"
							+ idcard + "' and dr = '0')");
			int executeUpdate = dao.executeUpdate(sql.toString());
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static DefdocPOJO queryDefdoc(String hid) {

		try {
			StringBuffer sql = new StringBuffer("");
			// sql.append(
			// "select (select code from bd_defdoc where pk_defdoc = vdef1) dqcode,(select
			// code from bd_region where pk_region=vdef2 ) sfcode,(select code from
			// bd_customer where pk_customer = vdef3) xxcode from fct_ar where dr = '0' and
			// pk_fct_ar = '"
			// + hid + "'");

			sql.append("select vdef1 dqcode,vdef2 sfcode,vdef3 xxcode from fct_ar where dr = '0' and pk_fct_ar = '"
					+ hid + "'");
			BaseDAO dao = new BaseDAO();
			DefdocPOJO defdocpojo = (DefdocPOJO) dao.executeQuery(sql.toString(), new BeanProcessor(DefdocPOJO.class));
			return defdocpojo;
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
