<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page language="java"  contentType="text/html; charset=UTF-8"  pageEncoding="utf-8" import="java.util.*"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${message }

<form action="BookList22Servlete?bookName=${bookName }" method="post" enctype="multipart/form-data">
        上传书籍文件：<input type="file" name="uploadFile" /><br/>
        上传图片&nbsp;&nbsp;&nbsp;<input type="file" name="uploadFile" /><br/>
     点我上传:&nbsp;&nbsp;   <input type="submit" value="上传" />
    </form>



</body>
</html>