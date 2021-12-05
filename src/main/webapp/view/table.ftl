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

    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }
        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }
        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
            <div class="title">
                <#if user.role == "teacher">
                    Students
                <#else>
                    Teachers
                </#if>
            </div>
            <table>
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
</body>