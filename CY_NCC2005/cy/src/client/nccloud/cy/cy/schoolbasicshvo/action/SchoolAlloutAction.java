package nccloud.cy.cy.schoolbasicshvo.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;

import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.JSONTool;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.pub.BusinessException;
import nccloud.framework.core.exception.ExceptionUtils;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

public class SchoolAlloutAction implements ICommonAction {

	List<SchoolBasicsHVO> list = null;

	@Override
	public Object doAction(IRequest request) {
		// 解析request
		String str = request.read();
		// 获取待分配的人员
		String pk_psndoc = (String) JSONTool.getJsonValueByKey(str, "pk_psndoc");
		String pk_psndoc2 = (String) JSONTool.getJsonValueByKey(str, "pk_psndoc2");
		String pk_psndoc3 = (String) JSONTool.getJsonValueByKey(str, "pk_psndoc3");
		String pk_psndoc4 = (String) JSONTool.getJsonValueByKey(str, "pk_psndoc4");
		// 获取用户选择的待分配的学校，批量
		JSONArray schoolVOS = (JSONArray) JSONTool.getJsonValueByKey(str, "schoolVOS");

		list = new ArrayList<SchoolBasicsHVO>();

		for (int i = 0; i < schoolVOS.size(); i++) {
			this.combineSchoolVO(schoolVOS, i);
		}

		// 直接传到后台统一处理
		try {
			int i = CyCommonUtils.getInstance(ISchoolbasicshvoMaintain.class).schoolAllot(pk_psndoc,pk_psndoc2,pk_psndoc3,pk_psndoc4,
					list.toArray(new SchoolBasicsHVO[] {}));
		} catch (BusinessException e) {
			ExceptionUtils.wrapException(e);
		}
		return null;
	}

	private void combineSchoolVO(JSONArray schoolVOS, int item) {
		JSONObject jsonObject = schoolVOS.getJSONObject(item);
		SchoolBasicsHVO schoolHVO = new SchoolBasicsHVO();
		schoolHVO.setPk_school(this.getJsonValue(jsonObject.getString("pk_school")));
		schoolHVO.setSaleman(this.getJsonValue(jsonObject.getString("saleman")));
		schoolHVO.setSenior(this.getJsonValue(jsonObject.getString("senior")));
		schoolHVO.setDef15(this.getJsonValue(jsonObject.getString("def15")));
		list.add(schoolHVO);
	}

	private String getJsonValue(String jsonstr) {
		Gson gs = new Gson();
		Map<String, String> map = gs.fromJson(jsonstr, Map.class);
		return map.get("value");
	}

}
