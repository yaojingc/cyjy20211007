package nc.api.cy.rest.entity.crm.basepojo;

/**
 * 营销管理专用：翻译公共语句
 * <p>whereReplaceTag: 查询条件标记用于替换成对应的查询数据</P>
 * <p>defcodeReplaceTag: 自定义档案编码标记用于替换对应的自定义档案编码</P>
 * @author csh
 *
 */
public class TranslateSql {
	
	/**
	 * 自定义档案信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_DEFDOC = "select name as label,code as code,pk_defdoc as value from bd_defdoc where pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code='defcodeReplaceTag') and pk_defdoc = 'whereReplaceTag'";

	/**
	 * 自定义档案信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_DEFDOCEXP = "";
	
	/**
	  * 学校信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_SCHOOL = "select sname as label,bill_no as code,pk_school as value from cy_schoolBasics where pk_school = 'whereReplaceTag'";
	
	/**
	 * 学校信息拓展查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.School实体</p>
	 */
	public static final String QUERYSQL_SCHOOLEXP = "select s.sname as label,s.bill_no as code,s.pk_school as value,s.saddress as schooladdress,d.name as schoolregion from cy_schoolBasics s left join bd_defdoc d on  d.pk_defdoc=s.def11 where s.pk_school = 'whereReplaceTag'";
	
	/**
	 * 班级信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_CLASSCY = "select clasname as label,bill_no as code,pk_classcy as value from cy_classcy where pk_classcy = 'whereReplaceTag'";
	
	/**
	 * 班级信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_CLASSCYEXP = "";
	
	/**
	 * 老师信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_TEACHER = "select intro as label,bill_no as code,pk_teachfile as value from cy_teacherfile where pk_teachfile = 'whereReplaceTag'";
	
	/**
	 * 老师信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_TEACHEREXP = "";
	
	/**
	 * 学生信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_STUDENT = "select sname as label,bill_no as code,pk_student as value from cy_student where pk_student = 'whereReplaceTag'";
	
	/**
	 * 学生信息拓展查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.Student实体</p>
	 */
	public static final String QUERYSQL_STUDENTEXP = "select sname as label,bill_no as code,pk_student as value,idcard as idcard from cy_student where pk_student = 'whereReplaceTag'";
	
	/**
	  * 人员信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_PERSONDOC = "select name as label,code as code,pk_psndoc as value from bd_psndoc where pk_psndoc = 'whereReplaceTag'";
	
	/**
	 * 人员信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_PERSONDOCEXP = "";
	
	/**
	  * 组织信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_ORG = "select name as label,code as code,pk_org as value from org_orgs where pk_org = 'whereReplaceTag'";
	
	/**
	 * 组织信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_ORGEXP = "";
	
	/**
	  * 部门信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_DEPT = "select name as label,code as code,pk_dept as value from org_dept where pk_dept = 'whereReplaceTag'";
	
	/**
	 * 部门信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_DEPTEXP = "";
	
	/**
	  * 行政区划信息查询
	 * <p>对应nc.api.cy.rest.entity.crm.refpojo.RefPOJO实体</p>
	 */
	public static final String QUERYSQL_REGION = "select name as label,code as code,pk_region as value from bd_region where pk_region = 'whereReplaceTag'";
	
	/**
	 * 行政区划信息拓展查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_REGIONEXP = "";
	
	/**
	 * 制单人信息查询
	 * <p>对应xxxxxxxxx实体</p>
	 */
	public static final String QUERYSQL_BILLMAKER = "select user_name as label,user_code as code,cuserid as value from sm_user where cuserid = 'whereReplaceTag'";
	
	
	
}
