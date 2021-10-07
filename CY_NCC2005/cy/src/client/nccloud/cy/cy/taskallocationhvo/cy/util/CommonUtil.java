
package nccloud.cy.cy.taskallocationhvo.cy.util;


import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nccloud.itf.cy.cy.ITaskallocationhvoMaintain;
import nc.md.persist.framework.IMDPersistenceQueryService;
import nc.pubitf.org.cache.IOrgUnitPubService_C;
import nc.util.mmf.framework.base.MMValueCheck;
import nccloud.vo.cy.cy.TaskallocationHVOConst;
import nc.vo.cy.taskallocation.AggTaskallocationHVO;
import nc.vo.cy.taskallocation.TaskallocationHVO;
import nc.vo.org.OrgVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nccloud.base.exception.ExceptionUtils;
import nccloud.cy.cy.util.BaseDataQueryUtil;
import nccloud.framework.service.ServiceLocator;
import nccloud.framework.web.container.SessionContext;
import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.grid.Grid;
import nccloud.pubitf.riart.pflow.ICloudScriptPFlowService;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nccloud.framework.web.ui.model.row.Cell;
import nccloud.framework.web.ui.model.row.Row;

public class CommonUtil {

  private static ITaskallocationhvoMaintain maintain = null;

  private static IMDPersistenceQueryService persistenceQueryService = null;

  private static ICloudScriptPFlowService cloudScriptPFlowService = null;

  
	/**
	 * 自定义参照翻译 列表态
	 * 
	 * @param grid
	 * @param bills
	 * @return
	 */
	public static Grid displayGridManager(Grid grid, AggTaskallocationHVO[] bills) {
		if (null == grid) {
			return grid;
		}
		Set<String> setDef1 = new HashSet<String>();
		for (AggTaskallocationHVO aggvo : bills) {
			TaskallocationHVO hvo = aggvo.getParentVO();
			// def1：学校
			String def1 = hvo.getDef1();
			if (MMValueCheck.isNotEmpty(def1)) {
				setDef1.add(def1);
			}

		}

		Map<String, String> schoolMap = BaseDataQueryUtil.schoolQuery(setDef1);
		List<Row> listRows = grid.getModel().getListRows();
		if (listRows != null && listRows.size() > 0) {
			for (Row row : listRows) {
				Cell cellDef1 = row.getCell("def1");
				cellDef1.setDisplay(schoolMap.get(cellDef1.getValue()));
			}
		}
		return grid;
	}

	/**
	 * 自定义参照翻译 卡片态
	 * 
	 * @param queryParam
	 * @param bill
	 * @return
	 */
	public static BillCard displayCardManager(BillCard billCard, AggTaskallocationHVO bill) {
		// def1：学校 ，def2：班级
		Set<String> setDef1 = new HashSet<String>();
		TaskallocationHVO hvo = bill.getParentVO();
		// def1：学校 ，
		String def1 = hvo.getDef1();
		if (MMValueCheck.isNotEmpty(def1)) {
			setDef1.add(def1);
		}

		Map<String, String> schoolMap = BaseDataQueryUtil.schoolQuery(setDef1);

		Row[] rows = billCard.getHead().getModel().getRows();
		if (rows != null && rows.length > 0) {
			for (int i = 0; i < rows.length; i++) {

				Cell cellDef1 = rows[i].getCell("def1");
				cellDef1.setDisplay(schoolMap.get(cellDef1.getValue()));

			}
		}
		return billCard;
	}
  /**
   * 单据类型编码
   *
   * @return
   */
  public static String getBillTypeCode() {
    return TaskallocationHVOConst.CONST_BILLTYPE_COST;
  }


  /**
   * 获取当前业务时间
   *
   * @return
   */
  public static UFDate getBusiDate() {
    return new UFDate(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }

  /**
   * 获取当前业务日期时间
   *
   * @return
   */
  public static UFDateTime getBusiDateTime() {
    return new UFDateTime(SessionContext.getInstance().getClientInfo()
        .getBizDateTime());
  }


  /**
   * 平台脚本服务类
   *
   * @return
   */
  public static synchronized ICloudScriptPFlowService getCloudScriptPFlowService() {
    if (CommonUtil.cloudScriptPFlowService == null) {
      CommonUtil.cloudScriptPFlowService =
          ServiceLocator.find(ICloudScriptPFlowService.class);
    }
    return CommonUtil.cloudScriptPFlowService;
  }

/**
   * 查询财务组织所在集团
   *
   * @param pk_org
   * @return
   * @throws BusinessException
   */
  public static String getGroupByOrg(String pk_org) throws BusinessException {
    IOrgUnitPubService_C orgUnitQryService =
        ServiceLocator.find(IOrgUnitPubService_C.class);
    OrgVO[] orgVOs = orgUnitQryService.getOrgs(new String[] {
      pk_org
    }, new String[] {
      OrgVO.PK_GROUP
    });
    if ((orgVOs == null) || (orgVOs.length <= 0)) {
      return null;
    }
    return (String) orgVOs[0].getAttributeValue(OrgVO.PK_GROUP);
  }

  /**
   * 查询财务组织最新版本
   *
   * @param pk_org
   * @return
   */
  public static String getOrgVByOrg(String pk_org) throws BusinessException {
    IOrgUnitPubService_C orgUnitQryService =
        ServiceLocator.find(IOrgUnitPubService_C.class);
    HashMap<String, String> org_vs =
        orgUnitQryService.getNewVIDSByOrgIDS(new String[] {
          pk_org
        });
    if ((org_vs == null) || (org_vs.size() <= 0)) {
      return null;
    }
    return org_vs.get(pk_org);
  }

  /**
   * 管理服务
   *
   * @return
   */
  public static synchronized ITaskallocationhvoMaintain getFinancingcostMaintainService() {
    if (CommonUtil.maintain == null) {
      CommonUtil.maintain = ServiceLocator.find(ITaskallocationhvoMaintain.class);
    }
    return CommonUtil.maintain;
  }

  /**
   * 元数据查询服务
   *
   * @return
   */
  public static synchronized IMDPersistenceQueryService getMDPersistenceQueryService() {
    if (CommonUtil.persistenceQueryService == null) {
      CommonUtil.persistenceQueryService =
          ServiceLocator.find(IMDPersistenceQueryService.class);
    }
    return CommonUtil.persistenceQueryService;
  }

  /**
   * 后台VO赋值前台的时间戳
   *
   * @param pk_org
   * @return
   */
  public static void setBillsTs(Map<String, String> pkMapTs,
      AbstractBill[] bills) {
    if (pkMapTs == null) {
      return;
    }
    for (AbstractBill bill : bills) {
      String ts = pkMapTs.get(bill.getParent().getPrimaryKey());
      if ((ts == null) || ts.equals("")) {
        break;
      }
      bill.getParent().setAttributeValue(TaskallocationHVOConst.CONST_TS, ts);
    }
  }


}
