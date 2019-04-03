package cn.edu.nun.item.listener;

import java.io.FileWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import cn.edu.nun.item.pojo.Item;
import cn.edu.nun.pojo.TbItem;
import cn.edu.nun.pojo.TbItemDesc;
import cn.edu.nun.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 监听商品添加消息，生成对应的静态页面
 * <p>Title: HtmlGenListener</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.cn</p> 
 * @version 1.0
 */
public class HtmlGenListener implements MessageListener {
	
	@Autowired
	private ItemService itemService;

	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;

	@Value("${HTML_GEN_PATH}")
	private String HTML_GEN_PATH;

	@Override
	public void onMessage(Message message) {
		try {
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			Long itemId = new Long(text);
			TbItem tbItem = itemService.getItemById(itemId);
			Item item = new Item(tbItem);
			TbItemDesc itemDesc = itemService.getItemDescById(itemId);
			Map data = new HashMap<>();
			data.put("item", item);
			data.put("itemDesc", itemDesc);
			Configuration configuration = freeMarkerConfigurer.getConfiguration();
			Template template = configuration.getTemplate("item.ftl");
			Writer out = new FileWriter(HTML_GEN_PATH + itemId + ".html");
			template.process(data, out);
			out.close();
		} catch (Exception e) {
		}

	}

}
