package cn.edu.nun.common.pojo;

import java.io.Serializable;
import java.util.List;

public class SearchResultModel implements Serializable {

	private long recordCount;
	private int totalPages;
	private List<SearchModel> itemList;
	public long getRecordCount() {
		return recordCount;
	}
	public void setRecordCount(long recordCount) {
		this.recordCount = recordCount;
	}
	public int getTotalPages() {
		return totalPages;
	}
	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}
	public List<SearchModel> getItemList() {
		return itemList;
	}
	public void setItemList(List<SearchModel> itemList) {
		this.itemList = itemList;
	}
	
}
