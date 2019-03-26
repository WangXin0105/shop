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
	
	@RequestMapping(value="/content/category/create", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel createContentCategory(Long parentId, String name) {
		ResultModel result = contentCatService.addContentCategory(parentId, name);
		return result;
	}

	@RequestMapping(value="/content/category/update", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel updateContentCategory(Long id, String name) {
		ResultModel result = contentCatService.updateContentCategory(id, name);
		return result;
	}

	@RequestMapping(value="/content/category/delete", method=RequestMethod.POST)
	@ResponseBody
	public ResultModel deleteContentCategory(Long id) {
		ResultModel result = contentCatService.deleteContentCategory(id);
		return result;
	}
	
}
