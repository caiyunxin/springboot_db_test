/**
 * 测试ajax异常访问
 */
$.ajax({
//	url: "http://localhost:8080/pinglian/err/postAjaxError",
	url: "/pinglian/err/postAjaxError",
	type: "POST",
	async: false,
	success: function(data) {
		debugger;
        if(data.status == 200 && data.msg == "OK") {
        	alert("success");
        } else {
        	alert("发生异常：" + data.msg);
        }
	},
    error: function (response, ajaxOptions, throwsError) {
    	alert("error");       
    }
});