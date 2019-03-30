package cn.edu.nun.search.dao;

import cn.edu.nun.common.pojo.SearchModel;
import cn.edu.nun.common.pojo.SearchResultModel;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class SearchDao {

    @Autowired
    private SolrServer solrServer;

    public SearchResultModel search(SolrQuery query) throws Exception {
        QueryResponse queryResponse = solrServer.query(query);
        SolrDocumentList solrDocumentList = queryResponse.getResults();
        Long nunFound = solrDocumentList.getNumFound();
        SearchResultModel result = new SearchResultModel();
        result.setRecordCount(nunFound);
        Map<String, Map<String, List<String>>> highlighting = queryResponse.getHighlighting();
        List<SearchModel> itemList = new ArrayList<SearchModel>();
        for (SolrDocument solrDocument : solrDocumentList){
            SearchModel item = new SearchModel();
            item.setId((String) solrDocument.get("id"));
            item.setCategory_name((String) solrDocument.get("item_category_name"));
            item.setImage((String) solrDocument.get("item_image"));
            item.setPrice((long) solrDocument.get("item_price"));
            item.setSell_point((String) solrDocument.get("item_sell_point"));

            List<String> list = highlighting.get(solrDocument.get("id")).get("item_title");
            String title = "";
            if (list != null && list.size() > 0){
                title = list.get(0);
            } else {
                title = (String) solrDocument.get("item_title");
            }
            item.setTitle(title);
            itemList.add(item);
        }
        result.setItemList(itemList);
        return result;
    }

}
