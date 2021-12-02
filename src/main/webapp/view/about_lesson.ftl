<#-- @ftlvariable name="teacher" type="ru.itis.schoolApp.dto.UserDto" -->
<#-- @ftlvariable name="lesson" type="ru.itis.schoolApp.dto.LessonDto" -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Мои занятия</title>

    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/css/menu.css" rel="stylesheet">
    <link href="/resources/css/profile.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">О занятии</div>
            <div>
                <div>
                    <div><h4>Teacher info</h4></div>
                    <div>
                        <#if teacher.avatarId??>
                            <img class="user-avatar" alt="IMAGE" src="/files/${teacher.avatarId}"/>
                        <#else>
                            <img class="user-avatar" alt="IMAGE" src="/no-avatar.png"/>
                        </#if>
                    </div>
                    <div class="text">
                        <h5>First Name</h5>
                    </div>
                    <div class="teacher-info attr">
                        ${lesson.author.firstName}
                    </div>
                    <div class="text">
                        <h5>Last Name</h5>
                    </div>
                    <div class="teacher-info attr">
                        ${lesson.author.lastName}
                    </div>
                    <div class="text">
                        <h5>email</h5>
                    </div>
                    <div class="teacher-info attr">
                        ${lesson.author.email}
                    </div>
                </div>
                <div>
                    <div>
                        <h4>Lesson info</h4>
                    </div>
                    <div class="text">
                        <h5>Subject</h5>
                    </div>
                    <div class="subject-info attr">
                        ${lesson.subject}
                    </div>
                    <div class="text">
                        <h5>For</h5>
                    </div>
                    <div class="subject-info attr">
                        ${lesson.forWhom}
                    </div>
                    <div class="text">
                        <h5>content</h5>
                    </div>
                    <div class="subject-info attr">
                        ${lesson.content}
                    </div>
                    <div class="text">
                        <h5>created at</h5>
                    </div>
                    <div class="light_blue text">
                        ${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>