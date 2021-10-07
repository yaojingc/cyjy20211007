package nc.utils.commonplugin.returnparam;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

import nc.vo.cy.clsschedule.ClsscheduleBVO;
import nc.vo.cy.studentfile.StudentHVO;

public class ReturnParamUtil  {

	
	public static TwoTuple<ClsscheduleBVO, Map<String, List<StudentHVO>>> returnTwoParamForStuTree(ClsscheduleBVO A,
			Map<String, List<StudentHVO>> B) {		
		return new TwoTuple<ClsscheduleBVO,Map<String, List<StudentHVO>>>(A,B);
	}
	
	
	public static TwoTuple<String, SortedMap> returnTwoParamForPayment(String A,
			SortedMap B) {		
		return new TwoTuple<String,SortedMap>(A,B);
	}
	
	/**
	 * 返回两个参数的元组
	 * @author yao
	 *
	 * @param <A>
	 * @param <B>
	 */
	static class TwoTuple<A,B> {
		public final A first;
		public final B sencond;
		
		public TwoTuple(A first,B sencond) {
			this.first = first;
			this.sencond = sencond;
		}
	}
	
	/**
	 * 返回三个参数的元组
	 * @author yao
	 *
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 */
	class ThreeTuple<A,B,C> extends TwoTuple<A,B> {
		public final C third;
		public ThreeTuple(A first,B sencond,C third) {
			super(first,sencond);
			this.third = third;
		}
	}
	
	
	/**
	 * 返回四个参数的元组
	 * @author yao
	 *
	 * @param <A>
	 * @param <B>
	 * @param <C>
	 */
	class FourTuple<A,B,C,D> extends ThreeTuple<A,B,C> {
		public final D fourth;
		public FourTuple(A first,B sencond,C third,D fourth) {
			super(first,sencond,third);
			this.fourth = fourth;
		}
	}
	
}
