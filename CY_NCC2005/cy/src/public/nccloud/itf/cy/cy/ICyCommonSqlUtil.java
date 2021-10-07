package nccloud.itf.cy.cy;

import java.util.List;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.vo.bd.banktype.BankTypeVO;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.org.DeptVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

public interface ICyCommonSqlUtil {

	/**
	 * 根据单据主键查询任意的整体aggVO
	 * 
	 * @param pk_bills
	 * @return
	 * @throws BusinessException
	 */
	public AbstractBill[] queryBillsByCondition(Class<? extends AbstractBill> voClass, String fieldname,
			String[] pk_bills) throws BusinessException;

	/**
	 * 查询表中符合条件的记录总数
	 */
	public PagingPOJO queryTablePageInfo(TableCountQueryVO tableCountQueryVO) throws BusinessException;

	/**
	 * 查询最新编码
	 */
	public String queryNewCode(String typecode) throws BusinessException;

	/**
	 * 标准自定义档案查询，
	 */
	public String queryDefdocPk(String defdoc, String defdoclist) throws BusinessException;

	public String queryDefdocName(String defdoc, String defdoclist) throws BusinessException;

	public String queryDefdocByField(String field1, String field2, String value, String defdoclistname)
			throws BusinessException;

	/**
	 * 通过学生主键查询是否存在学费收款合同
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public Boolean queryContractByStudent(String id) throws BusinessException;

	/**
	 * 通过客户主键查询客户编码
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public String queryCustCodeById(String id) throws BusinessException;

	/**
	 * 通过组织主键查询组织编码
	 * 
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	public String queryOrgCodeById(String id) throws BusinessException;

	/**
	 * 通过人员信息查询部门信息
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public DeptVO queryDeptByPsnInfo(String pk_psndoc) throws BusinessException;

	/**
	 * 通过条件查询某个表
	 * 
	 * @return
	 * @throws BusinessException
	 */
	public SuperVO getVO(String tab, String where, Class<? extends ISuperVO> clazz) throws BusinessException;

	/**
	 * 通过人员编码查询人员主键
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public String queryPsnIdByCode(String code) throws BusinessException;

	/**
	 * 通过交易类型主键查询交易类型编码
	 * 
	 * @param pk_transtype
	 * @return
	 * @throws BusinessException
	 */
	public String queryCodeByTransId(String pk_transtype) throws BusinessException;

	/**
	 * 回写学费首款合同明细单号
	 * 
	 * @param pk_detail, payNo
	 * @return
	 * @throws BusinessException
	 */
	public Boolean updateContract(String pk_detail, String payNo) throws BusinessException;

	/**
	 * 通过用户主键查询人员主键
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public String queryPsnIdByUserId(String id) throws BusinessException;

	/**
	 * 通过用户主键查询所属角色组
	 * 
	 * @param userid
	 * @return
	 * @throws BusinessException
	 */
	public List<String> queryRoleCodeByUserId(String userid) throws BusinessException;

	/**
	 * 通过用户主键查询所属角色组
	 * 
	 * @param userid,rolegroup
	 * @return
	 * @throws BusinessException
	 */
	public Boolean queryRoleGroupCodeByUserId(String userid, String rolegroup) throws BusinessException;

	/**
	 * 通过人员主键查询关联学校
	 * 
	 * @param psnid
	 * @return
	 * @throws BusinessException
	 */
	public List<String> querySchoolByPsndoc(String psnid, String wheresql) throws BusinessException;

	/**
	 * 通过学生主键查询身份证号
	 * 
	 * @param sid
	 * @return
	 * @throws BusinessException
	 */
	public String queryIdCardByStudent(String sid) throws BusinessException;

	/**
	 * 通过班级编码查询班级主键
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public String queryClasscyIdByCycode(String code) throws BusinessException;

	/**
	 * 回写学费减免单核销装填
	 * 
	 * @param pk_detail, payNo
	 * @return
	 * @throws BusinessException
	 */
	public Boolean updateXfjmStatus(String pk_xfjm) throws BusinessException;
	
	
	
	/**
	 * 查询银行档案
	 * @return
	 * @throws BusinessException
	 */
	public BankTypeVO[] queryAllBankType(String field,String value) throws BusinessException;
	
	
	
	

}