﻿<%@ page import="com.example.Flying_Tiger.Recipient" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Recipients List</title>
    
    <!-- plugin css file  -->
    <link rel="stylesheet" href="../assets/plugin/datatables/responsive.dataTables.min.css">
    <link rel="stylesheet" href="../assets/plugin/datatables/dataTables.bootstrap5.min.css">
    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">
</head>
<body>

<div id="ihealth-layout" class="theme-tradewind">

    <!-- sidebar -->
    <div class="sidebar px-4 py-4 py-md-5 me-0">
        <div class="d-flex flex-column h-100">
            <a href="index.html" class="mb-0 brand-icon">
                <span class="logo-icon">
                    <i class="icofont-heart-beat fs-2"></i>
                </span>
                <span class="logo-text">Administrator</span>
            </a>
            <!-- Menu: main ul -->

            <ul class="menu-list flex-grow-1 mt-3">
                <li><a class="m-link" href="Admin_Dashboard.jsp"><i class="icofont-dashboard fs-5"></i> <span>Dashboard</span></a></li>
                <li><a class="m-link" href="manage_recipients.jsp"><i class="icofont-student-alt fs-5"></i> <span>Manage Recipients</span></a></li>
                <li><a class="m-link" href="manage_providers.jsp"><i class="icofont-patient-file fs-5"></i> <span>Manage Providers</span></a></li>
                <li><a class="m-link" href="manage_vaccines.jsp"><i class="icofont-blood-test fs-5"></i> <span>Manage Vaccines</span></a></li>
                <li><a class="m-link" href="view_booking.jsp"><i class="icofont-prescription fs-5"></i> <span>View Booking</span></a></li>
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
                                <small>Administrator</small>
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
        <div class="body d-flex py-lg-3 py-md-2">
            <div class="container-xxl">
                <div class="row align-items-center">
                    <div class="border-0 mb-4">
                        <div class="card-header py-3 no-bg bg-transparent d-flex align-items-center px-0 justify-content-between border-bottom flex-wrap">
                            <h3 class="fw-bold mb-0">Recipients List</h3>
                            <div class="col-auto d-flex w-sm-100">
                                <button type="button" class="btn btn-primary btn-set-task w-sm-100" data-bs-toggle="modal" data-bs-target="#expadd"><i class="icofont-plus-circle me-2 fs-6"></i>Add Recipients</button>
                            </div>
                        </div>
                    </div>
                </div> <!-- Row end  -->
                <div class="row clearfix g-3">
                  <div class="col-sm-12">
                        <div class="card mb-3">
                            <div class="card-body">
                                <%
                                    Recipient[] recipients=Recipient.getMapper().findall();
                                %>
                                <table id="myProjectTable" class="table table-hover align-middle mb-0" style="width:100%">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th>Health Care Provider</th>
                                            <th>Vaccine Type</th>
                                            <th>Date</th>
                                            <th>Time</th>
                                            <th>Status</th>   
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <%
                                                for (Recipient recipient:recipients){
                                            %>
                                            <td>
                                                <%=recipient.getID()%>
                                            </td>
                                            <td>
                                                <%=recipient.getName()%>
                                           </td>
                                           <td>
                                            <span class="fw-bold ms-1"><%=Recipient.getMapper().getHcpName(recipient.getID())%></span>
                                           </td>
                                           <td>
                                               <%=Recipient.getMapper().getVaccineType(recipient.getID())%>
                                            </td>
                                           <td >
                                               <%=Recipient.getMapper().timeslotDate(recipient.getID())%>
                                           </td>
                                           <td ><%=Recipient.getMapper().timeslotTime(recipient.getID())%></td>
                                           <td><span class="badge bg-warning">In Progress</span></td>
                                            <td>
                                                <div class="btn-group" role="group" aria-label="Basic outlined example">
                                                    <button type="button" class="btn btn-outline-secondary"  data-bs-toggle="modal" data-bs-target="#expedit"><i class="icofont-edit text-success"></i></button>
                                                    <button type="button" onclick="window.location='delete_recipients.jsp?id=<%=recipient.getID()%>'" class="btn btn-outline-secondary deleterow"><i class="icofont-ui-delete text-danger"></i></button>
                                                </div>
                                            </td>
                                        </tr>
                                        <%
                                            }
                                        %>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                  </div>
                </div><!-- Row End -->
            </div>
        </div>
        
   
        <!-- Add Recipients -->

        <div class="modal fade" id="expadd" tabindex="-1"  aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title  fw-bold" id="expaddLabel"> Add Recipients</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>

                <div class="modal-body">
                    <form id="form1" action="../../AddNewRec-Servlet" method="post">
                    <div class="mb-3">
                        <label for="item" class="form-label">Password</label>
                        <input type="text" class="form-control" name="password">
                    </div>
                    <div class="deadline-form">
                            <div class="row g-3 mb-3">
                              <div class="col-sm-6">
                                <label for="depone" class="form-label">Name</label>
                                <input type="text" class="form-control" name="name">
                              </div>
                              <div class="col-sm-6">
                                <label for="abc" class="form-label">Birthdate</label>
                                <input type="date" class="form-control" name="birthdate">
                              </div>
                            </div>


                    </div>
                    </form>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Done</button>
                    <button type="submit" class="btn btn-primary" onclick="document.getElementById('form1').submit();">Add</button>
                </div>
            </div>
            </div>
        </div>

<%--         <!-- Edit Recipients-->--%>
<%--        <div class="modal fade" id="expedit" tabindex="-1"  aria-hidden="true">--%>
<%--            <div class="modal-dialog modal-dialog-centered modal-md modal-dialog-scrollable">--%>
<%--            <div class="modal-content">--%>
<%--                <div class="modal-header">--%>
<%--                    <h5 class="modal-title  fw-bold" id="expeditLabel"> Edit Recipients</h5>--%>
<%--                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>--%>
<%--                </div>--%>
<%--                <div class="modal-body">--%>
<%--                        <div class="mb-3">--%>
<%--                            <label for="item" class="form-label">Id</label>--%>
<%--                            <input type="text" class="form-control" id="item">--%>
<%--                        </div>--%>
<%--                        <div class="deadline-form">--%>
<%--                            <form>--%>
<%--                                <div class="row g-3 mb-3">--%>
<%--                                    <div class="col-sm-6">--%>
<%--                                        <label for="depone" class="form-label">Name</label>--%>
<%--                                        <input type="text" class="form-control" name="depone">--%>
<%--                                    </div>--%>
<%--                                    <div class="col-sm-6">--%>
<%--                                        <label for="abc" class="form-label">Birthdate</label>--%>
<%--                                        <input type="date" class="form-control" birthdate="abc">--%>
<%--                                    </div>--%>
<%--                                </div>--%>

<%--                            </form>--%>
<%--                        </div>--%>
<%--                    --%>
<%--                </div>--%>
<%--                <div class="modal-footer">--%>
<%--                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Done</button>--%>
<%--                    <button type="submit" class="btn btn-primary">Save</button>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--            </div>--%>
<%--        </div>--%>

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
