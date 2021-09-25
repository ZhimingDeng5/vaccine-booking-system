<%//Browser fallback force refresh
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma","no-cache");
%>
<%
    String script="";
    if(session.getAttribute("user")==null||Integer.parseInt(session.getAttribute("user").toString()) != 3) {
        script = "<script>alert('permission denied!');location.href='auth-signin.jsp'</script>";
    }
    response.getWriter().println(script);
%>
<%@ page import="com.example.Flying_Tiger.HealthCareProvider" %>
<%@ page import="java.sql.SQLException" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Provider Profile </title>
    
    <!-- plugin css file  -->
    <link rel="stylesheet" href="../assets/plugin/datatables/responsive.dataTables.min.css">
    <link rel="stylesheet" href="../assets/plugin/datatables/dataTables.bootstrap5.min.css">
    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">
</head>
<body>
<%
    long id= Long.parseLong(request.getParameter("id"));
    String name="";
    String type="";
    int postcode=0;
    HealthCareProvider hcp= null;
    try {
        hcp = HealthCareProvider.getMapper().find(id);
        name=hcp.getName();
        type=hcp.getType();
        postcode=hcp.getPost();
    } catch (SQLException e) {
        e.printStackTrace();
    }
%>
<div id="ihealth-layout" class="theme-tradewind">
    
   <!-- sidebar -->
   <div class="sidebar px-4 py-4 py-md-5 me-0">
    <div class="d-flex flex-column h-100">
        <a href="index.html" class="mb-0 brand-icon">
            <span class="logo-icon">
                <i class="icofont-heart-beat fs-2"></i>
            </span>
            <span class="logo-text">Health Care Provider</span>
        </a>
        <!-- Menu: main ul -->

        <ul class="menu-list flex-grow-1 mt-3">
            <li><a class="m-link" href="Provider_profile.jsp?id=<%=id%>"><i class="icofont-student-alt fs-5"></i> <span>My profile</span></a></li>
            <li><a class="m-link" href="Add_questionnaire.jsp?id=<%=id%>"><i class="icofont-paper fs-5"></i> <span>Add questionnaire</span></a></li>
            <li><a class="m-link" href="Edit_questionnaire.jsp?id=<%=id%>"><i class="icofont-pen-alt-2 fs-5"></i> <span>Edit questionnaire</span></a></li>
            <li><a class="m-link" href="booking_list.jsp?id=<%=id%>"><i class="icofont-prescription fs-5"></i> <span>Booking list</span></a></li>
            <li><a class="m-link" href="my_timeslot.jsp?id=<%=id%>"><i class="icofont-clock-time fs-5"></i> <span>My Timeslots</span></a></li>
            <li><a class="m-link" href="public_timeslot.jsp?id=<%=id%>"><i class="icofont-ui-timer fs-5"></i> <span>Public Timeslots</span></a></li>
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
                                <p class="mb-0 text-end line-height-sm "><span class="font-weight-bold"><%=name%></span></p>
                                <small>Health Care Provider</small>
                            </div>
                            <a class="nav-link dropdown-toggle pulse p-0" href="#" role="button" data-bs-toggle="dropdown" data-bs-display="static">
                                <img class="avatar lg rounded-circle img-thumbnail" src="../assets/images/profile_av3.png" alt="profile">
                            </a>
                            <div class="dropdown-menu rounded-lg shadow border-0 dropdown-animation dropdown-menu-end p-0 m-0">
                                <div class="card border-0 w280">
                                    <div class="card-body pb-0">
                                        <div class="d-flex py-1">
                                            <img class="avatar rounded-circle" src="../assets/images/profile_av3.png" alt="profile">
                                            <div class="flex-fill ms-3">
                                                <p class="mb-0"><span class="font-weight-bold"><%=name%></span></p>
                                                <small class="">ID:<%=id%></small>
                                            </div>
                                        </div>

                                        <div><hr class="dropdown-divider border-dark"></div>
                                    </div>
                                    <div class="list-group m-2 ">
                                        <a href="logout.jsp" class="list-group-item list-group-item-action border-0 "><i class="icofont-logout fs-6 me-3"></i>Signout</a>
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
                <div class="row g-3">
                    <div class="col-xl-12 col-lg-12 col-md-12">
                        <div class="card teacher-card  mb-3">
                            <div class="card-body d-flex teacher-fulldeatil">
                                <div class="profile-teacher pe-xl-4 pe-md-2 pe-sm-4 pe-4 text-center w220">
                                    <a href="#">
                                        <img src="../assets/images/avatar3.jpg" alt="" class="avatar xl rounded-circle img-thumbnail shadow-sm">
                                    </a>
                                </div>
                                <div class="teacher-info border-start ps-xl-4 ps-md-4 ps-sm-4 ps-4 w-100">
                                    <h6  class="mb-0 mt-2  fw-bold d-block fs-6">Name: <%=name%></h6>
                                     <div class="row g-2 pt-2">
                                         <div class="col-xl-12">
                                             <div class="d-flex align-items-center">
                                                 <i class="icofont-teacher"></i>
                                                 <span class="ms-2">Provider ID: <%=id%></span>
                                             </div>
                                         </div>
                                         <div class="col-xl-12">
                                            <div class="d-flex align-items-center">
                                                <i class="icofont-home"></i>
                                                <span class="ms-2">Type of provider: <%=type%></span>
                                            </div>
                                        </div>
                                        <div class="col-xl-12">
                                            <div class="d-flex align-items-center">
                                                <i class="icofont-address-book"></i>
                                                <span class="ms-2">Postcode: <%=postcode%></span>
                                            </div>
                                        </div>
                                    </div>
                                </div>
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
<script src="../assets/bundles/apexcharts.bundle.js"></script>
<script src="../assets/bundles/dataTables.bundle.js"></script>

<!-- Jquery Page Js -->
<script src="../js/template.js"></script>
<script src="../js/page/doctor-profile.js"></script>
 
</body>
</html> 
