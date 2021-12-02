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

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Занятия</div>
            <div>
                <div>
                    <a href="/for-whom?param=adult" class="button1">adult</a>
                </div>
                <div>
                    <a href="/for-whom?param=junior" class="button1">junior</a>
                </div>
                <div>
                    <a href="/for-whom?param=kids" class="button1">kids</a>
                </div>
            </div>
            <div>
                <div>
                    <a href="/subject?subject=listening" class="button1">listening</a>
                </div>
                <div>
                    <a href="/subject?subject=writing" class="button1">writing</a>
                </div>
                <div>
                    <a href="/subject?subject=speaking" class="button1">speaking</a>
                </div>
                <div>
                    <a href="/subject?subject=reading" class="button1">reading</a>
                </div>
                <div>
                    <a href="/subject?subject=vocabulary" class="button1">vocabulary</a>
                </div>
            </div>
            <div>
                <#list lessons as lesson>
                    <div>
                        <div class="text"><h4>${lesson.subject} for ${lesson.forWhom}</h4></div>
                        <div class="text">${lesson.content}</div>
                        <div class="light_blue text">${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                        <div><a href="/details?lessonId=${lesson.id}" class="button1">details</a></div>
                        <#if lesson_index < lessons?size - 1>
                            <div class="divider"></div>
                        </#if>
                    </div>
                </#list>
                <div style="text-align: center">Всего <span>${lessons?size}</span> уроков</div>
            </div>
        </div>
    </div>
</div>
</body>