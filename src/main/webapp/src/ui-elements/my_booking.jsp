﻿<%//Browser fallback force refresh
    response.setHeader("Cache-Control","no-store");
    response.setDateHeader("Expires", 0);
    response.setHeader("Pragma","no-cache");
%>
<%
    String script="";
    if(session.getAttribute("user")==null||Integer.parseInt(session.getAttribute("user").toString()) != 2) {
        script = "<script>alert('permission denied!');location.href='auth-signin.jsp'</script>";
    }
    response.getWriter().println(script);
%>
<%@ page import="com.example.Flying_Tiger.Class.Recipient" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>My booking </title>
    
  
    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">
</head>
<body>
<% long id= Long.parseLong(request.getParameter("id"));
    Recipient recipient=Recipient.getMapper().find(id);
    String hcpName=Recipient.getMapper().getHcpName(id);
    long hcpid=Recipient.getMapper().getHcpID(id);
%>
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
                <li><a class="m-link" href="booking.jsp?id=<%=id%>&hcpid=&date="><i class="icofont-meeting-add fs-5"></i> <span>Online Booking</span></a></li>
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
                                <p class="mb-0 text-end line-height-sm "><span class="font-weight-bold"><%=recipient.getName()%></span></p>
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
                                                <p class="mb-0"><span class="font-weight-bold"><%=recipient.getName()%></span></p>
                                                <small class="">ID:<%=recipient.getID()%></small>
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
        <div class="body d-flex py-lg-3 py-md-2">
            <div class="container-xxl">
                <div class="row align-items-center">
                    <div class="border-0 mb-4">
                        <div class="card-header no-bg bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 class="fw-bold mb-0 py-3 pb-2">My booking</h3>
                            <div class="col-auto py-2 w-sm-100">
                                <ul class="nav nav-tabs tab-body-header rounded invoice-set" role="tablist">
                                    <li class="nav-item"><a class="nav-link active" data-bs-toggle="tab" href="#Booking-Detail" role="tab">Booking Detail</a></li>
                                    <li class="nav-item"><a class="nav-link" data-bs-toggle="tab" href="#View-Proof" role="tab">View Proof</a></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div> <!-- Row end  -->

                <div class="row justify-content-center">
                    <div class="col-lg-12 col-md-12">
                        <%if(recipient.getSuitable()) {%>
                        <div class="tab-content">
                            <div class="tab-pane fade show active" id="Booking-Detail">
                                <div class="row justify-content-center">
                                    <div class="col-lg-8 col-md-12">
                                        <div class="card mb-3">
                                            <div class="card-body d-sm-flex justify-content-between">
                                                <a href="javascript:void(0);" class="d-flex">
                                                    <div class="flex-fill ms-3 text-truncate">
                                                        <h6 class="d-flex justify-content-between mb-0"><span>Recipient Name:<%=recipient.getName()%> </span></h6>
                                                        <span class="text-muted">Recipient Id: <%=recipient.getID()%></span>
                                                    </div>
                                                </a>
                                                <div class="text-end d-none d-md-block">
                                                    <p class="mb-1"><i class="icofont-location-pin ps-1"></i><%=hcpName%></p>
                                                    <span class="text-muted">Booking date&time: <%=Recipient.getMapper().timeslotDate(id)%> <%=Recipient.getMapper().timeslotTime(id)%></span>
                                                </div>
                                            </div>
                                            <div class="card-footer justify-content-between d-flex align-items-center">
                                                <div class="d-none d-md-block">
                                                    <strong>Status:</strong>
                                                    <%
                                                        String statu="In Progress";
                                                        if (recipient.getInjected())
                                                            statu="Completed";
                                                    %>
                                                    <span><%=statu%></span>
                                                </div>
                                                <div class="card-hover-show">
                                                    <a class="btn btn-sm btn-white border lift" href="delete_booking_rec.jsp?id=<%=id%>&hcpid=<%=hcpid%>">
                                                        Delete</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>  <!-- Row end  -->
                            </div> <!-- tab end  -->
                            <%}%>
                            <% if(recipient.getInjected()) {%>
                            <div class="tab-pane fade" id="View-Proof">
                                <div class="row justify-content-center">
                                    <div class="col-lg-8 col-md-12">
                                        <div class="d-flex justify-content-center">
                                            <table class="card p-5">
                                                <tr>
                                                    <td></td>
                                                    <td class="text-center">
                                                        <table>
                                                            <tr>
                                                                <td class="text-center py-2">
                                                                    <h4 class="mb-0"><strong>Letter of Commitment on COVID-19 Vaccination</strong></h4>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="pt-2 pb-4">
                                                                    <table>
                                                                        <tr>
                                                                            <td>
                                                                                This is to certify that <%=recipient.getName()%> has completed the COVID-19 vaccination, on date.<br>
                                                                                *This statement is true and valid.<br>
                                                                            </td>
                                                                        </tr>
                                                                    </table>
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td class="pt-2 pb-2 text-end">
                                                                    <a href="#"><%=hcpName%></a><br>
                                                                    <button type="button" class="btn btn-outline-secondary btn-lg my-1"><i class="fa fa-print"></i> Print</button>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>
                                            </table>
                                        </div>
                                    </div>
                                </div> <!-- Row end  -->
                            </div> <!-- tab end  -->
                        <%}%>
                        </div>
                    </div>
                </div> <!-- Row end  -->
            </div>
        </div>
    </div>
</div>

<!-- Jquery Core Js -->
<script src="../assets/bundles/libscripts.bundle.js"></script>

<!-- Jquery Page Js -->
<script src="../js/template.js"></script>

</body>
</html>
