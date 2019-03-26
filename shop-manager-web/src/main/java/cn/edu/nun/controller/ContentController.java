package cn.edu.nun.controller;

import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.content.service.ContentService;
import cn.edu.nun.pojo.TbContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ContentController {
	
	@Autowired
	private ContentService contentService;

	@RequestMapping(value="/content/save", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel addContent(TbContent content) {
		ResultModel result = contentService.addContent(content);
		return result;
	}
}
