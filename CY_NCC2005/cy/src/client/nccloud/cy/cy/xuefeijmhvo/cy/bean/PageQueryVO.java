
package nccloud.cy.cy.xuefeijmhvo.cy.bean;
import java.util.HashMap;
import java.util.Map;

import nc.vo.pubapp.pagination.PaginationQueryVO;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;

/**
 * 单据分页查询
 */
public class PageQueryVO {

  /**
   * 每页的单据数
   */
  public static final String RECORD_IN_PAGE = "recordInPage";

  /**
   * 当前页的单据实体数组（只有第一个单据实体有表体）
   */
  protected final IBill[] currentPageBills;

  /**
   * 当前查询出来的所有单据主键
   */
  protected final String[] pks;

  /**
   * 单据分页查询结果构造函数
   *
   * @param pks 当前查询出来的所有单据主键
   * @param bills 当前页的单据实体数组（只有第一个单据实体有表体）
   */
  public PageQueryVO(String[] pks, IBill[] bills) {
    this.pks = pks;
    this.currentPageBills = bills;
  }

  /**
   * 获取当前页的单据实体数组（只有第一个单据实体有表体）
   *
   * @return 当前页的单据实体数组（只有第一个单据实体有表体）
   */
  public IBill[] getCurrentPageBills() {
    return this.currentPageBills;
  }

  /**
   * 获取当前查询出来的所有单据主键
   *
   * @return 当前查询出来的所有单据主键
   */
  public String[] getPks() {
    return this.pks;
  }

  /**
   * 转换为平台的分页VO
   *
   * @return 平台的分页VO
   */
  public PaginationQueryVO toPaginationQueryVO() {
    PaginationQueryVO vo = new PaginationQueryVO();
    Map<String, Object> billmap = new HashMap<String, Object>();
    for (IBill bill : this.currentPageBills) {
      billmap.put(bill.getPrimaryKey(), bill);
    }
    vo.setAllpks(this.pks);
    vo.setBillmap(billmap);
    return vo;
  }

}