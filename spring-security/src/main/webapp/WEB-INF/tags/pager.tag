<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ tag import="com.lin.security.page.PageInfo" %>
<!-- 属性 -->
<%@ attribute name="currPage" type="java.lang.Integer" required="false" description="当前页码"%>
<%@ attribute name="total" type="java.lang.Long" required="false" description="记录总数"%>
<%@ attribute name="pageSize" type="java.lang.Short" required="false" description="每页记录总数"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="跳转"%>


<%
	String pageNumTag = "#{pageNumber}";
	String disabledClass = "class=\"disabled\"";
	String enabledClass = "class=\"active\"";
	short len = 9;
	short half = 4;
	
	if(null == currPage || currPage <= 0){
		currPage = 1;
	}
	if(null == total || total < 0){
		total = 0l;
	}
	if(null == pageSize || pageSize <= 0){
		pageSize = 10;
	}
	PageInfo pageInfo = new PageInfo(currPage, pageSize, total);
	
	
	// 总页数
	int pageCount = pageInfo.getPageCount();
	StringBuilder pageBuf = new StringBuilder();
	
	// ul
	pageBuf.append("<ul class=\"pagination pull-right\">");
	if(1 == currPage){
		// 首页
		pageBuf.append("<li ").append(disabledClass).append(">").append("<a href=\"javascript:void(0);\">首页</a></li>");
		// 上一页
		pageBuf.append("<li ").append(disabledClass).append(">").append("<a href=\"javascript:void(0);\" aria-label=\"上一页\"><span aria-hidden=\"true\">«</span></a></li>");
	}else{
		pageBuf.append("<li>").append("<a href=\"").append(url.replace(pageNumTag, "1")).append("\">首页</a></li>");
		pageBuf.append("<li>").append("<a href=\"").append(url.replace(pageNumTag, "" + (currPage - 1))).append("\" aria-label=\"上一页\"><span aria-hidden=\"true\">«</span></a></li>");
	}
	
	// 中间
	int startPage = Math.max(1, currPage - half);
	int pageLen = Math.min(len, pageCount - startPage + 1);
	for(short i=1; i<=pageLen ;i++){
		if(currPage == i){
			pageBuf.append("<li ").append(enabledClass).append(">").append("<a href=\"javascript:void(0);\">").append(i).append("</a></li>");
		}else{
			pageBuf.append("<li>").append("<a href=\"").append(url.replace(pageNumTag, "" + i)).append("\">").append(i).append("</a></li>");
		}
	}
	
	if(pageCount == currPage){
		// 下一页
		pageBuf.append("<li ").append(disabledClass).append(">").append("<a href=\"javascript:void(0);\" aria-label=\"下一页\"><span aria-hidden=\"true\">»</span></a></li>");
		// 末页
		pageBuf.append("<li ").append(disabledClass).append(">").append("<a href=\"javascript:void(0);\">末页</a></li>");
	}else{
		pageBuf.append("<li>").append("<a href=\"").append(url.replace(pageNumTag, "" + (currPage + 1))).append("\" aria-label=\"下一页\"><span aria-hidden=\"true\">»</span></a></li>");
		pageBuf.append("<li>").append("<a href=\"").append(url.replace(pageNumTag, "" + pageCount)).append("\">末页</a></li>");
	}
	// ul end
	pageBuf.append("</ul>");
	out.print(pageBuf.toString());
%>




