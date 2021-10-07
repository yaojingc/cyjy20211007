package nc.vo.cy.pojo;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFLiteralDate;

public class GlbdefVO extends SuperVO {
	private static final long serialVersionUID = 1L;
	private UFDateTime modifiedtime;
	private String modifier;
	private UFDateTime creationtime;
	private String creator;
	private Integer dr = Integer.valueOf(0);
	private String pk_psndoc;
	private String pk_psndoc_sub;
	private UFLiteralDate begindate;
	private UFLiteralDate enddate;
	private Integer recordnum;
	private UFBoolean lastflag= new UFBoolean(false);

	private String glbdef1;// 大区
	private String glbdef2;// 省份
	private String glbdef3;// 地区
	private String glbdef4; // 学校
	private String glbdef5;// 年级
	private String glbdef6;// 班级
	private String glbdef7;// 班级人数
	private String glbdef8;// 来源主键

	@Override
	public String getPrimaryKey() {
		// TODO Auto-generated method stub
		return pk_psndoc_sub;
	}

	@Override
	public String getPKFieldName() {
		// TODO Auto-generated method stub
		return "pk_psndoc_sub";
	}

	/**
	 * @return the modifiedtime
	 */
	public UFDateTime getModifiedtime() {
		return modifiedtime;
	}

	/**
	 * @param modifiedtime the modifiedtime to set
	 */
	public void setModifiedtime(UFDateTime modifiedtime) {
		this.modifiedtime = modifiedtime;
	}

	/**
	 * @return the modifier
	 */
	public String getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	/**
	 * @return the creationtime
	 */
	public UFDateTime getCreationtime() {
		return creationtime;
	}

	/**
	 * @param creationtime the creationtime to set
	 */
	public void setCreationtime(UFDateTime creationtime) {
		this.creationtime = creationtime;
	}

	/**
	 * @return the creator
	 */
	public String getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(String creator) {
		this.creator = creator;
	}

	/**
	 * @return the dr
	 */
	public Integer getDr() {
		return dr;
	}

	/**
	 * @param dr the dr to set
	 */
	public void setDr(Integer dr) {
		this.dr = dr;
	}

	/**
	 * @return the pk_psndoc
	 */
	public String getPk_psndoc() {
		return pk_psndoc;
	}

	/**
	 * @param pk_psndoc the pk_psndoc to set
	 */
	public void setPk_psndoc(String pk_psndoc) {
		this.pk_psndoc = pk_psndoc;
	}

	/**
	 * @return the pk_psndoc_sub
	 */
	public String getPk_psndoc_sub() {
		return pk_psndoc_sub;
	}

	/**
	 * @param pk_psndoc_sub the pk_psndoc_sub to set
	 */
	public void setPk_psndoc_sub(String pk_psndoc_sub) {
		this.pk_psndoc_sub = pk_psndoc_sub;
	}

	/**
	 * @return the begindate
	 */
	public UFLiteralDate getBegindate() {
		return begindate;
	}

	/**
	 * @param begindate the begindate to set
	 */
	public void setBegindate(UFLiteralDate begindate) {
		this.begindate = begindate;
	}

	/**
	 * @return the enddate
	 */
	public UFLiteralDate getEnddate() {
		return enddate;
	}

	/**
	 * @param enddate the enddate to set
	 */
	public void setEnddate(UFLiteralDate enddate) {
		this.enddate = enddate;
	}

	/**
	 * @return the recordnum
	 */
	public Integer getRecordnum() {
		return recordnum;
	}

	/**
	 * @param recordnum the recordnum to set
	 */
	public void setRecordnum(Integer recordnum) {
		this.recordnum = recordnum;
	}

	/**
	 * @return the lastflag
	 */
	public UFBoolean getLastflag() {
		return lastflag;
	}

	/**
	 * @param lastflag the lastflag to set
	 */
	public void setLastflag(UFBoolean lastflag) {
		this.lastflag = lastflag;
	}

	/**
	 * @return the glbdef1
	 */
	public String getGlbdef1() {
		return glbdef1;
	}

	/**
	 * @param glbdef1 the glbdef1 to set
	 */
	public void setGlbdef1(String glbdef1) {
		this.glbdef1 = glbdef1;
	}

	/**
	 * @return the glbdef2
	 */
	public String getGlbdef2() {
		return glbdef2;
	}

	/**
	 * @param glbdef2 the glbdef2 to set
	 */
	public void setGlbdef2(String glbdef2) {
		this.glbdef2 = glbdef2;
	}

	/**
	 * @return the glbdef3
	 */
	public String getGlbdef3() {
		return glbdef3;
	}

	/**
	 * @param glbdef3 the glbdef3 to set
	 */
	public void setGlbdef3(String glbdef3) {
		this.glbdef3 = glbdef3;
	}

	/**
	 * @return the glbdef4
	 */
	public String getGlbdef4() {
		return glbdef4;
	}

	/**
	 * @param glbdef4 the glbdef4 to set
	 */
	public void setGlbdef4(String glbdef4) {
		this.glbdef4 = glbdef4;
	}

	/**
	 * @return the glbdef5
	 */
	public String getGlbdef5() {
		return glbdef5;
	}

	/**
	 * @param glbdef5 the glbdef5 to set
	 */
	public void setGlbdef5(String glbdef5) {
		this.glbdef5 = glbdef5;
	}

	/**
	 * @return the glbdef6
	 */
	public String getGlbdef6() {
		return glbdef6;
	}

	/**
	 * @param glbdef6 the glbdef6 to set
	 */
	public void setGlbdef6(String glbdef6) {
		this.glbdef6 = glbdef6;
	}

	/**
	 * @return the glbdef7
	 */
	public String getGlbdef7() {
		return glbdef7;
	}

	/**
	 * @param glbdef7 the glbdef7 to set
	 */
	public void setGlbdef7(String glbdef7) {
		this.glbdef7 = glbdef7;
	}

	/**
	 * @return the glbdef8
	 */
	public String getGlbdef8() {
		return glbdef8;
	}

	/**
	 * @param glbdef8 the glbdef8 to set
	 */
	public void setGlbdef8(String glbdef8) {
		this.glbdef8 = glbdef8;
	}

	@Override
	public String getTableName() {
		return "hi_psndoc_glbdef1";
	}

}
