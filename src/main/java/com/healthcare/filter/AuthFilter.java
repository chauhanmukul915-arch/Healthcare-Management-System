package com.healthcare.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebFilter(urlPatterns = {"/dashboard", "/patients", "/doctors", "/appointments", "/billing"})
public class AuthFilter implements Filter {
    @Override public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)request;
        HttpServletResponse res=(HttpServletResponse)response;
        if(req.getSession(false)==null || req.getSession(false).getAttribute("user")==null) {
            res.sendRedirect(req.getContextPath()+"/login"); return;
        }
        chain.doFilter(request,response);
    }
}
