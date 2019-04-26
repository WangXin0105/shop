<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>后台管理系统</title>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/gray/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.4.1/themes/icon.css" />
	<link rel="stylesheet" type="text/css" href="css/e3.css" />
	<link rel="stylesheet" type="text/css" href="css/default.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.4.1/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<style type="text/css">
		.content {
			padding: 10px 10px 10px 10px;
		}
		.left_menu {
			background: #deb887;
			color: #000;
		}
	</style>
</head>
<body class="easyui-layout">
<!-- 头部标题 -->
<div data-options="region:'north',border:false" style="height:110px; padding:5px; background:#F3F3F3">
	<%--<span class="northTitle">后台管理系统</span>
    <span class="loginInfo">登录用户：admin&nbsp;&nbsp;姓名：管理员&nbsp;&nbsp;角色：系统管理员</span>--%>
	<div style="width: 20%;float: left;font-size: 30px;line-height: 110px;margin-left: 50px;">后台管理主页</div>
	<div id="goodManager" style="width: 10%;float: left;height: 100%;background: burlywood;" class="easyui-linkbutton" onclick="showManager(this);">商品管理</div>
	<div id="contentManager" style="width: 10%;float: left;height: 100%;background: burlywood;margin-left: 5%;" class="easyui-linkbutton" onclick="showManager(this);">主页管理</div>
	<div id="indexManager" style="width: 10%;float: left;height: 100%;background: burlywood;margin-left: 5%;" class="easyui-linkbutton" onclick="showManager(this);">索引管理</div>
	<div id="sallerLogin" style="width: 10%;float: left;height: 100%;background: burlywood;margin-left: 5%;" class="easyui-linkbutton" onclick="">登陆</div>
</div>
<div data-options="region:'west',title:'',split:true" style="width:300px;">
	<ul id="menu" class="easyui-tree" style="margin-top: 10px;margin-left: 45px;">
		<li>
			<span>商品管理</span>
			<ul>
				<li data-options="attributes:{'url':'item-add'}">新增商品</li>
				<li data-options="attributes:{'url':'item-list'}">查询商品</li>
				<li data-options="attributes:{'url':'item-param-list'}">规格参数</li>
			</ul>
		</li>
		<li>
			<span>主页管理</span>
			<ul>
				<li data-options="attributes:{'url':'content-category'}">内容分类管理</li>
				<li data-options="attributes:{'url':'content'}">内容管理</li>
			</ul>
		</li>
		<li>
			<span>索引管理</span>
			<ul>
				<li data-options="attributes:{'url':'index-item'}">导入索引</li>
			</ul>
		</li>
	</ul>
</div>
<div data-options="region:'center',title:''">
	<div id="tabs" class="easyui-tabs">

	</div>
</div>
<!-- 页脚信息 -->
<div data-options="region:'south',border:false" style="height:10px; background:#F3F3F3; padding:2px; vertical-align:middle;">
	<span id="sysVersion"></span>
	<span id="nowTime"></span>
</div>
<script type="text/javascript">
    $(function(){
        $('#menu').tree({
            onClick: function(node){
                if($('#menu').tree("isLeaf",node.target)){
                    var tabs = $("#tabs");
                    var tab = tabs.tabs("getTab",node.text);
                    if(tab){
                        tabs.tabs("select",node.text);
                    }else{
                        tabs.tabs('add',{
                            title:node.text,
                            href: node.attributes.url,
                            closable:true,
                            bodyCls:"content"
                        });
                    }
                }
            }
        });
        $(".tree-icon,.tree-file").removeClass("tree-icon tree-file");
        $(".tree-icon,.tree-folder").removeClass("tree-icon tree-folder tree-folder-open tree-folder-closed");
        $("#menu .tree-expanded").removeClass("tree-expanded");
        $("#menu #_easyui_tree_1,#_easyui_tree_5,#_easyui_tree_8").addClass("left_menu");
        $("#tabs .tabs-wrap").hide();
        $("#menu").children("li").eq(0).hide();
        $("#menu").children("li").eq(1).hide();
        $("#menu").children("li").eq(2).hide();
    });
    setInterval("document.getElementById('nowTime').innerHTML=new Date().toLocaleString()+' 星期'+'日一二三四五六'.charAt(new Date().getDay());",1000);

    function showManager(ele) {
        if (ele.id == "goodManager"){
            $("#menu").children("li").eq(0).show();
            $("#menu").children("li").eq(1).hide();
            $("#menu").children("li").eq(2).hide();
        } else if (ele.id == "contentManager") {
            $("#menu").children("li").eq(0).hide();
            $("#menu").children("li").eq(1).show();
            $("#menu").children("li").eq(2).hide();
        } else if (ele.id == "indexManager") {
            $("#menu").children("li").eq(0).hide();
            $("#menu").children("li").eq(1).hide();
            $("#menu").children("li").eq(2).show();
        }
    }
</script>
</body>
</html>