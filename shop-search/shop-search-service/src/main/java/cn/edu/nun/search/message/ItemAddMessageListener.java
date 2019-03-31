package cn.edu.nun.search.message;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import cn.edu.nun.common.pojo.SearchModel;
import cn.edu.nun.search.mapper.ItemMapper;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;


public class ItemAddMessageListener implements MessageListener {
	
	@Autowired
	private ItemMapper itemMapper;

	@Autowired
	private SolrServer solrServer;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long id = new Long(text);
			SearchModel item = itemMapper.getItemById(id);
			SolrInputDocument document = new SolrInputDocument();
			document.addField("id",item.getId());
			document.addField("item_title",item.getTitle());
			document.addField("item_sell_point",item.getSell_point());
			document.addField("item_price",item.getPrice());
			document.addField("item_image",item.getImage());
			document.addField("item_category_name",item.getCategory_name());
			solrServer.add(document);
			solrServer.commit();
		} catch (Exception e){
			e.printStackTrace();
		}


	}

}
