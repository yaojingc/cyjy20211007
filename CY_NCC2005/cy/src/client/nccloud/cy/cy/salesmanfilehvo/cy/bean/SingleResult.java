
package nccloud.cy.cy.salesmanfilehvo.cy.bean;

/**
 * @Description: 单个操作结果
 * @author wangjias
 * @date 2018-08-28
 * @version V1.0
 */
public class SingleResult {

  /**
   * 消息
   */
  private String msg;

  /**
   * 主键
   */
  private String pk;

  /**
   * 处理结果
   */
  private Object result;

  /**
   * 行号
   */
  private Integer rowIndex;

  /**
   * 执行标志
   */
  private int state;

  /**
   * 单据编号
   */
  private String vbillno;

  public SingleResult() {
    super();
  }

  private SingleResult(MultipleResult.ResultState state, String msg,
                       Object result, String pk, String vbillno, Integer rowIndex) {
    super();
    this.state = state.getValue();
    this.msg = msg;
    this.result = result;
    this.pk = pk;
    this.vbillno = vbillno;
    this.rowIndex = rowIndex;
  }

  /**
   * 构建失败的单笔操作结果
   *
   * @param pk
   *          主键
   * @param vbillno
   *          单据编号
   * @param rowIndex
   *          行号
   * @param msg
   *          操作消息
   * @return
   */
  public static SingleResult buildErrResult(String pk, String vbillno,
                                            Integer rowIndex, String msg) {
    return new SingleResult(MultipleResult.ResultState.FAIL, msg, null, pk,
            vbillno, rowIndex);
  }

  /**
   * 构建成功的单笔操作结果
   *
   * @param pk
   *          主键
   * @param vbillno
   *          单据编号
   * @param rowIndex
   *          行号
   * @param result
   *          操作结果
   * @return
   */
  public static SingleResult buildSuccessResult(String pk, String vbillno,
                                                Integer rowIndex, Object result) {
    return new SingleResult(MultipleResult.ResultState.SUCCESS,
            nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID(
                    "tm_tmpub_ncc003_0", "0tm_tmpub_ncc003-0010")/*@res 成功*/,
            result, pk, vbillno, rowIndex);
  }

  public String getMsg() {
    return this.msg;
  }

  public String getPk() {
    return this.pk;
  }

  public Object getResult() {
    return this.result;
  }

  public Integer getRowIndex() {
    return this.rowIndex;
  }

  public int getState() {
    return this.state;
  }

  public String getVbillno() {
    return this.vbillno;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public void setPk(String pk) {
    this.pk = pk;
  }

  public void setResult(Object result) {
    this.result = result;
  }

  public void setRowIndex(Integer rowIndex) {
    this.rowIndex = rowIndex;
  }

  public void setState(int state) {
    this.state = state;
  }

  public void setVbillno(String vbillno) {
    this.vbillno = vbillno;
  }

}
