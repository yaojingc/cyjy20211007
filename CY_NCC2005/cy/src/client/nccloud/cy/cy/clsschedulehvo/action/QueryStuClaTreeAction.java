package nccloud.cy.cy.clsschedulehvo.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.commonplugin.returnparam.TwoTuple;
import nc.vo.cy.classfilexz.ClassxzHVO;
import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.cy.studentfile.StudentHVO;
import nc.vo.pub.lang.UFBoolean;
import nccloud.cy.cy.clsschedulehvo.cy.stutree.StuTreeVO;
import nccloud.cy.cy.clsschedulehvo.cy.stutree.StuTreeWapper;
import nccloud.framework.core.json.IJson;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.itf.cy.cy.IClsschedulehvoMaintain;

/**
 * 课表定义节点表体的 "students"字段存放表体的这节课应该有哪些学生来上
 * 
 * @author YAO
 */
public class QueryStuClaTreeAction implements ICommonAction {

	public Object doAction(IRequest request) {
		String str = request.read();
		IJson json = JsonFactory.create();
		Map<String, Object> result = new HashMap<String, Object>();

		if (str == null || "{}".equals(str)) {
			return result;
		}
		Map<String, String> param = (Map) json.fromJson(str, Map.class);
		// 课表定义中的表体行主键
		String pk_clsschedule = (String) param.get("respid");
		
		// 课表定义中的表头def2,词源班级参照多选
		String def2 = (String) param.get("headcyclass");
		
		if (CyCommonUtils.isEmpty(pk_clsschedule)) {
			return result;
		}

		List<StuTreeVO> leftlist = new ArrayList<StuTreeVO>();
		List<StuTreeVO> rightlist = new ArrayList<StuTreeVO>();

		try {
			// 得到表体行VO+<行政班级主键，学生> 对象
			TwoTuple<ClsscheduleBVO, Map<ClassxzHVO, List<StudentHVO>>> returnparam = CyCommonUtils
					.getInstance(IClsschedulehvoMaintain.class).queryStuByXzcls(pk_clsschedule,def2);

			// 第一个参数 用来初始化加载右边已选学生的树
			ClsscheduleBVO bodyVO = returnparam.first;
			// 第二个参数用来加载左边待选学生树
			Map<ClassxzHVO, List<StudentHVO>> stuMap = returnparam.sencond;
			
			leftlist = this.stuTreeVOConstractor(stuMap);


			/**
			 * 开始构造右边的学生树 rightdata 右边的树表示已经选择好的学生 直接取表体
			 * XSDA2021082000000156&XSDA2021081900000155&XSDA2021081800000154
			 */
			String students = bodyVO.getStudents();
			if (CyCommonUtils.isNotEmpty(students)) {
				String[] studentarr = students.split("&");

				Map<ClassxzHVO, List<StudentHVO>> rightMap = CyCommonUtils.getInstance(IClsschedulehvoMaintain.class)
						.constractStuTreeByPKStu(studentarr);
				
				rightlist = this.stuTreeVOConstractor(rightMap);
			}
		} catch (Exception e) {
			
			

		}

		/**
		 * leftdata 待选择的学生
		 */
		StuTreeVO[] leftStuData = (StuTreeVO[]) leftlist.toArray(new StuTreeVO[leftlist.size()]);
		StuTreeWapper leftstutree = new StuTreeWapper(leftStuData);
		result.put("leftdata", leftstutree.getTree());

		/**
		 * rightdata 已选择的学生
		 */
		StuTreeVO[] rightStuData = (StuTreeVO[]) rightlist.toArray(new StuTreeVO[rightlist.size()]);
		StuTreeWapper rightstutree = new StuTreeWapper(rightStuData);
		result.put("rightdata", rightstutree.getTree());

		return result;
	}

	private List<StuTreeVO> stuTreeVOConstractor(Map<ClassxzHVO, List<StudentHVO>> beforeTreeMap) {
		List<StuTreeVO> returnlist = new ArrayList<StuTreeVO>();
		
		Iterator<Map.Entry<ClassxzHVO, List<StudentHVO>>> it2 = beforeTreeMap.entrySet().iterator();
		while (it2.hasNext()) {
			Entry<ClassxzHVO, List<StudentHVO>> entry = it2.next();
			ClassxzHVO xzclass = entry.getKey();
			List<StudentHVO> stulist = entry.getValue();
			/**
			 * 构造父节点 这里的父节点是学生所属的行政班级
			 */
			StuTreeVO tree = new StuTreeVO();
			tree.setPk(xzclass.getPk_classxz());
			tree.setPid("");
			tree.setCode(xzclass.getPk_classxz());
			tree.setName(xzclass.getClasname());
			tree.setIsleaf(UFBoolean.FALSE);
			returnlist.add(tree);

			stulist.forEach(item -> {
				StuTreeVO treeinside = new StuTreeVO();
				treeinside.setPk(item.getPk_student());
				treeinside.setPid(xzclass.getPk_classxz());
				treeinside.setCode(item.getBill_no());
				treeinside.setName(item.getSname());
				treeinside.setIsleaf(UFBoolean.FALSE);
				returnlist.add(treeinside);
			});
		}
		
		return returnlist;
	}

}
