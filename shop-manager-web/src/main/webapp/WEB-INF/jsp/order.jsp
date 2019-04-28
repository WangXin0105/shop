<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<table class="easyui-datagrid" id="orderList" title="" style="height: 600px"
       data-options="singleSelect:false,collapsible:true,pagination:true,url:'/order/list',method:'get',pageSize:10,toolbar:toolbar">
    <thead>
    <tr>
        <th data-options="field:'ck',checkbox:true"></th>
        <th data-options="field:'orderId',width:150">订单号</th>
        <th data-options="field:'payment',width:150">订单金额</th>
        <th data-options="field:'paymentType',width:150,formatter:E3.formatPayTypeStatus">支付类型</th>
        <th data-options="field:'postFee',width:85">邮费</th>
        <th data-options="field:'status',width:150,formatter:E3.formatOrderStatus">订单状态</th>
        <th data-options="field:'buyerNick',width:150">买家昵称</th>
        <th data-options="field:'createTime',width:170,align:'center',formatter:E3.formatDateTime">创建日期</th>
        <th data-options="field:'updateTime',width:170,align:'center',formatter:E3.formatDateTime">更新日期</th>

    </tr>
    </thead>
</table>
<div id="orderEditWindow" class="easyui-window" title="订单详情"
     data-options="modal:true,closed:true,href:'/order-edit'"
     style="width:65%;height:80%;padding:10px;">
</div>
<script>
    function getSelectionsIds() {
        var orderList = $("#orderList");
        var sels = orderList.datagrid("getSelections");
        var ids = [];
        for (var i in sels) {
            ids.push(sels[i].orderId);
        }
        ids = ids.join(",");
        return ids;
    }

    var toolbar = [{
        text: '订单详情',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '必须选择一个订单才能查看!');
                return;
            }
            if (ids.indexOf(',') > 0) {
                $.messager.alert('提示', '只能选择一个订单!');
                return;
            }

            $("#orderEditWindow").window({
                onLoad: function () {

                }
            }).window("open");
        }
    }, {
        text: '发货',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中订单!');
                return;
            }
            $.messager.confirm('确认', '确定发货订单号为 ' + ids + ' 的订单吗？', function (r) {
                if (r) {
                    var params = {"ids": ids};
                    $.post("/order/send", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '订单发货成功!', undefined, function () {
                                $("#orderList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }, '-', {
        text: '完成交易',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中订单!');
                return;
            }
            $.messager.confirm('确认', '确定订单号为 ' + ids + ' 的订单交易完成吗？', function (r) {
                if (r) {
                    var params = {"ids": ids};
                    $.post("/order/end", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '订单交易成功!', undefined, function () {
                                $("#orderList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }, {
        text: '删除',
        handler: function () {
            var ids = getSelectionsIds();
            if (ids.length == 0) {
                $.messager.alert('提示', '未选中订单!');
                return;
            }
            $.messager.confirm('确认', '确定删除订单号为 ' + ids + ' 的订单吗？', function (r) {
                if (r) {
                    var params = {"ids": ids};
                    $.post("/order/delete", params, function (data) {
                        if (data.status == 200) {
                            $.messager.alert('提示', '订单删除成功!', undefined, function () {
                                $("#orderList").datagrid("reload");
                            });
                        }
                    });
                }
            });
        }
    }];
</script>