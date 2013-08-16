
/**
 * 
 */
var PageNavi = function(first, prev, pageNo, next, last, pageStr, formNm){
	this.first = first;
	this.prev = prev;
	this.pageNo = pageNo;
	this.next = next;
	this.last = last;
	this.pageStr = pageStr;
	this.formNm = formNm;
};

/** 
 * Object 개체 추가
 * @param object - Object 개체
 */
PageNavi.prototype.print = function(id){
	try {
		
		//alert(this.pageStr);
		
		var page_list = this.pageStr.split(",");
		
		var naviObj = $("#" + id);
		
		naviObj.html("");
		
		var html = "";
		
		if (this.first != null && this.first != "" && this.first != "null"){
			html += '<a href="#" class="pageNum" pageNo="' + this.first + '">처음으로</a> ';
		}
			
		if (this.prev != null && this.prev != "" && this.prev != "null"){
			html += '<a href="#" class="pageNum" pageNo="' + this.prev + '">이전</a> ';
		}
			
		for (var i = 0; i < page_list.length; i++) {
			if (i > 0 && i < page_list.length - 1 )	{
				html += ' / ';
			}
			
			//console.log("this.pageNo : " + this.pageNo);
			
			if (this.pageNo == page_list[i]){
				html += "<b>" + page_list[i] + "</b> ";
			}else{
				html += '<a href="#" class="pageNum" pageNo="' + page_list[i] + '">' + page_list[i] + '</a> ';
			}
		}
		
		if (this.next != null && this.next != "" && this.next != "null"){
			html += '<a href="#" class="pageNum" pageNo="' + this.next + '">다음</a> ';
		}
			
		if (this.last != null && this.last != "" && this.last != "null"){
			html += '<a href="#" class="pageNum" pageNo="' + this.last + '">마지막으로</a>';
		}
		
		naviObj.html(html);
		
		$(".pageNum").live("click", function(){
			
			$("#pageNo").val($(this).attr("pageNo"));
			
			//alert($(this).attr("pageNo"));
			getDataList();
			//$(location).attr('href', url + '?pageNo=' + $(this).attr("pageNo"));
			//window.nbp.fn_OpenApiCall(fn_formCheck("docuListForm"), listCallback, "oa_np_getDocuList");	
		});
			
	} catch (e) {
		alert(e);
	}
};


var isIE = '\v' == 'v';

//페이지 이동
function movePage(formID, pageNo) {
	// alert("movePage=" + userId);
	try {
		var formObj = document.getElementById(formID);
		if (isIE && getInternetExplorerVersion() < 8.0) {
			formObj.page_no.value = pageNo;
		} else {
			formObj.page_no.value = pageNo;
			/*
			var obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'page_no');
			obj.setAttribute('value', pageNo);
			formObj.appendChild(obj);
			
			obj = document.createElement('input');
			obj.setAttribute('type', 'hidden');
			obj.setAttribute('name', 'strSearch');
			obj.setAttribute('value', userId);
			formObj.appendChild(obj);
			*/
		}
		formObj.submit();
	} catch(e) {
		alert(e);
	}
}

//리스트 하단의 페이지 네비게이션
function printPageNavigation(pageNavi, objectID, formID, pageNo) {
	// alert("printPageNavigation = " + user_id);
	if (pageNavi == null)	return;
	if (pageNo == null)		return;
	
	try {
		var naviObj = document.getElementById(objectID);
		naviObj.innerHTML = "";
		
		if (pageNavi.first!=null)
			naviObj.innerHTML += '<a href="#here" onclick="movePage(\''+formID+'\', '+pageNavi.first+')">첫페이지</a> ';
		if (pageNavi.prev!=null)
			naviObj.innerHTML += '<a href="#here" onclick="movePage(\''+formID+'\', '+pageNavi.prev+')">이전페이지</a> ';
		for (var i = 0; pageNavi.page_list!=null && i<pageNavi.page_list.length; i++) {
			if (i > 0)	naviObj.innerHTML += ' / ';
			if (pageNo == pageNavi.page_list[i])
				naviObj.innerHTML += "<b>" + pageNavi.page_list[i] + "</b> ";
			else
				naviObj.innerHTML += '<a href="#here" onclick="movePage(\''+formID+'\', '+pageNavi.page_list[i]+' )">'+pageNavi.page_list[i]+'</a> ';
		}
		
		if (pageNavi.next!=null)
			naviObj.innerHTML += '<a href="#here" onclick="movePage(\''+formID+'\', '+pageNavi.next+')">다음페이지</a> ';
		if (pageNavi.last!=null)
			naviObj.innerHTML += '<a href="#here" onclick="movePage(\''+formID+'\', '+pageNavi.last+')">마지막페이지</a>';
		if (isIE && getInternetExplorerVersion() < 8.0)	naviObj.innerHTML += '<input type="hidden" name="page_no">';
	} catch (e) {
		alert(e);
	}
}