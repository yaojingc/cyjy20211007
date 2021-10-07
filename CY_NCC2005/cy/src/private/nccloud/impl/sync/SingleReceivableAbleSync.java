package nccloud.impl.sync;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.ErpItfDataPool;
import nc.utils.commonplugin.SingleReceivableXmlUtils;
import nc.vo.cy.pojo.DefdocPOJO;
import nc.vo.cy.pojo.SingleReceivablePOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import nccloud.impl.dao.CreditContractBaseDao;
import nccloud.itf.cy.cy.IBankcollflowhvoMaintain;

/**
 * 应收单通过后台任务自动生成
 * 
 * @author rongleia
 *
 */
public class SingleReceivableAbleSync implements IBackgroundWorkPlugin {

	private IBankcollflowhvoMaintain service;

	public IBankcollflowhvoMaintain getService() {
		if (service == null) {
			service = NCLocator.getInstance().lookup(IBankcollflowhvoMaintain.class);
		}
		return service;
	}

	// 调用第三方接口查询缴费记录
	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		/*
		 * 获取执行任务的时间
		 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		// 获取当前系统日期
		Date date = new Date();
		cd.setTime(date);
		String strdate = sdf.format(cd.getTime()); // 当前日期
//		String billperiod = strdate.substring(5, 7);// 会计期间
		String stime = strdate + " 23:59:59";// stime
		String etime = strdate + " 00:00:00";// etime
		String countnum = CreditContractBaseDao.queryTotal();
		UFDouble totalPages;
		int split = new UFDouble(countnum).div(new UFDouble(1000)).intValue();
		UFDouble mod = new UFDouble(countnum).mod(new UFDouble(1000));
		if (mod.sub(new UFDouble(0)).doubleValue() > 0) {
			totalPages = new UFDouble(split).add(new UFDouble(1));
		} else {
			totalPages = new UFDouble(split);
		}
		/*
		 * 查询收款合同中符合生成应收单的数据
		 */
		for (int i = 0; i < totalPages.intValue(); i++) {
			List<SingleReceivablePOJO> srpojolist = CreditContractBaseDao.queryContract(stime, etime,
					Integer.toString(i), Integer.toString(i + 1));
			if (CyCommonUtils.isNotEmpty(srpojolist)) {
				for (SingleReceivablePOJO singleReceivablePOJO : srpojolist) {
					String begin_date = singleReceivablePOJO.getStime();
					String end_date = singleReceivablePOJO.getEtime();
					LocalDate sdate = LocalDate.of(Integer.parseInt(begin_date.substring(0, 4)),
							Integer.parseInt(begin_date.substring(5, 7)),
							Integer.parseInt(begin_date.substring(8, 10)));
					LocalDate edate = LocalDate.of(Integer.parseInt(end_date.substring(0, 4)),
							Integer.parseInt(end_date.substring(5, 7)), Integer.parseInt(end_date.substring(8, 10)));
					int period = (int) ChronoUnit.MONTHS.between(sdate, edate);
					String bhsje = singleReceivablePOJO.getBhsje();// 不含税金额
					String se = singleReceivablePOJO.getSe();// 税额
					if (period == 0) {
						period = 1;
					}
					UFDouble mqbhs = new UFDouble(bhsje).div(new UFDouble(period)).setScale(2, UFDouble.ROUND_HALF_UP);// 每期不含税金额
					UFDouble mqse = new UFDouble(se).div(new UFDouble(period)).setScale(2, UFDouble.ROUND_HALF_UP);// 每期税额
					singleReceivablePOJO.setBhsje(mqbhs.toString());
					singleReceivablePOJO.setSe(mqse.toString());
					singleReceivablePOJO.setHsje(mqbhs.add(mqse).toString());
					/**
					 * 根据表头主键查询大区，省份，学校
					 */
					DefdocPOJO defpojo = new DefdocPOJO();

					defpojo = CreditContractBaseDao.queryDefdoc(singleReceivablePOJO.getHid());
					/*
					 * 根据数据拼装xml
					 */
					String singlexml = SingleReceivableXmlUtils.SingleXML(singleReceivablePOJO, defpojo);
					if (CyCommonUtils.isNotEmpty(singlexml)) {
						String billid = ErpItfDataPool.toSingle(singlexml);
						if (CyCommonUtils.isNotEmpty(billid)) {
							// 应收单生成成功 回写金额到合同基本
							CreditContractBaseDao.updatePrice(singleReceivablePOJO.getHid(),
									singleReceivablePOJO.getHsje());

						}
					}
				}
			}
		}
		return null;
	}
}
