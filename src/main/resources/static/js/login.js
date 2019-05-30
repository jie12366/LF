/****************************************************************
 *																*
 * 						      代码库							*
 *                        www.dmaku.com							*
 *       		  努力创建完善、持续更新插件以及模板			*
 * 																*
 ****************************************************************/
$(function(){
    var tab = 'account_number';
    // 选项卡切换
    $(".account_number").click(function () {
        $('.tel-warn').addClass('hide');
        tab = $(this).attr('class').split(' ')[0];
        checkBtn();
        $(this).addClass("on");
        $(".message").removeClass("on");
        $(".form2").addClass("hide");
        $(".form1").removeClass("hide");
    });
    // 选项卡切换
    $(".message").click(function () {
        $('.tel-warn').addClass('hide');
        tab = $(this).attr('class').split(' ')[0];
        checkBtn();
        $(this).addClass("on");
        $(".account_number").removeClass("on");
        $(".form2").removeClass("hide");
        $(".form1").addClass("hide");

    });

    $('#num').keyup(function(event) {
        $('.tel-warn').addClass('hide');
        checkBtn();
    });

    $('#pass').keyup(function(event) {
        $('.tel-warn').addClass('hide');
        checkBtn();
    });

    $('#veri').keyup(function(event) {
        $('.tel-warn').addClass('hide');
        checkBtn();
    });

    $('#num2').keyup(function(event) {
        $('.tel-warn').addClass('hide');
        checkBtn();
    });

    $('#veri-code').keyup(function(event) {
        $('.tel-warn').addClass('hide');
        checkBtn();
    });

    // 按钮是否可点击
    function checkBtn()
    {
        $(".log-btn").off('click');
        if (tab == 'account_number') {
            var inp = $.trim($('#num').val());
            var pass = $.trim($('#pass').val());
            if (inp != '' && pass != '') {
                if (!$('.code').hasClass('hide')) {
                    code = $.trim($('#veri').val());
                    if (code == '') {
                        $(".log-btn").addClass("off");
                    } else {
                        $(".log-btn").removeClass("off");
                        sendBtn();
                    }
                } else {
                    $(".log-btn").removeClass("off");
                    sendBtn();
                }
            } else {
                $(".log-btn").addClass("off");
            }
        } else {
            var phone = $.trim($('#num2').val());
            var code2 = $.trim($('#veri-code').val());
            if (phone != '' && code2 != '') {
                $(".log-btn").removeClass("off");
                sendBtn();
            } else {
                $(".log-btn").addClass("off");
            }
        }
    }

    // 登录的回车事件
    $(window).keydown(function(event) {
        if (event.keyCode == 13) {
            $('.log-btn').trigger('click');
        }
    });


    $(".form-data").delegate(".send","click",function () {
        var phone = $.trim($('#num2').val());
        if (checkPhone(phone)) {
            $.ajax({
                url: '/getcode',
                type: 'post',
                dataType: 'json',
                async: true,
                data: {phone:phone,type:"login"},
                success:function(data){
                    if (data.code == '0') {

                    } else {

                    }
                },
                error:function(){

                }
            });
            var oTime = $(".form-data .time"),
                oSend = $(".form-data .send"),
                num = parseInt(oTime.text()),
                oEm = $(".form-data .time em");
            $(this).hide();
            oTime.removeClass("hide");
            var timer = setInterval(function () {
                var num2 = num-=1;
                oEm.text(num2);
                if(num2==0){
                    clearInterval(timer);
                    oSend.text("重新发送验证码");
                    oSend.show();
                    oEm.text("120");
                    oTime.addClass("hide");
                }
            },1000);
        }
    });



});