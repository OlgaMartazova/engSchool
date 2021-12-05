<#-- @ftlvariable name="user" type="ru.itis.schoolApp.dto.UserDto" -->
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

<div>

    <#include "menu.ftl">

    <div class="center-content">
            <div class="title">Lessons</div>

            <div class="navbar-collapse-centre  navbar-nav">
                    <div class="text group">for whom</div>
                    <ul class="navbar-row navigate">
                        <li><a href="/for-whom?param=adult" class="button1">adult</a></li>
                        <li><a href="/for-whom?param=junior" class="button1">junior</a></li>
                        <li><a href="/for-whom?param=kids" class="button1">kids</a></li>
                    </ul>
                    <div class="text group-new group">subject</div>
                    <ul class="navbar-row navigate">
                        <li><a href="/subject?subject=listening" class="button1">listening</a></li>
                        <li><a href="/subject?subject=writing" class="button1">writing</a></li>
                        <li><a href="/subject?subject=speaking" class="button1">speaking</a></li>
                        <li><a href="/subject?subject=reading" class="button1">reading</a></li>
                        <li><a href="/subject?subject=vocabulary" class="button1">vocabulary</a></li>
                    </ul>
            </div>
            <div>
                <#list lessons as lesson>
                    <div class="white-container">
                        <div class="navigate new navbar-row">
                            <div class="text container-half"><h4>${lesson.subject} for ${lesson.forWhom}</h4></div>
                            <div><a href="/details?lessonId=${lesson.id}" class="button-left button_text">details</a></div>
                        </div>

                        <div class="divider"></div>
                        <div class="text-style">${lesson.content}</div>
                        <div class="light_blue text-style">${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                    </div>
                </#list>
                <div class="text end">Всего <span>${lessons?size}</span> уроков</div>
            </div>
        </div>
    </div>
</body>