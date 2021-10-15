<%//Browser fallback force refresh
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
<%@ page import="com.example.Flying_Tiger.Class.HealthCareProvider" %>
<%@ page import="java.sql.Date" %>
<%@ page import="com.example.Flying_Tiger.Class.Timeslot" %>
<%@ page import="com.example.Flying_Tiger.Class.Vaccine" %>
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
    Recipient recipient=Recipient.getMapper().find(Long.parseLong(id));
    String hcpid=request.getParameter("hcpid");
    String datestr=request.getParameter("date");
    HealthCareProvider[] hcps;
    if(!hcpid.equals("")) {
        hcps = new HealthCareProvider[1];
        long longHcpid= Long.parseLong(hcpid);
        hcps[0]=HealthCareProvider.getMapper().find(longHcpid);
        if(!datestr.equals(""))
        {
            Date date= Date.valueOf(datestr);
            hcps[0]=HealthCareProvider.getMapper().find(longHcpid,date);
        }
    }
    else if(!datestr.equals(""))
    {
        Date date= Date.valueOf(datestr);
        hcps=HealthCareProvider.getMapper().findallWithTimeslot(date);
    }
    else
    {
        hcps=HealthCareProvider.getMapper().findallWithTimeslot();
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
                                <form id="basic-form" action="booking.jsp?id=<%=id%>" method="post" novalidate>
                                    <div class="row g-3 align-items-center">
                                            <div class="col-sm-6">
                                                <%
                                                    HealthCareProvider[] hcpslist=HealthCareProvider.getMapper().findall();
                                                %>
                                                <label class="form-label">Select the Health Care Provider</label>
                                                <select class="form-select" name="hcpid">
                                                    <option value=""></option>
                                                    <%for (HealthCareProvider hcp:hcpslist) {%>
                                                    <option value=<%=hcp.getID()%>><%=hcp.getName()%></option>
                                                    <%}%>
                                                </select>
                                            </div>
                                            <div class="col-sm-6">
                                                <label for="date_info" class="form-label">Select Date</label>
                                                <input type="date" class="form-control"  name="date" id="date_info" >
                                            </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary mt-4">Search</button>
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
                                        <th>Providers Name</th>
                                        <th>Date</th>
                                        <th>Time</th>
                                        <th>Number of vaccines</th>
                                        <th>Actions</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <div class="btn-group" role="group" aria-label="Basic outlined example">
                                        <% for(HealthCareProvider hcp2:hcps){
                                            for (Timeslot timeslot:hcp2.getTimeslots()){
                                        %>
                                    <tr>
                                        <td>
                                            <%=hcp2.getName()%>
                                        </td>
                                        <td>
                                            <%=timeslot.getDate()%>
                                        </td>
                                        <td>
                                            <%=timeslot.getTime()%>
                                        </td>
                                        <% int numLeft=Timeslot.getMapper().getNumLeft(hcp2.getID(),timeslot.getTimeslotID());%>
                                        <td>
                                            <%=numLeft%>
                                        </td>
                                        <%if(!recipient.getSuitable()) {%>
                                        <td>
                                            <button type="button"  data-bs-toggle="modal" data-bs-target="#expadd"
                                                    class="btn btn-outline-secondary editrow"
                                            data-hcpid="<%=hcp2.getID()%>" data-tid="<%=timeslot.getTimeslotID()%>"
                                                    >
                                                <i class="icofont-ui-check text-success"></i></button>
                                        </td>
                                        <%}
                                        else{;%>
                                        <td></td>
                                        <%}%>
                                    </tr>
                                        <%}
                                            }%>
                                    </div>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div><!-- Row End -->
            </div>
        </div>
        <!-- Choose Type -->
        <div class="modal fade" id="expadd" tabindex="-1"  aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md modal-dialog-scrollable">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title  fw-bold" id="expaddLabel"> Choose Vaccine Type</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form id="form1" action="Recipients_questionnaire.jsp?id=<%=id%>" method="post">
                        <div class="modal-body">

                            <div class="col-sm-12">
                                <label  class="form-label">Health Care Provider Id</label>
                                <input type="text" class="form-control"  id="hcpid" name="hcpid" readonly="readonly">
                            </div>
                            <div class="col-sm-12">
                                <label  class="form-label">Timeslot Id</label>
                                <input type="text" class="form-control"  id="tid" name="tid" readonly="readonly">
                            </div>
                            <div class="col-sm-12">
                                <label class="form-label">Select the Vaccine Type</label>
                                <select class="form-select" id="type" name="type" required>
                                    <%  Vaccine[] vaccines=Vaccine.getMapper().findall();
                                        for (Vaccine vaccine:vaccines) {%>
                                    <option value="<%=vaccine.getID()%>"><%=vaccine.getType()%></option>
                                    <%}%>
                                </select>
                            </div>

                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                            <input type="submit" class="btn btn-primary" value="Submit">
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
<script src="../assets/plugin/parsleyjs/js/parsley.js"></script>
    

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
        $('.editrow').on('click',function(){
            var tid = $(this).data('tid');
            var hcpid=$(this).data('hcpid');
            $(".modal-body #hcpid").val( hcpid );
            $(".modal-body #tid").val( tid );
        } );
    });
</script>
 
</body>
</html> 