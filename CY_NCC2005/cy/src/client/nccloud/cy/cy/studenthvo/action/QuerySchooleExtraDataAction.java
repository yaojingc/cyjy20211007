package nccloud.cy.cy.studenthvo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.utils.commonplugin.JSONTool;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.ui.model.row.Cell;
import nccloud.framework.web.ui.model.row.Row;

public class QuerySchooleExtraDataAction  implements ICommonAction {

	@SuppressWarnings("unchecked")
	@Override
	public Object doAction(IRequest paramIRequest) {
		String str = paramIRequest.read();
		// 学校主键
		List<Map<String,String>> listStu = new ArrayList<Map<String,String>>(); 
		String pk_school = (String) JSONTool.getJsonValueByKey(str, "pk_school");
		if(MMValueCheck.isEmpty(pk_school)) {
			return listStu;
		}
		try {

			IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			SqlBuilder sql = new SqlBuilder();
			sql.append(" SELECT sch.def11,defdoc.name def11docname,sch.province,regi.name reginame FROM cy_schoolbasics sch ");
			sql.append(" left join bd_defdoc defdoc on defdoc.pk_defdoc=sch.def11 ");
			sql.append(" left join bd_region regi on regi.pk_region=sch.province ");
			sql.append(" WHERE nvl(sch.dr,0)=0 and nvl(defdoc.dr,0)=0 and nvl(regi.dr,0)=0 and nvl(defdoc.enablestate,0)=2  ");
			sql.append(" and defdoc.pk_defdoclist in (SELECT pk_defdoclist FROM bd_defdoclist WHERE name like '%大区%') ");
			sql.append(" and sch.pk_school",pk_school);
			listStu = (List<Map<String, String>>) service.executeQuery(sql.toString(), new MapListProcessor());
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		List<Row> listRow = new ArrayList<Row>();
		for (Map<String, String> map : listStu) {
			Row row = new Row();
			
			Cell celldef1 = new Cell();
			celldef1.setValue(map.get("def11"));
			celldef1.setDisplay(map.get("def11docname"));
			row.addCell("def10", celldef1);
			
			Cell celldef2 = new Cell();
			celldef2.setValue(map.get("province"));
			celldef2.setDisplay(map.get("reginame"));
			row.addCell("def11", celldef2);
			
			listRow.add(row);
		}
		return listRow;
	}

}
