package nc.utils.commonplugin.PushRequest;

public class QueryUserPOJO {
	private String searchcode;  // 	string 	否 	是 	查询条件   
	private String index;		// 	int 	否 	否 	第几页/默认1   
	private String size;		// 	int 	否 	否 	一页大小/默认20   
	private String sortType;	// 	string 	否 	否 	排序字段，默认按字段升序排列
	public String getSearchcode() {
		return searchcode;
	}
	public void setSearchcode(String searchcode) {
		this.searchcode = searchcode;
	}
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getSortType() {
		return sortType;
	}
	public void setSortType(String sortType) {
		this.sortType = sortType;
	}
}
