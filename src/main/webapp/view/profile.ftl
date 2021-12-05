<#-- @ftlvariable name="user" type="ru.itis.schoolApp.dto.UserDto" -->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Профиль</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">
</head>
<body>

<div>

    <#include "menu.ftl">

    <div class="center-content">
        <div>
            <div class="title">Profile</div>
            <div class="info">

                <div>
                    <#if user.avatarId??>
                        <img  class="image-holder" alt="IMAGE" src="/files/${user.avatarId}"/>
                    <#else>
                        <img  class="image-holder" alt="IMAGE" src="/no-avatar.png"/>
                    </#if>
                </div>

<#--                <div class="user-info-text">-->
<#--                    <div class="user-info">${user.firstName}</div>-->
<#--                    <div class="user-info">${user.lastName}</div>-->
<#--                    <div class="user-info">${user.email}</div>-->
<#--                    <div class="user-info">${user.role}</div>-->
<#--                </div>-->

                <div>
                    <div class="user-info">
                        <div class="user-info text">
                            <h5>First Name</h5>
                        </div>
                        <div class="user-info attr">${user.firstName}</div>
                        <div class="user-info text">
                            <h5>Last Name</h5>
                        </div>
                        <div class="user-info attr">${user.lastName}</div>
                        <div class="user-info text">
                            <h5>Email</h5>
                        </div>
                        <div class="user-info attr">${user.email}</div>
                        <div class="user-info text">
                            <h5>Role</h5>
                        </div>
                        <div class="user-info attr">${user.role}</div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>