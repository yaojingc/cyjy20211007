package nc.utils.crm.itf;

import java.util.List;
import java.util.Map;

import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.teach.course.CourseDeatil;
import nc.api.cy.rest.entity.teach.course.CourseStudentSave;
import nc.api.cy.rest.entity.teach.course.StudentInfo;
import nc.api.cy.rest.entity.teach.course.TeacherCourse;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.sm.UserVO;

/**
 * 营销管理对接接口对应NCC单据接口服务
 * @author csh
 *
 */
public interface ICrmService {
	
	/**
	  * 根据用户手机号查询用户信息
	 * 
	 * @param  userMobile 用户手机号
	 * @return
	 * @throws BusinessException
	 */
	public UserVO getUserInfoByUserMobile(String userMobile) throws BusinessException;
	
	/**
	 *   根据组织主键查询组织信息
	 * @param pk_org 组织主键
	 * @return
	 * @throws BusinessException
	 */
	public OrgVO getOrgByPrimaryKey(String pk_org) throws BusinessException;
	
	/**
	 *  根据单据类型/交易类型编码，查询单据类型/交易类型信息
	 * @param billTypeCode
	 * @return
	 * @throws BusinessException
	 */
	public BilltypeVO getBillTypeVOByCode(String billTypeCode) throws BusinessException;
	
	/**
	 *  根据条件分页查询数据
	 * @param condition 查询条件
	 * @param queryPage 分页查询参数
	 * @param NccAggClazz NccAggvo参数类型
	 * @param RestHeadClazz 接口头部实体参数类型
	 * @return
	 * @throws BusinessException
	 */
	public ResponseList queryListPage(String condition, QueryPage queryPage, Class<? extends AbstractBill> NccAggClazz, Class RestHeadClazz) throws BusinessException;
	
	/**
	 * 根据主键查询单据详情
	 * @param primaryKey 单据主键
	 * @return
	 * @throws BusinessException
	 */
	public AbstractBill queryDeatil(String primaryKey,Class<? extends AbstractBill> NccAggClazz) throws BusinessException;
	
	/**
	  * 对NccAggvo数据执行保存操作
	 * 
	 * @param billType 单据类型
	 * @param aggvo NccAggVO数据
	 * @param isSaveCommit 是否进行保存提交。true:保存提交，false:只保存不提交
	 * @return
	 * @throws BusinessException
	 */
	public Object pfSaveBusiAction(String billType, AbstractBill aggvo, boolean isSaveCommit) throws BusinessException;
	
	/**
	 * 对NccAggvo数据执行操作
	 * <p>标准按钮编码示例：保存SAVEBASE、删除DELETE、提交SAVE、收回UNSAVEBILL、审批APPROVE 、取消审批UNAPPROVE</p>
	 * 
	 * @param billType 单据类型/交易类型
	 * @param actionCode 动作脚本编码
	 * @param aggvo NccAggVO数据
	 * @return
	 * @throws BusinessException
	 */
	public Object pfBusiAction(String billType,String actionCode, AbstractBill aggvo) throws BusinessException;
	
	/**
	 * 查询当前用户指定月份的课表信息
	 * @param user 用户信息
	 * @param year 年度
	 * @param month 月份
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, TeacherCourse> getTeacherCourse(UserVO user,String year,String month) throws BusinessException;
	
	/**
	 * 通过主键查询当前该节课的上课详细情况
	 * @param pk
	 * @return
	 * @throws BusinessException
	 */
	public CourseDeatil getCourseDeatilByPk(String pk) throws BusinessException;
	
	/**
	 * 更改学生考勤信息
	 * @param courseStudentSave
	 * @throws BusinessException
	 */
	public Map<String,Object> updateStudentAttence(CourseStudentSave courseStudentSave) throws BusinessException;
	
	/**
	 * 课堂添加上课学生
	 * @param courseStudentSave
	 * @throws BusinessException
	 */
	public void courseAddStudent(CourseStudentSave courseStudentSave) throws BusinessException;
	
	/**
	  * 根据词源班级主键查询班级下的所有学生信息
	 * @param listClasscyId
	 * @return
	 * @throws BusinessException
	 */
	public List<StudentInfo> queryStudentByClasscy(List<String> listClasscyId) throws BusinessException;
	
}
