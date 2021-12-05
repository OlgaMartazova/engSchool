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
    <link href="/resources/css/sign_up.css" rel="stylesheet">

    <script src="/resources/js/update-avatar.js"></script>
    <script src="/resources/js/jquery.js"></script>

</head>
<body>

<div>

    <#include "menu.ftl">

    <div class="center-content">
        <div>
            <div class="title">About me</div>
            <div class="info">
                <div class="under-photo">
                    <#if user.avatarId??>
                        <img  id="image" class="image-holder" alt="IMAGE" src="/files/${user.avatarId}"/>
                    <#else>
                        <img  id="image" class="image-holder" alt="IMAGE" src="/no-avatar.png"/>
                    </#if>

                    <div>
                        <input id="file-input" type="file" name="name" style="display: none;" accept="image/png, image/gif, image/jpeg" onchange="uploadSelectedImage()"/>
                        <button  value="Update avatar" onclick="updateAvatar()">Update avatar</button>
                    </div>
                </div>

                <form class="user-info" method="post" action="/edit">
                    <div class="form-group">
                        <input name="firstName" type="text" placeholder="First Name" class="form-control" value="${user.firstName}">
                        <input name="lastName" type="text" placeholder="Last Name" class="form-control" value="${user.lastName}">
                    </div>

                    <div class="form-wrapper">
                        <select class="form-control" name="role">
                            <#if user.role == "teacher">
                                <option value="teacher" selected>Teacher</option>
                                <option value="student">Student</option>
                            <#else>
                                <option value="teacher">Teacher</option>
                                <option value="student" selected>Student</option>
                            </#if>
                        </select>
                    </div>
                    <button type="submit">
                        save
                    </button>

                    <div class="link">
                         <a href="/profile">cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>