<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="/resources/css/sign_up.css" rel="stylesheet">
    <title>Sign Up</title>
</head>
<body>
<div class="wrapper">
    <div class="inner">
        <div class="image-holder">
            <img src="https://static6.depositphotos.com/1000765/574/i/600/depositphotos_5741326-stock-photo-3d-small-positive-pose.jpg" alt="image">
        </div>
        <form method="post" action="/sign-in">
            <h3>Welcome</h3>
            <div class="form-wrapper">
                <input name="email" type="text" placeholder="Email Address" class="form-control">
            </div>

            <div class="form-wrapper">
                <input name="password" type="password" placeholder="Password" class="form-control">
            </div>
            <button type="submit">
                Log in
            </button>


            <div class="card-info">
                <p>Don't have an account yet?
                    <br><a href="/sign-up">Sign up</a>
                </p>
            </div>
        </form>
    </div>
</div>
</body>
</html>
