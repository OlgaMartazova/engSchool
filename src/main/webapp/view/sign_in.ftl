<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign In</title>
    <link rel="stylesheet" href="/resources/css/style.css">
    <link rel="stylesheet" href="/resources/css/sign_in.css">
    <link rel="stylesheet" href="/resources/css/sign_up.css">
</head>
<body>
<div class="container">

    <form class="form-center-content" method="post" action="/sign-in">
        <div class="form-sign_up-heading">Sign in</div>
        <label>
            <input class="form-control" name="email" type="email" placeholder="Email">
        </label>
        <label>
            <input class="form-control" name="password" type="password" placeholder="Password">
        </label>
        <input class="login-button" value="Log in" type="submit">
        <div>
            <h2 class="">No account?</h2>
            <div>
                <a href="/sign-up" class="button1">Sign up</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>