package cn.edu.nun.controller;

import java.util.List;

import cn.edu.nun.common.pojo.TreeDataModel;
import cn.edu.nun.common.utils.ResultModel;
import cn.edu.nun.content.service.ContentCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class ContentCatController {

	@Autowired
	private ContentCatService contentCatService;
	
	@RequestMapping("/content/category/list")
	@ResponseBody
	public List<TreeDataModel> getContentCatList(@RequestParam(name="id", defaultValue="0")Long parentId) {
		List<TreeDataModel> list = contentCatService.getContentCatList(parentId);
		return list;
	}
	
	/**
	 * 添加分类节点
	 */
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel createContentCategory(Long parentId, String name) {
		//调用服务添加节点
		ResultModel Result = contentCatService.addContentCategory(parentId, name);
		return Result;
	}
	
	
}
