/*保存和取出cookie*/
function getCookie(c_name) {
	if (document.cookie.length > 0) {
		c_start = document.cookie.indexOf(c_name + "=");
		if (c_start != -1) {
			c_start = c_start + c_name.length + 1;
			c_end = document.cookie.indexOf(";", c_start);
			if (c_end == -1)
				c_end = document.cookie.length;
			return unescape(document.cookie.substring(c_start, c_end));
		}
	}
	return "";
}

function setCookie(c_name, value) {
	var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = c_name + "="+ escape (value) + ";expires=" + exp.toGMTString()+"; path=/";
}

function EmptyCookie(c_name){
	
	var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(c_name);
    if(cval!='')
        document.cookie= c_name + "="+cval+";expires="+exp.toGMTString()+"; path=/";
}