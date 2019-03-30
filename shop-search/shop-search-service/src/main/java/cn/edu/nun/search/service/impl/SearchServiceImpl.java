package cn.edu.nun.search.service.impl;


import cn.edu.nun.common.pojo.SearchResultModel;
import cn.edu.nun.search.dao.SearchDao;
import cn.edu.nun.search.service.SearchService;
import org.apache.solr.client.solrj.SolrQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchDao searchDao;

    @Override
    public SearchResultModel search(String keyword, int page, int rows) throws Exception {
        SolrQuery query = new SolrQuery();
        query.setQuery(keyword);
        if (page <= 0){
            page = 1;
        }
        query.setStart((page-1)*rows);
        query.setRows(rows);
        query.set("df","item_title");
        query.setHighlight(true);
        query.addHighlightField("item_title");
        query.setHighlightSimplePre("<em style=\"color:red\">");
        query.setHighlightSimplePost("</em>");
        SearchResultModel search = searchDao.search(query);
        Long recordCount = search.getRecordCount();
        int totalPage = (int) (recordCount / rows);
        totalPage = recordCount % rows > 0 ? ++totalPage : totalPage;
        search.setTotalPages(totalPage);
        return search;
    }
}
