<%@ page import="com.example.Flying_Tiger.HealthCareProvider" %>
<!doctype html>
<html class="no-js" lang="en" dir="ltr">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Providers List</title>
    
    <!-- plugin css file  -->
    <link rel="stylesheet" href="../assets/plugin/datatables/responsive.dataTables.min.css">
    <link rel="stylesheet" href="../assets/plugin/datatables/dataTables.bootstrap5.min.css">
    <!-- project css file  -->
    <link rel="stylesheet" href="../assets/css/ihealth.style.min.css">
    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(c) {
            $('.alert-close').on('click', function(c){
                $('.message').fadeOut('slow', function(c){
                    $('.message').remove();
                });
            });
        });
    </script>
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
<%
    HealthCareProvider[] hcps=HealthCareProvider.getMapper().findall();
%>
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
                                <small>Administrator</small>
                            </div>
                            <a class="nav-link dropdown-toggle pulse p-0" href="#" role="button" data-bs-toggle="dropdown" data-bs-display="static">
                                <img class="avatar lg rounded-circle img-thumbnail" src="../assets/images/profile_av1.png" alt="profile">
                            </a>
                            <div class="dropdown-menu rounded-lg shadow border-0 dropdown-animation dropdown-menu-end p-0 m-0">
                                <div class="card border-0 w280">
                                    <div class="card-body pb-0">
                                        <div class="d-flex py-1">
                                            <img class="avatar rounded-circle" src="../assets/images/profile_av1.png" alt="profile">
                                            <div class="flex-fill ms-3">
                                                <p class="mb-0"><span class="font-weight-bold">Administrator</span></p>
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
                            <h3 class="fw-bold mb-0">Providers List</h3>
                            <div class="col-auto d-flex w-sm-100">
                                <button type="button" class="btn btn-primary btn-set-task w-sm-100" data-bs-toggle="modal" data-bs-target="#expadd"><i class="icofont-plus-circle me-2 fs-6"></i>Add Providers</button>
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
                                            <th>Name</th>
                                            <th>Type</th>
                                            <th>Postcode</th>
                                            <th>Actions</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <%for (HealthCareProvider hcp: hcps) {%>
                                        <tr>
                                            <td>
                                                <%=hcp.getID()%>
                                            </td>
                                            <td>
                                                <%=hcp.getName()%>
                                           </td>
                                           <td>
                                               <span class="fw-bold ms-1"><%=hcp.getType()%></span>
                                           </td>
                                           <td >
                                                <%=hcp.getPost()%>
                                           </td>

                                            <td>
                                                <div class="btn-group" role="group" aria-label="Basic outlined example">
                                                    <button type="button" data-id="<%=hcp.getID()%>" class="btn btn-outline-secondary editrow" data-password="<%=hcp.getPassword()%>"
                                                            data-name="<%=hcp.getName()%>" data-type="<%=hcp.getType()%>" data-post="<%=hcp.getPost()%>"
                                                            data-bs-toggle="modal" data-bs-target="#expedit"><i class="icofont-edit text-success"></i></button>
                                                    <button type="submit" onclick="window.location='delete_hcp.jsp?id=<%=hcp.getID()%>'" class="btn btn-outline-secondary deleterow" ><i class="icofont-ui-delete text-danger"></i></button>
                                                </div>
                                            </td>
                                            <% } %>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                  </div>
                </div><!-- Row End -->
            </div>
        </div>


        <!-- Add Providers -->
        <div class="modal fade" id="expadd" tabindex="-1"  aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title  fw-bold" id="expaddLabel"> Add Providers</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="form1" action="../../AddNewHcp-Servlet" method="post">
                <div class="modal-body">

                    <div class="mb-3">
                        <label  class="form-label">Password</label>
                        <input type="text" class="form-control" name="password" required>
                    </div>
                    <div class="deadline-form">
                            <div class="row g-3 mb-3">
                              <div class="col-sm-6">
                                <label  class="form-label">Name</label>
                                <input type="text" class="form-control" name="name" required>
                              </div>
                              <div class="col-sm-6">
                                <label class="form-label">Type</label>
                                  <input type="text" class="form-control" name="type" required>
                              </div>
                            </div>
                            <div class="row g-3 mb-3">
                                <div class="col-sm-6">
                                    <label  class="form-label">Postcode</label>
                                    <input type="text" class="form-control" name="postcode" required>
                                </div>      
                            </div>

                    </div>
                    
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Done</button>
                    <input type="submit" class="btn btn-primary" value="Add">
                </div>
                </form>
            </div>

            </div>

        </div>

         <!-- Edit Recipients-->
        <div class="modal fade" id="expedit" tabindex="-1"  aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title  fw-bold" id="expeditLabel"> Edit Providers</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form id="form2" action="../../UpdateHcp-Servlet" method="post">
                <div class="modal-body">

                    <div class="mb-3">
                        <label class="form-label">Id</label>
                        <input type="text" class="form-control"  id="hcpId" name="hcpId" readonly="readonly">
                    </div>
                    <div class="mb-3">
                        <label  class="form-label">Password</label>
                        <input type="text" class="form-control" name="password" id="password" required>
                    </div>
                    <div class="row g-3 mb-3">
                        <div class="col-sm-6">
                            <label  class="form-label">Name</label>
                            <input type="text" class="form-control" id="name" name="name" required>
                        </div>
                        <div class="col-sm-6">
                            <label  class="form-label">Type</label>
                            <input type="text" class="form-control" id="type" name="type" required>
                        </div>
                    </div>
                    <div class="row g-3 mb-3">
                        <div class="col-sm-6">
                            <label class="form-label">Postcode</label>
                            <input type="text" class="form-control" id="post" name="post" required>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Done</button>
                    <input type="submit" class="btn btn-primary" value="Save">
                </div>
                </form>
            </div>
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
            console.log("1");
        var tablename = $(this).closest('table').DataTable();  
        tablename
                .row( $(this)
                .parents('tr') )
                .remove()
                .draw();

        } );
        $('.editrow').on('click',function(){
            var hcpId = $(this).data('id');
            var name=$(this).data('name');
            var type=$(this).data('type');
            var post=$(this).data('post');
            var password=$(this).data('password');
            $(".modal-body #hcpId").val( hcpId );
            $(".modal-body #name").val( name );
            $(".modal-body #type").val( type );
            $(".modal-body #post").val( post );
            $(".modal-body #password").val( password );
        } );
    });

</script>
</body>
</html>
