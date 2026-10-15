package com.healthcare.servlet;
import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
 private final HealthcareDAO dao=new HealthcareDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{
   req.setAttribute("patientCount",dao.count("patients"));
   req.setAttribute("doctorCount",dao.count("doctors"));
   req.setAttribute("appointmentCount",dao.count("appointments"));
   req.setAttribute("billCount",dao.count("bills"));
   req.getRequestDispatcher("/dashboard.jsp").forward(req,res);
  }catch(Exception e){throw new jakarta.servlet.ServletException(e);}
 }
}
