package nccloud.impl.sync;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.bs.dao.BaseDAO;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.pa.PreAlertReturnType;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.ExceptionDetailUtil;
import nc.utils.commonplugin.NoteDataUtils;
import nc.utils.commonplugin.PushDataUtil;
import nc.vo.bd.psn.PsndocVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;

/*
 * 学校档案90天未开发，到期回收，学校推送友空间预警,huangcong
 */
public class SchoolCautionSync implements IBackgroundWorkPlugin {
	private static String fileName = "CautionExpirylog.txt";

	// 调用第三方接口查询缴费记录
	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		// 拼装接口所需的参数
		PreAlertObject retObj = null;
		Date now = new Date();
		Calendar after = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		after.setTime(now);
		Integer beforedays=Integer.valueOf(AppletsParams.YOUYOU_beforedays);
		after.set(Calendar.DATE, after.get(Calendar.DATE) + beforedays);
		String afters = sdf.format(after.getTime());
		SqlBuilder sqlsale = new SqlBuilder();
		sqlsale.append("select * from bd_psndoc where dr=0 and pk_psndoc in");
		sqlsale.append("(select DISTINCT(saleman) from cy_schoolbasics where ");
		sqlsale.append("dr", 0);
		sqlsale.append(" and def18 <='" + afters + "')");
		SqlBuilder sqlsenior = new SqlBuilder();
		sqlsenior.append("select * from bd_psndoc where dr=0 and pk_psndoc in");
		sqlsenior.append("(select DISTINCT(senior) from cy_schoolbasics where ");
		sqlsenior.append("dr", 0);

		sqlsenior.append(" and def18 <='" + afters + "')");
//		NoteDataUtils.addLog("获取到需要发送消息的业务员和高管" + sqlsale.toString(), fileName, true, null);
		try {
			@SuppressWarnings("unchecked")
			List<PsndocVO> saleids = (List<PsndocVO>) new BaseDAO().executeQuery(sqlsale.toString(),
					new BeanListProcessor(PsndocVO.class));

			@SuppressWarnings("unchecked")
			List<PsndocVO> seniorids = (List<PsndocVO>) new BaseDAO().executeQuery(sqlsenior.toString(),
					new BeanListProcessor(PsndocVO.class));
			saleids.addAll(seniorids);
			NoteDataUtils.addLog("获取到需要发送消息的业务员和高管" + saleids.toString(), fileName, false, null);
			String msgTitle = "学校档案预警提醒";
			String msgsale = "您有学校即将回收，请及时处理相关业务";
			// 推送数据到友空间
			PushDataUtil.pushData(saleids, msgTitle, msgsale);
			SqlBuilder condition = new SqlBuilder();
			String d = "9999-12-28";
			condition.append("def18 >'" + afters + "'");
			condition.append(" and def18 <'" + d + "'");
			TableCountQueryVO tvo = new TableCountQueryVO("cy_schoolbasics", condition.toString());

			PagingPOJO pojo = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(tvo);
			NoteDataUtils.addLog("查询需要回收的超期学校数量" + pojo.getTotal() + seniorids.toString(), fileName, false, null);
			if (pojo.getTotal() != 0) {
				SqlBuilder updateSql = new SqlBuilder();
				updateSql.append("update cy_schoolbasics set dr=1 where def18>'" + afters + "'");
				updateSql.append(" and def18 <'" + d + "'");
				CyCommonUtils.getInstance(BaseDAO.class).executeUpdate(updateSql.toString());
			}
			retObj = new PreAlertObject();
			retObj.setReturnType(PreAlertReturnType.RETURNOBJECT);
			return retObj;
		} catch (Exception e) {
			String exceptionDetail = ExceptionDetailUtil.getExceptionDetail(e);
			NoteDataUtils.addLog("预警任务出现报错：" + "\n" + exceptionDetail, fileName, false, null);
		}
		return getNothingObject();

	}

	private PreAlertObject getNothingObject() {
		PreAlertObject retObj = new PreAlertObject();
		retObj.setReturnType(PreAlertReturnType.RETURNNOTHING);
		return retObj;
	}
}
