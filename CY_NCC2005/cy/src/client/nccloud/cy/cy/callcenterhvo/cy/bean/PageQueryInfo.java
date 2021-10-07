
package nccloud.cy.cy.callcenterhvo.cy.bean;

import java.util.Map;

/**
 * 单条或多条数据查询
 */
public class PageQueryInfo {

  protected String pagecode;

  protected String pk;

  protected String[] pks;

  protected Map<String, String> userObj;

  public String getPagecode() {
    return this.pagecode;
  }

  public String getPk() {
    return this.pk;
  }

  public String[] getPks() {
    return this.pks;
  }

  public Map<String, String> getUserObj() {
    return this.userObj;
  }

  public void setPagecode(String pagecode) {
    this.pagecode = pagecode;
  }

  public void setPk(String pk) {
    this.pk = pk;
  }

  public void setPks(String[] pks) {
    this.pks = pks;
  }

  public void setUserObj(Map<String, String> userObj) {
    this.userObj = userObj;
  }
}
