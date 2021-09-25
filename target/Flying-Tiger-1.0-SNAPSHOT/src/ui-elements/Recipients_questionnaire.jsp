<%@ page import="com.example.Flying_Tiger.Recipient" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Answer Questionnaire </title>
    
    <!-- plugin css file  -->
    <link rel="stylesheet" href="../assets/plugin/parsleyjs/css/parsley.css">

    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">
</head>
<body>
<% String id=request.getParameter("id");
    Recipient recipient=Recipient.getMapper().find(Long.parseLong(id)); %>
<div id="ihealth-layout" class="theme-tradewind">

    <!-- sidebar -->
    <div class="sidebar px-4 py-4 py-md-5 me-0">
        <div class="d-flex flex-column h-100">
            <a href="index.html" class="mb-0 brand-icon">
                <span class="logo-icon">
                    <i class="icofont-heart-beat fs-2"></i>
                </span>
                <span class="logo-text">Vaccine Recipient</span>
            </a>
            <!-- Menu: main ul -->

            <ul class="menu-list flex-grow-1 mt-3">
                <li><a class="m-link" href="my_booking.jsp?id=<%=id%>"><i class="icofont-prescription fs-5"></i> <span>My Booking</span></a></li>
                <li><a class="m-link" href="booking.jsp?id=<%=id%>"><i class="icofont-meeting-add fs-5"></i> <span>Online Booking</span></a></li>
            </ul>

            <!-- Menu: menu collepce btn -->
            <button type="button" class="btn btn-link sidebar-mini-btn text-light">
                <span class="ms-2"><i class="icofont-bubble-right"></i></span>
            </button>
        </div>
    </div>

    <!-- main body area -->
    <div class="main px-lg-4 px-md-4">

        <!-- Body: Header -->
        <div class="header">
            <nav class="navbar py-4">
                <div class="container-xxl">

                    <!-- header rightbar icon -->
                    <div class="h-right d-flex align-items-center mr-5 mr-lg-0 order-1">
                        <div class="dropdown user-profile ml-2 ml-sm-3 d-flex align-items-center zindex-popover">
                            <div class="u-info me-2">
                                <p class="mb-0 text-end line-height-sm "><span class="font-weight-bold">John Quinn</span></p>
                                <small>Vaccine Recipient</small>
                            </div>
                            <a class="nav-link dropdown-toggle pulse p-0" href="#" role="button" data-bs-toggle="dropdown" data-bs-display="static">
                                <img class="avatar lg rounded-circle img-thumbnail" src="../assets/images/profile_av2.png" alt="profile">
                            </a>
                            <div class="dropdown-menu rounded-lg shadow border-0 dropdown-animation dropdown-menu-end p-0 m-0">
                                <div class="card border-0 w280">
                                    <div class="card-body pb-0">
                                        <div class="d-flex py-1">
                                            <img class="avatar rounded-circle" src="../assets/images/profile_av2.png" alt="profile">
                                            <div class="flex-fill ms-3">
                                                <p class="mb-0"><span class="font-weight-bold">John	Quinn</span></p>
                                                <small class="">ID:0020392</small>
                                            </div>
                                        </div>

                                        <div><hr class="dropdown-divider border-dark"></div>
                                    </div>
                                    <div class="list-group m-2 ">
                                        <a href="auth-signin.jsp" class="list-group-item list-group-item-action border-0 "><i class="icofont-logout fs-6 me-3"></i>Signout</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- menu toggler -->
                    <button class="navbar-toggler p-0 border-0 menu-toggle order-3" type="button" data-bs-toggle="collapse" data-bs-target="#mainHeader">
                        <span class="fa fa-bars"></span>
                    </button>

                </div>
            </nav>
        </div>

        <!-- Body: Body -->
        <div class="body d-flex py-3">
            <div class="container-xxl">
                <div class="row align-items-center">
                    <div class="border-0 mb-4">
                        <div class="card-header py-3 no-bg bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 class="fw-bold mb-0">Questionnaire</h3>
                        </div>
                    </div>
                </div> <!-- Row end  -->

                <div class="row align-item-center">
                    <div class="col-md-12">
                        <div class="card mb-3">
                            <div class="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                                <h6 class="mb-0 fw-bold ">Please answer the questionnaire before completing your booking.</h6>
                            </div>
                            <div class="card-body">
                                <form>
                                    <div class="row g-3 align-items-center">
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">Qestion 1</label>
                                                <br />
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q1" value="Yes" required data-parsley-errors-container="#error-radio">
                                                    <span><i></i>Yes</span>
                                                </label>
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q1" value="No">
                                                    <span><i></i>No</span>
                                                </label>
                                                <p id="error-radio"></p>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">Qestion 2</label>
                                                <br />
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q2" value="Yes" required data-parsley-errors-container="#error-radio">
                                                    <span><i></i>Yes</span>
                                                </label>
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q2" value="No">
                                                    <span><i></i>No</span>
                                                </label>
                                                <p id="error-radio"></p>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">Qestion 3</label>
                                                <br />
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q3" value="Yes" required data-parsley-errors-container="#error-radio">
                                                    <span><i></i>Yes</span>
                                                </label>
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q3" value="No">
                                                    <span><i></i>No</span>
                                                </label>
                                                <p id="error-radio"></p>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">Qestion 4</label>
                                                <br />
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q4" value="Yes" required data-parsley-errors-container="#error-radio">
                                                    <span><i></i>Yes</span>
                                                </label>
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q4" value="No">
                                                    <span><i></i>No</span>
                                                </label>
                                                <p id="error-radio"></p>
                                            </div>
                                        </div>
                                        <div class="col-md-12">
                                            <div class="form-group">
                                                <label class="form-label">Qestion 5</label>
                                                <br />
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q5" value="Yes" required data-parsley-errors-container="#error-radio">
                                                    <span><i></i>Yes</span>
                                                </label>
                                                <label class="fancy-radio form-check-label">
                                                    <input class="form-check-input" type="radio" name="Q5" value="No">
                                                    <span><i></i>No</span>
                                                </label>
                                                <p id="error-radio"></p>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <button type="submit" class="btn btn-primary mt-4">Submit</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div> 

    </div> 
  
</div>

<!-- Jquery Core Js -->
<script src="../assets/bundles/libscripts.bundle.js"></script>

<!-- Plugin Js-->
<script src="../assets/plugin/parsleyjs/js/parsley.js"></script>
    

<!-- Jquery Page Js -->
<script src="../js/template.js"></script>
<script>
    $(function() {
        // initialize after multiselect
        $('#basic-form').parsley();
    });
</script>
 
</body>
</html> 
