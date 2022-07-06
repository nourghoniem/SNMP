<%-- 
    Document   : dashboard
    Created on : Jul 5, 2022, 6:03:23 PM
    Author     : nour
--%>

<%@page import="java.util.List"%>
<%@page import="com.iti.snmp.nodes.Node"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.iti.snmp.admin.HandlingAdmin"%>
<%@page import="com.iti.snmp.traps.TrapHandler"%>
<%@page import="com.iti.snmp.nodes.NodeHandler"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% TrapHandler th= new TrapHandler();
     Integer adId=(Integer)session.getAttribute("adminId");
    String name=(String)session.getAttribute("name");
    List<Node>nodes=NodeHandler.getNodesByAdminId(adId);
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
<!DOCTYPE html>
<html>
 <head>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>

    </head>
    <%@include file="sidebar.jsp" %>
<main class="main-content position-relative max-height-vh-100 h-100 border-radius-lg " style="user-select: auto;">
    <!-- Navbar -->
    <%@include file="topbar.jsp" %>
    <!-- End Navbar -->
    <div class="container-fluid py-4" style="user-select: auto;">
      <div class="row" style="user-select: auto;">
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4" style="user-select: auto;">
          <div class="card" style="user-select: auto;">
            <div class="card-header p-3 pt-2" style="user-select: auto;">
              <div class="icon icon-lg icon-shape bg-gradient-dark shadow-dark text-center border-radius-xl mt-n4 position-absolute" style="user-select: auto;">
                <i class="fas fa-database" style="user-select: auto;" aria-hidden="true"></i>
              </div>
              <div class="text-end pt-1" style="user-select: auto;">
                <p class="text-sm mb-0 text-capitalize" style="user-select: auto;">Nodes</p>
                <h4 class="mb-0" style="user-select: auto;"><%=NodeHandler.getNodesByAdminId(Integer.valueOf(adId)).size()%></h4>
              </div>
            </div>
            
            <div class="card-footer p-3" style="user-select: auto;">
              
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4" style="user-select: auto;">
          <div class="card" style="user-select: auto;">
            <div class="card-header p-3 pt-2" style="user-select: auto;">
              <div class="icon icon-lg icon-shape bg-gradient-primary shadow-primary text-center border-radius-xl mt-n4 position-absolute" style="user-select: auto;background-image: linear-gradient(195deg, #e51515 0%, #9f0940 100%);">
                <i class="fas fa-skull-crossbones" style="user-select: auto;" aria-hidden="true"></i>
              </div>
              <div class="text-end pt-1" style="user-select: auto;">
                <p class="text-sm mb-0 text-capitalize" style="user-select: auto;">Issues</p>
                <h4 class="mb-0" style="user-select: auto;"><%= th.getTrapsByAdminId(adId).size()%></h4>
              </div>
            </div>
            
            <div class="card-footer p-3" style="user-select: auto;">
              
            </div>
          </div>
        </div>
        <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4" style="user-select: auto;">
          <div class="card" style="user-select: auto;">
            <div class="card-header p-3 pt-2" style="user-select: auto;">
              <div class="icon icon-lg icon-shape bg-gradient-success shadow-success text-center border-radius-xl mt-n4 position-absolute" style="user-select: auto;">
                <i class="material-icons opacity-10" style="user-select: auto;">person</i>
              </div>
                <div class="text-end pt-1" style="user-select: auto;" onclick=function(){
                      window.location.href = 'admins.jsp';
                }}>
                <p class="text-sm mb-0 text-capitalize" style="user-select: auto;">Admins</p>
                <h4 class="mb-0" style="user-select: auto;"><%=HandlingAdmin.getAdmins().size()%></h4>
              </div>
            </div>
            
            <div class="card-footer p-3" style="user-select: auto;">
              
            </div>
          </div>
        </div>
        
      </div>
         <h6 class="font-weight-bolder mb-0" style="user-select: auto;margin: 12px;">Your Nodes</h6>
      <div class="row mt-4" style="user-select: auto;">
       <% String color,comment="";
       
           for (Node node:nodes){
           if (node.getStatus().equalsIgnoreCase("red"))
           { color="#db2310 0%, #933012";
                  comment="click to view issues";}
           else
           {color="##66BB6A 0%, #43A047";
            comment="no issues exist";}
           
                
       %>
        <div class="col-lg-4 col-md-6 mt-4 mb-4" style="user-select: auto;">
          <div class="card z-index-2 " style="user-select: auto;">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent" style="user-select: auto;">
                
              <div class="bg-gradient-success shadow-success border-radius-lg py-3 pe-1" style="user-select: auto;background-image: linear-gradient(195deg,  <%=color%> 100%);">
               
               
                  <div class="chart" style="user-select: auto;">
                  <canvas id="chart-line" class="chart-canvas" height="170" style="user-select: auto;"></canvas>
                </div>
              </div>
            </div>
            <div class="card-body" style="user-select: auto;">
              <h6 class="mb-0 " style="user-select: auto;"><%=node.getName()%></h6>
              <p class="text-sm " style="user-select: auto;"><%=node.getNodeIp()%></p>
              <hr class="dark horizontal" style="user-select: auto;">
              <div class="d-flex " style="user-select: auto;">
                <i class="material-icons text-sm my-auto me-1" style="user-select: auto;"></i>
                
                <p class="mb-0 text-sm" style="user-select: auto;"><%=comment%> </p>
              </div>
            </div>
          </div>
        </div>
      <%}%>
      </div>
      
      <footer class="footer py-4  " style="user-select: auto;">
        <div class="container-fluid" style="user-select: auto;">
          <div class="row align-items-center justify-content-lg-between" style="user-select: auto;">
            <div class="col-lg-6 mb-lg-0 mb-4" style="user-select: auto;">
              <div class="copyright text-center text-sm text-muted text-lg-start" style="user-select: auto;">
                © <script style="user-select: auto;">
                  document.write(new Date().getFullYear())
                </script>20222022,
                made with <i class="fa fa-heart" aria-hidden="true" style="user-select: auto;"></i> by
                <a href="https://www.creative-tim.com" class="font-weight-bold" target="_blank" style="user-select: auto;">Creative Tim</a>
                for a better web.
              </div>
            </div>
            <div class="col-lg-6" style="user-select: auto;">
              <ul class="nav nav-footer justify-content-center justify-content-lg-end" style="user-select: auto;">
                <li class="nav-item" style="user-select: auto;">
                  <a href="https://www.creative-tim.com" class="nav-link text-muted" target="_blank" style="user-select: auto;">Creative Tim</a>
                </li>
                <li class="nav-item" style="user-select: auto;">
                  <a href="https://www.creative-tim.com/presentation" class="nav-link text-muted" target="_blank" style="user-select: auto;">About Us</a>
                </li>
                <li class="nav-item" style="user-select: auto;">
                  <a href="https://www.creative-tim.com/blog" class="nav-link text-muted" target="_blank" style="user-select: auto;">Blog</a>
                </li>
                <li class="nav-item" style="user-select: auto;">
                  <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-muted" target="_blank" style="user-select: auto;">License</a>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </footer>
    </div>
  </main>
  <div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
      <i class="material-icons py-2">settings</i>
    </a>
    <div class="card shadow-lg">
      <div class="card-header pb-0 pt-3">
        <div class="float-start">
          <h5 class="mt-3 mb-0">Material UI Configurator</h5>
          <p>See our dashboard options.</p>
        </div>
        <div class="float-end mt-4">
          <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
            <i class="material-icons">clear</i>
          </button>
        </div>
        <!-- End Toggle Button -->
      </div>
      <hr class="horizontal dark my-1">
      <div class="card-body pt-sm-3 pt-0">
        <!-- Sidebar Backgrounds -->
        <div>
          <h6 class="mb-0">Sidebar Colors</h6>
        </div>
        <a href="javascript:void(0)" class="switch-trigger background-color">
          <div class="badge-colors my-2 text-start">
            <span class="badge filter bg-gradient-primary active" data-color="primary" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-success" data-color="success" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-warning" data-color="warning" onclick="sidebarColor(this)"></span>
            <span class="badge filter bg-gradient-danger" data-color="danger" onclick="sidebarColor(this)"></span>
          </div>
        </a>
        <!-- Sidenav Type -->
        <div class="mt-3">
          <h6 class="mb-0">Sidenav Type</h6>
          <p class="text-sm">Choose between 2 different sidenav types.</p>
        </div>
        <div class="d-flex">
          <button class="btn bg-gradient-dark px-3 mb-2 active" data-class="bg-gradient-dark" onclick="sidebarType(this)">Dark</button>
          <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-transparent" onclick="sidebarType(this)">Transparent</button>
          <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-white" onclick="sidebarType(this)">White</button>
        </div>
        <p class="text-sm d-xl-none d-block mt-2">You can change the sidenav type just on desktop view.</p>
        <!-- Navbar Fixed -->
        <div class="mt-3 d-flex">
          <h6 class="mb-0">Navbar Fixed</h6>
          <div class="form-check form-switch ps-0 ms-auto my-auto">
            <input class="form-check-input mt-1 ms-auto" type="checkbox" id="navbarFixed" onclick="navbarFixed(this)">
          </div>
        </div>
        <hr class="horizontal dark my-3">
        <div class="mt-2 d-flex">
          <h6 class="mb-0">Light / Dark</h6>
          <div class="form-check form-switch ps-0 ms-auto my-auto">
            <input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version" onclick="darkMode(this)">
          </div>
        </div>
        <hr class="horizontal dark my-sm-4">
        <a class="btn bg-gradient-info w-100" href="https://www.creative-tim.com/product/material-dashboard-pro">Free Download</a>
        <a class="btn btn-outline-dark w-100" href="https://www.creative-tim.com/learning-lab/bootstrap/overview/material-dashboard">View documentation</a>
        <div class="w-100 text-center">
          <a class="github-button" href="https://github.com/creativetimofficial/material-dashboard" data-icon="octicon-star" data-size="large" data-show-count="true" aria-label="Star creativetimofficial/material-dashboard on GitHub">Star</a>
          <h6 class="mt-3">Thank you for sharing!</h6>
          <a href="https://twitter.com/intent/tweet?text=Check%20Material%20UI%20Dashboard%20made%20by%20%40CreativeTim%20%23webdesign%20%23dashboard%20%23bootstrap5&amp;url=https%3A%2F%2Fwww.creative-tim.com%2Fproduct%2Fsoft-ui-dashboard" class="btn btn-dark mb-0 me-2" target="_blank">
            <i class="fab fa-twitter me-1" aria-hidden="true"></i> Tweet
          </a>
          <a href="https://www.facebook.com/sharer/sharer.php?u=https://www.creative-tim.com/product/material-dashboard" class="btn btn-dark mb-0 me-2" target="_blank">
            <i class="fab fa-facebook-square me-1" aria-hidden="true"></i> Share
          </a>
        </div>
      </div>
    </div>
  </div>
  <!--   Core JS Files   -->
  <script src="assets/js/core/popper.min.js"></script>
  <script src="assets/js/core/bootstrap.min.js"></script>
  <script src="assets/js/plugins/perfect-scrollbar.min.js"></script>
  <script src="assets/js/plugins/smooth-scrollbar.min.js"></script>
  <script src="assets/js/plugins/chartjs.min.js"></script>
  <script>
    var ctx = document.getElementById("chart-bars").getContext("2d");

    new Chart(ctx, {
      type: "bar",
      data: {
        labels: ["M", "T", "W", "T", "F", "S", "S"],
        datasets: [{
          label: "Sales",
          tension: 0.4,
          borderWidth: 0,
          borderRadius: 4,
          borderSkipped: false,
          backgroundColor: "rgba(255, 255, 255, .8)",
          data: [50, 20, 10, 22, 50, 10, 40],
          maxBarThickness: 6
        }, ],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false,
          }
        },
        interaction: {
          intersect: false,
          mode: 'index',
        },
        scales: {
          y: {
            grid: {
              drawBorder: false,
              display: true,
              drawOnChartArea: true,
              drawTicks: false,
              borderDash: [5, 5],
              color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
              suggestedMin: 0,
              suggestedMax: 500,
              beginAtZero: true,
              padding: 10,
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
              color: "#fff"
            },
          },
          x: {
            grid: {
              drawBorder: false,
              display: true,
              drawOnChartArea: true,
              drawTicks: false,
              borderDash: [5, 5],
              color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
              display: true,
              color: '#f8f9fa',
              padding: 10,
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
            }
          },
        },
      },
    });


    var ctx2 = document.getElementById("chart-line").getContext("2d");

    new Chart(ctx2, {
      type: "line",
      data: {
        labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        datasets: [{
          label: "Mobile apps",
          tension: 0,
          borderWidth: 0,
          pointRadius: 5,
          pointBackgroundColor: "rgba(255, 255, 255, .8)",
          pointBorderColor: "transparent",
          borderColor: "rgba(255, 255, 255, .8)",
          borderColor: "rgba(255, 255, 255, .8)",
          borderWidth: 4,
          backgroundColor: "transparent",
          fill: true,
          data: [50, 40, 300, 320, 500, 350, 200, 230, 500],
          maxBarThickness: 6

        }],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false,
          }
        },
        interaction: {
          intersect: false,
          mode: 'index',
        },
        scales: {
          y: {
            grid: {
              drawBorder: false,
              display: true,
              drawOnChartArea: true,
              drawTicks: false,
              borderDash: [5, 5],
              color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
              display: true,
              color: '#f8f9fa',
              padding: 10,
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
            }
          },
          x: {
            grid: {
              drawBorder: false,
              display: false,
              drawOnChartArea: false,
              drawTicks: false,
              borderDash: [5, 5]
            },
            ticks: {
              display: true,
              color: '#f8f9fa',
              padding: 10,
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
            }
          },
        },
      },
    });

    var ctx3 = document.getElementById("chart-line-tasks").getContext("2d");

    new Chart(ctx3, {
      type: "line",
      data: {
        labels: ["Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
        datasets: [{
          label: "Mobile apps",
          tension: 0,
          borderWidth: 0,
          pointRadius: 5,
          pointBackgroundColor: "rgba(255, 255, 255, .8)",
          pointBorderColor: "transparent",
          borderColor: "rgba(255, 255, 255, .8)",
          borderWidth: 4,
          backgroundColor: "transparent",
          fill: true,
          data: [50, 40, 300, 220, 500, 250, 400, 230, 500],
          maxBarThickness: 6

        }],
      },
      options: {
        responsive: true,
        maintainAspectRatio: false,
        plugins: {
          legend: {
            display: false,
          }
        },
        interaction: {
          intersect: false,
          mode: 'index',
        },
        scales: {
          y: {
            grid: {
              drawBorder: false,
              display: true,
              drawOnChartArea: true,
              drawTicks: false,
              borderDash: [5, 5],
              color: 'rgba(255, 255, 255, .2)'
            },
            ticks: {
              display: true,
              padding: 10,
              color: '#f8f9fa',
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
            }
          },
          x: {
            grid: {
              drawBorder: false,
              display: false,
              drawOnChartArea: false,
              drawTicks: false,
              borderDash: [5, 5]
            },
            ticks: {
              display: true,
              color: '#f8f9fa',
              padding: 10,
              font: {
                size: 14,
                weight: 300,
                family: "Roboto",
                style: 'normal',
                lineHeight: 2
              },
            }
          },
        },
      },
    });
  </script>
  <script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
      var options = {
        damping: '0.5'
      }
      Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
  </script>
  <!-- Github buttons -->
  <script async defer src="https://buttons.github.io/buttons.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="assets/js/material-dashboard.min.js?v=3.0.4"></script>
</body>

</html>