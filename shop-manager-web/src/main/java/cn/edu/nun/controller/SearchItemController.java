package cn.edu.nun.controller;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.search.service.SearchItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchItemController {
	
	@Autowired
	private SearchItemService searchItemService;

	@RequestMapping("/index/item/import")
	@ResponseBody
	public ResultModel importItemList() {
		ResultModel result = searchItemService.importAllItems();
		return result;
		
	}
}
