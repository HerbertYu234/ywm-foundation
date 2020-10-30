<html>

<head>
    <title>login</title>
</head>

<body>
    <form action="${wolf.module}/login" method="post">
        <input type="text" name="username" value="${form.username!}">
        <input type="password" name="password" value="${form.password}">
        <input type="hidden" name="refer" value="${form.refer}">

        <button type="submit">提交</button>
    </form>
</body>

</html>