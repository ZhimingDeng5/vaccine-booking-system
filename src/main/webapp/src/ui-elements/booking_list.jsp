﻿<%//Browser fallback force refresh
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
    <title>Booking List</title>
    
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
    HealthCareProvider hcp= null;
    try {
        hcp = HealthCareProvider.getMapper().find(id);
        name=hcp.getName();
        type=hcp.getType();
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
                                <p class="mb-0 text-end line-height-sm "><span class="font-weight-bold">A hospital</span></p>
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
                                                <p class="mb-0"><span class="font-weight-bold">A hospital</span></p>
                                                <small class="">ID:0020392</small>
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
                        <div class="card-header py-3 no-bg bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 class="fw-bold mb-0">Booking List</h3>
                            <div class="col-auto d-flex w-sm-100">
                                <button type="button" class="btn btn-primary btn-set-task w-sm-100" ></i>Save</button>
                            </div>
                        </div>
                    </div>
                </div> <!-- Row end  -->
                <div class="row clearfix g-3">
                  <div class="col-sm-12">
                        <div class="card mb-3">
                            <div class="card-body">
                                <table id="myProjectTable" class="table table-hover align-middle mb-0" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Recipients Name</th>
                                            <th>Vaccine Type</th>
                                            <th>Date</th>
                                            <th>Time</th>
                                            <th>Status</th>   
                                            <th>Actions</th>  
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>
                                                #EX-00002
                                            </td>
                                            <td>
                                                Laundry 
                                            </td>
                                            <td>
                                                AstraZeneca
                                            </td>
                                           <td>
                                                12/03/2021
                                           </td>
                                           <td>
                                               12:00
                                           </td>
                                            <td>
                                                <select class="form-select">
                                                    <option selected>In Progress</option>
                                                    <option value="1">Completed</option>
                                                </select>
                                           </td>
                                            <td>
                                                <div class="btn-group" role="group" aria-label="Basic outlined example">
                                                    <button type="button" class="btn btn-outline-secondary deleterow"><i class="icofont-ui-delete text-danger"></i></button>
                                                </div>
                                            </td>
                                        </tr>
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
<script src="../assets/bundles/dataTables.bundle.js"></script>

<!-- Jquery Page Js -->
<script src="../js/template.js"></script>
<script>
    
    $(document).ready(function() {
        $('#myProjectTable')
        .addClass( 'nowrap' )
        .dataTable( {
            responsive: true,
            columnDefs: [
                { targets: [-1, -3], className: 'dt-body-right' }
            ]
        });
        $('.deleterow').on('click',function(){
        var tablename = $(this).closest('table').DataTable();  
        tablename
                .row( $(this)
                .parents('tr') )
                .remove()
                .draw();

        } );
    });
</script>
</body>
</html>
