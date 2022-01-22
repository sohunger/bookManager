layui.use(['form', 'element','layer'], function () {
    let form = layui.form;
    let element = layui.element;
    let layer = layui.layer;
});
$(function(){
//点击删除按钮后删除一行
    $(".del_btn").click(function () {

        let that = $(this);

        layer.confirm('确认删除?', {
            btn: ['确认', '取消'] //按钮
        }, function () {
            let bookId = that.val();

            deleteUserById(bookId);

            that.parent().parent().remove();
            layer.msg("删除成功", {icon: 1, time: 1000});

            setTimeout(function () {

                // 关闭所有 layer选项框
                parent.layer.closeAll();
            }, 1000)

        });

    });
});

//ajax删除用户
function deleteUserById(bookId) {
    $.ajax({
        async: false,
        type: "get",
        url: "/adminDeleteBookPage",
        dataType: "json",
        data: {bookId: bookId},
        success: function (data) {

        },
        error: function (data) {
            alert(data.result);
        }
    });
}