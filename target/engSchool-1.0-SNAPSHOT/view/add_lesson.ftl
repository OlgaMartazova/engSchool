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
</head>
<body>

<div class="container">

    <#include "menu.ftl">

    <div class="center-content">
        <div class="container">
            <div class="title">Мои занятия</div>

            <form action="/add-lesson" method="post">
                <label for="role">Subject:</label>
                <select name="subject">
                    <option value="listening">listening</option>
                    <option value="writing">writing</option>
                    <option value="speaking">speaking</option>
                    <option value="reading">reading</option>
                    <option value="vocabulary">vocabulary</option>
                </select>
                <label for="role">For whom:</label>
                <select name="for_whom">
                    <option value="adult">adult</option>
                    <option value="junior">junior</option>
                    <option value="kids">kids</option>
                    <option value="everyone">everyone</option>
                </select>
                <label>Content
                    <textarea name="content" cols="20" rows="5" placeholder="write up to 1000 characters"></textarea>
                </label>
                <input class="button1" value="Отправить" type="submit">
            </form>

            <div class="divider"></div>

            <div>
                <#list lessons as lesson>
                    <div>
                        <div class="text"><h4>${lesson.subject} for ${lesson.forWhom}</h4></div>
                        <div class="text">${lesson.content}</div>
                        <div class="light_blue text">${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
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