package cn.edu.nun.search.service;


import cn.edu.nun.common.pojo.SearchResultModel;

public interface SearchService {
    SearchResultModel search(String keyword,int page,int rows) throws Exception;
}
