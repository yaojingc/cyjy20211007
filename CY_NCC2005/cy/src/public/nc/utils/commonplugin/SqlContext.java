package nc.utils.commonplugin;

/**
 * 学校档案新增，修改sql
 */
public class SqlContext {
	/*
	 * 
	 */
	public static final String INSERT_BUSIN = "insert into cy_schoolbasics_busin (pk_busin,def1,def2,def3,def4,def5,Pk_School,Dr,ts)values(?,?,?,?,?,?,?,'0',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))";

	public static final String INSERT_CLASS = "insert into Cy_Schoolbasics_Class (pk_class,grade,psnnum,whetherclas,cause,Pk_School,Dr,ts)values(?,?,?,?,?,?,'0',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))";

	public static final String UPDATE_CLASS = "update Cy_Schoolbasics_Class set grade=?,psnnum=?,whetherclas=?,cause=?,ts=? where pk_class=?";

	public static final String UPDATE_CLASS_D = "update Cy_Schoolbasics_Class set dr = '1' where pk_class=?";

	public static final String INSERT_LINK = "insert into cy_schoolBasics_Link (pk_link,name,position,duty,birthday,linkman1,linkman2,linkman3,Pk_School,Dr,ts)\r\n"
			+ "values(?,?,?,?,?,?,?,?,?,'0',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))";

	public static final String UPDATE_LINK = "update cy_schoolBasics_Link set name=?,position=?,duty=?,birthday=?,linkman1=?,linkman2=?,linkman3=?,ts=? where pk_link =?";

	public static final String UPDATE_LINK_D = "update cy_schoolBasics_Link set dr = '1' where pk_link=?";

	public static final String INSERT_TOP = "insert into Cy_Schoolbasics_Top (pk_top,visitobj,solveproblem,visitpatrol,stablelevel,redornot,gradepsnnum,isexistside,specialnote,visitdate,Pk_School,Dr,ts)\r\n"
			+ "values(?,?,?,?,?,?,?,?,?,?,?,'0',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))";

	public static final String UPDATE_TOP = "update Cy_Schoolbasics_Top set visitobj=?,solveproblem=?,visitpatrol=?,stablelevel=?,redornot=?,gradepsnnum=?,isexistside=?,specialnote=?,visitdate=?,ts=? where pk_top=?";

	public static final String UPDATE_TOP_D = "update Cy_Schoolbasics_Top set dr = '1' where pk_top=?";

	public static final String INSERT_VISIT = "insert into Cy_Schoolbasics_Visit (pk_visit,busilevel,develsituation,gradenum,competitor,rivaldevelsituation,gkgrade,intedegree,specialnote,Pk_School,Dr,ts)\r\n"
			+ "values(?,?,?,?,?,?,?,?,?,?,'0',to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'))";

	public static final String UPDATE_VISIT = "update Cy_Schoolbasics_Visit set busilevel=?,develsituation=?,gradenum=?,competitor=?,rivaldevelsituation=?,gkgrade=?,intedegree=?,specialnote=?,ts=? where pk_visit=?";

	public static final String UPDATE_VISIT_D = "update Cy_Schoolbasics_Visit set dr = '1' where pk_visit=?";
}
