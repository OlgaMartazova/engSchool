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
    <link href="/resources/css/add_lesson.css" rel="stylesheet">

    <script src="/resources/js/jquery.js"></script>
</head>
<body>

<div>

    <#include "menu.ftl">

    <div class="center-content">
            <div class="title">My lessons</div>

            <form method="post" action="/add-lesson">
                <div class="form-wrapper">
                    <select class="form-control" name="subject">
                        <option value="listening" selected>listening</option>
                        <option value="writing">writing</option>
                        <option value="speaking">speaking</option>
                        <option value="reading">reading</option>
                        <option value="vocabulary">vocabulary</option>
                    </select>
                </div>
                <div class="form-wrapper">
                    <select class="form-control" name="for_whom">
                        <option value="adult" selected>adult</option>
                        <option value="junior">junior</option>
                        <option value="kids">kids</option>
                        <option value="everyone">everyone</option>
                    </select>
                </div>
                <div class="form-wrapper">
                    <textarea class="form-control" name="content" cols="20" rows="5" placeholder="Write up to 1000 characters"></textarea>
                </div>
                <button type="submit">
                    Add lesson
                </button>
            </form>
        <div>
            <#list lessons as lesson>
                <div class="white-container">
                    <div class="new text container-half"><h4>${lesson.subject} for ${lesson.forWhom}</h4></div>
                    <div class="divider"></div>
                    <div class="text-style">${lesson.content}</div>
                    <div class="light_blue text-style">${lesson.createdAt?string("dd MMMM yyyy 'г.,' HH:mm")}</div>
                </div>
            </#list>
            <div class="text end">${lessons?size} lessons</div>
        </div>
    </div>
</div>
</body>