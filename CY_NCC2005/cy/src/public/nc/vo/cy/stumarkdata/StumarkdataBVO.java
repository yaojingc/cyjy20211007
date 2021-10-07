package nc.vo.cy.stumarkdata;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> 此处简要描述此类功能 </b>
 * <p>
 *   此处添加累的描述信息
 * </p>
 *  创建日期:2021-8-19
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class StumarkdataBVO extends SuperVO {
	
/**
*试卷子表主键
*/
public java.lang.String pk_stumarkd;
/**
*行号
*/
public java.lang.String rowno;
/**
*考试名称
*/
public java.lang.String testname;
/**
*考试编码
*/
public java.lang.String testcode;
/**
*进班英语成绩
*/
public nc.vo.pub.lang.UFDouble enachievement;
/**
*总得分
*/
public nc.vo.pub.lang.UFDouble totalscore;
/**
*学生类型
*/
public java.lang.String stutype;
/**
*分项一得分
*/
public nc.vo.pub.lang.UFDouble subscore1;
/**
*分项二得分
*/
public nc.vo.pub.lang.UFDouble subscore2;
/**
*分项三得分
*/
public nc.vo.pub.lang.UFDouble subscore3;
/**
*分项四得分
*/
public nc.vo.pub.lang.UFDouble subscore4;
/**
*分项五得分
*/
public nc.vo.pub.lang.UFDouble subscore5;
/**
*分项六得分
*/
public nc.vo.pub.lang.UFDouble subscore6;
/**
*分项七得分
*/
public nc.vo.pub.lang.UFDouble subscore7;
/**
*分项八得分
*/
public nc.vo.pub.lang.UFDouble subscore8;
/**
*分项九得分
*/
public nc.vo.pub.lang.UFDouble subscore9;
/**
*分项十得分
*/
public nc.vo.pub.lang.UFDouble subscore10;
/**
*自定义项1
*/
public java.lang.String def1;
/**
*自定义项2
*/
public java.lang.String def2;
/**
*自定义项3
*/
public java.lang.String def3;
/**
*自定义项4
*/
public java.lang.String def4;
/**
*自定义项5
*/
public java.lang.String def5;
/**
*自定义项6
*/
public java.lang.String def6;
/**
*自定义项7
*/
public java.lang.String def7;
/**
*自定义项8
*/
public java.lang.String def8;
/**
*自定义项9
*/
public java.lang.String def9;
/**
*自定义项10
*/
public java.lang.String def10;
/**
*自定义项11
*/
public java.lang.String def11;
/**
*自定义项12
*/
public java.lang.String def12;
/**
*自定义项13
*/
public java.lang.String def13;
/**
*自定义项14
*/
public java.lang.String def14;
/**
*自定义项15
*/
public java.lang.String def15;
/**
*自定义项16
*/
public java.lang.String def16;
/**
*自定义项17
*/
public java.lang.String def17;
/**
*自定义项18
*/
public java.lang.String def18;
/**
*自定义项19
*/
public java.lang.String def19;
/**
*自定义项20
*/
public java.lang.String def20;
/**
*自定义项21
*/
public java.lang.String def21;
/**
*自定义项22
*/
public java.lang.String def22;
/**
*自定义项23
*/
public java.lang.String def23;
/**
*自定义项24
*/
public java.lang.String def24;
/**
*自定义项25
*/
public java.lang.String def25;
/**
*自定义项26
*/
public java.lang.String def26;
/**
*自定义项27
*/
public java.lang.String def27;
/**
*自定义项28
*/
public java.lang.String def28;
/**
*自定义项29
*/
public java.lang.String def29;
/**
*自定义项30
*/
public java.lang.String def30;
/**
*上层单据主键
*/
public String pk_stumark;
/**
*时间戳
*/
public UFDateTime ts;
    
    
/**
* 属性 pk_stumarkd的Getter方法.属性名：试卷子表主键
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getPk_stumarkd() {
return this.pk_stumarkd;
} 

/**
* 属性pk_stumarkd的Setter方法.属性名：试卷子表主键
* 创建日期:2021-8-19
* @param newPk_stumarkd java.lang.String
*/
public void setPk_stumarkd ( java.lang.String pk_stumarkd) {
this.pk_stumarkd=pk_stumarkd;
} 
 
/**
* 属性 rowno的Getter方法.属性名：行号
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getRowno() {
return this.rowno;
} 

/**
* 属性rowno的Setter方法.属性名：行号
* 创建日期:2021-8-19
* @param newRowno java.lang.String
*/
public void setRowno ( java.lang.String rowno) {
this.rowno=rowno;
} 
 
/**
* 属性 testname的Getter方法.属性名：考试名称
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getTestname() {
return this.testname;
} 

/**
* 属性testname的Setter方法.属性名：考试名称
* 创建日期:2021-8-19
* @param newTestname java.lang.String
*/
public void setTestname ( java.lang.String testname) {
this.testname=testname;
} 
 
/**
* 属性 testcode的Getter方法.属性名：考试编码
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getTestcode() {
return this.testcode;
} 

/**
* 属性testcode的Setter方法.属性名：考试编码
* 创建日期:2021-8-19
* @param newTestcode java.lang.String
*/
public void setTestcode ( java.lang.String testcode) {
this.testcode=testcode;
} 
 
/**
* 属性 enachievement的Getter方法.属性名：进班英语成绩
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getEnachievement() {
return this.enachievement;
} 

/**
* 属性enachievement的Setter方法.属性名：进班英语成绩
* 创建日期:2021-8-19
* @param newEnachievement nc.vo.pub.lang.UFDouble
*/
public void setEnachievement ( nc.vo.pub.lang.UFDouble enachievement) {
this.enachievement=enachievement;
} 
 
/**
* 属性 totalscore的Getter方法.属性名：总得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getTotalscore() {
return this.totalscore;
} 

/**
* 属性totalscore的Setter方法.属性名：总得分
* 创建日期:2021-8-19
* @param newTotalscore nc.vo.pub.lang.UFDouble
*/
public void setTotalscore ( nc.vo.pub.lang.UFDouble totalscore) {
this.totalscore=totalscore;
} 
 
/**
* 属性 stutype的Getter方法.属性名：学生类型
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getStutype() {
return this.stutype;
} 

/**
* 属性stutype的Setter方法.属性名：学生类型
* 创建日期:2021-8-19
* @param newStutype java.lang.String
*/
public void setStutype ( java.lang.String stutype) {
this.stutype=stutype;
} 
 
/**
* 属性 subscore1的Getter方法.属性名：分项一得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore1() {
return this.subscore1;
} 

/**
* 属性subscore1的Setter方法.属性名：分项一得分
* 创建日期:2021-8-19
* @param newSubscore1 nc.vo.pub.lang.UFDouble
*/
public void setSubscore1 ( nc.vo.pub.lang.UFDouble subscore1) {
this.subscore1=subscore1;
} 
 
/**
* 属性 subscore2的Getter方法.属性名：分项二得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore2() {
return this.subscore2;
} 

/**
* 属性subscore2的Setter方法.属性名：分项二得分
* 创建日期:2021-8-19
* @param newSubscore2 nc.vo.pub.lang.UFDouble
*/
public void setSubscore2 ( nc.vo.pub.lang.UFDouble subscore2) {
this.subscore2=subscore2;
} 
 
/**
* 属性 subscore3的Getter方法.属性名：分项三得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore3() {
return this.subscore3;
} 

/**
* 属性subscore3的Setter方法.属性名：分项三得分
* 创建日期:2021-8-19
* @param newSubscore3 nc.vo.pub.lang.UFDouble
*/
public void setSubscore3 ( nc.vo.pub.lang.UFDouble subscore3) {
this.subscore3=subscore3;
} 
 
/**
* 属性 subscore4的Getter方法.属性名：分项四得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore4() {
return this.subscore4;
} 

/**
* 属性subscore4的Setter方法.属性名：分项四得分
* 创建日期:2021-8-19
* @param newSubscore4 nc.vo.pub.lang.UFDouble
*/
public void setSubscore4 ( nc.vo.pub.lang.UFDouble subscore4) {
this.subscore4=subscore4;
} 
 
/**
* 属性 subscore5的Getter方法.属性名：分项五得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore5() {
return this.subscore5;
} 

/**
* 属性subscore5的Setter方法.属性名：分项五得分
* 创建日期:2021-8-19
* @param newSubscore5 nc.vo.pub.lang.UFDouble
*/
public void setSubscore5 ( nc.vo.pub.lang.UFDouble subscore5) {
this.subscore5=subscore5;
} 
 
/**
* 属性 subscore6的Getter方法.属性名：分项六得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore6() {
return this.subscore6;
} 

/**
* 属性subscore6的Setter方法.属性名：分项六得分
* 创建日期:2021-8-19
* @param newSubscore6 nc.vo.pub.lang.UFDouble
*/
public void setSubscore6 ( nc.vo.pub.lang.UFDouble subscore6) {
this.subscore6=subscore6;
} 
 
/**
* 属性 subscore7的Getter方法.属性名：分项七得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore7() {
return this.subscore7;
} 

/**
* 属性subscore7的Setter方法.属性名：分项七得分
* 创建日期:2021-8-19
* @param newSubscore7 nc.vo.pub.lang.UFDouble
*/
public void setSubscore7 ( nc.vo.pub.lang.UFDouble subscore7) {
this.subscore7=subscore7;
} 
 
/**
* 属性 subscore8的Getter方法.属性名：分项八得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore8() {
return this.subscore8;
} 

/**
* 属性subscore8的Setter方法.属性名：分项八得分
* 创建日期:2021-8-19
* @param newSubscore8 nc.vo.pub.lang.UFDouble
*/
public void setSubscore8 ( nc.vo.pub.lang.UFDouble subscore8) {
this.subscore8=subscore8;
} 
 
/**
* 属性 subscore9的Getter方法.属性名：分项九得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore9() {
return this.subscore9;
} 

/**
* 属性subscore9的Setter方法.属性名：分项九得分
* 创建日期:2021-8-19
* @param newSubscore9 nc.vo.pub.lang.UFDouble
*/
public void setSubscore9 ( nc.vo.pub.lang.UFDouble subscore9) {
this.subscore9=subscore9;
} 
 
/**
* 属性 subscore10的Getter方法.属性名：分项十得分
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore10() {
return this.subscore10;
} 

/**
* 属性subscore10的Setter方法.属性名：分项十得分
* 创建日期:2021-8-19
* @param newSubscore10 nc.vo.pub.lang.UFDouble
*/
public void setSubscore10 ( nc.vo.pub.lang.UFDouble subscore10) {
this.subscore10=subscore10;
} 
 
/**
* 属性 def1的Getter方法.属性名：自定义项1
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef1() {
return this.def1;
} 

/**
* 属性def1的Setter方法.属性名：自定义项1
* 创建日期:2021-8-19
* @param newDef1 java.lang.String
*/
public void setDef1 ( java.lang.String def1) {
this.def1=def1;
} 
 
/**
* 属性 def2的Getter方法.属性名：自定义项2
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef2() {
return this.def2;
} 

/**
* 属性def2的Setter方法.属性名：自定义项2
* 创建日期:2021-8-19
* @param newDef2 java.lang.String
*/
public void setDef2 ( java.lang.String def2) {
this.def2=def2;
} 
 
/**
* 属性 def3的Getter方法.属性名：自定义项3
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef3() {
return this.def3;
} 

/**
* 属性def3的Setter方法.属性名：自定义项3
* 创建日期:2021-8-19
* @param newDef3 java.lang.String
*/
public void setDef3 ( java.lang.String def3) {
this.def3=def3;
} 
 
/**
* 属性 def4的Getter方法.属性名：自定义项4
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef4() {
return this.def4;
} 

/**
* 属性def4的Setter方法.属性名：自定义项4
* 创建日期:2021-8-19
* @param newDef4 java.lang.String
*/
public void setDef4 ( java.lang.String def4) {
this.def4=def4;
} 
 
/**
* 属性 def5的Getter方法.属性名：自定义项5
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef5() {
return this.def5;
} 

/**
* 属性def5的Setter方法.属性名：自定义项5
* 创建日期:2021-8-19
* @param newDef5 java.lang.String
*/
public void setDef5 ( java.lang.String def5) {
this.def5=def5;
} 
 
/**
* 属性 def6的Getter方法.属性名：自定义项6
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef6() {
return this.def6;
} 

/**
* 属性def6的Setter方法.属性名：自定义项6
* 创建日期:2021-8-19
* @param newDef6 java.lang.String
*/
public void setDef6 ( java.lang.String def6) {
this.def6=def6;
} 
 
/**
* 属性 def7的Getter方法.属性名：自定义项7
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef7() {
return this.def7;
} 

/**
* 属性def7的Setter方法.属性名：自定义项7
* 创建日期:2021-8-19
* @param newDef7 java.lang.String
*/
public void setDef7 ( java.lang.String def7) {
this.def7=def7;
} 
 
/**
* 属性 def8的Getter方法.属性名：自定义项8
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef8() {
return this.def8;
} 

/**
* 属性def8的Setter方法.属性名：自定义项8
* 创建日期:2021-8-19
* @param newDef8 java.lang.String
*/
public void setDef8 ( java.lang.String def8) {
this.def8=def8;
} 
 
/**
* 属性 def9的Getter方法.属性名：自定义项9
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef9() {
return this.def9;
} 

/**
* 属性def9的Setter方法.属性名：自定义项9
* 创建日期:2021-8-19
* @param newDef9 java.lang.String
*/
public void setDef9 ( java.lang.String def9) {
this.def9=def9;
} 
 
/**
* 属性 def10的Getter方法.属性名：自定义项10
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef10() {
return this.def10;
} 

/**
* 属性def10的Setter方法.属性名：自定义项10
* 创建日期:2021-8-19
* @param newDef10 java.lang.String
*/
public void setDef10 ( java.lang.String def10) {
this.def10=def10;
} 
 
/**
* 属性 def11的Getter方法.属性名：自定义项11
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef11() {
return this.def11;
} 

/**
* 属性def11的Setter方法.属性名：自定义项11
* 创建日期:2021-8-19
* @param newDef11 java.lang.String
*/
public void setDef11 ( java.lang.String def11) {
this.def11=def11;
} 
 
/**
* 属性 def12的Getter方法.属性名：自定义项12
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef12() {
return this.def12;
} 

/**
* 属性def12的Setter方法.属性名：自定义项12
* 创建日期:2021-8-19
* @param newDef12 java.lang.String
*/
public void setDef12 ( java.lang.String def12) {
this.def12=def12;
} 
 
/**
* 属性 def13的Getter方法.属性名：自定义项13
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef13() {
return this.def13;
} 

/**
* 属性def13的Setter方法.属性名：自定义项13
* 创建日期:2021-8-19
* @param newDef13 java.lang.String
*/
public void setDef13 ( java.lang.String def13) {
this.def13=def13;
} 
 
/**
* 属性 def14的Getter方法.属性名：自定义项14
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef14() {
return this.def14;
} 

/**
* 属性def14的Setter方法.属性名：自定义项14
* 创建日期:2021-8-19
* @param newDef14 java.lang.String
*/
public void setDef14 ( java.lang.String def14) {
this.def14=def14;
} 
 
/**
* 属性 def15的Getter方法.属性名：自定义项15
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef15() {
return this.def15;
} 

/**
* 属性def15的Setter方法.属性名：自定义项15
* 创建日期:2021-8-19
* @param newDef15 java.lang.String
*/
public void setDef15 ( java.lang.String def15) {
this.def15=def15;
} 
 
/**
* 属性 def16的Getter方法.属性名：自定义项16
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef16() {
return this.def16;
} 

/**
* 属性def16的Setter方法.属性名：自定义项16
* 创建日期:2021-8-19
* @param newDef16 java.lang.String
*/
public void setDef16 ( java.lang.String def16) {
this.def16=def16;
} 
 
/**
* 属性 def17的Getter方法.属性名：自定义项17
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef17() {
return this.def17;
} 

/**
* 属性def17的Setter方法.属性名：自定义项17
* 创建日期:2021-8-19
* @param newDef17 java.lang.String
*/
public void setDef17 ( java.lang.String def17) {
this.def17=def17;
} 
 
/**
* 属性 def18的Getter方法.属性名：自定义项18
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef18() {
return this.def18;
} 

/**
* 属性def18的Setter方法.属性名：自定义项18
* 创建日期:2021-8-19
* @param newDef18 java.lang.String
*/
public void setDef18 ( java.lang.String def18) {
this.def18=def18;
} 
 
/**
* 属性 def19的Getter方法.属性名：自定义项19
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef19() {
return this.def19;
} 

/**
* 属性def19的Setter方法.属性名：自定义项19
* 创建日期:2021-8-19
* @param newDef19 java.lang.String
*/
public void setDef19 ( java.lang.String def19) {
this.def19=def19;
} 
 
/**
* 属性 def20的Getter方法.属性名：自定义项20
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef20() {
return this.def20;
} 

/**
* 属性def20的Setter方法.属性名：自定义项20
* 创建日期:2021-8-19
* @param newDef20 java.lang.String
*/
public void setDef20 ( java.lang.String def20) {
this.def20=def20;
} 
 
/**
* 属性 def21的Getter方法.属性名：自定义项21
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef21() {
return this.def21;
} 

/**
* 属性def21的Setter方法.属性名：自定义项21
* 创建日期:2021-8-19
* @param newDef21 java.lang.String
*/
public void setDef21 ( java.lang.String def21) {
this.def21=def21;
} 
 
/**
* 属性 def22的Getter方法.属性名：自定义项22
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef22() {
return this.def22;
} 

/**
* 属性def22的Setter方法.属性名：自定义项22
* 创建日期:2021-8-19
* @param newDef22 java.lang.String
*/
public void setDef22 ( java.lang.String def22) {
this.def22=def22;
} 
 
/**
* 属性 def23的Getter方法.属性名：自定义项23
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef23() {
return this.def23;
} 

/**
* 属性def23的Setter方法.属性名：自定义项23
* 创建日期:2021-8-19
* @param newDef23 java.lang.String
*/
public void setDef23 ( java.lang.String def23) {
this.def23=def23;
} 
 
/**
* 属性 def24的Getter方法.属性名：自定义项24
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef24() {
return this.def24;
} 

/**
* 属性def24的Setter方法.属性名：自定义项24
* 创建日期:2021-8-19
* @param newDef24 java.lang.String
*/
public void setDef24 ( java.lang.String def24) {
this.def24=def24;
} 
 
/**
* 属性 def25的Getter方法.属性名：自定义项25
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef25() {
return this.def25;
} 

/**
* 属性def25的Setter方法.属性名：自定义项25
* 创建日期:2021-8-19
* @param newDef25 java.lang.String
*/
public void setDef25 ( java.lang.String def25) {
this.def25=def25;
} 
 
/**
* 属性 def26的Getter方法.属性名：自定义项26
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef26() {
return this.def26;
} 

/**
* 属性def26的Setter方法.属性名：自定义项26
* 创建日期:2021-8-19
* @param newDef26 java.lang.String
*/
public void setDef26 ( java.lang.String def26) {
this.def26=def26;
} 
 
/**
* 属性 def27的Getter方法.属性名：自定义项27
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef27() {
return this.def27;
} 

/**
* 属性def27的Setter方法.属性名：自定义项27
* 创建日期:2021-8-19
* @param newDef27 java.lang.String
*/
public void setDef27 ( java.lang.String def27) {
this.def27=def27;
} 
 
/**
* 属性 def28的Getter方法.属性名：自定义项28
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef28() {
return this.def28;
} 

/**
* 属性def28的Setter方法.属性名：自定义项28
* 创建日期:2021-8-19
* @param newDef28 java.lang.String
*/
public void setDef28 ( java.lang.String def28) {
this.def28=def28;
} 
 
/**
* 属性 def29的Getter方法.属性名：自定义项29
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef29() {
return this.def29;
} 

/**
* 属性def29的Setter方法.属性名：自定义项29
* 创建日期:2021-8-19
* @param newDef29 java.lang.String
*/
public void setDef29 ( java.lang.String def29) {
this.def29=def29;
} 
 
/**
* 属性 def30的Getter方法.属性名：自定义项30
*  创建日期:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef30() {
return this.def30;
} 

/**
* 属性def30的Setter方法.属性名：自定义项30
* 创建日期:2021-8-19
* @param newDef30 java.lang.String
*/
public void setDef30 ( java.lang.String def30) {
this.def30=def30;
} 
 
/**
* 属性 生成上层主键的Getter方法.属性名：上层主键
*  创建日期:2021-8-19
* @return String
*/
public String getPk_stumark(){
return this.pk_stumark;
}
/**
* 属性生成上层主键的Setter方法.属性名：上层主键
* 创建日期:2021-8-19
* @param newPk_stumark String
*/
public void setPk_stumark(String pk_stumark){
this.pk_stumark=pk_stumark;
} 
/**
* 属性 生成时间戳的Getter方法.属性名：时间戳
*  创建日期:2021-8-19
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* 属性生成时间戳的Setter方法.属性名：时间戳
* 创建日期:2021-8-19
* @param newts nc.vo.pub.lang.UFDateTime
*/
public void setTs(UFDateTime ts){
this.ts=ts;
} 
     
    @Override
    public IVOMeta getMetaData() {
    return VOMetaFactory.getInstance().getVOMeta("cy.stumarkdataBVO");
    }
   }
    