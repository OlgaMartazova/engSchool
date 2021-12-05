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
            <img src="https://www.gov.kz/uploads/2021/7/27/189068f792efc6e244a10417db213c1f_original.7227.jpg" alt="image">
        </div>
    <form method="post" action="/sign-up">
        <h3>Registration Form</h3>
        <div class="form-group">
            <input name="firstName" type="text" placeholder="First Name" class="form-control">
            <input name="lastName" type="text" placeholder="Last Name" class="form-control">
        </div>

        <div class="form-wrapper">
            <input name="email" type="text" placeholder="Email Address" class="form-control">
        </div>

        <div class="form-wrapper">
            <input name="password" type="password" placeholder="Password" class="form-control">
        </div>
        <div class="form-wrapper">
            <select class="form-control" name="role">
                <option value="" disabled selected>Choose your role</option>
                <option value="teacher">Teacher</option>
                <option value="student">Student</option>
            </select>
        </div>

        <button type="submit">
            Sign up
        </button>
        <div class="card-info">
            <p>Already have an account?
                <br><a href="/sign-in">Log in</a>
                </p>
        </div>
    </form>
    </div>
</div>
</body>
</html>