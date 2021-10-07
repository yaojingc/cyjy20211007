package nc.vo.cy.stumarkdata;

import nc.vo.pub.IVOMeta;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.model.meta.entity.vo.VOMetaFactory;

/**
 * <b> �˴���Ҫ�������๦�� </b>
 * <p>
 *   �˴�����۵�������Ϣ
 * </p>
 *  ��������:2021-8-19
 * @author yonyouBQ
 * @version NCPrj ??
 */
 
public class StumarkdataBVO extends SuperVO {
	
/**
*�Ծ��ӱ�����
*/
public java.lang.String pk_stumarkd;
/**
*�к�
*/
public java.lang.String rowno;
/**
*��������
*/
public java.lang.String testname;
/**
*���Ա���
*/
public java.lang.String testcode;
/**
*����Ӣ��ɼ�
*/
public nc.vo.pub.lang.UFDouble enachievement;
/**
*�ܵ÷�
*/
public nc.vo.pub.lang.UFDouble totalscore;
/**
*ѧ������
*/
public java.lang.String stutype;
/**
*����һ�÷�
*/
public nc.vo.pub.lang.UFDouble subscore1;
/**
*������÷�
*/
public nc.vo.pub.lang.UFDouble subscore2;
/**
*�������÷�
*/
public nc.vo.pub.lang.UFDouble subscore3;
/**
*�����ĵ÷�
*/
public nc.vo.pub.lang.UFDouble subscore4;
/**
*������÷�
*/
public nc.vo.pub.lang.UFDouble subscore5;
/**
*�������÷�
*/
public nc.vo.pub.lang.UFDouble subscore6;
/**
*�����ߵ÷�
*/
public nc.vo.pub.lang.UFDouble subscore7;
/**
*����˵÷�
*/
public nc.vo.pub.lang.UFDouble subscore8;
/**
*����ŵ÷�
*/
public nc.vo.pub.lang.UFDouble subscore9;
/**
*����ʮ�÷�
*/
public nc.vo.pub.lang.UFDouble subscore10;
/**
*�Զ�����1
*/
public java.lang.String def1;
/**
*�Զ�����2
*/
public java.lang.String def2;
/**
*�Զ�����3
*/
public java.lang.String def3;
/**
*�Զ�����4
*/
public java.lang.String def4;
/**
*�Զ�����5
*/
public java.lang.String def5;
/**
*�Զ�����6
*/
public java.lang.String def6;
/**
*�Զ�����7
*/
public java.lang.String def7;
/**
*�Զ�����8
*/
public java.lang.String def8;
/**
*�Զ�����9
*/
public java.lang.String def9;
/**
*�Զ�����10
*/
public java.lang.String def10;
/**
*�Զ�����11
*/
public java.lang.String def11;
/**
*�Զ�����12
*/
public java.lang.String def12;
/**
*�Զ�����13
*/
public java.lang.String def13;
/**
*�Զ�����14
*/
public java.lang.String def14;
/**
*�Զ�����15
*/
public java.lang.String def15;
/**
*�Զ�����16
*/
public java.lang.String def16;
/**
*�Զ�����17
*/
public java.lang.String def17;
/**
*�Զ�����18
*/
public java.lang.String def18;
/**
*�Զ�����19
*/
public java.lang.String def19;
/**
*�Զ�����20
*/
public java.lang.String def20;
/**
*�Զ�����21
*/
public java.lang.String def21;
/**
*�Զ�����22
*/
public java.lang.String def22;
/**
*�Զ�����23
*/
public java.lang.String def23;
/**
*�Զ�����24
*/
public java.lang.String def24;
/**
*�Զ�����25
*/
public java.lang.String def25;
/**
*�Զ�����26
*/
public java.lang.String def26;
/**
*�Զ�����27
*/
public java.lang.String def27;
/**
*�Զ�����28
*/
public java.lang.String def28;
/**
*�Զ�����29
*/
public java.lang.String def29;
/**
*�Զ�����30
*/
public java.lang.String def30;
/**
*�ϲ㵥������
*/
public String pk_stumark;
/**
*ʱ���
*/
public UFDateTime ts;
    
    
/**
* ���� pk_stumarkd��Getter����.���������Ծ��ӱ�����
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getPk_stumarkd() {
return this.pk_stumarkd;
} 

/**
* ����pk_stumarkd��Setter����.���������Ծ��ӱ�����
* ��������:2021-8-19
* @param newPk_stumarkd java.lang.String
*/
public void setPk_stumarkd ( java.lang.String pk_stumarkd) {
this.pk_stumarkd=pk_stumarkd;
} 
 
/**
* ���� rowno��Getter����.���������к�
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getRowno() {
return this.rowno;
} 

/**
* ����rowno��Setter����.���������к�
* ��������:2021-8-19
* @param newRowno java.lang.String
*/
public void setRowno ( java.lang.String rowno) {
this.rowno=rowno;
} 
 
/**
* ���� testname��Getter����.����������������
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getTestname() {
return this.testname;
} 

/**
* ����testname��Setter����.����������������
* ��������:2021-8-19
* @param newTestname java.lang.String
*/
public void setTestname ( java.lang.String testname) {
this.testname=testname;
} 
 
/**
* ���� testcode��Getter����.�����������Ա���
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getTestcode() {
return this.testcode;
} 

/**
* ����testcode��Setter����.�����������Ա���
* ��������:2021-8-19
* @param newTestcode java.lang.String
*/
public void setTestcode ( java.lang.String testcode) {
this.testcode=testcode;
} 
 
/**
* ���� enachievement��Getter����.������������Ӣ��ɼ�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getEnachievement() {
return this.enachievement;
} 

/**
* ����enachievement��Setter����.������������Ӣ��ɼ�
* ��������:2021-8-19
* @param newEnachievement nc.vo.pub.lang.UFDouble
*/
public void setEnachievement ( nc.vo.pub.lang.UFDouble enachievement) {
this.enachievement=enachievement;
} 
 
/**
* ���� totalscore��Getter����.���������ܵ÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getTotalscore() {
return this.totalscore;
} 

/**
* ����totalscore��Setter����.���������ܵ÷�
* ��������:2021-8-19
* @param newTotalscore nc.vo.pub.lang.UFDouble
*/
public void setTotalscore ( nc.vo.pub.lang.UFDouble totalscore) {
this.totalscore=totalscore;
} 
 
/**
* ���� stutype��Getter����.��������ѧ������
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getStutype() {
return this.stutype;
} 

/**
* ����stutype��Setter����.��������ѧ������
* ��������:2021-8-19
* @param newStutype java.lang.String
*/
public void setStutype ( java.lang.String stutype) {
this.stutype=stutype;
} 
 
/**
* ���� subscore1��Getter����.������������һ�÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore1() {
return this.subscore1;
} 

/**
* ����subscore1��Setter����.������������һ�÷�
* ��������:2021-8-19
* @param newSubscore1 nc.vo.pub.lang.UFDouble
*/
public void setSubscore1 ( nc.vo.pub.lang.UFDouble subscore1) {
this.subscore1=subscore1;
} 
 
/**
* ���� subscore2��Getter����.��������������÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore2() {
return this.subscore2;
} 

/**
* ����subscore2��Setter����.��������������÷�
* ��������:2021-8-19
* @param newSubscore2 nc.vo.pub.lang.UFDouble
*/
public void setSubscore2 ( nc.vo.pub.lang.UFDouble subscore2) {
this.subscore2=subscore2;
} 
 
/**
* ���� subscore3��Getter����.���������������÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore3() {
return this.subscore3;
} 

/**
* ����subscore3��Setter����.���������������÷�
* ��������:2021-8-19
* @param newSubscore3 nc.vo.pub.lang.UFDouble
*/
public void setSubscore3 ( nc.vo.pub.lang.UFDouble subscore3) {
this.subscore3=subscore3;
} 
 
/**
* ���� subscore4��Getter����.�������������ĵ÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore4() {
return this.subscore4;
} 

/**
* ����subscore4��Setter����.�������������ĵ÷�
* ��������:2021-8-19
* @param newSubscore4 nc.vo.pub.lang.UFDouble
*/
public void setSubscore4 ( nc.vo.pub.lang.UFDouble subscore4) {
this.subscore4=subscore4;
} 
 
/**
* ���� subscore5��Getter����.��������������÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore5() {
return this.subscore5;
} 

/**
* ����subscore5��Setter����.��������������÷�
* ��������:2021-8-19
* @param newSubscore5 nc.vo.pub.lang.UFDouble
*/
public void setSubscore5 ( nc.vo.pub.lang.UFDouble subscore5) {
this.subscore5=subscore5;
} 
 
/**
* ���� subscore6��Getter����.���������������÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore6() {
return this.subscore6;
} 

/**
* ����subscore6��Setter����.���������������÷�
* ��������:2021-8-19
* @param newSubscore6 nc.vo.pub.lang.UFDouble
*/
public void setSubscore6 ( nc.vo.pub.lang.UFDouble subscore6) {
this.subscore6=subscore6;
} 
 
/**
* ���� subscore7��Getter����.�������������ߵ÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore7() {
return this.subscore7;
} 

/**
* ����subscore7��Setter����.�������������ߵ÷�
* ��������:2021-8-19
* @param newSubscore7 nc.vo.pub.lang.UFDouble
*/
public void setSubscore7 ( nc.vo.pub.lang.UFDouble subscore7) {
this.subscore7=subscore7;
} 
 
/**
* ���� subscore8��Getter����.������������˵÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore8() {
return this.subscore8;
} 

/**
* ����subscore8��Setter����.������������˵÷�
* ��������:2021-8-19
* @param newSubscore8 nc.vo.pub.lang.UFDouble
*/
public void setSubscore8 ( nc.vo.pub.lang.UFDouble subscore8) {
this.subscore8=subscore8;
} 
 
/**
* ���� subscore9��Getter����.������������ŵ÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore9() {
return this.subscore9;
} 

/**
* ����subscore9��Setter����.������������ŵ÷�
* ��������:2021-8-19
* @param newSubscore9 nc.vo.pub.lang.UFDouble
*/
public void setSubscore9 ( nc.vo.pub.lang.UFDouble subscore9) {
this.subscore9=subscore9;
} 
 
/**
* ���� subscore10��Getter����.������������ʮ�÷�
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDouble
*/
public nc.vo.pub.lang.UFDouble getSubscore10() {
return this.subscore10;
} 

/**
* ����subscore10��Setter����.������������ʮ�÷�
* ��������:2021-8-19
* @param newSubscore10 nc.vo.pub.lang.UFDouble
*/
public void setSubscore10 ( nc.vo.pub.lang.UFDouble subscore10) {
this.subscore10=subscore10;
} 
 
/**
* ���� def1��Getter����.���������Զ�����1
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef1() {
return this.def1;
} 

/**
* ����def1��Setter����.���������Զ�����1
* ��������:2021-8-19
* @param newDef1 java.lang.String
*/
public void setDef1 ( java.lang.String def1) {
this.def1=def1;
} 
 
/**
* ���� def2��Getter����.���������Զ�����2
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef2() {
return this.def2;
} 

/**
* ����def2��Setter����.���������Զ�����2
* ��������:2021-8-19
* @param newDef2 java.lang.String
*/
public void setDef2 ( java.lang.String def2) {
this.def2=def2;
} 
 
/**
* ���� def3��Getter����.���������Զ�����3
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef3() {
return this.def3;
} 

/**
* ����def3��Setter����.���������Զ�����3
* ��������:2021-8-19
* @param newDef3 java.lang.String
*/
public void setDef3 ( java.lang.String def3) {
this.def3=def3;
} 
 
/**
* ���� def4��Getter����.���������Զ�����4
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef4() {
return this.def4;
} 

/**
* ����def4��Setter����.���������Զ�����4
* ��������:2021-8-19
* @param newDef4 java.lang.String
*/
public void setDef4 ( java.lang.String def4) {
this.def4=def4;
} 
 
/**
* ���� def5��Getter����.���������Զ�����5
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef5() {
return this.def5;
} 

/**
* ����def5��Setter����.���������Զ�����5
* ��������:2021-8-19
* @param newDef5 java.lang.String
*/
public void setDef5 ( java.lang.String def5) {
this.def5=def5;
} 
 
/**
* ���� def6��Getter����.���������Զ�����6
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef6() {
return this.def6;
} 

/**
* ����def6��Setter����.���������Զ�����6
* ��������:2021-8-19
* @param newDef6 java.lang.String
*/
public void setDef6 ( java.lang.String def6) {
this.def6=def6;
} 
 
/**
* ���� def7��Getter����.���������Զ�����7
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef7() {
return this.def7;
} 

/**
* ����def7��Setter����.���������Զ�����7
* ��������:2021-8-19
* @param newDef7 java.lang.String
*/
public void setDef7 ( java.lang.String def7) {
this.def7=def7;
} 
 
/**
* ���� def8��Getter����.���������Զ�����8
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef8() {
return this.def8;
} 

/**
* ����def8��Setter����.���������Զ�����8
* ��������:2021-8-19
* @param newDef8 java.lang.String
*/
public void setDef8 ( java.lang.String def8) {
this.def8=def8;
} 
 
/**
* ���� def9��Getter����.���������Զ�����9
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef9() {
return this.def9;
} 

/**
* ����def9��Setter����.���������Զ�����9
* ��������:2021-8-19
* @param newDef9 java.lang.String
*/
public void setDef9 ( java.lang.String def9) {
this.def9=def9;
} 
 
/**
* ���� def10��Getter����.���������Զ�����10
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef10() {
return this.def10;
} 

/**
* ����def10��Setter����.���������Զ�����10
* ��������:2021-8-19
* @param newDef10 java.lang.String
*/
public void setDef10 ( java.lang.String def10) {
this.def10=def10;
} 
 
/**
* ���� def11��Getter����.���������Զ�����11
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef11() {
return this.def11;
} 

/**
* ����def11��Setter����.���������Զ�����11
* ��������:2021-8-19
* @param newDef11 java.lang.String
*/
public void setDef11 ( java.lang.String def11) {
this.def11=def11;
} 
 
/**
* ���� def12��Getter����.���������Զ�����12
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef12() {
return this.def12;
} 

/**
* ����def12��Setter����.���������Զ�����12
* ��������:2021-8-19
* @param newDef12 java.lang.String
*/
public void setDef12 ( java.lang.String def12) {
this.def12=def12;
} 
 
/**
* ���� def13��Getter����.���������Զ�����13
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef13() {
return this.def13;
} 

/**
* ����def13��Setter����.���������Զ�����13
* ��������:2021-8-19
* @param newDef13 java.lang.String
*/
public void setDef13 ( java.lang.String def13) {
this.def13=def13;
} 
 
/**
* ���� def14��Getter����.���������Զ�����14
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef14() {
return this.def14;
} 

/**
* ����def14��Setter����.���������Զ�����14
* ��������:2021-8-19
* @param newDef14 java.lang.String
*/
public void setDef14 ( java.lang.String def14) {
this.def14=def14;
} 
 
/**
* ���� def15��Getter����.���������Զ�����15
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef15() {
return this.def15;
} 

/**
* ����def15��Setter����.���������Զ�����15
* ��������:2021-8-19
* @param newDef15 java.lang.String
*/
public void setDef15 ( java.lang.String def15) {
this.def15=def15;
} 
 
/**
* ���� def16��Getter����.���������Զ�����16
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef16() {
return this.def16;
} 

/**
* ����def16��Setter����.���������Զ�����16
* ��������:2021-8-19
* @param newDef16 java.lang.String
*/
public void setDef16 ( java.lang.String def16) {
this.def16=def16;
} 
 
/**
* ���� def17��Getter����.���������Զ�����17
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef17() {
return this.def17;
} 

/**
* ����def17��Setter����.���������Զ�����17
* ��������:2021-8-19
* @param newDef17 java.lang.String
*/
public void setDef17 ( java.lang.String def17) {
this.def17=def17;
} 
 
/**
* ���� def18��Getter����.���������Զ�����18
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef18() {
return this.def18;
} 

/**
* ����def18��Setter����.���������Զ�����18
* ��������:2021-8-19
* @param newDef18 java.lang.String
*/
public void setDef18 ( java.lang.String def18) {
this.def18=def18;
} 
 
/**
* ���� def19��Getter����.���������Զ�����19
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef19() {
return this.def19;
} 

/**
* ����def19��Setter����.���������Զ�����19
* ��������:2021-8-19
* @param newDef19 java.lang.String
*/
public void setDef19 ( java.lang.String def19) {
this.def19=def19;
} 
 
/**
* ���� def20��Getter����.���������Զ�����20
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef20() {
return this.def20;
} 

/**
* ����def20��Setter����.���������Զ�����20
* ��������:2021-8-19
* @param newDef20 java.lang.String
*/
public void setDef20 ( java.lang.String def20) {
this.def20=def20;
} 
 
/**
* ���� def21��Getter����.���������Զ�����21
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef21() {
return this.def21;
} 

/**
* ����def21��Setter����.���������Զ�����21
* ��������:2021-8-19
* @param newDef21 java.lang.String
*/
public void setDef21 ( java.lang.String def21) {
this.def21=def21;
} 
 
/**
* ���� def22��Getter����.���������Զ�����22
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef22() {
return this.def22;
} 

/**
* ����def22��Setter����.���������Զ�����22
* ��������:2021-8-19
* @param newDef22 java.lang.String
*/
public void setDef22 ( java.lang.String def22) {
this.def22=def22;
} 
 
/**
* ���� def23��Getter����.���������Զ�����23
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef23() {
return this.def23;
} 

/**
* ����def23��Setter����.���������Զ�����23
* ��������:2021-8-19
* @param newDef23 java.lang.String
*/
public void setDef23 ( java.lang.String def23) {
this.def23=def23;
} 
 
/**
* ���� def24��Getter����.���������Զ�����24
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef24() {
return this.def24;
} 

/**
* ����def24��Setter����.���������Զ�����24
* ��������:2021-8-19
* @param newDef24 java.lang.String
*/
public void setDef24 ( java.lang.String def24) {
this.def24=def24;
} 
 
/**
* ���� def25��Getter����.���������Զ�����25
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef25() {
return this.def25;
} 

/**
* ����def25��Setter����.���������Զ�����25
* ��������:2021-8-19
* @param newDef25 java.lang.String
*/
public void setDef25 ( java.lang.String def25) {
this.def25=def25;
} 
 
/**
* ���� def26��Getter����.���������Զ�����26
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef26() {
return this.def26;
} 

/**
* ����def26��Setter����.���������Զ�����26
* ��������:2021-8-19
* @param newDef26 java.lang.String
*/
public void setDef26 ( java.lang.String def26) {
this.def26=def26;
} 
 
/**
* ���� def27��Getter����.���������Զ�����27
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef27() {
return this.def27;
} 

/**
* ����def27��Setter����.���������Զ�����27
* ��������:2021-8-19
* @param newDef27 java.lang.String
*/
public void setDef27 ( java.lang.String def27) {
this.def27=def27;
} 
 
/**
* ���� def28��Getter����.���������Զ�����28
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef28() {
return this.def28;
} 

/**
* ����def28��Setter����.���������Զ�����28
* ��������:2021-8-19
* @param newDef28 java.lang.String
*/
public void setDef28 ( java.lang.String def28) {
this.def28=def28;
} 
 
/**
* ���� def29��Getter����.���������Զ�����29
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef29() {
return this.def29;
} 

/**
* ����def29��Setter����.���������Զ�����29
* ��������:2021-8-19
* @param newDef29 java.lang.String
*/
public void setDef29 ( java.lang.String def29) {
this.def29=def29;
} 
 
/**
* ���� def30��Getter����.���������Զ�����30
*  ��������:2021-8-19
* @return java.lang.String
*/
public java.lang.String getDef30() {
return this.def30;
} 

/**
* ����def30��Setter����.���������Զ�����30
* ��������:2021-8-19
* @param newDef30 java.lang.String
*/
public void setDef30 ( java.lang.String def30) {
this.def30=def30;
} 
 
/**
* ���� �����ϲ�������Getter����.���������ϲ�����
*  ��������:2021-8-19
* @return String
*/
public String getPk_stumark(){
return this.pk_stumark;
}
/**
* ���������ϲ�������Setter����.���������ϲ�����
* ��������:2021-8-19
* @param newPk_stumark String
*/
public void setPk_stumark(String pk_stumark){
this.pk_stumark=pk_stumark;
} 
/**
* ���� ����ʱ�����Getter����.��������ʱ���
*  ��������:2021-8-19
* @return nc.vo.pub.lang.UFDateTime
*/
public UFDateTime getTs() {
return this.ts;
}
/**
* ��������ʱ�����Setter����.��������ʱ���
* ��������:2021-8-19
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
    