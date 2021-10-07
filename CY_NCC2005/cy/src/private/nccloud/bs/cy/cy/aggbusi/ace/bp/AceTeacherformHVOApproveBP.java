
package nccloud.bs.cy.cy.aggbusi.ace.bp;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillUpdate;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.vo.cy.pojo.GlbdefVO;
import nc.vo.cy.schoolarchives.SchoolBasicsHVO;
import nc.vo.cy.schoolarchives.pojo.SchoolCardRetuenPOJO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.teacherform.TeacherformHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;
import nccloud.itf.cy.cy.ISchoolbasicshvoMaintain;

/**
 * 标准单据审核的BP
 */
public class AceTeacherformHVOApproveBP {

	private IUAPQueryBS iQueryBS;

	/**
	 * 审核动作
	 * 
	 * @param vos
	 * @param script
	 * @return
	 * @throws Exception
	 */
	public AggTeacherformHVO[] approve(AggTeacherformHVO[] clientBills, AggTeacherformHVO[] originBills)
			throws Exception {
		for (AggTeacherformHVO clientBill : clientBills) {
			clientBill.getParentVO().setStatus(VOStatus.UPDATED);
		}
		BillUpdate<AggTeacherformHVO> update = new BillUpdate<AggTeacherformHVO>();
		AggTeacherformHVO[] returnVos = update.update(clientBills, originBills);

		// TeacherformHVO tvo = returnVos[0].getParentVO();
		// if (tvo.getApprovestatus() == 1) {
		// SchoolCardRetuenPOJO aggpojo;
		//
		// try {
		// SchoolBasicsHVO origintvo = (SchoolBasicsHVO)
		// getIQueryBS().retrieveByPK(SchoolBasicsHVO.class,
		// tvo.getDef1());
		// if (origintvo != null) {
		// String glbdef1 = CyCommonUtils.isEmpty(origintvo.getDef11()) ? "" :
		// origintvo.getDef11();// 大区
		// String glbdef2 = CyCommonUtils.isEmpty(origintvo.getProvince()) ? "" :
		// origintvo.getProvince();// 省份
		// String glbdef3 = CyCommonUtils.isEmpty(origintvo.getSaddress()) ? "" :
		// origintvo.getSaddress();// 地区
		// String glbdef4 = tvo.getDef1();
		// String glbdef5 = tvo.getAgeclass();// 年级
		// String glbdef6 = tvo.getDef2();// 班级
		// String glbdef7 = tvo.getClassnum() == null ? "" :
		// tvo.getClassnum().toString();// 班级人数
		// StringBuffer sql = new StringBuffer("");
		// sql.append("update hi_psndoc_glbdef1 set glbdef2 =
		// '").append(glbdef2).append("',glbdef3 = '").append(glbdef3);
		// if (tvo.getClassnum() != null) {
		// sql.append("',glbdef7 = '").append(glbdef7);
		// }
		// sql.append("',glbdef4 = '").append(glbdef4).append("',glbdef1 =
		// '").append(glbdef1).append("',glbdef5 = '");
		// sql.append(glbdef5).append("',glbdef6 = '").append(glbdef6);
		// if (tvo.getStartdate() != null && tvo.getEnddate() != null) {
		// sql.append("',begindate='").append(new
		// UFLiteralDate(tvo.getStartdate().toString()))
		// .append("',enddate='").append(new
		// UFLiteralDate(tvo.getEnddate().toString()));
		// }
		// sql.append("' where pk_psndoc = '");
		// sql.append(tvo.getTeacher()).append("'");
		// Integer rs = (int) new BaseDAO().executeQuery("select count(0) from
		// hi_psndoc_glbdef1 where dr = '0' and pk_psndoc = '"
		// + tvo.getTeacher() + "'", new ColumnProcessor());
		// if (rs != null && rs == 0) {
		// GlbdefVO def = new GlbdefVO();
		// def.setCreationtime(new UFDateTime());
		// def.setCreator(tvo.getCreator());
		// def.setPk_psndoc(tvo.getTeacher());
		// def.setDr(0);
		// def.setRecordnum(0);
		// def.setGlbdef1(glbdef1);
		// def.setGlbdef2(glbdef2);
		// def.setGlbdef3(glbdef3);
		// def.setGlbdef4(glbdef4);
		// if (tvo.getAgeclass() != null) {
		// def.setGlbdef5(glbdef5);
		// }
		// if (tvo.getStartdate() != null && tvo.getEnddate() != null) {
		// def.setBegindate(new UFLiteralDate(tvo.getStartdate().toString()));
		// def.setEnddate(new UFLiteralDate(tvo.getEnddate().toString()));
		// }
		// def.setGlbdef6(glbdef6);
		// if (tvo.getClassnum() != null) {
		// def.setGlbdef7(glbdef7);
		// }
		// new BaseDAO().insertVO(def);
		// }
		// new BaseDAO().executeUpdate(sql.toString());
		// }
		//
		//// Statement st = getConn().createStatement();
		//// rs=st.executeUpdate(sql.toString());
		// } catch (BusinessException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// }

		return returnVos;
	}

	private static ISchoolbasicshvoMaintain schoolservice;

	public static ISchoolbasicshvoMaintain getSchoolService() {
		if (schoolservice == null) {
			schoolservice = NCLocator.getInstance().lookup(ISchoolbasicshvoMaintain.class);
		}
		return schoolservice;
	}

	// public static Connection getConn() throws Exception {
	// Driver drivesr = new OracleDriver();
	// DriverManager.deregisterDriver(drivesr);
	//
	// // ���ز��Ի���(ncc0624)
	// // Connection connect =
	// // DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl",
	// // "ncc0624", "yj19921001");
	// // Զ�̲��Ի���(wccs)
	// Connection connect =
	// DriverManager.getConnection("jdbc:oracle:thin:@81.71.64.253:1521:orcl",
	// "NC2005",
	// "NC2005");
	//
	// return connect;
	// }

	public IUAPQueryBS getIQueryBS() {
		if (iQueryBS == null) {
			iQueryBS = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		}
		return iQueryBS;
	}

}
