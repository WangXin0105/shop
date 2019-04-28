<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<link href="/js/kindeditor-4.1.10/themes/default/default.css" type="text/css" rel="stylesheet">
<div style="padding:10px 10px 10px 10px">
    <table class="easyui-datagrid" id="orderItemList" title="" style="height: 520px"
           data-options="pagination:true,url:'/order/dect',method:'get',pageSize:10">
        <thead>
        <tr>
            <th data-options="field:'itemId',width:160">商品号</th>
            <th data-options="field:'title',width:200">商品主题</th>
            <th data-options="field:'picPath',width:140,formatter:showImg">商品图片</th>
            <th data-options="field:'price',width:140,formatter:E3.formatOrderPrice">单价</th>
            <th data-options="field:'num',width:140">数量</th>
            <th data-options="field:'totalFee',width:160,formatter:E3.formatOrderPrice">总价</th>
        </tr>
        </thead>
    </table>
</div>
<script type="text/javascript">
    $(function () {
        var ids = window.parent.getSelectionsIds();
        $('#orderItemList').datagrid({
            queryParams: {
                ids: ids
            }
        });
    });
    //图片展示
    function showImg(value, row, index){
        if(value){
            return "<img style='width:80px;height:80px;margin-left:25px' src='"+value+"'/>";
        }
    }
</script>