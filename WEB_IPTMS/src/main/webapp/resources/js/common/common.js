document.write("<script type='text/javascript' src='./resources/js/common/jquery-2.0.3.min.js'></script>");
document.write("<script type='text/javascript' src='./resources/js/common/json2.js'></script>");

function getRequestSuffix() {
	return ".html";
}

//IE 체크
var isIE = '\v' == 'v';

// IE 버전체크
function getInternetExplorerVersion() {
    var rv = -1; // Return value assumes failure.
    if (navigator.appName == 'Microsoft Internet Explorer') {
        var ua = navigator.userAgent;
        var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
        if (re.exec(ua) != null)
            rv = parseFloat(RegExp.$1);
    }
    return rv;
}

// 공백 체크
function nullCheck(name, obj){
	var objVal = "";
	objVal = obj.val() + "";
	
	if(objVal == ""){
		alert(name + "을(를) 입력하세요");
		obj.focus();
		return false;
	}
	
	return true;
}

function addChildObject(formObj, type, key, val) {
	try {
		var obj = eval("document."+ formObj.name + "." + key);
		if (obj == null) {
			obj = document.createElement('input');
			obj.setAttribute('type', type);
			obj.setAttribute('name', key);
			obj.setAttribute('value', val);
			formObj.appendChild(obj);
		} else {
			obj.value = val;
		}
	} catch (e) {
		alert(e);
	}
	return formObj;
}


/**
 * ajax 공통
 * @param urlStr - Full Target URL
 * @param paramStr - All Parameter String(paramA=value1&paramB=value2...)
 * @param callbackMethod - Callback Method(작업하는 jsp 파일 내에 정의해서 function name 만을 넘겨준다.
 */
function requestAjax(urlStr, paramStr, callbackMethod){
	try{
		$.ajax({
			type:"POST",
			url :urlStr,
			data: paramStr,
			success: callbackMethod
		});
	}catch(e){
		alert(e);
	}
}




