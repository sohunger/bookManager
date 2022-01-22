layui.use(['laypage', 'layer'], function(){
    var laypage = layui.laypage
        ,layer = layui.layer;

    table.render({
        elem: '#demo'
        ,height: 420
        ,url: '/demo/table/user/' //数据接口
        ,title: '用户表'
        ,page: true //开启分页
        ,toolbar: 'default' //开启工具栏，此处显示默认图标，可以自定义模板，详见文档
        ,totalRow: true //开启合计行
        ,cols: [[ //表头
            {type: 'checkbox', fixed: 'left'}
            ,{field: 'id', title: 'ID', width:80, sort: true, fixed: 'left', totalRowText: '合计：'}
            ,{field: 'username', title: '用户名', width:80}
            ,{field: 'experience', title: '积分', width: 90, sort: true, totalRow: true}
            ,{field: 'sex', title: '性别', width:80, sort: true}
            ,{field: 'score', title: '评分', width: 80, sort: true, totalRow: '{{ parseInt(d.TOTAL_NUMS) }} 分'}
            ,{field: 'city', title: '城市', width:150}
            ,{field: 'sign', title: '签名', width: 200}
            ,{field: 'classify', title: '职业', width: 100}
            ,{field: 'wealth', title: '财富', width: 135, sort: true, totalRow: true}
            ,{fixed: 'right', width: 150, align:'center', toolbar: '#barDemo'}
        ]]
    });

});