package com.healthcare.servlet;

import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private final HealthcareDAO dao=new HealthcareDAO();

    @Override protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, jakarta.servlet.ServletException {
        req.getRequestDispatcher("/login.jsp").forward(req,res);
    }

    @Override protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, jakarta.servlet.ServletException {
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        try {
            if(!dao.hasUsers()) dao.createUser("admin",BCrypt.hashpw("ChangeMe123!",BCrypt.gensalt()),"Administrator","ADMIN");
            var user=dao.findUser(username==null?"":username.trim());
            if(user!=null && BCrypt.checkpw(password==null?"":password,user.get("password_hash"))) {
                HttpSession session=req.getSession(true);
                session.setAttribute("user",user.get("display_name"));
                session.setAttribute("role",user.get("role"));
                session.setAttribute("username",user.get("username"));
                session.setMaxInactiveInterval(30*60);
                res.sendRedirect(req.getContextPath()+"/dashboard");
            } else {
                req.setAttribute("error","Incorrect username or password.");
                req.getRequestDispatcher("/login.jsp").forward(req,res);
            }
        } catch(Exception e) {
            getServletContext().log("Login failed",e);
            req.setAttribute("error","Unable to sign in. Check database configuration and schema.");
            req.getRequestDispatcher("/login.jsp").forward(req,res);
        }
    }
}
