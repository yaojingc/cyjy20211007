package nccloud.cy.cy.stumarkdatahvo.action;

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

/**
 * 选中班级后查询班级下的所有学生数据组装成界面格式赋值表体用
 * @author tanrg
 *
 */
public class QueryStuOfClasscyAction  implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		String str = paramIRequest.read();
		// 班级主键
		List<Map<String,String>> listStu = new ArrayList<Map<String,String>>(); 
		String pk_classcy = (String) JSONTool.getJsonValueByKey(str, "pk_classcy");
		if(MMValueCheck.isEmpty(pk_classcy)) {
			return listStu;
		}
		try {

			IUAPQueryBS service = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			SqlBuilder sql = new SqlBuilder();
			sql.append(" select distinct cystu.pk_student def1,cystu.sname def1name,cystu.def13 def2,bddef.name def2name ");
			sql.append(" FROM cy_student cystu left join bd_defdoc bddef on cystu.def13=bddef.pk_defdoc ");
			sql.append(" WHERE nvl(cystu.dr,0)=0 and cystu.def1 is not null ");
			sql.append(" and nvl(bddef.dr,0)=0 and nvl(bddef.enablestate,0)=2 ");
			sql.append(" and cystu.def1",pk_classcy);
			listStu = (List<Map<String, String>>) service.executeQuery(sql.toString(), new MapListProcessor());
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		List<Row> listRow = new ArrayList<Row>();
		int rowno=1;
		for (Map<String, String> map : listStu) {
			Row br = new Row();
			Cell cellindex = new Cell();
			cellindex.setValue(rowno-1+"");
			br.addCell("index", cellindex);

			Cell cellrowno = new Cell();
			cellrowno.setValue(rowno+"");
			br.addCell("rowno", cellrowno);
			
			Cell celldef1 = new Cell();
			celldef1.setValue(map.get("def1"));
			celldef1.setDisplay(map.get("def1name"));
			br.addCell("def1", celldef1);
			
			Cell celldef2 = new Cell();
			celldef2.setValue(map.get("def2"));
			celldef2.setDisplay(map.get("def2name"));
			br.addCell("def2", celldef2);
			
			listRow.add(br);
			rowno++;
		}
		return listRow;
	}

}
