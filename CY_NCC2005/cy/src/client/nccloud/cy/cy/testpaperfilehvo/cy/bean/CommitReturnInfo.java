
package nccloud.cy.cy.testpaperfilehvo.cy.bean;

import java.util.Map;

import nccloud.framework.web.ui.pattern.billcard.BillCard;
import nccloud.framework.web.ui.pattern.grid.Grid;

/**
 * 操作返回信息
 *
 * @author weiyjc
 *
 */
public class CommitReturnInfo {

  // 指派标志
  private boolean assignFlag;

  // 卡片信息
  private BillCard billCard;

  // 错误信息
  private String errorMsg;

  // 列表信息
  private Grid grid;

  // 前后台交互信息
  private String interactMsg;

  // 聚合vo
  private Object[] objects;

  // 提交即指派返回信息
  private Map<String, Object> returnMsg;

  // 成功信息
  private String successMsg;

  // 警告提示信息，如预算预警等
  private String warningMsg;

  public boolean getAssignFlag() {
    return this.assignFlag;
  }

  public BillCard getBillCard() {
    return this.billCard;
  }

  public String getErrorMsg() {
    return this.errorMsg;
  }

  public Grid getGrid() {
    return this.grid;
  }

  public String getInteractMsg() {
    return this.interactMsg;
  }

  public Object[] getObjects() {
    return this.objects;
  }

  public Map<String, Object> getReturnMsg() {
    return this.returnMsg;
  }

  public String getSuccessMsg() {
    return this.successMsg;
  }

  public String getWarningMsg() {
    return this.warningMsg;
  }

  public void setAssignFlag(boolean assignFlag) {
    this.assignFlag = assignFlag;
  }

  public void setBillCard(BillCard billCard) {
    this.billCard = billCard;
  }

  public void setErrorMsg(String errorMsg) {
    this.errorMsg = errorMsg;
  }

  public void setGrid(Grid grid) {
    this.grid = grid;
  }

  public void setInteractMsg(String interactMsg) {
    this.interactMsg = interactMsg;
  }

  public void setObjects(Object[] objects) {
    this.objects = objects;
  }

  public void setReturnMsg(Map<String, Object> returnMsg) {
    this.returnMsg = returnMsg;
  }

  public void setSuccessMsg(String successMsg) {
    this.successMsg = successMsg;
  }

  public void setWarningMsg(String warningMsg) {
    this.warningMsg = warningMsg;
  }

}
