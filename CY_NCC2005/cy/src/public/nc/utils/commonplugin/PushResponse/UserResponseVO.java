package nc.utils.commonplugin.PushResponse;

public class UserResponseVO {
	private String number;//当前页
    private String size;//一页大小
    private String totalPages;//总页数 nc.utils.commonplugin.PushResponse;
    private UserDetailVO[] content;
    
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(String totalPages) {
		this.totalPages = totalPages;
	}
	public UserDetailVO[] getContent() {
		return content;
	}
	public void setContent(UserDetailVO[] content) {
		this.content = content;
	}

}
