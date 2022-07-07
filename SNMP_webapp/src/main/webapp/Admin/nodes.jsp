<%-- 
    Document   : history
    Created on : Jul 5, 2022, 4:16:30 PM
    Author     : nour
--%>

<%@page import="com.iti.snmp.nodes.Node"%>
<%@page import="com.iti.snmp.nodes.NodeHandler"%>
<%@page import="com.iti.snmp.admin.Admin"%>
<%@page import="com.iti.snmp.admin.HandlingAdmin"%>
<%@page import="com.iti.snmp.history.History"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="com.iti.snmp.history.HistoryHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
     Integer aid = (Integer) session.getAttribute("adminId");
  List<Node>nodes=NodeHandler.getNodesByAdminId(aid);
List<Admin>admins=HandlingAdmin.getAdmins();
%>
<!--
=========================================================
* Material Dashboard 2 - v3.0.4
=========================================================

* Product Page: https://www.creative-tim.com/product/material-dashboard
* Copyright 2022 Creative Tim (https://www.creative-tim.com)
* Licensed under MIT (https://www.creative-tim.com/license)
* Coded by Creative Tim

=========================================================

* The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
-->
<html>
    <head>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    </head>
    <%@include file="sidebar.jsp" %>
    <main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg ">
        <!-- Navbar -->
       
       <%@include file="topbar.jsp" %>
        <!-- End Navbar -->
        <div class="container-fluid py-4">
           
            <div class="row">
               
                <div class="col-12">
                     
                    <div class="card my-4">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                                <h6 class="text-white text-capitalize ps-3">Nodes</h6>
                            </div>
                        </div>
                        <div class="card-body px-0 pb-2">
                            <div class="table-responsive p-0">
                                <table class="table align-items-center mb-0">
                                    <thead>
                                        <tr>
                                            <th style="display:none;"></th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 align-middle text-center ">Name</th>
                                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 align-middle text-center ">IP</th>
                                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2 align-middle text-center ">Description</th>
                                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 align-middle text-center ">Admin Email</th>
                                         
                                               
                                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 align-middle text-center">Action needed</th>
                                                                                   
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <%                                            for (Node node : nodes) {
                                        %>

                                        <tr>
                                            
                                            <td>
                                                
                                                        <h6 class="align-middle text-center text-sm"><%=node.getName()%></h6>
                                                 
                                               
                                            </td>
                                            <td>
                                                 <h6 class="align-middle text-center text-sm"><%= node.getNodeIp()%></h6>
                                              

                                            </td>
                                            <td class="align-middle text-center text-sm">
                                                 <h6 class="mb-0 text-sm"><%=node.getDescription()%></h6>
                                               
                                            </td>
                                            <td class="align-middle text-center">
                                                 <h6 class="mb-0 text-sm"><%=node.getAdminName()%></h6>
                                              
                                            </td>
                                            
                                          <% if (node.getStatus().equalsIgnoreCase("red")){%>
                                                   <td class="align-middle text-center">
                                                       <h6 class="mb-0 text-sm"><a href="traps.jsp" class=" btn btn-outline btn-danger border-radius-lg">Check traps to be resolved </a></h6>
     
                                            </td>
                                            <%}else{%>
                                                <td class="align-middle text-center">
                                                       <h6 class="mb-0 text-sm">No action needed</h6>
     
                                            </td>
                                            <%}%>
                                        </tr>
                                    
                                    <% }%>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                                                <!-- Modal -->
            
               

            <footer class="footer py-4  ">
                <div class="container-fluid">
                    <div class="row align-items-center justify-content-lg-between">
                        <div class="col-lg-6 mb-lg-0 mb-4">
                            <div class="copyright text-center text-sm text-muted text-lg-start">
                                © <script>
                                    document.write(new Date().getFullYear())
                                </script>,
                                made with <i class="fa fa-heart"></i> by
                                <a href="https://www.creative-tim.com" class="font-weight-bold" target="_blank">Creative Tim</a>
                                for a better web.
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                             
                            </ul>
                        </div>
                    </div>
                </div>
            </footer>
        </div>
    </main>
   <div class="fixed-plugin ps " style="user-select: auto;">
        <a class="fixed-plugin-button text-dark position-fixed px-3 py-2" style="user-select: auto;">
            <i class="material-icons py-2" style="user-select: auto;">settings</i>
        </a>
        <div class="card shadow-lg" style="user-select: auto;">
            <div class="card-header pb-0 pt-3" style="user-select: auto;">
                <div class="float-start" style="user-select: auto;">
                    <h5 class="mt-3 mb-0" style="user-select: auto;">Action on Nodes</h5>
                    <p style="user-select: auto;"> please choose your action</p>
                </div>
                <div class="float-end mt-4" style="user-select: auto;">
                    
                </div>
                <!-- End Toggle Button -->
            </div>
            <hr class="horizontal dark my-1" style="user-select: auto;">
            <div class="card-body pt-sm-3 pt-0" style="user-select: auto;">
                <!-- Sidebar Backgrounds -->
                
                
                <!-- Sidenav Type -->
                
                
                
                <!-- Navbar Fixed -->
                
                
                
                
                <a class="btn bg-gradient-info w-100" style="user-select: auto;background-image: linear-gradient(195deg, #da1e62 0%, #e43170 100%);" onclick="addNode()">Add new Node</a>
                
                
                <a class="btn btn-outline-dark w-100" style="user-select: auto;visibility: hidden" onclick="deleteNode()">Delete Existing Node</a>
               
            </div>
        </div>
                                       <div id="addModal" class="modal">
                           <div class="modal-dialog modal-notify modal-info" role="document" style="user-select: auto;height: 50%;">
                               
                                 <div class="modal-content text-center" style="height: 90%;user-select: auto;height: fit-content;">
                                 
                                   <div  class="modal-header d-flex justify-content-center" style="user-select: auto;height: fit-content;">
                                       <p id="messageh" class="heading" style="user-select: auto;color: #d1e2fb;">Adding Node</p>
                                   </div>

                                 
                               <div class="row" style="user-select: auto;height: fit-content;justify-content: center;">
                               

                               <div id="messageb" class="col-9" style="user-select: auto;height: fit-content;">
                                 
                                   <form class="text-center border border-light p-5" style="user-select: auto;" id="adding">
                                     <div class="input-group input-group-outline my-3" style="user-select: auto;">
                    <label class="form-label" style="user-select: auto;">Node name</label>
                    <input type="text" class="form-control" style="user-select: auto;" name="nname" id="name">
          </div>
                                       
                                          <div class="input-group input-group-outline my-3" style="user-select: auto;">
                    <label class="form-label" style="user-select: auto;">Node Ip</label>
                    <input type="text" class="form-control" style="user-select: auto;" name="nip" id="ip">
          </div>
                                       <p id="errip" style="color: red"></p>
                                          <div class="input-group input-group-outline my-3" style="user-select: auto;">
                    <label class="form-label" style="user-select: auto;">Description</label>
                    <input type="text" class="form-control" style="user-select: auto;" name="ndesc" id="desc">
          </div>
                                          <div class="input-group input-group-outline my-3" style="user-select: auto;">
                    <label class="form-label" style="user-select: auto;"></label>
                    <select name="adminId" id="adId" class="form-control mb-4" >
                        <option value="0">Please choose an admin</option>
                        <% for (Admin admin:admins) {
                                    %><option value=<%=admin.getEmail()%>><%=admin.getEmail()%></option><% }
                                        %></select><input type="hidden" name="operation" value="addNode" style="user-select: auto;">
          </div>
                                   </form>

                               </div>
                             </div>

                                   <!--Footer-->
                                   <div class="modal-footer flex-center" style="align-items: end;user-select: auto;height: 30%;display: flex;justify-content: center;">
                                     <a id="action" class="btn bg-gradient-info w-50" style="user-select: auto;height: fit-content;background-image: linear-gradient(195deg, #da1e62 0%, #e43170 100%);" onclick="doAction('adding')">Add node</a>
                                     
                                   </div>
                                 </div>
                                 <!--/.Content-->
                               </div>
                               </div>
                            <div id="delModal" class="modal">
                           <div class="modal-dialog modal-notify modal-info" role="document" style="user-select: auto;height: 50%;">
                               
                                 <div class="modal-content text-center" style="height: 90%;user-select: auto;height: fit-content;">
                                 
                                   <div  class="modal-header d-flex justify-content-center" style="user-select: auto;height: fit-content;">
                                       <p id="messageh" class="heading" style="user-select: auto;color: #d1e2fb;">Deleting Node</p>
                                   </div>

                                 
                               <div class="row" style="user-select: auto;height: fit-content;justify-content: center;">
                               

                               <div id="messageb" class="col-9" style="user-select: auto;height: fit-content;">
                                 
                                   <form class="text-center border border-light p-5" style="user-select: auto;" id="deleting">
         
                                          <div class="input-group input-group-outline my-3" style="user-select: auto;">
                    <label class="form-label" style="user-select: auto;"></label>
                    <select name="nodeIp" id="adId" class="form-control mb-4" style="height: fit-content" >
                        <option value="0">Please choose node ip to be deleted</option><% for (Node node:nodes) {
                                    %><option value=<%=node.getNodeIp()%>><%=node.getNodeIp()%></option><% }
                                        %></select><input type="hidden" name="operation" value="delNode" style="user-select: auto;">
          </div>
                                   </form>

                               </div>
                             </div>

                                   <!--Footer-->
                                   <div class="modal-footer flex-center" style="align-items: end;user-select: auto;height: 30%;display: flex;justify-content: center;">
                                       <a id="action" class="btn bg-gradient-info w-50" style="user-select: auto;height: fit-content;background-image: linear-gradient(195deg, #da1e62 0%, #e43170 100%);"onclick="doAction('deleting')">Delete node</a>
                                     
                                   </div>
                                 </div>
                                 <!--/.Content-->
                               </div>
                               </div>
    <div class="ps__rail-x" style="left: 0px; bottom: 0px; user-select: auto;"><div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px; user-select: auto;"></div></div><div class="ps__rail-y" style="top: 0px; right: 0px; user-select: auto;"><div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px; user-select: auto;"></div></div></div>
    <!--   Core JS Files   -->
    <script src="../assets/js/core/popper.min.js"></script>
    <script src="../assets/js/core/bootstrap.min.js"></script>
    <script src="../assets/js/plugins/perfect-scrollbar.min.js"></script>
    <script src="../assets/js/plugins/smooth-scrollbar.min.js"></script>
    <script>
                            var win = navigator.platform.indexOf('Win') > -1;
                            if (win && document.querySelector('#sidenav-scrollbar')) {
                                var options = {
                                    damping: '0.5'
                                }
                                Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
                            }
                             var addmodal = document.getElementById("addModal");
                             var delmodal = document.getElementById("delModal");
                             window.onclick = function(event) {
  if (event.target === addmodal) {
    addmodal.style.display = "none";
  };
  if (event.target === delmodal) {
    delmodal.style.display = "none";
  }};
  function addNode(){
       addmodal.style.display = "block"; 
     
  }
  function deleteNode(){
       delmodal.style.display = "block"; 
     
  }
  function doAction(form){
     var formdata=$("#"+form).serialize();
     $.get("/SNMP_webapp/NodesAction", formdata,function(data){
         chk(data)
       
});

  }
  function chk(data){
      if(data==""){
          location.reload();
      }else{
          $("#errip").html(data)
      }
  }
 
 
    </script>
    <!-- Github buttons -->
    <script async defer src="https://buttons.github.io/buttons.js"></script>
    <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
    <script src="../assets/js/material-dashboard.min.js?v=3.0.4"></script>
</body>

</html>
