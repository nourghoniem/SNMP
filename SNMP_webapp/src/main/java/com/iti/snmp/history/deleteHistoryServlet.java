/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.snmp.history;

import com.iti.snmp.traps.TrapHandler;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nour
 */
public class deleteHistoryServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        String trap_type = request.getParameter("type");
        TrapHandler trapHandler = new TrapHandler();
        int trap_id = trapHandler.getTrapid(trap_type);
        HistoryHandler historyHandler = new HistoryHandler();
        historyHandler.removeFromHistory(id, trap_id);
        
    }
}
