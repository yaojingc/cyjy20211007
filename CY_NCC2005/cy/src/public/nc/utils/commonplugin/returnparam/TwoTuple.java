package nc.utils.commonplugin.returnparam;

public class TwoTuple<A,B> {
	
	/**
	 * 返回两个参数的元组
	 * @author yao
	 *
	 * @param <A>
	 * @param <B>
	 */
	public final A first;
	public final B sencond;
	
	public TwoTuple(A first,B sencond) {
		this.first = first;
		this.sencond = sencond;
	}

}
