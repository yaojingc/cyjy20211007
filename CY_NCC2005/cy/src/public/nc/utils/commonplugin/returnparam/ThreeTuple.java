package nc.utils.commonplugin.returnparam;

public class ThreeTuple<A,B,C> extends TwoTuple<A, B> {

	public final C third;
	
	public ThreeTuple(A first,B sencond,C third) {
		super(first,sencond);
		this.third = third;
	}
	
}
