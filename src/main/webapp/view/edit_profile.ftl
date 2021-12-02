<#-- @ftlvariable name="user" type="ru.itis.schoolApp.dto.UserDto" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Мой профиль</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/update-avatar.js"></script>
    <script src="/resources/js/jquery.js"></script>

</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Информация обо мне</div>
            <div id="avatar" class="white-container">

                <#if user.avatarId??>
                    <img class="user-avatar" id="image" alt="IMAGE" src="/files/${user.avatarId}"/>
                <#else>
                    <img class="user-avatar" id="image" alt="IMAGE" src="/no-avatar.png"/>
                </#if>

                <div>
                    <form class="user-info-text" method="post" action="/edit">
                        <label>First name
                            <input name="firstName" type="text" value="${user.firstName}">
                        </label>
                        <label>Last name
                            <input name="lastName" type="text" value="${user.lastName}">
                        </label>
                        <label for="role">Role</label>
                        <select id="role" name="role">
                            <#if user.role == "teacher">
                                <option value="teacher" selected>Teacher</option>
                                <option value="student">Student</option>
                            <#else>
                                <option value="teacher">Teacher</option>
                                <option value="student" selected>Student</option>
                            </#if>
                        </select>
                        <input class="save" type="submit" value="save">
                    </form>

                    <div id="edit avatar">
                        <input id="file-input" type="file" name="name" style="display: none;" accept="image/png, image/gif, image/jpeg" onchange="uploadSelectedImage()"/>
                        <button  value="Update avatar" onclick="updateAvatar()">Update avatar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div>
        <a href="/profile" class="button1">cancel</a>
    </div>
</div>
</body>