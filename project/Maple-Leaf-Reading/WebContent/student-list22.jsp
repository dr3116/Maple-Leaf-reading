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

<form action="Studnet22Servlet?name=${name }" method="post" enctype="multipart/form-data">
     上传头像:&nbsp;&nbsp;<input type="file" name="uploadFile" /><br/>
     点我上传:&nbsp;&nbsp;<input type="submit" value="上传" />
    </form>



</body>
</html>