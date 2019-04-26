<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div style="text-align: center;">
	 <a style="height: 35px;" class="easyui-linkbutton" onclick="importItems()">导入索引</a>
</div>
<script type="text/javascript">
	function importItems() {
		$.post("/index/item/import",null,function(data){
			if(data.status == 200){
				$.messager.alert('提示','导入索引库成功！');
			} else {
				$.messager.alert('提示','导入索引库失败！');
			}
		});
	}
</script>