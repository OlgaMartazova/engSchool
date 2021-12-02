<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/sign_up.css">
</head>
<body>
<div class="container">

<form class="form-center-content" method="post" action="/sign-up">
    <div class="form-sign_up-heading">Sign up</div>
    <label>First name
        <input name="firstName" type="text">
    </label>
    <label>Last name
        <input name="lastName" type="text">
    </label>
    <label>Email
        <input name="email" type="email" placeholder="Email">
    </label>
    <label>Password
        <input name="password" type="password" placeholder="Password">
    </label>
    <label for="role">Choose your role:</label>
    <select id="role" name="role">
        <option value="teacher">Teacher</option>
        <option value="student">Student</option>
    </select>
    <input class="button1" type="submit" value="sign-up">
    <div>
        <h2 class="">Already have an account?</h2>
        <div>
            <a href="/sign-in" class="button1">Sign in</a>
        </div>
    </div>
</form>
</div>
</body>
</html>