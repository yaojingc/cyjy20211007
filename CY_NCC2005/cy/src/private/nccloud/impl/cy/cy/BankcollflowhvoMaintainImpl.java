
package nccloud.impl.cy.cy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.bankcollflow.AggBankcollflowHVO;
import nc.vo.cy.bankcollflow.BankQueryPOJO;
import nc.vo.cy.bankcollflow.BankReturnPOJO;
import nc.vo.cy.bankcollflow.BankcollflowHVO;
import nc.vo.cy.bankcollflow.InvoiceSendPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.impl.pub.ace.AceAggbusiBankcollflowHVOPubServiceImpl;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;

public class BankcollflowhvoMaintainImpl extends AceAggbusiBankcollflowHVOPubServiceImpl
		implements IBankcollflowhvoMaintain {

	@Override
	public void delete(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills) throws BusinessException {
		super.pubdeleteBills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] insert(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubinsertBills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] update(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubupdateBills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] query(IQueryScheme queryScheme) throws BusinessException {
		return super.pubquerybills(queryScheme);
	}

	@Override
	public AggBankcollflowHVO[] save(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] unsave(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubunsendapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] approve(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubapprovebills(clientFullVOs, originBills);
	}

	@Override
	public AggBankcollflowHVO[] unapprove(AggBankcollflowHVO[] clientFullVOs, AggBankcollflowHVO[] originBills)
			throws BusinessException {
		return super.pubunapprovebills(clientFullVOs, originBills);
	}

	@Override
	public BankcollflowHVO[] bankFlowUpdate(BankcollflowHVO bfhvo) {
		VOUpdate<BankcollflowHVO> updateOnhand = new VOUpdate<BankcollflowHVO>();
		return updateOnhand.update(new BankcollflowHVO[] { bfhvo }, new String[] { "acquiescent" });
	}

	/**
	 * 根据缴费单号查询是否存在该张单据rongleia
	 */
	public Boolean queryVoucher(String paycode) throws BusinessException {
		IUAPQueryBS queryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		StringBuffer sql = new StringBuffer("");
		sql.append("select paycode from cy_bankcollflow where paycode = '" + paycode + "'");
		Object result = queryBS.executeQuery(sql.toString(), new ColumnProcessor());
		if (CyCommonUtils.isEmpty(result)) {
			return true;
		}
		return false;
	}

	/**
	 * 查询获取银行收款流水信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankcollflowHVO> queryBakFlows(InvoiceSendPOJO bfvo) throws BusinessException {
		SqlBuilder sql = new SqlBuilder();
		sql.append(" select * from cy_bankcollflow where nvl(dr,0)=0 ");
		sql.append(" and idcard", bfvo.getIdcard());
		sql.append(" and bill_no", bfvo.getBill_no());

		List<BankcollflowHVO> listBankf = (List<BankcollflowHVO>) CyCommonUtils.getInstance(BaseDAO.class)
				.executeQuery(sql.toString(), new BeanListProcessor(BankcollflowHVO.class));

		// 匹配查询学生邮箱号
		if (MMValueCheck.isNotEmpty(listBankf)) {
			try {
				Set<String> setkey = new HashSet<String>();
				for (BankcollflowHVO bf : listBankf) {
					setkey.add(bf.getSname() + "/" + bf.getIdcard());
				}
				sql.reset();
				sql.append(" SELECT email,strkey FROM ( ");
				sql.append(
						" SELECT distinct email, CONCAT(sname,CONCAT（'/',idcard)) strkey FROM cy_student WHERE nvl(dr,0)=0 ");
				sql.append(" ) temp WHERE temp.strkey ", setkey.toArray(new String[0]));
				List<Map<String, String>> mapEmails = (List<Map<String, String>>) CyCommonUtils
						.getInstance(BaseDAO.class).executeQuery(sql.toString(), new MapListProcessor());

				Map<String, String> mapdata = new HashMap<String, String>();// 邮箱
				if (MMValueCheck.isNotEmpty(mapEmails)) {
					for (Map<String, String> map : mapEmails) {
						mapdata.put(map.get("strkey"), map.get("email"));
					}
				}
				if (MMValueCheck.isNotEmpty(mapdata)) {
					for (BankcollflowHVO bf : listBankf) {
						String key = bf.getSname() + "/" + bf.getIdcard();
						bf.setStrkey(mapdata.get(key));// 做邮箱用
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return listBankf;
	}

	/**
	 * 查询获取银行收款流水信息 需要分页处理
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BankReturnPOJO> queryBak(BankQueryPOJO querypojo, int pagetotal) throws BusinessException {

		int index = Integer.parseInt(querypojo.getIndex());// 第几页
		int size = Integer.parseInt(querypojo.getPageSize());// 每页数据量
		UFDouble start = (new UFDouble(index).sub(new UFDouble(1))).multiply(new UFDouble(size)).add(new UFDouble(1));
		UFDouble end = new UFDouble(index).multiply(new UFDouble(size));
		BaseDAO dao = new BaseDAO();
		SqlBuilder sql = new SqlBuilder();

		sql.append(
				" SELECT table_alias.sname payname,table_alias.payamount paymoney,table_alias.bill_no paybillno,table_alias.acquiescent paybilltype,table_alias.paydate paytime");
		sql.append(" FROM (SELECT tt.*, ROWNUM AS rowno ");
		sql.append(" 	FROM (SELECT * ");
		sql.append(" 			FROM cy_bankcollflow t ");
		sql.append(" 			WHERE ");
		sql.append(" idcard ", querypojo.getIdcard());
		sql.append(" AND ");
		sql.append(" nvl(dr,0) ", 0);
		sql.append(" ORDER BY T.CREATIONTIME DESC) tt) table_alias ");
		sql.append(" WHERE table_alias.rowno BETWEEN " + start + " AND " + end + " ");

		List<BankReturnPOJO> listStu = (List<BankReturnPOJO>) dao.executeQuery(sql.toString(),
				new BeanListProcessor(BankReturnPOJO.class));
		return listStu;
	}

	private SqlBuilder builder = CyCommonUtils.getInstance(SqlBuilder.class);

	@Override
	public Boolean updateMakestate(List<String> pklist) throws BusinessException {
		// 根据单据主键修改是否开票状态
		builder.reset();
		builder.append("update cy_bankcollflow  set makestate = 'Y' where ");
		builder.append("pk_bank", pklist.toArray(new String[0]));
		int executeUpdate = CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(builder.toString());
		if (executeUpdate > 0) {
			return true;
		}
		return false;
	}

}
