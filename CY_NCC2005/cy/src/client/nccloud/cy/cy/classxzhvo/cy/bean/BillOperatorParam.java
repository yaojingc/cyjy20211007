
package nccloud.cy.cy.classxzhvo.cy.bean;

import java.util.Arrays;
import java.util.Map;

import nc.vo.pub.lang.UFDate;

public class BillOperatorParam {

  /**
   * 结束日期
   */
  private UFDate endDate;

  /**
   * 页面编码
   */
  private String pageCode;

  /**
   * 主键
   */
  private String pk;

  /**
   * 主键与行号映射关系
   */
  private Map<String, Integer> pkMapRowIndex;

  /**
   * 主键与时间戳的映射关系 必传
   */
  private Map<String, String> pkMapTs;

  /**
   * 主键与单据编号的映射关系 必传
   */
  private Map<String, String> pkMapVbillno;

  /**
   * 主键数组
   */
  private String[] pks;

  /**
   * 开始日期
   */
  private UFDate startDate;

  /**
   * 时间戳
   */
  private String ts;

  // 提交指派
  private Object userObj;

  public UFDate getEndDate() {
    return this.endDate;
  }

  public String getPageCode() {
    return this.pageCode;
  }

  public String getPk() {
    return this.pk;
  }

  public Map<String, Integer> getPkMapRowIndex() {
    return this.pkMapRowIndex;
  }

  public Map<String, String> getPkMapTs() {
    return this.pkMapTs;
  }

  public Map<String, String> getPkMapVbillno() {
    return this.pkMapVbillno;
  }

  public String[] getPks() {
    return this.pks;
  }

  public UFDate getStartDate() {
    return this.startDate;
  }

  public String getTs() {
    return this.ts;
  }

  public Object getUserObj() {
    return this.userObj;
  }

  public void setEndDate(UFDate endDate) {
    this.endDate = endDate;
  }

  public void setPageCode(String pageCode) {
    this.pageCode = pageCode;
  }

  public void setPk(String pk) {
    this.pk = pk;
  }

  public void setPkMapRowIndex(Map<String, Integer> pkMapRowIndex) {
    this.pkMapRowIndex = pkMapRowIndex;
  }

  public void setPkMapTs(Map<String, String> pkMapTs) {
    this.pkMapTs = pkMapTs;
  }

  public void setPkMapVbillno(Map<String, String> pkMapVbillno) {
    this.pkMapVbillno = pkMapVbillno;
  }

  public void setPks(String[] pks) {
    this.pks = pks;
  }

  public void setStartDate(UFDate startDate) {
    this.startDate = startDate;
  }

  public void setTs(String ts) {
    this.ts = ts;
  }

  public void setUserObj(Object userObj) {
    this.userObj = userObj;
  }

  @Override
  public String toString() {
    return "CardOperatorParam [pk=" + this.pk + ", pks="
            + Arrays.toString(this.pks) + ", ts=" + this.ts + ", pageCode="
            + this.pageCode + "]";
  }

}
