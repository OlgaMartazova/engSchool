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
    <link href="/resources/css/about_lesson.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div>
        <div>
            <div class="title center-content">about lesson</div>
            <div class="all">
                <div class="info">
                    <div>
                        <#if teacher.avatarId??>
                            <img  class="image-holder" alt="IMAGE" src="/files/${teacher.avatarId}"/>
                        <#else>
                            <img  class="image-holder" alt="IMAGE" src="/no-avatar.png"/>
                        </#if>
                    </div>

                    <div>
                        <div class="user-info">
                            <div class="title edit">
                                teacher info
                            </div>
                            <div class="user-info text">
                                <h5>First Name</h5>
                            </div>
                            <div class="user-info attr">${teacher.firstName}</div>
                            <div class="user-info text">
                                <h5>Last Name</h5>
                            </div>
                            <div class="user-info attr">${teacher.lastName}</div>
                            <div class="user-info text">
                                <h5>Email</h5>
                            </div>
                            <div class="user-info attr">${teacher.email}</div>
                            <div class="user-info text">
                                <h5>Role</h5>
                            </div>
                            <div class="user-info attr">${teacher.role}</div>
                        </div>
                    </div>
                </div>
                <div class="lesson-info">
                    <div class="title edit">
                        Lesson info
                    </div>
                    <div class="text">
                        <h5>Subject</h5>
                    </div>
                    <div class="attr">
                        ${lesson.subject}
                    </div>
                    <div class="text">
                        <h5>For</h5>
                    </div>
                    <div class="attr">
                        ${lesson.forWhom}
                    </div>
                    <div class="text">
                        <h5>content</h5>
                    </div>
                    <div class="attr">
                        ${lesson.content}
                    </div>
                    <div class="text">
                        <h5>created at</h5>
                    </div>
                    <div class="attr text blue_text">
                        ${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>