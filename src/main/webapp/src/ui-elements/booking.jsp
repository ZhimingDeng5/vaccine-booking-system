<%@ page import="com.example.Flying_Tiger.Recipient" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Booking</title>
    
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
                                <img class="avatar lg rounded-circle img-thumbnail" src="../assets/images/profile_av.png" alt="profile">
                            </a>
                            <div class="dropdown-menu rounded-lg shadow border-0 dropdown-animation dropdown-menu-end p-0 m-0">
                                <div class="card border-0 w280">
                                    <div class="card-body pb-0">
                                        <div class="d-flex py-1">
                                            <img class="avatar rounded-circle" src="../assets/images/profile_av.png" alt="profile">
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
                            <h3 class="fw-bold mb-0">Booking</h3>
                        </div>
                    </div>
                </div> <!-- Row end  -->

                <div class="row align-item-center">
                    <div class="col-md-12">
                        <div class="card mb-3">
                            <div class="card-header py-3 d-flex justify-content-between bg-transparent border-bottom-0">
                                <h6 class="mb-0 fw-bold ">Please choose the option that suits you.</h6>
                            </div>
                            <div class="card-body">
                                <form id="basic-form" method="post" novalidate>
                                    <div class="row g-3 align-items-center">
                                            <div class="col-sm-6">
                                                <label class="form-label">Select the Health Care Provider</label>
                                                <select class="form-select">
                                                    <option selected>A hospital</option>
                                                    <option value="1">B hospital</option>
                                                    <option value="2">C hospital</option>
                                                    <option value="3">D hospital</option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Select Vaccine Type</label>
                                                <select class="form-select">
                                                    <option selected>A </option>
                                                    <option value="1">B </option>
                                                    <option value="2">C </option>
                                                    <option value="3">D </option>
                                                </select>
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="abc" class="form-label">Select Date</label>
                                                <input type="date" class="form-control" id="abc">
                                            </div>
                                            <div class="col-sm-6">
                                                <label class="form-label">Select Time</label>
                                                <select class="form-select">
                                                    <option selected>9:00 </option>
                                                    <option value="1">11:00 </option>
                                                    <option value="2">14:00 </option>
                                                    <option value="3">16:00 </option>
                                                </select>
                                            </div>
                                    </div>
                                    <button type="sreach" class="btn btn-primary mt-4">Search</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row clearfix g-3">
                    <div class="col-sm-12">
                        <div class="card mb-3">
                            <div class="card-body">
                                <table id="myProjectTable" class="table table-hover align-middle mb-0" style="width:100%">
                                    <thead>
                                    <tr>
                                        <th>Id</th>
                                        <th>Providers Name</th>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Number of vaccines</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <div class="btn-group" role="group" aria-label="Basic outlined example">
                                    <tr>
                                        <td>
                                            #EX-00002
                                        </td>
                                        <td>
                                            A hospital
                                        </td>
                                        <td>
                                            12/03/2021
                                        </td>
                                        <td>
                                            12:00
                                        </td>
                                        <td>
                                            10
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-outline-secondary checkrow"><i class="icofont-ui-check text-success"></i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            #EX-00005
                                        </td>
                                        <td>
                                            B hospital
                                        </td>
                                        <td>
                                            12/05/2021
                                        </td>
                                        <td>
                                            11:00
                                        </td>
                                        <td>
                                            5
                                        </td>
                                        <td>
                                            <button type="button" class="btn btn-outline-secondary checkrow"><i class="icofont-ui-check text-success"></i></button>
                                        </td>
                                    </tr>
                                    </div>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div><!-- Row End -->
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