<!doctype html>
<html class="no-js" lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>COVID-19 Vaccine Booking and Management System login</title>
    <link rel="icon" href="../favicon.ico" type="image/x-icon"> <!-- Favicon-->
    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">

</head>

<body>

<div id="ihealth-layout" class="theme-tradewind">

    <!-- main body area -->
    <div class="main p-2 py-3 p-xl-5 ">

        <!-- Body: Body -->
        <div class="body d-flex p-0 p-xl-5">
            <div class="container-xxl">
                <div class="row g-0">
                    <div class="col-lg-6 d-none d-lg-flex justify-content-center align-items-center rounded-lg auth-h100">
                        <div style="max-width: 25rem;">
                            <div class="text-center mb-5">
                                <i class="icofont-heart-beat secondary-color" style="font-size: 90px;"></i>
                            </div>
                            <div class="mb-5">
                                <h2 class="color-900 text-center">COVID-19 Vaccine Booking and Management System</h2>
                            </div>
                        </div>
                    </div>

                    <div class="col-lg-6 d-flex justify-content-center align-items-center border-0 rounded-lg auth-h100">
                        <div class="w-100 p-3 p-md-5 card border-0 bg-dark text-light" style="max-width: 32rem;">
                            <!-- Form -->
                            <form class="row g-1 p-3 p-md-4" action="login.jsp">
                                <div class="col-12 text-center mb-5">
                                    <h1>Sign in</h1>
                                    <span>Free access to dashboard.</span>
                                </div>


                                <div class="col-12">
                                    <div class="mb-2">
<!--                                        <a data-bs-toggle="collapse" data-bs-target="#dashboard" href="#">-->
<!--                                          <span class="form-label d-flex justify-content-between align-items-center">Chose Your Role</span>-->
<!--                                            <span class="arrow icofont-rounded-down ms-auto text-end fs-5"></span>-->
<!--                                        </a>-->
                                        <label class="form-label">Select Role</label>
                                        <select class="form-select form-control form-control-lg" name="type">
                                            <option value="1">Administrator</option>
                                            <option value="2">Vaccine Recipients</option>
                                            <option value="3">Health Care Providers</option>
                                        </select>
<!--                                        <ul class="sub-menu collapse" id="dashboard">-->
<!--                                            <li><a class="ms-link" href="coviddashboard.html">Administrator</a></li>-->
<!--                                            <li><a class="ms-link" href="coviddashboard.html">Vaccine Recipients</a></li>-->
<!--                                            <li><a class="ms-link" href="index.html">Health Care Providers</a></li>-->
<!--                                        </ul>-->
                                    </div>
                                    <div class="mb-2">
                                        <label class="form-label">ID</label>
                                        <input type="text" class="form-control form-control-lg" name="username">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="mb-2">
                                        <div class="form-label">
                                            <span class="d-flex justify-content-between align-items-center" >
                                                Password
                                            </span>
                                        </div>
                                        <input type="password" class="form-control form-control-lg" name="password">
                                    </div>
                                </div>
                                <div class="col-12">
                                    <div class="form-check">
                                        <input class="form-check-input" type="checkbox" value="" id="flexCheckDefault">
                                        <label class="form-check-label" for="flexCheckDefault">
                                            Remember me
                                        </label>
                                    </div>
                                </div>
                                <div class="col-12 text-center mt-4">
                                    <input type="submit" value="SIGN IN" class="btn btn-lg btn-block btn-light lift text-uppercase"  >
                                </div>
                            </form>
                            <!-- End Form -->

                        </div>
                    </div>
                </div> <!-- End Row -->

            </div>
        </div>

    </div>

</div>

<!-- Jquery Core Js -->
<script src="../assets/bundles/libscripts.bundle.js"></script>

</body>
</html>