<#-- @ftlvariable name="user" type="ru.itis.schoolApp.dto.UserDto" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Другие пользователи</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">
                <#if user.role == "teacher">
                    Студенты
                <#else>
                    Учителя
                </#if>
            </div>
            <table border="1" cellspacing="0" cellpadding="1">
                <tr>
                <th>Id</th>
                <th>First name</th>
                <th>Last name</th>
                </tr>
                <#forEach userT in usersTable>
                    <tr>
                        <td>${userT.id}</td>
                        <td>${userT.firstName}</td>
                        <td>${userT.lastName}</td>
                    </tr>
                </#forEach>
            </table>
        </div>
    </div>
</div>
</body>