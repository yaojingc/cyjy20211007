package nccloud.cy.cy.clsschedulehvo.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.itf.uap.IUAPQueryBS;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.classfilexz.ClassxzHVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nccloud.cy.cy.clsschedulehvo.cy.stutree.StuTreeVO;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;

public class StuClaTreeSaveAction implements ICommonAction {

	@Override
	public Object doAction(IRequest request) {

		String str = request.read();
		IJson json = JsonFactory.create();
		Map<String, Object> result = new HashMap<String, Object>();

		if (str == null || "{}".equals(str)) {
			return result;
		}
		Map<String, Object> param = (Map) json.fromJson(str, Map.class);
		// 课表定义中的表体行主键
		String pk_clsschedule = (String) param.get("pk_clsschedule");
		if (CyCommonUtils.isEmpty(pk_clsschedule)) {
			return result;
		}
		List<Map<String, Object>> applist = (List<Map<String, Object>>) param.get("studata");
		StringBuffer buffer = new StringBuffer();
		applist.forEach(item -> {
			List<Map<String, String>> childlist = (List) item.get("children");
			childlist.forEach(item2 -> {
				String stu_billno = (String) item2.get("key");
				buffer.append(stu_billno);
				buffer.append("&");
			});
		});


		// 下一步，将buffer 即组装好的学生数据写到对应的表体行中去
		try {
			CyCommonUtils.getInstance(IClsschedulehvoMaintain.class).updateBodyLineByPK(pk_clsschedule,buffer.toString());
			
			
		} catch (BusinessException e) {
			e.printStackTrace();
		}
			


		return null;
	}

}
