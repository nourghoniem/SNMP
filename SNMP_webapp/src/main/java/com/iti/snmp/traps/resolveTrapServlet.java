/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.iti.snmp.traps;

import com.iti.snmp.resolved.Resolved;
import com.iti.snmp.resolved.ResolvedHandler;
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
public class resolveTrapServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        Integer id = Integer.parseInt(request.getParameter("id"));
        TrapHandler handler = new TrapHandler();
        handler.updateStatus(id);
        Resolved resolved = new Resolved(id);
        ResolvedHandler resolvedHandler = new ResolvedHandler();
        resolvedHandler.addResolved(resolved);
        
    }
}
