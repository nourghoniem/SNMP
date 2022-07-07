<%-- 
    Document   : topbar
    Created on : Jul 6, 2022, 5:00:17 PM
    Author     : Salma
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
  <script src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
  <%String name=(String)session.getAttribute("name");
  
  %>
<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur" data-scroll="true" style="user-select: auto;">
      <div class="container-fluid py-1 px-3" style="user-select: auto;">
        <nav aria-label="breadcrumb" style="user-select: auto;">
          <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5" style="user-select: auto;visibility: hidden">
            <li class="breadcrumb-item text-sm" style="user-select: auto;"><a class="opacity-5 text-dark" href="javascript:;" style="user-select: auto;">Pages mnvgnvn</a></li>
            <li class="breadcrumb-item text-sm text-dark active" aria-current="page" style="user-select: auto;">Dashboard</li>
          </ol>
          <h6 class="font-weight-bolder mb-0" style="user-select: auto;">Hello <%=name%></h6>
        </nav>
        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar" style="user-select: auto;">
          <div class="ms-md-auto pe-md-3 d-flex align-items-center" style="user-select: auto;">
            
          </div>
          <ul class="navbar-nav  justify-content-end" style="user-select: auto;">
            <li class="nav-item d-flex align-items-center" style="user-select: auto;">
              <a href="pages/sign-in.html" class="nav-link text-body font-weight-bold px-0" style="user-select: auto;">
                <i class="fa fa-user me-sm-1" aria-hidden="true" style="user-select: auto;"></i>
                <span class="d-sm-inline d-none" style="user-select: auto;">Sign Out</span>
              </a>
            </li>
            <li class="nav-item d-xl-none ps-3 d-flex align-items-center" style="user-select: auto;">
              <a href="javascript:;" class="nav-link text-body p-0" id="iconNavbarSidenav" style="user-select: auto;">
                <div class="sidenav-toggler-inner" style="user-select: auto;">
                  <i class="sidenav-toggler-line" style="user-select: auto;"></i>
                  <i class="sidenav-toggler-line" style="user-select: auto;"></i>
                  <i class="sidenav-toggler-line" style="user-select: auto;"></i>
                </div>
              </a>
            </li>
            
            
          </ul>
        </div>
      </div>
    </nav>
