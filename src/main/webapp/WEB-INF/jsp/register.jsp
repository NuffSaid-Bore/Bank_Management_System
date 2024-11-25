<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap/bootstrap.css">
    <link rel="stylesheet" href="css/fontawesome/css/all.css">
    <link rel="stylesheet" href="css/default.css">
    <title>Register</title>
</head>
<body class="d-flex align-items-center justify-content-center">

    <!-- Card: Registration Form Card -->
    <div class="card registration-form-card col-6 bg-transparent border-0">
        <!-- Card Body -->
        <div class="card-body">
            <!-- Form Header -->
            <h1 class="form-header card-title mb-3">
                <i class="fas fa-edit"></i> Register
            </h1>
            <!-- End Of Form Header -->

            <!-- Display Message -->
            <c:if test="${requestScope.passwordMisMatch != null}">
                <div class="alert alert-danger text-center border border-danger">
                    <b>${requestScope.passwordMisMatch}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Display Message -->
            <c:if test="${requestScope.success != null}">
                <div class="alert alert-success text-center border border-success">
                    <b>${requestScope.success}</b>
                </div>
            </c:if>
            <!-- End Of Display Message -->

            <!-- Registration Form -->
            <form:form action="/register" class="reg-form" method="POST" modelAttribute="registerUser">
                <!-- Row -->
                <div class="row">
                    <!-- Form Group -->
                    <div class="form-group col">
                        <form:input type="text" path="first_name" class="form-control form-control-lg" placeholder="Enter First Name"  aria-label="First Name" />
                        <form:errors path="first_name" class="text-white bg-danger" />                        
                    </div>
                    <!-- End Of Form Group -->

                    <!-- Form Group -->
                    <div class="form-group col">
                        <form:input type="text" path="last_name" class="form-control form-control-lg" placeholder="Enter Last Name"  aria-label="Last Name" />
                        <form:errors path="last_name" class="text-white bg-danger" />
                    </div>
                    <!-- End Of Form Group -->
                </div>
                <!-- End Of Row -->

                <!-- Form Group -->
                <div class="form-group">
                    <form:input type="email" path="email" class="form-control form-control-lg" placeholder="Enter Email"  aria-label="Email" />
                    <form:errors path="email" class="text-white bg-danger" />
                </div>
                <!-- End Of Form Group -->

                <!-- Form Group -->
                <div class="form-group">
                    <form:input type="number" path="id_num" class="form-control form-control-lg" placeholder="ID Number"  aria-label="ID Number" />
                    <form:errors path="id_num" class="text-white bg-danger" />                    
                </div>
                <!-- End Of Form Group -->

                <!-- Row -->
                <div class="row">
                    <!-- Form Group -->
                    <div class="form-group col">
                        <form:input type="password" path="password" class="form-control form-control-lg" placeholder="Enter Password"  aria-label="Password" />
                        <form:errors path="password" class="text-white bg-danger" />
                    </div>
                    <!-- End Of Form Group -->

                    <!-- Form Group -->
                    <div class="form-group col">
                        <input type="password" name="confirm_password" class="form-control form-control-lg" placeholder="Confirm Password"/>
                        <small class="text-white bg-danger">${confirm_pass}</small>                        
                    </div>
                    <!-- End Of Form Group -->
                </div>
                <!-- End Of Row -->

                <!-- Submit Button -->
                <div class="form-group col">
                    <button type="submit" class="btn btn-lg btn-primary">Register</button>
                </div>
                <!-- End Of Submit Button -->

            </form:form>
            <!-- End Of Registration Form -->

            <!-- Card Text -->
            <p class="card-text text-white my-2">
                Already have an account? <span class="ms-2"><a href="/login" class="btn btn-sm text-warning">Sign In</a></span>
            </p>
            <!-- End Of Card Text -->

            <!-- Back Button To Landing Page -->
            <small class="text-warning">
                <i class="fas fa-arrow-alt-circle-left"></i> <a href="/" class="btn btn-sm text-warning">Back</a>
            </small>
            <!-- End Of Back Button To Landing Page -->

        </div>
        <!-- End Of Card Body -->
    </div>
    <!-- Card: Registration Form Card -->

</body>
</html>
