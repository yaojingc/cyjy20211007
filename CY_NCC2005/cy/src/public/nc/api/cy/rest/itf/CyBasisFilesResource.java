package nc.api.cy.rest.itf;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.json.JSONString;
import org.springframework.web.bind.annotation.RequestBody;

import nc.api.cy.rest.entity.TableCountQueryVO;
import nc.api.cy.rest.entity.crm.basedoc.defdoc.DefdocQuery;
import nc.api.cy.rest.entity.crm.basedoc.org.OrgQuery;
import nc.api.cy.rest.entity.crm.basedoc.psndoc.PsndocQuery;
import nc.api.cy.rest.entity.crm.basedoc.region.RegionQuery;
import nc.api.cy.rest.entity.crm.basepojo.QueryPage;
import nc.api.cy.rest.entity.crm.basepojo.ResponseList;
import nc.api.cy.rest.entity.crm.refpojo.RefDefDoc;
import nc.api.cy.rest.entity.crm.refpojo.RefOrg;
import nc.api.cy.rest.entity.crm.refpojo.RefPOJO;
import nc.api.cy.rest.entity.crm.refpojo.RefPerson;
import nc.api.cy.rest.entity.crm.refpojo.RefRegion;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.utils.commonplugin.CyCommonUtils;
import nc.utils.crm.CrmEnumUtil;
import nc.utils.crm.itf.ICrmService;
import nc.vo.cy.pojo.PagingPOJO;
import nc.vo.cy.pojo.ResponsePOJO;
import nc.vo.cy.studentfile.pojo.StudentQueryPOJO;
import nc.vo.cy.studentfile.pojo.StudentqVO;
import nc.vo.pub.BusinessException;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import uap.ws.rest.resource.AbstractUAPRestResource;

/***
 * 参照接口
 * 
 * @author RL
 *
 */
@Path("cybasicf")
public class CyBasisFilesResource extends AbstractUAPRestResource {

	@Override
	public String getModule() {
		CyCommonUtils.login();
		return "cy";
	}

	/*
	 * 参照查询接口rongleia
	 */
	@POST
	@Path("psndoc")
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "all" })
	public JSONString psnDoc(@RequestBody PsndocQuery psndocquery) {
		try {

			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			condition
					.append("select rownum as rn, pk_psndoc value,name label,code code from bd_psndoc where dr = '0' ");
			if (!StringUtils.isBlank(psndocquery.getName())) {
				condition.append(" and name like '%" + psndocquery.getName() + "%'");
			}
			// 2、进行分页查询
			ResponseList queryList = queryList(condition.toString(), psndocquery, "psn");
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryList, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 参照查询接口rongleia
	 */
	@POST
	@Path("region")
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "all" })
	public JSONString region(@RequestBody RegionQuery regionquery) {
		try {
			// 1、查询条件拼装
			StringBuilder condition = new StringBuilder();
			condition
					.append("select rownum as rn, pk_region value,name label,code code from bd_region where dr = '0' ");
			if (!StringUtils.isBlank(regionquery.getName())) {
				condition.append(" and name like '%" + regionquery.getName() + "%'");
			}
			if (!StringUtils.isBlank(regionquery.getPk_father())) {
				condition.append(" and  pk_father =  '" + regionquery.getPk_father() + "'");
			} else {
				condition.append(" and  pk_father =  '~'");
			}
			// 2、进行分页查询
			ResponseList queryList = queryList(condition.toString(), regionquery, "region");
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryList, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 参照查询接口rongleia
	 */
	@POST
	@Path("org")
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "all" })
	public JSONString org(@RequestBody OrgQuery orgquery) {
		try {

			// 1、查询条件拼装 isbusinessunit 是否业务单元数据
			StringBuilder condition = new StringBuilder();
			condition.append(
					"select rownum as rn, pk_org value,name label,code code from org_orgs where dr = '0' and isbusinessunit = 'Y'");
			if (!StringUtils.isBlank(orgquery.getName())) {
				condition.append(" and name like '%" + orgquery.getName() + "%'");
			}
			// 2、进行分页查询
			ResponseList queryList = queryList(condition.toString(), orgquery, "org");
			// 3、返回响应结果
			return ResponsePOJO.toJSON(queryList, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	/*
	 * 参照查询接口rongleia
	 */
	@POST
	@Path("defdoc")
	@Consumes("application/json")
	@Produces("application/json")
	@SuppressWarnings({ "all" })
	public JSONString defDoc(@RequestBody DefdocQuery defdocquery) {
		try {
			Map<String, Object> mapData = new HashMap<String, Object>();
			List<String> listEnumTag = new ArrayList<>();// 枚举请求标记
			List<String> listNccDocTag = new ArrayList<>();// 档案请求标记

			String[] defdoclistcode = defdocquery.getDefdoclistcode().split(",");
			for (String tag : defdoclistcode) {
				if (tag.length() > 4 && tag.substring(0, 4).equals("enum")) {
					listEnumTag.add(tag);
				} else {
					listNccDocTag.add(tag);
				}
			}

			// 枚举请求数据
			for (String strCode : listEnumTag) {
				List<RefPOJO> listEmun = CrmEnumUtil.getEnmu(strCode);
				if (listEmun != null && listEmun.size() > 0) {
					mapData.put(strCode, listEmun);
				} else {
					// listEmun = new ArrayList<RefPOJO>();
					mapData.put(strCode, "无数据");
				}
			}

			// 自定义档案请求数据
			for (String strCode : listNccDocTag) {
				String querySql = "select pk_defdoc value,name label,code code from bd_defdoc where dr = '0' and pk_defdoclist = (select pk_defdoclist from bd_defdoclist where code='"
						+ strCode + "')";
				List<T> list = (List<T>) getBaseDao().executeQuery(querySql, new BeanListProcessor(RefDefDoc.class));
				if (list != null && list.size() > 0) {
					mapData.put(strCode, list);
				} else {
					// list = new ArrayList<T>();
					mapData.put(strCode, "无数据");
				}
			}

			return ResponsePOJO.toJSON(mapData, "200", "成功");
		} catch (Exception e) {
			return ResponsePOJO.toJSON(null, "400", e.getMessage());
		} finally {
			CyCommonUtils.logout();
		}
	}

	private ICrmService crmService;

	public ICrmService getCrmService() {
		if (crmService == null) {
			crmService = NCLocator.getInstance().lookup(ICrmService.class);
		}
		return crmService;
	}

	private static BaseDAO baseDao;

	private static BaseDAO getBaseDao() {
		if (baseDao == null) {
			baseDao = new BaseDAO();
		}
		return baseDao;
	}

	/**
	 * Add By rongleia 根据查询条件查询学校档案列表态数据
	 * 
	 * @param refquerypage 查询条件vo
	 * @param voClass      voClass 查询类型
	 * @param sql          查询语句
	 * @return
	 */
	@SuppressWarnings({ "all" })
	public static ResponseList queryList(String sql, QueryPage refquerypage, String refcode) {
		// 组装查询SQL语句并执行
		int pageindex = refquerypage.getIndex();// 当前页
		int pagesize = refquerypage.getSize();// 每页大小
		String sort = refquerypage.getSort();// 排序条件
		// 拼装分页查询sql
		int bpage = (pageindex - 1) * (pagesize) + 1;// 开始
		int epage = pageindex * pagesize;// 结束
		String sqlwhere = " select value,code,label from ( " + sql + " and rownum <=" + epage;
		if (!StringUtils.isBlank(sort)) {
			sql = sqlwhere + " order by " + sort + ") where 1 =1 and rn >= " + bpage;
		} else {
			sql = sqlwhere + " order by ts desc) where 1 =1 and rn >= " + bpage;
		}
		// 查询结果 根据SQL语句和对应的VO类型查询，返回相应的VO数据
		ResponseList resp = new ResponseList();
		List<T> listvos = new ArrayList<T>();
		try {
			String Total = "0";
			if (refcode.equals("defdoc")) {
				listvos = (List<T>) getBaseDao().executeQuery(sql.toString(), new BeanListProcessor(RefDefDoc.class));
			} else if (refcode.equals("psn")) {
				listvos = (List<T>) getBaseDao().executeQuery(sql.toString(), new BeanListProcessor(RefPerson.class));
				TableCountQueryVO vo=new TableCountQueryVO("bd_psndoc",null);
				PagingPOJO r=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo);
				if(CyCommonUtils.isNotEmpty(r)) {
					Total =r.getTotal().toString();
				}
			} else if (refcode.equals("org")) {
				listvos = (List<T>) getBaseDao().executeQuery(sql.toString(), new BeanListProcessor(RefOrg.class));
				TableCountQueryVO vo=new TableCountQueryVO("org_orgs", "isbusinessunit = 'Y' ");
				PagingPOJO r=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo);
				if(CyCommonUtils.isNotEmpty(r)) {
					Total =r.getTotal().toString();
				}
//				Total = CyCommonUtils.queryTotal("org_orgs", " and isbusinessunit = 'Y' ");
			} else if (refcode.equals("region")) {
				listvos = (List<T>) getBaseDao().executeQuery(sql.toString(), new BeanListProcessor(RefRegion.class));
				TableCountQueryVO vo=new TableCountQueryVO("bd_region",null);
				PagingPOJO r=CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryTablePageInfo(vo);
				if(CyCommonUtils.isNotEmpty(r)) {
					Total =r.getTotal().toString();
				}
//				Total = CyCommonUtils.queryTotal("bd_region", null);
			} 
			resp.setContent(listvos);
			resp.setTotal(Integer.parseInt(Total));
			int totalPage = getTotalPage(pagesize, Integer.parseInt(Total));
			resp.setTotalPages(totalPage);
			resp.setCurrentPage(pageindex);
			resp.setPageSize(pagesize);
			return resp;
		} catch (BusinessException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 总页数
	 * 
	 * @param pageSize 每页条数
	 * @param length   总条数
	 * @return
	 */
	private static int getTotalPage(int pageSize, int length) {
		int size = pageSize;
		int total = 0;
		if ((length % size) == 0) {
			total = length / size;
		} else {
			total = (length / size) + 1;
		}
		return total;
	}
}
