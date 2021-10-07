
package nccloud.cy.cy.studenthvo.action;

import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.logging.Logger;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.utils.commonConstant.ConstantUtil;
import nc.utils.commonplugin.CyCommonUtils;
import nccloud.framework.web.ui.model.PageInfo;
import nc.vo.cy.schoolarchives.AggSchoolBasicsHVO;
import nc.vo.cy.studentfile.AggStudentHVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.Constructor;
import nccloud.base.exception.ExceptionUtils;
import nccloud.dto.baseapp.querytree.dataformat.QueryTreeFormatVO;
import nccloud.framework.web.action.itf.ICommonAction;
import nccloud.framework.web.container.IRequest;
import nccloud.framework.web.json.JsonFactory;
import nccloud.framework.web.querytemplet.QueryUtil4NCC;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.framework.web.ui.pattern.grid.GridOperator;
import nccloud.itf.cy.cy.ICyCommonSqlUtil;
import nccloud.itf.cy.cy.IRightControlService;
import nccloud.cy.cy.studenthvo.cy.bean.PageQueryVO;
import nccloud.cy.cy.studenthvo.cy.util.CommonUtil;

/**
 * 列表查询（查询方案）操作
 * 
 * @version @since v3.5.6-1903
 */
public class StudentHVOQueryListAction implements ICommonAction {

	@Override
	public Object doAction(IRequest paramIRequest) {
		PageQueryVO page = null;
		QueryTreeFormatVO queryParam = this.getQueryParam(paramIRequest);
		try {
			// 1、 获取scheme
			IQueryScheme scheme = this.getScheme(queryParam);
			/*
			 * 根据当前登录用户进行权限控制
			 */
			String userId = InvocationInfoProxy.getInstance().getUserId();
			String pk_psndoc = CyCommonUtils.getInstance(ICyCommonSqlUtil.class).queryPsnIdByUserId(userId);
			String usergroupid = CyCommonUtils.getInstance(IRightControlService.class).queryUserGroupByUserId(userId);
			String groupcode = CyCommonUtils.getInstance(IRightControlService.class).queryCodeByGroupId(usergroupid);
			if (!ConstantUtil.grouplist.contains(groupcode)) {
				List<String> usergroups = new ArrayList<String>();
				usergroups.add(usergroupid);
				List<String> psnids = new ArrayList<String>();
				psnids = CyCommonUtils.getInstance(IRightControlService.class).queryBottomUserId(usergroups);
				if (CyCommonUtils.isEmpty(psnids)) {
					psnids.add(pk_psndoc);
				}
				// 根据关联的业务员或则老师查询班级
				List<String> classcyids = CyCommonUtils.getInstance(IRightControlService.class)
						.queryClasscyByPsnid(psnids);
				AggStudentHVO[] aggvos = CommonUtil.getFinancingcostMaintainService().query(scheme);// 原始查询结果
				List<AggStudentHVO> agglist = new ArrayList<AggStudentHVO>();
				if (CyCommonUtils.isNotEmpty(aggvos) && CyCommonUtils.isNotEmpty(classcyids)) {
					for (String pk_classcy : classcyids) {
						for (AggStudentHVO aggstudenthvo : aggvos) {
							String classcy = aggstudenthvo.getParentVO().getDef2();
							if (CyCommonUtils.isNotEmpty(classcy) && classcy.equals(pk_classcy)) {
								agglist.add(aggstudenthvo);
							}
						}
					}
				}
				// 2、调用服务,获取VO信息(平台默认生成方法，有效率问题，最佳实现要改掉)
				AggStudentHVO[] aggvos2 = agglist.toArray(new AggStudentHVO[agglist.size()]);
				// 查询服务(scheme);
				if ((aggvos2 != null) && (aggvos2.length > 0)) {
					// 3、处理分页
					String[] pks2 = this.getPks(aggvos2);
					AggStudentHVO[] bills = this.getDefaulePageBill(queryParam, aggvos2);
					page = new PageQueryVO(pks2, bills);
				} else {
					page = this.createNullPage();
				}
				// 4、转换grid信息，返回前端
				return this.transPageInfoToGrid(page, queryParam, queryParam.getPageCode());
			} else {
				// 2、调用服务,获取VO信息(平台默认生成方法，有效率问题，最佳实现要改掉)
				AggStudentHVO[] aggvos = CommonUtil.getFinancingcostMaintainService().query(scheme);
				// 查询服务(scheme);
				if ((aggvos != null) && (aggvos.length > 0)) {
					// 3、处理分页
					String[] pks = this.getPks(aggvos);
					AggStudentHVO[] bills = this.getDefaulePageBill(queryParam, aggvos);
					page = new PageQueryVO(pks, bills);
				} else {
					page = this.createNullPage();
				}
				// 4、转换grid信息，返回前端
				return this.transPageInfoToGrid(page, queryParam, queryParam.getPageCode());
			}
		} catch (BusinessException ex) {
			// 处理异常信息
			Logger.error(ex);
			ExceptionUtils.wrapException(ex);
		}
		return null;
	}

	/**
	 * 转换Grid信息
	 *
	 * @param pagevo
	 * @param pageSize
	 * @param pagecode
	 * @return
	 */
	private Grid covert(PageQueryVO pagevo, int pageSize, String pagecode) {
		AggStudentHVO[] bills = (AggStudentHVO[]) pagevo.getCurrentPageBills();

		Grid grid = null;
		if (bills != null) {
			int size = bills.length > pageSize ? pageSize : bills.length;
			Object[] heads = new Object[size];

			for (int i = 0; i < size; i++) {
				heads[i] = bills[i].getParent();
			}
			GridOperator operator = new GridOperator(pagecode);
			grid = operator.toGrid(heads);
			grid.getModel().setAllpks(pagevo.getPks());
			grid = CommonUtil.displayGridManager(grid, bills);
		}
		return grid;
	}

	/**
	 * 创建空页面信息
	 *
	 * @return
	 */
	private PageQueryVO createNullPage() {
		String[] ids = new String[0];
		AggStudentHVO[] bills = Constructor.construct(AggStudentHVO.class, 0);
		PageQueryVO page = new PageQueryVO(ids, bills);
		return page;
	}

	/**
	 * 设置默认页面
	 *
	 * @param info
	 * @param aggvos
	 * @return
	 */
	private AggStudentHVO[] getDefaulePageBill(QueryTreeFormatVO info, AggStudentHVO... aggvos) {
		String pageSizeStr = info.getPageInfo().getPageSize();
		int pageSize = 10;
		if ((pageSizeStr != null) && (pageSizeStr.length() > 0)) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		info.getPageInfo().setPageIndex("0");
		List<AggStudentHVO> billLst = new ArrayList<AggStudentHVO>();
		for (int i = 0; (i < aggvos.length) && (i < pageSize); i++) {
			AggStudentHVO bill = new AggStudentHVO();

			bill.setParent(aggvos[i].getParent());
			billLst.add(bill);
		}
		return billLst.toArray(new AggStudentHVO[0]);
	}

	/**
	 * 获取主键信息
	 *
	 * @param aggvos
	 * @return
	 */
	private String[] getPks(AggStudentHVO... aggvos) {
		List<String> pks = new ArrayList<String>();
		for (AggStudentHVO bill : aggvos) {
			pks.add(bill.getPrimaryKey());
		}
		return pks.toArray(new String[0]);
	}

	/**
	 * 获取查询参数
	 *
	 * @param paramIRequest
	 * @return
	 */
	private QueryTreeFormatVO getQueryParam(IRequest paramIRequest) {
		String strRead = paramIRequest.read();
		QueryTreeFormatVO queryParam = JsonFactory.create().fromJson(strRead, QueryTreeFormatVO.class);
		return queryParam;
	}

	/**
	 * 获取查询方案
	 *
	 * @param queryParam
	 * @return
	 */
	private IQueryScheme getScheme(QueryTreeFormatVO queryParam) {
		QueryUtil4NCC queryutil = new QueryUtil4NCC(queryParam);
		IQueryScheme scheme = queryutil.convertCondition();
		return scheme;
	}

	/**
	 * 总页数
	 *
	 * @param pageInfo
	 * @param length
	 * @return
	 */
	private int getTotalPage(nccloud.framework.web.ui.model.PageInfo pageInfo, int length) {
		int size = pageInfo.getPageSize();
		int total = 0;
		if ((length % size) == 0) {
			total = length / size;
		} else {
			total = (length / size) + 1;
		}
		return total;
	}

	/**
	 * 页面设置
	 *
	 * @param retObj
	 * @param pagevo
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	private void pageSet(Grid grid, PageQueryVO pagevo, int pageSize, int pageIndex) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setTotal(pagevo.getPks().length);
		pageInfo.setPageSize(pageSize);
		pageInfo.setPageIndex(pageIndex);
		pageInfo.setTotalPage(this.getTotalPage(pageInfo, pagevo.getPks().length));

		grid.getModel().setPageinfo(pageInfo);
	}

	/**
	 * page信息转前端需要的grid模型
	 *
	 * @param pagevo
	 * @param info
	 * @param pagecode
	 * @return
	 */
	private Object transPageInfoToGrid(PageQueryVO pagevo, QueryTreeFormatVO info, String pagecode) {
		Grid grid = null;
		int pageSize = Integer.parseInt(info.getPageInfo().getPageSize());
		int pageIndex = Integer.parseInt(info.getPageInfo().getPageIndex());
		if (pagevo != null) {
			if (pagevo.getCurrentPageBills().length == 0) {
				return null;
			}
			grid = this.covert(pagevo, pageSize, pagecode);

			this.pageSet(grid, pagevo, pageSize, pageIndex);
		}
		return grid;
	}
}
