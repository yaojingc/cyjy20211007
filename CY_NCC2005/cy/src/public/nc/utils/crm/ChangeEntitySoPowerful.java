package nc.utils.crm;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import nc.api.cy.rest.annotation.ZdyClassTagAnnotation;
import nc.api.cy.rest.annotation.ZdyFieldTagAnnotation;
import nc.api.cy.rest.annotation.ZdyTranslateAnnotation;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.basepojo.UserInfoByDiworkPOJO;
import nc.api.cy.rest.entity.crm.itf.ParentPojoTagItf;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.School;
import nc.api.cy.rest.entity.crm.refpojo.Student;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.IVOMeta;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.pub.Constructor;

/**
  * 营销管理接口对接专用：POJO->AggVO(正向转换)、AggVO-POJO(反向转换)
 * @author csh
 *
 */
public class ChangeEntitySoPowerful {
	
	private static BaseDAO baseDao;
	
	/**
	 * 在实际业务中是为否多实体，目前NCC中的所有单据，均发布成了主子表形式，单表业务的表单也发布成了主子表，故实际业务只为单表的就只赋值主VO
	 */
	private static boolean isAgg = false;
	
	/**
	 * isAgg为true时该项忽略无效，为false时采用，主要是为了用于实际业务为单表的实体进行转换成主表vo来使用的
	 */
	private static String nccVO;
	
	/**
	 * 转换后的NccAggVo实体
	 */
	private static AbstractBill aggvo;
	
	/**
	 * 转换后的用户信息实体
	 */
	private static UserInfoByDiworkPOJO userInfo;
	
	/**
	 * 转换后的详情响应实体
	 */
	private static Object restObj;
	
	/**
	 * 转换后的列表响应实体
	 */
	private static ResponseList pageList;
	
	/**
	 *  接口实体转NccAggvo实体，获得转换后的Aggvo数据
	 * @return
	 */
	public AbstractBill getAggvo() {
		return aggvo;
	}
	
	/**
	 * 接口实体转NccAggvo实体，获得当前数据用户信息
	 * @return
	 */
	public UserInfoByDiworkPOJO getUserInfo() {
		return userInfo;
	}
	
	/**
	 * NccAggvo实体转接口实体，获得转换后的接口实体数据
	 * @return
	 */
	public Object getRestObj() {
		return restObj;
	}
	
	/**
	 * 查询分页得到的数据信息
	 * @return
	 */
	public ResponseList getPageList() {
		return pageList;
	}

	public ChangeEntitySoPowerful() {
		
	}

	/**
	 * <p>注意：NCC一律是AggVO，,别整别的VO出来，搞事情</p>
	 * 
	 * @param sourceData 源头数据
	 * @param targetVoClazz 转换目标实体类型
	 * @param isForward 是否正向转换（true:接口实体转NCC实体、false:NCC实体转接口实体）
	 */
	@SuppressWarnings({"all"})
	public ChangeEntitySoPowerful(Object sourceData, Class targetVoClazz, boolean isForward) throws BusinessException {
		try {
			if(sourceData instanceof Object[] || sourceData instanceof List) {
				throw new BusinessException("ChangeEntitySoPowerful转换工具类报错异常：构造方法中，sourceData参数，不能为数组类型对象");
			}
			if(isForward) {
				//接口实体转NCC实体
				restPojoChangeToNccAggVO(sourceData, targetVoClazz);
			}else {
				//NCC实体转接口实体
				restNccAggVOChangeToPojo(sourceData, targetVoClazz);
			}
		} catch (Exception e) {
			throw new BusinessException("ChangeEntitySoPowerful转换工具类报错异常："+e.getMessage());
		}
	}
	
	/**
	 * 分页查询数据包装
	 * @param targetVoClazz 转换目标实体类型(接口实体的表头POJO类型)
	 * @param listAggvo 分页数据
	 * @param total 总数量
	 * @param totalPages 总页数
	 * @param currentPage 当前页数
	 * @param pageSize 页数大小
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings({"all"})
	public ChangeEntitySoPowerful pageListPage(Class targetVoClazz, List<? extends AbstractBill> listAggvo, int total, int totalPages, int currentPage, int pageSize) throws BusinessException {
		try {
			Object restPojo = Constructor.construct(targetVoClazz);
			if(restPojo instanceof ParentPojoTagItf) {
				ResponseList responseList = new ResponseList();
				if(listAggvo==null || listAggvo.size()==0) {
					//没有内容数据，一律为空
					responseList.setCurrentPage(0);
					responseList.setPageSize(0);
					responseList.setTotal(0);
					responseList.setTotalPages(0);
					this.pageList = responseList;
				}else {
					responseList.setCurrentPage(currentPage);
					responseList.setPageSize(pageSize);
					responseList.setTotal(total);
					responseList.setTotalPages(totalPages);
					
					List<Object> listContent = new ArrayList<Object>();
					Field[] restPojoFields = targetVoClazz.getDeclaredFields();
					for(AbstractBill nccAggvo : listAggvo) {
						restPojo = Constructor.construct(targetVoClazz);
						ISuperVO nccPraentObject = (ISuperVO)nccAggvo.getParentVO();
						
						for(Field restField : restPojoFields) {
		    				String name = restField.getName();
		    				restField.setAccessible(true);
		    				
		    				Object nccFieldValue = reflexToGetValue(nccPraentObject, name);
		    				if(nccFieldValue == null) {
		    					continue;
		    				}
		    				reflexToRestPojoSetValue(restPojo, restField, nccFieldValue);
		            	}
						listContent.add(restPojo);
					}
					
					responseList.setContent(listContent);
					this.pageList = responseList;
				}
			}else {
				throw new BusinessException("ChangeEntitySoPowerful转换工具类报错异常：分页查询数据包装方法中，targetVoClazz参数，必须为头部实体类型");
			}
			return this;
		} catch (Exception e) {
			throw new BusinessException("ChangeEntitySoPowerful转换工具类包装分页数据报错异常："+e.getMessage());
		}
	}

	/**
	  * 接口实体转换成NccAggVO实体属性
	 * @param restObj 接口实体属性参数
	 * @param nccAggVoClazz NccAggvo类型
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings({"all"})
	private void restPojoChangeToNccAggVO(Object restObj, Class nccAggVoClazz) throws Exception {
			List<Field> fieldList = new ArrayList<>() ;
			Class restClass = restObj.getClass();
	        while (restClass != null) {
	        	if(restClass.getAnnotation(ZdyClassTagAnnotation.class) != null) {
	        		ZdyClassTagAnnotation zdyClassTagAnnotation = (ZdyClassTagAnnotation) restClass.getAnnotation(ZdyClassTagAnnotation.class);
	            	isAgg = zdyClassTagAnnotation.isMultiEntity();
	            	nccVO = zdyClassTagAnnotation.nccVO();
	        	}
	            fieldList.addAll(Arrays.asList(restClass.getDeclaredFields()));
	            restClass = restClass.getSuperclass();//获得父级
	        }
	        
	        //如果实际业务是单表，nccVO为空则也无法转换
	        if(isAgg==false && StringUtils.isBlank(nccVO)) {
	        	return;
	        }
	        
	        Object bill = Constructor.construct(nccAggVoClazz);
			if (bill instanceof AbstractBill) {
				AbstractBill aggVO = (AbstractBill) bill;
				 if(isAgg) {
					 //实际业务为多实体vo
					 for (Field field : fieldList) {
				        String name = field.getName();
				        field.setAccessible(true);
				        
				        Object obj = reflexToGetValue(restObj, name);
				        if(obj == null) {
				        	//实体字段为空跳过
				        	continue;
				        }
				        
				        if(name.equals("userinfo")) {
				        	//用户信息
				        	userInfo = (UserInfoByDiworkPOJO) obj;
				        	continue;
				        }
				        
				        boolean isEntityFiled = field.getAnnotation(ZdyFieldTagAnnotation.class).isEntityFiled();//是否为实体字段
				        String fieldNccVO = field.getAnnotation(ZdyFieldTagAnnotation.class).nccVO();//字段实体属性全路径
				        
				        if(isEntityFiled && !StringUtils.isBlank(fieldNccVO)) {
				        	Class NccVoClass = Class.forName(fieldNccVO);
				        	
				        	List<ISuperVO> nccListVo= fieldChangeNccVOByMs(field, obj, restObj, NccVoClass);
				        	if(nccListVo.size()>0) {
				        		if (List.class.isAssignableFrom(field.getType())) {
						        	//数组实体(NCC子页签实体)
						        	//IVOMeta[] metaB = aggVO.getMetaData().getChildren();
						        	aggVO.setChildren(NccVoClass, nccListVo.toArray(new ISuperVO[nccListVo.size()]));
						        }else {
						        	//单实体(NCC主表实体)
						        	//IVOMeta metaH = aggVO.getMetaData().getParent();
						        	aggVO.setParent(nccListVo.get(0));
						        }
				        	}
				        }
				     }
			     }else {
			    	 //实际业务单实体vo，只处理当前vo数据转换为aggvo中的表头vo
			    	 Class NccPraentVoClass = Class.forName(nccVO);
			    	 ISuperVO nccPraentObject = (ISuperVO) Constructor.construct(NccPraentVoClass);
			    	 
			    	 for (Field field : fieldList) {
					        String name = field.getName();
					        field.setAccessible(true);
					        
					        Object fieldValue = reflexToGetValue(restObj, name);
					        if(fieldValue == null) {
					        	//实体字段为空跳过
					        	continue;
					        }
					        
					        if(name.equals("userinfo")) {
					        	//用户信息
					        	userInfo = (UserInfoByDiworkPOJO) fieldValue;
					        	continue;
					        }
					        
					        String value = null;
						    if(fieldValue!=null) {
						    	if(fieldValue instanceof RefPOJO || fieldValue instanceof School || fieldValue instanceof Student) {
							    	RefPOJO refPOJO = (RefPOJO) fieldValue;
							    	value = refPOJO.getValue();
							    }else if(fieldValue instanceof Integer) {
							    	value =  fieldValue.toString();
							    }else {
							    	value = fieldValue.toString();
							    }
						    	
						    	if(StringUtils.isBlank(value)) {
						    		//throw new Exception("字段"+name+"存在数据，未能成功转换，请检查！");
						    		Logger.error("字段"+name+"存在数据，未能成功转换，请检查！");
						    		continue;
						    	}
						    }
					        
					        reflexToNccVoSetValue(nccPraentObject, name, value);
			    	 }
			    	 aggVO.setParent(nccPraentObject);
			     }
				 
				 if(aggVO.getPrimaryKey() == null) {
					 aggVO.getParentVO().setStatus(VOStatus.NEW);
				 }else {
					 aggVO.getParentVO().setStatus(VOStatus.UPDATED);
				 }
				 
				 aggvo = aggVO;
			}
	}
	
	/**
	  * 实际业务为主子多实体的数据转换成NCC的vo组装(包含表头表体数据转换赋值组装)
	 * @param field 接口实体字段
	 * @param fieldValue 接口实体字段值
	 * @param restObj 接口实体数据
	 * @param NccVoClass Ncc实体类型
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings({"all"})
	private static List<ISuperVO> fieldChangeNccVOByMs(Field field, Object fieldValue, Object restObj,Class NccVoClass) throws Exception{
    	Type type=field.getGenericType();
    	
    	List<ISuperVO> listNccBody = new ArrayList<ISuperVO>();
        if(type instanceof ParameterizedType){
        	//当前字段是否为参数化类型。 如Collection<String>就是一个参数化类型。只有表体数组页签才能是这样的格式。
    		if (fieldValue instanceof ArrayList<?>) {
    	        for (Object restBodyObject : (List<?>) fieldValue) {
    	        	ISuperVO nccBodyObject = (ISuperVO) Constructor.construct(NccVoClass);
    	        	
    	        	restFieldToNccVoFieldSetValue(restBodyObject, nccBodyObject);
    	        	
    	        	listNccBody.add(nccBodyObject);
    	        }
    	    }
        }else {
        	//当前字段不为参数化类型，说明是表头数据
        	ISuperVO nccHeadObject = (ISuperVO) Constructor.construct(NccVoClass);
        	restFieldToNccVoFieldSetValue(fieldValue, nccHeadObject);
        	listNccBody.add(nccHeadObject);
        }
        return listNccBody;
	}
	
	/**
	 * 接口字段赋值给NCC字段
	 * @param restObject 接口POJO实体
	 * @param nccObject NccVO实体
	 */
	@SuppressWarnings({"all"})
	private static void restFieldToNccVoFieldSetValue(Object restObject,Object nccObject) throws Exception {
		Class restPojoClazz = restObject.getClass();
		Field[] restBodyFieldList = restPojoClazz.getDeclaredFields();
		
		for (Field restBodyField : restBodyFieldList) {
		    String name = restBodyField.getName();
		    restBodyField.setAccessible(true);
		    
		    //接口接收到的表体页签数据中的目前字段存在为这几种类型，参照类型、String类型、Integer类型
		    Object restBodyFieldValue = reflexToGetValue(restObject, name);
		    if(restBodyFieldValue == null) {
		    	//数据值为空跳过
		    	continue;
		    }
		    
		    String value = null;
	    	if(restBodyFieldValue instanceof RefPOJO || restBodyFieldValue instanceof School || restBodyFieldValue instanceof Student) {
		    	RefPOJO refPOJO = (RefPOJO) restBodyFieldValue;
		    	value = refPOJO.getValue();
		    }else if(restBodyFieldValue instanceof Integer) {
		    	value =  restBodyFieldValue.toString();
		    }else {
		    	value = restBodyFieldValue.toString();
		    }
	    	
	        if(StringUtils.isBlank(value)) {
	        	//存在如下情况字段转译不了
	        	//当前字段是参照,但是接口传输过来的参照数据格式有误，导致参照字段没有value值, 故此跳过
	        	//throw new Exception("字段"+name+"存在数据，未能成功转换，请检查！");
	        	Logger.error("字段"+name+"存在数据，未能成功转换，请检查！");
	        	continue;
	        }
		    
		    //处理行数据状态
		    if(name.equals("vostatus")) {
		    	name = "status";
		    	//1更新行
		    	if(value.equals("1")) {
		    		value = String.valueOf(VOStatus.UPDATED);
		    	}
		    	//2新增行
		    	if(value.equals("2")) {
		    		value = String.valueOf(VOStatus.NEW);
		    	}
		    	//3删除行
		    	if(value.equals("3")) {
		    		value = String.valueOf(VOStatus.DELETED);
		    	}
	        }
		    
		    reflexToNccVoSetValue(nccObject, name, value);
		 }
		
	}

	/**
         * 通过反射得到字段值
     * @param obj  实体数据
     * @param fieldName 字段名
     * @return
     */
	@SuppressWarnings({"all"})
    private static Object reflexToGetValue(Object obj,String fieldName) throws Exception {
        @SuppressWarnings("rawtypes")
        Class object = obj.getClass();

        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(fieldName, object);
        Class fieldClass= proDescriptor.getPropertyType();
        
        // 获得用于读取属性值的方法
        Method readMethod = proDescriptor.getReadMethod();
        // 读取属性值
        Object invoke = readMethod.invoke(obj);
        
        if(invoke!=null && (UFDate.class.isAssignableFrom(fieldClass) || UFDateTime.class.isAssignableFrom(fieldClass))) {
        	//针对NCC为UFDate和UFDateTime类型字段取值，为了符合前端要求，将该类型字符截取为YYYY-MM-DD
        	return invoke.toString().substring(0, 10);
        }

        return invoke;
    }
    
    /**
         * 通过反射给NccVO属性赋值
     * @param obj NCC实体数据
     * @param fieldName NCC字段名
     * @param value 值
     * @return
     */
	@SuppressWarnings({"all"})
    public static void reflexToNccVoSetValue(Object obj,String fieldName,String value) throws Exception {
        Class object = obj.getClass();
        
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(fieldName, object);
        
        Object valObj = null;
        try {
        	if(!StringUtils.isBlank(value)) {
	        	Class nccFieldClass= proDescriptor.getPropertyType();
	            if(String.class.isAssignableFrom(nccFieldClass)) {
	            	valObj = value;
	            }else if(Integer.class.isAssignableFrom(nccFieldClass) || int.class.isAssignableFrom(nccFieldClass)) {
	            	valObj = Integer.valueOf(value);
	            }else if(UFDouble.class.isAssignableFrom(nccFieldClass)) {
	            	valObj = new UFDouble(value);
	            }else if(UFBoolean.class.isAssignableFrom(nccFieldClass)) {
	            	valObj = new UFBoolean(value);
	            }else if(UFDate.class.isAssignableFrom(nccFieldClass)) {
	            	valObj = new UFDate(value);
	            }else if(UFDateTime.class.isAssignableFrom(nccFieldClass)) {
	            	if(value.length() == 10) {
	            		//2021-07-28
	            		valObj = new UFDateTime(value+" 00:00:00");
	            	}
	            	if(value.length() == 19) {
	            		//2021-07-28 00:00:00
	            		valObj = new UFDateTime(value);
	            	}
	            }
        	}
		} catch (Exception e) {
			throw new Exception("字段"+fieldName+"转换错误："+e.getMessage());
		}
        
        if(!StringUtils.isBlank(value) && valObj==null) {
        	//存在当前字段有值，但是当前值与需要转换成对应的类型不匹配，导致异常后，值为空，故此种情况应该跳过
        	//throw new Exception("字段"+fieldName+"存在数据，未能成功转换，请检查！");
        	Logger.error("字段"+fieldName+"存在数据，未能成功转换，请检查！");
        	return;
        }
        
        // 获得用于读取属性值的方法
        Method writeMethod = proDescriptor.getWriteMethod();
        // 对属性进行赋值
        writeMethod.invoke(obj,valObj);
    }

	/**
	 * NccAggVO实体转换为接口实体
	 * @param nccAggVo NccAggVO实体属性参数
	 * @param restPojoClazz 接口实体属性类型
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({"all"})
	private void restNccAggVOChangeToPojo(Object nccAggVo, Class restPojoClazz) throws Exception {
		if(nccAggVo instanceof AbstractBill && restPojoClazz.getAnnotation(ZdyClassTagAnnotation.class) != null) {
        	ZdyClassTagAnnotation zdyClassTagAnnotation = (ZdyClassTagAnnotation) restPojoClazz.getAnnotation(ZdyClassTagAnnotation.class);
            isAgg = zdyClassTagAnnotation.isMultiEntity();
            nccVO = zdyClassTagAnnotation.nccVO();
            
            Object restPojo = Constructor.construct(restPojoClazz);
            Field[] restPojoFields = restPojoClazz.getDeclaredFields();
            if(isAgg) {
            	//实际业务为多实体vo
            	for(Field restPojoField : restPojoFields) {
            		String name = restPojoField.getName();
            		restPojoField.setAccessible(true);
            		
            		boolean isEntityFiled = restPojoField.getAnnotation(ZdyFieldTagAnnotation.class).isEntityFiled();//是否为实体字段
            		
            		if(isEntityFiled) {
            			List<Object> listPojo = changePojoSetValueByNccAggVO((AbstractBill)nccAggVo, restPojoField);
    			        if(listPojo.size() > 0) {
    			        	if(List.class.isAssignableFrom(restPojoField.getType())) {
    	            			//明细页签数据实体字段
    			        		reflexToRestPojoSetValue(restPojo, restPojoField, listPojo);
    	            		}else {
    	            			//主表数据实体字段
    	            			Object obj = listPojo.get(0);
    	            			reflexToRestPojoSetValue(restPojo, restPojoField, obj);
    	            		}
    			        }
            		}
            	}
            }else {
            	//实际业务为单实体vo
            	AbstractBill aggVO  = (AbstractBill)nccAggVo;
            	IVOMeta metaH = aggVO.getMetaData().getParent();
    			ISuperVO nccParentVO = (ISuperVO) aggVO.getParentVO();
    			
    			for(Field restField : restPojoFields) {
    				String name = restField.getName();
    				restField.setAccessible(true);
    				
    				Object nccFieldValue = reflexToGetValue(nccParentVO, name);
    				if(nccFieldValue == null) {
    					continue;
    				}
    				reflexToRestPojoSetValue(restPojo, restField, nccFieldValue);
            	}
            }
            
            this.restObj = restPojo;
		}
	}
	
	/**
	 * 通过NCvo实体属性路径从Aggvo数据中匹配获取对应的数据，并进行组装返回对应数据
	 * @param aggVO NccAggvo实体参数
	 * @param restEntityField 接口实体参数字段
	 * @throws Exception 
	 * @return
	 */
	@SuppressWarnings({"all"})
	private List<Object> changePojoSetValueByNccAggVO(AbstractBill aggVO,Field restEntityField) throws Exception{
        String fieldNccVO = restEntityField.getAnnotation(ZdyFieldTagAnnotation.class).nccVO();//字段实体属性全路径
		List<Object> listPOJO = new ArrayList<Object>();
		
		Type type = restEntityField.getGenericType();
		if(type instanceof ParameterizedType){
			//当前字段是否为参数化类型。 如Collection<String>就是一个参数化类型。只有表体数组页签才能是这样的格式。
			ParameterizedType pt = (ParameterizedType) type;
			Class<?> actualTypeArgument = (Class<?>)pt.getActualTypeArguments()[0];//当当前数组实体属性类型
			
			ISuperVO[] nccBodyVOs = null;
			IVOMeta[] metaB = aggVO.getMetaData().getChildren();
			for(IVOMeta meta : metaB) {
				ISuperVO[] bodyVOs = aggVO.getChildren(meta);
				if(bodyVOs!=null && bodyVOs.length>0) {
					if(bodyVOs[0].getClass().getName().equals(fieldNccVO)) {
						nccBodyVOs = bodyVOs;
						break;
					}
				}
			}
			
			if(nccBodyVOs!=null && nccBodyVOs.length>0) {
				for(ISuperVO nccBodyVO : nccBodyVOs) {
					Object restbodyPojo =  Constructor.construct(actualTypeArgument);
					Field[] restEntityFields = actualTypeArgument.getDeclaredFields();
					for(Field field : restEntityFields) {
						String name = field.getName();
						field.setAccessible(true);
						
						if(name.equals("vostatus")) {
							//vostatus字段不属于NCC中的字段故需要排除掉
							continue;
						}
						
						Object nccFieldValue = reflexToGetValue(nccBodyVO, name);
						if(nccFieldValue == null) {
							continue;
						}
						
						reflexToRestPojoSetValue(restbodyPojo, field, nccFieldValue);
					}
					listPOJO.add(restbodyPojo);
				}
			}
		}else {
			//当前字段不为参数化类型，说明是表头数据
			IVOMeta metaH = aggVO.getMetaData().getParent();
			ISuperVO nccParentVO = (ISuperVO) aggVO.getParentVO();
			
			Class restParentPojoClazz = Class.forName(type.getTypeName());
			Object restParentPojo =  Constructor.construct(restParentPojoClazz);
			
			Field[] restEntityFields = restParentPojoClazz.getDeclaredFields();
			for(Field field : restEntityFields) {
				String name = field.getName();
				field.setAccessible(true);
				
				Object nccFieldValue = reflexToGetValue(nccParentVO, name);
				if(nccFieldValue == null) {
					continue;
				}

				reflexToRestPojoSetValue(restParentPojo, field, nccFieldValue);
			}
			listPOJO.add(restParentPojo);
		}
		return listPOJO;
	}
	
	/**
	 * 通过反射给接口实体字段赋值
	 * @param restPojo 接口实体
	 * @param fieldName 字段
	 * @param fieldValue 值
	 * @throws Exception
	 */
	@SuppressWarnings({"all"})
	private static void reflexToRestPojoSetValue(Object restPojo,Field field,Object fieldValue) throws Exception {
		Class object = restPojo.getClass();
        
		String fieldName = field.getName();
		
        // 获取Bean的某个属性的描述符
        PropertyDescriptor proDescriptor = new PropertyDescriptor(fieldName, object);

        Object valObj = null;
        try {
        	Integer refFieldType = null;//参照属性字段类型：1、实体参照，2、自定义档案参照，3、枚举
            String refEnumTag = null;//参照属性枚举标记
            String refDefdocCode = null;//参照属性自定义档案编码
            String refQuerySql = null;//参照属性查询语句（需要处理替换标记）
            if(field.getAnnotation(ZdyTranslateAnnotation.class) != null) {
            	ZdyTranslateAnnotation zdyTranslateAnnotation = field.getAnnotation(ZdyTranslateAnnotation.class);
            	refFieldType = zdyTranslateAnnotation.type();
            	refEnumTag = zdyTranslateAnnotation.enumTag();
            	refDefdocCode = zdyTranslateAnnotation.defdocCode();
            	refQuerySql = zdyTranslateAnnotation.refQuerySql();
            }
        	
        	Class restFieldClazz= proDescriptor.getPropertyType();
        	if(RefPOJO.class.isAssignableFrom(restFieldClazz) || School.class.isAssignableFrom(restFieldClazz) || Student.class.isAssignableFrom(restFieldClazz)) {
        		//参照类型数据进行转译包装
            	if(refFieldType!=null && (refFieldType==1 || refFieldType==2 || refFieldType==3)) {
            		valObj = translateChangeEntity(refFieldType, refEnumTag, refDefdocCode, refQuerySql, restFieldClazz, fieldValue.toString());
            	}
    		}else if(String.class.isAssignableFrom(restFieldClazz)) {
				valObj = fieldValue.toString();
			}else if(Integer.class.isAssignableFrom(restFieldClazz)) {
				valObj = Integer.valueOf(fieldValue.toString());
			}else {
				valObj = fieldValue;
			}
		} catch (Exception e) {
			throw new Exception("字段"+fieldName+"转换错误："+e.getMessage());
		}
        
        if(valObj==null) {
        	//一般这种情况会存在数据转译不了
        	//1、当前字段是参照，该字段所引用的参照档案数据被暴力从数据库中删除了，导致该值无法翻译，此种情况应该跳过
        	//2、当前字段是参照，但是NCC模板上配置成了字符录入后保存到数据库中或者从数据库中暴力更新成一个乱七八糟的值，导致该值无法翻译，此种情况应该跳过
        	//throw new Exception("字段"+fieldName+"存在数据，未能成功转换，请检查！");
        	Logger.error("字段"+fieldName+"存在数据，未能成功转换，请检查！");
        	return;
        }
        
        // 获得用于读取属性值的方法
        Method writeMethod = proDescriptor.getWriteMethod();
        // 对属性进行赋值
        writeMethod.invoke(restPojo,valObj);
	}
	
	/**
	 *  翻译转译包装成实体属性
	 * @param type 参照属性字段类型：1、实体参照，2、自定义档案参照，3、枚举
	 * @param enumTag 参照属性枚举标记
	 * @param defdocCode 参照属性自定义档案编码
	 * @param querySql 参照属性查询语句（需要处理替换标记）
	 * @param restFieldClazz 字段属性类型
	 * @param value pk值/枚举值
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings({"all"})
	private static Object translateChangeEntity(int type,String enumTag,String defdocCode,String querySql,Class restFieldClazz,String value) throws Exception {
		 Object valObj = null;
		 
		 //1实体参照、2自定义档案参照
		 if((type==1 || type==2) && !StringUtils.isBlank(querySql) && !StringUtils.isBlank(value)) {
			 querySql = theSqlTagReplace(querySql, value, defdocCode);
			 valObj = executeQueryVO(querySql, restFieldClazz);
//			 if(RefPOJO.class.isAssignableFrom(restFieldClazz)) {
//				 valObj = executeQueryVO(querySql, RefPOJO.class);
//			 }else if(School.class.isAssignableFrom(restFieldClazz)) {
//				 valObj = executeQueryVO(querySql, School.class);	
//			 }
		 }
		 
		 //3枚举
		 if(type==3) {
			 List<RefPOJO> listRefPOJO = CrmEnumUtil.getEnmu(enumTag);
			 valObj = listRefPOJO.stream().filter(o -> o.getValue().equals(value)).findAny().orElse(null);
		 }
		
		return valObj;
	}
	
	/**
	  * 替换sql语句中的标识
	 * <p>whereReplaceTag: 查询条件标记用于替换成对应的查询数据</P>
     * <p>defcodeReplaceTag: 自定义档案编码标记用于替换对应的自定义档案编码</P>
	 * @param sqlStr SQL语句
	 * @param wherePk 查询条件主键pk值
	 * @param defdocCode 自定义档案编码
	 * @return
	 */
	private static String theSqlTagReplace(String sqlStr, String wherePk, String defdocCode) {
		sqlStr = sqlStr.replaceAll("whereReplaceTag", wherePk);
		if(!StringUtils.isBlank(defdocCode)) {
			sqlStr = sqlStr.replaceAll("defcodeReplaceTag", defdocCode);
		}
		return sqlStr;
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
	  *  查询数据
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

}
