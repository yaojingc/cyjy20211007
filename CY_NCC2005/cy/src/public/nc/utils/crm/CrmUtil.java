package nc.utils.crm;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.utils.commonConstant.AppletsParams;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.classfilecy.AggClasscyHVO;
import nc.vo.cy.classfilecy.ClasscyHVO;
import nc.vo.cy.daily.AggDailyHVO;
import nc.vo.cy.jiedaiapply.AggJiedaiapplyHVO;
import nc.vo.cy.plantravel.AggPlantravelHVO;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.schoolform.AggSchoolformHVO;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskfeedback.AggTaskfeedbackHVO;
import nc.vo.cy.teacherform.AggTeacherformHVO;
import nc.vo.cy.workplan.AggWorkplanHVO;
import nc.vo.cy.xuefeiapply.AggXuefeijmHVO;
import nc.vo.cy.yejiapply.AggYejiapplyHVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.billtype.BilltypeVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;
import nc.vo.sm.UserVO;

/**
 * 营销管理节点工具类
 * @author csh
 *
 */
public class CrmUtil {
	
	private static BaseDAO baseDao;
	
	private static ICrmService crmService;
	
	//如果需加入字母就改成0123456789abcdefg...........
	private static final String SYMBOLS = "0123456789"; // 纯数字
	private static final Random RANDOM = new SecureRandom();
	
	/**
	 * 词源班级生成不重复的6位随机数字，作为班级编码
	 * @return
	 * @throws BusinessException
	 */
	public static String generateClassNUM() throws BusinessException {
		String classNum = getSixstr(6);
		if(thisClassNumIsExist(classNum)){
			generateClassNUM();
		}
		return classNum;
	}
	
	/**
	 * 根据班级码查询当前是否有存在的数据
	 * @param classNum 班级编码
	 * @return
	 * @throws DAOException 
	 */
	private static boolean thisClassNumIsExist(String classNum) throws DAOException {
		ClasscyHVO classcyHVO = executeQueryVO("select * from cy_classcy where dr = 0 and bill_no='"+classNum+"'", ClasscyHVO.class);
		if(classcyHVO == null) {
			return false;//不存在
		}
		return true;//存在
	}
	
	/**
	 * 获取随机数字
	 * @param length 随机数字长度
	 * @return
	 */
	public static String getSixstr(int length) {
	    char[] nonceChars = new char[length];

	    for (int index = 0; index < nonceChars.length; ++index) {
	        nonceChars[index] = SYMBOLS.charAt(RANDOM.nextInt(SYMBOLS.length()));
	    }
	    return new String(nonceChars);
	}
	
	/**
	 * 新增单据，初始化参数
	 * 
	 * @param aggvo 工具类转译后的aggvo数据
	 * @param userMobile 用户手机号
	 */
	public static void addBillInitData(AbstractBill aggvo,String userMobile) throws BusinessException {
		String pk_group = AppletsParams.Pk_Group;
		String pk_org = AppletsParams.Pk_Org;
		OrgVO org = getCrmService().getOrgByPrimaryKey(pk_org);
		UserVO user = getCrmService().getUserInfoByUserMobile(userMobile);
		if(user == null) {
			throw new BusinessException("新增单据初始化单据信息，找不到用户手机号为"+userMobile+"的用户信息数据");
		}
		
//		InvocationInfoProxy.getInstance().setUserCode(user.getUser_code());
//		InvocationInfoProxy.getInstance().setUserId(user.getCuserid());
		
		aggvo.getParentVO().setAttributeValue("pk_group", pk_group);//集团
		aggvo.getParentVO().setAttributeValue("pk_org", pk_org);//组织
		aggvo.getParentVO().setAttributeValue("pk_org_v", org.getPk_vid());//组织多版本
		aggvo.getParentVO().setAttributeValue("dbilldate", new UFDate());//单据日期
		aggvo.getParentVO().setAttributeValue("billstatus", -1);//单据状态（默认自由）
		aggvo.getParentVO().setAttributeValue("approvestatus", -1);//审批状态（默认自由）
		aggvo.getParentVO().setAttributeValue("billmaker", user.getCuserid());//制单人（用户表主键）
		aggvo.getParentVO().setAttributeValue("creator", user.getCuserid());//创建人（用户表主键）
		aggvo.getParentVO().setAttributeValue("creationtime", new UFDateTime());//创建时间
		aggvo.getParentVO().setStatus(VOStatus.NEW);
		
		
		//单据类型填充
		setBillType(aggvo);
		
		//填充表体行号
		thisBodyRowNumFill(aggvo);
	}
	
	/**
	 * 修改单据，进行属性copy赋值
	 * @param queryAggvo 当前查询得到的的aggvo数据
	 * @param nowAggvo 工具类转译后的aggvo数据
	 * @param restPojoClazz 接口集合属性实体类型
	 * @param userMobile 用户手机号
	 */
	@SuppressWarnings({"all"})
	public static void editBillReplenishData(AbstractBill queryAggvo, AbstractBill nowAggvo, Class restPojoClazz, String userMobile) throws BusinessException {
		if(restPojoClazz.getAnnotation(ZdyClassTagAnnotation.class) == null) {
			throw new BusinessException("参数restPojoClazz必须为接口集合属性实体类型");
		}

		try {
			Field[] restPojoFields = restPojoClazz.getDeclaredFields();
			for(Field restEntityField : restPojoFields) {
				boolean isEntityFiled = restEntityField.getAnnotation(ZdyFieldTagAnnotation.class).isEntityFiled();//是否为实体字段
				if(isEntityFiled) {
					String fieldNccVO = restEntityField.getAnnotation(ZdyFieldTagAnnotation.class).nccVO();//字段实体属性全路径
					Type type = restEntityField.getGenericType();
					
					IVOMeta metaH = queryAggvo.getMetaData().getParent();
					IVOMeta[] metaB = queryAggvo.getMetaData().getChildren();
					Field[] restFields = null;
					List<String> listCopyFieldName = new ArrayList<>();//copy属性的字段
					IVOMeta voMeta = null;
					
					if(type instanceof ParameterizedType){
						//表体
						for (IVOMeta meta : metaB) {
							String NccClazzName = queryAggvo.getMetaData().getVOClass(meta).getName();
							if(NccClazzName.equals(fieldNccVO)) {
								ParameterizedType pt = (ParameterizedType) type;
								Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];//当当前数组实体属性类型
								restFields = actualTypeArgument.getDeclaredFields();
								voMeta = meta;
								break;
							}
						}
						
						if(restFields != null) {
							for(Field restField: restFields) {
								String name = restField.getName();
								if(name.equals("vostatus")) {
									//表体将vostatus替换成status
									listCopyFieldName.add("status");
								}else {
									listCopyFieldName.add(name);
								}
							}
							
							ISuperVO[] queryBodys = queryAggvo.getChildren(voMeta);//当前数据库中的表体数据
							ISuperVO[] nowBodys = nowAggvo.getChildren(voMeta);//工具类转换后的得到表体数据
							
							if(queryBodys==null || queryBodys.length==0) {
								//当前明细页签在数据库中不存在数据
								if(nowBodys!=null && nowBodys.length>0) {
									queryAggvo.setChildren(voMeta, nowBodys);//将转换的表体数据直接赋值给对应的表体
								}
							}else {
								//当前明细页签在数据库中存在数据,则开始比对替换已存在的数据和添加新的数据
								if(nowBodys!=null && nowBodys.length>0) {
									ISuperVO[] bodys = copyNccFieldSetValue(queryBodys, nowBodys, listCopyFieldName);
									queryAggvo.setChildren(voMeta, bodys);//将处理好的表体数据赋值给对应的表体
								}
							}
						}	
					}else {
						//表头
						String NccparentClazzName = queryAggvo.getMetaData().getVOClass(metaH).getName();
						if(fieldNccVO.equals(NccparentClazzName)) {
							Class restParentPojoClazz = Class.forName(type.getTypeName());
							restFields = restParentPojoClazz.getDeclaredFields();
							voMeta = metaH;
							
							for(Field restField: restFields) {
								String name = restField.getName();
								if(name.equals("billstatus")) {
									//单据状态属性不需要拷贝
									continue;
								}
								listCopyFieldName.add(name);
							}
							
							ISuperVO queryParent = (ISuperVO) queryAggvo.getParentVO();//当前数据库中的表头数据
							ISuperVO nowParent = (ISuperVO) nowAggvo.getParentVO();//工具类转换后的得到表头数据
							
							for(String fieldName : listCopyFieldName) {
								//从nowAggvo中取值，赋值到queryAggvo
								queryParent.setAttributeValue(fieldName, nowParent.getAttributeValue(fieldName));
							}
							queryParent.setStatus(VOStatus.UPDATED);
							//将处理好的表头数据赋值给表头
							queryAggvo.setParent(queryParent);
						}
					} 
				}
			}
			UserVO user = getCrmService().getUserInfoByUserMobile(userMobile);
			if(user == null) {
				throw new BusinessException("修改单据初始化单据信息，找不到用户手机号为"+userMobile+"的用户信息数据");
			}
			
//			InvocationInfoProxy.getInstance().setUserCode(user.getUser_code());
//			InvocationInfoProxy.getInstance().setUserId(user.getCuserid());
			
			queryAggvo.getParentVO().setAttributeValue("modifier", user.getCuserid());//修改人（用户表主键）
			queryAggvo.getParentVO().setAttributeValue("modifiedtime", new UFDateTime());//修改时间
			queryAggvo.getParentVO().setStatus(VOStatus.UPDATED);
			
			//单据类型填充
			setBillType(queryAggvo);
			
			//填充表体行号
			thisBodyRowNumFill(queryAggvo);
		} catch (Exception e) {
			throw new BusinessException(e);
		}
	}
	
	/**
	  * 表体数据行号进行填充
	 * @param aggvo
	 * @throws BusinessException
	 */
	private static void thisBodyRowNumFill(AbstractBill aggvo) throws BusinessException {
		IVOMeta[] metaB = aggvo.getMetaData().getChildren();
		for (IVOMeta meta : metaB) {
			ISuperVO[] nccBodyVO = aggvo.getChildren(meta);
			if(nccBodyVO!=null && nccBodyVO.length>0) {
				int maxRowNum = 0;//最大行号为0
				for(ISuperVO bodyVO:nccBodyVO) {
					Object value = bodyVO.getAttributeValue("rowno");
					if(value!=null && value.toString().trim().length()!=0 && bodyVO.getStatus()!=VOStatus.DELETED) {
						int rownum = Integer.valueOf(value.toString());
						if(rownum > maxRowNum) {
							maxRowNum = rownum;
						}
					}
				}
				
				for(ISuperVO bodyVO:nccBodyVO) {
					Object value = bodyVO.getAttributeValue("rowno");
					if((value==null || value.toString().trim().length()==0) && bodyVO.getStatus()!=VOStatus.DELETED) {
						//开始给行号赋值
						maxRowNum += 10;
						bodyVO.setAttributeValue("rowno", String.valueOf(maxRowNum));
					}
				}
				
			}
		}
		
	}
	
	/**
	 * 单据类型自动填充
	 * @param aggvo
	 * @throws BusinessException
	 */
	public static void setBillType(AbstractBill aggvo) throws BusinessException {
		String billTypeCode = null;
		boolean istranstype = false;//是否交易类型发布
		if(aggvo instanceof AggSchoolBasicsHVO) {
			//学校档案单据类型
			billTypeCode = "XXDA";
		}else if(aggvo instanceof AggTeacherformHVO) {
			//老师、讲师申请单单据类型
			istranstype = true;
			String teachertype = aggvo.getParentVO().getAttributeValue("teachertype").toString();
			if(teachertype.equals("0")) {
				//老师申请单
				billTypeCode = "LSSQ-Cxx-001";
			}
			if(teachertype.equals("1")) {
				//讲师申请单
				billTypeCode = "LSSQ-Cxx-002";
			}
		}else if(aggvo instanceof AggSchoolformHVO) {
			//学校发现
			billTypeCode = "XXSQ";
		}else if(aggvo instanceof AggClasscyHVO) {
			//词源班级
			billTypeCode = "CYBJ";
		}else if(aggvo instanceof AggWorkplanHVO) {
			//工作计划
			billTypeCode = "GZJH";
		}else if(aggvo instanceof AggDailyHVO) {
			//日报
			billTypeCode = "GZRB";
		}else if(aggvo instanceof AggTaskallocationHVO) {
			//任务分配
			billTypeCode = "RWFP";
		}else if(aggvo instanceof AggYejiapplyHVO) {
			//业绩申请
			billTypeCode = "YJSQ";
		}else if(aggvo instanceof AggXuefeijmHVO) {
			//学费减免
			billTypeCode = "XFJM";
		}else if(aggvo instanceof AggJiedaiapplyHVO) {
			//接待申请
			billTypeCode = "JDSQ";
		}else if(aggvo instanceof AggTaskfeedbackHVO) {
			//任务反馈
			billTypeCode = "RWFK";
		}else if(aggvo instanceof AggPlantravelHVO) {
			//行程安排
			billTypeCode = "XCAP";
		}
		
		if(StringUtils.isBlank(billTypeCode)) {
			throw new BusinessException("未匹配到单据类型，请检查");
		}
		
		BilltypeVO billtype = getCrmService().getBillTypeVOByCode(billTypeCode);
		if(istranstype) {
			//交易类型赋值
			if(aggvo.getParentVO().getAttributeValue("transtypecode")==null) {
				aggvo.getParentVO().setAttributeValue("transtypecode", billTypeCode);
			}
			if(aggvo.getParentVO().getAttributeValue("pk_transtype")==null) {
				aggvo.getParentVO().setAttributeValue("pk_transtype", billtype.getPk_billtypeid());
			}
			if(aggvo.getParentVO().getAttributeValue("billtype")==null) {
				aggvo.getParentVO().setAttributeValue("billtype", billTypeCode);
			}
			if(aggvo.getParentVO().getAttributeValue("pk_billtypeid")==null) {
				aggvo.getParentVO().setAttributeValue("pk_billtypeid", billtype.getPk_billtypeid());
			}
		}else {
			//单据类型赋值
			if(aggvo.getParentVO().getAttributeValue("billtype")==null) {
				aggvo.getParentVO().setAttributeValue("billtype", billTypeCode);
			}
			if(aggvo.getParentVO().getAttributeValue("pk_billtypeid")==null) {
				aggvo.getParentVO().setAttributeValue("pk_billtypeid", billtype.getPk_billtypeid());
			}
		}
	}
	
	/**
	 * 复制表体实体进行赋值
	 * 
	 * @param queryBodys 当前数据库中的表体数据
	 * @param nowBodys 工具类转换后的得到表体数据
	 * @param listCopyFieldName copy属性的字段
	 * @throws Exception 
	 */
	private static ISuperVO[] copyNccFieldSetValue(ISuperVO[] queryBodys,ISuperVO[] nowBodys,List<String> listCopyFieldName) throws Exception {
		List<ISuperVO> addBodys = new ArrayList<ISuperVO>();//增加的表体
		
		for(ISuperVO nowBodyVO : nowBodys) {
			String nowBodyPrimaryKey = nowBodyVO.getPrimaryKey();
			if(StringUtils.isBlank(nowBodyPrimaryKey)) {
				nowBodyVO.setStatus(VOStatus.NEW);
				addBodys.add(nowBodyVO);
				continue;
			}
			ISuperVO queryBodyVO = null;
			for(ISuperVO bodyVO : queryBodys) {
				String queryBodyPrimaryKey = bodyVO.getPrimaryKey();
				//处理更新的数据
				if(queryBodyPrimaryKey.equals(nowBodyPrimaryKey)) {
					queryBodyVO = bodyVO;
					break;
				}
			}
			
			if(queryBodyVO!=null) {
				for(String fieldName : listCopyFieldName) {
					if(fieldName.equals("status")) {
						//数据状态赋值
						queryBodyVO.setStatus(nowBodyVO.getStatus());
					}else {
						//从nowAggvo中取值，赋值到queryAggvo
						queryBodyVO.setAttributeValue(fieldName, nowBodyVO.getAttributeValue(fieldName));
					}
				}
			}
			
		}
		
		if(addBodys.size()>0) {
			//新增的数据和原来存在的数据合并一起
			for(ISuperVO queryBodyVO : queryBodys) {
				addBodys.add(queryBodyVO);
			}
			return addBodys.toArray(new ISuperVO[addBodys.size()]);
		}
		
		return queryBodys;
	}

	/**
	 * 校验单据能都修改
	 * @param pk
	 * @param aggClazz
	 * @return
	 */
	@SuppressWarnings({"all"})
	private static void checkBillIsEdit(String pk,Class nccAggVoClazz) throws BusinessException  {
		Object bill = Constructor.construct(nccAggVoClazz);
		
		AbstractBill aggVO = (AbstractBill) bill;
		IVOMeta metaH = aggVO.getMetaData().getParent();
		String tableName = metaH.getStatisticInfo().getTables()[0].toString();
		String tablePrimaryKey = metaH.getPrimaryAttribute().getName();
		
		String querySql = "select * from "+tableName+" where dr = 0 and "+tablePrimaryKey+" = "+pk;
		
		ISuperVO parentVO = executeQueryVO(querySql, aggVO.getMetaData().getVOClass(metaH));
		
		if(parentVO==null) {
			throw new BusinessException("未查询到主键为'"+pk+"'的"+metaH.getLabel()+"单据数据");
		}
		
		String billstatus = parentVO.getAttributeValue("approvestatus").toString();
		
		//-1自由态和3提交态的单据可以修改，其他单据状态均不能修改
		if(!billstatus.equals("-1") && !billstatus.equals("3")) {
			throw new BusinessException("单据已被审批，不能修改");
		}
		
	}
	
	
	
	/**
	  *  查询vo数据
	 * @param <T>
	 * @param sql SQL语句
	 * @param voClass 查询类型
	 * @return
	 * @throws DAOException
	 */
	@SuppressWarnings({"all"})
	private static <T> T executeQueryVO(String sql, Class<T> voClass) throws DAOException {
			return (T)getBaseDao().executeQuery(sql, new BeanProcessor(voClass));
	}
	
	/**
	 * 营销管理接口服务
	 * @return
	 */
	private static ICrmService getCrmService() {
	    if (crmService == null)
	    	crmService = NCLocator.getInstance().lookup(ICrmService.class);
	    return crmService;
	}
	
	/**
	 * 
	 * @return
	 */
	private static BaseDAO getBaseDao(){
		if (baseDao == null){
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

	/**
	 * 得到指定月度的第一天(firstDay)和最后一天(lastDay)
	 * @param year 年度
	 * @param month 月份
	 * @return
	 */
	public static Map<String,String> getMonthDay(int year,int month) {
		Map<String,String> map = new HashMap<String, String>();
		Calendar calendar = Calendar.getInstance();
	    calendar.set(year, month - 1, 1);
        String firstDayOfMonth = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());
        //System.out.println("第一天："+firstDayOfMonth);
        map.put("firstDay", firstDayOfMonth);
        
        calendar.set(year, month, 1);//这里先设置要获取月份的下月的第一天
        calendar.add(Calendar.DATE, -1);//这里将日期值减去一天，从而获取到要求的月份最后一天
        String lastDayOfMonth = new SimpleDateFormat( "yyyy-MM-dd ").format(calendar.getTime());
        //System.out.println("最后一天："+lastDayOfMonth);
        map.put("lastDay", lastDayOfMonth);
		return map;
	}
	
	/**
	  * 得到月首和月尾相对应的需要展示填充的日期
	 * @param year
	 * @param month
	 * @return
	 * @throws ParseException
	 */
	public static Map<String,List<String>> getWeekOfDate(int year,int month) {
		Map<String,List<String>> mapShowTime = new HashMap<String, List<String>>();
		Map<String,String> mapDate = getMonthDay(year, month);
		String firstDay = mapDate.get("firstDay");
		String lastDay = mapDate.get("lastDay");
		String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
		Calendar cal = Calendar.getInstance();
		try {
			Date firstTimeDate = new SimpleDateFormat( "yyyy-MM-dd ").parse(firstDay);
			cal.setTime(firstTimeDate);
		    int fw = cal.get(Calendar.DAY_OF_WEEK) - 1;
		    if (fw < 0) {
		    	fw = 0;
		    }
		    String firstDayWeek = weekDays[fw];
		    List<String> listDateByFirst = addData(firstTimeDate, true, firstDayWeek);
		    if(listDateByFirst!=null && listDateByFirst.size()>0) {
		    	mapShowTime.put("firstShowTime", listDateByFirst);
		    }

			Date lastTimeDate = new SimpleDateFormat( "yyyy-MM-dd ").parse(lastDay);
		    cal.setTime(lastTimeDate);
		    int lw = cal.get(Calendar.DAY_OF_WEEK) - 1;
		    if (lw < 0) {
		    	lw = 0;
		    }
		    String lastDayWeek = weekDays[lw];
		    
		    List<String> lastDateByFirst = addData(lastTimeDate, false, lastDayWeek);
		    if(lastDateByFirst!=null && lastDateByFirst.size()>0) {
		    	mapShowTime.put("lastShowTime", lastDateByFirst);
		    }
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return mapShowTime;
	}
	
	/**
	  * 任意日期和今天比较（如果日期大于等于今天则为true,反之false）
	 * @param data 日期
	 * @return
	 */
	public static boolean theDataCompareToNow(String data) {
		SimpleDateFormat sdf  =new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date today = new Date();
			return sdf.parse(data).getTime() >= today.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	/**
	 * 得到日期对应的前几天或后几天的日期值
	 * @param date 日期
	 * @param isfirstDay 是否月首
	 * @param week 星期
	 * @return
	 */
	public static List<String> addData(Date date,boolean isfirstDay,String week){
		int beforeDays = 0;//前几天
		int afterDays = 0;//后几天
		switch (week) {
			case "星期一":
				if(!isfirstDay) {
					//月尾
					afterDays = 6;
				}
				break;
			case "星期二":
				if(isfirstDay) {
					//月首
					beforeDays = 1;
				}else {
					//月尾
					afterDays = 5;
				}
				break;
			case "星期三":
				if(isfirstDay) {
					//月首
					beforeDays = 2;
				}else {
					//月尾
					afterDays = 4;
				}	
				break;
			case "星期四":
				if(isfirstDay) {
					//月首
					beforeDays = 3;
				}else {
					//月尾
					afterDays = 3;
				}	
				break;
			case "星期五":
				if(isfirstDay) {
					//月首
					beforeDays = 4;
				}else {
					//月尾
					afterDays = 2;
				}
				break;
			case "星期六":
				if(isfirstDay) {
					//月首
					beforeDays = 5;
				}else {
					//月尾
					afterDays = 1;
				}
				break;
			case "星期日":
				if(isfirstDay) {
					//月首
					beforeDays = 6;
				}
				break;
			default:
				break;
		}
		
		List<String> listData = new ArrayList<String>();
		
		//得到几天前的时间 
		if(beforeDays!=0) {
			for(int i = 1; i<=beforeDays;i++) {
				String dayTime = getDateBefore(date, i);
				listData.add(dayTime);
			}
		}
		
		//得到几天后的时间 
		if(afterDays!=0) {
			for(int i = 1; i<=afterDays;i++) {
				String dayTime = getDateAfter(date, i);
				listData.add(dayTime);
			}
		}
		
		return listData;
	}
	
	/** 
         * 得到几天前的时间 
     * @param d 日期
     * @param day 第几天
     * @return 
     */ 
    private static String getDateBefore(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) - day);  
        return  new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());  
    }  
 
    /** 
         * 得到几天后的时间 
     * @param d 日期
     * @param day 第几天
     * @return 
     */ 
    public static String getDateAfter(Date d, int day) {  
        Calendar now = Calendar.getInstance();  
        now.setTime(d);  
        now.set(Calendar.DATE, now.get(Calendar.DATE) + day);  
        return new SimpleDateFormat("yyyy-MM-dd").format(now.getTime());  
    }
    
    /**
         * 获取指定日期下个月的第一天
     * @param dateStr 日期
     * @return
    */
    public static String getFirstDayOfNextMonth(String dateStr){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
		try {
		    Date date = sdf.parse(dateStr);
		    Calendar calendar = Calendar.getInstance();
		    calendar.setTime(date);
		    calendar.set(Calendar.DAY_OF_MONTH,1);
		    calendar.add(Calendar.MONTH, 1);
		    return sdf.format(calendar.getTime());
	    } catch (ParseException e) {
	    	e.printStackTrace();
	    }
	    return null;
    }
}
