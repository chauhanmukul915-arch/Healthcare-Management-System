package com.healthcare.servlet;
import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/doctors")
public class DoctorsServlet extends HttpServlet {
 private final HealthcareDAO dao=new HealthcareDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{req.setAttribute("doctors",dao.doctors());req.getRequestDispatcher("/doctors.jsp").forward(req,res);}
  catch(Exception e){throw new jakarta.servlet.ServletException(e);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  String name=v(req,"fullName"),spec=v(req,"specialization");
  if(name.isBlank()||spec.isBlank()){req.setAttribute("error","Doctor name and specialization are required.");doGet(req,res);return;}
  try{dao.addDoctor(name,spec,v(req,"phone"),v(req,"email"),v(req,"availability"));res.sendRedirect(req.getContextPath()+"/doctors?success=Doctor+added");}
  catch(Exception e){getServletContext().log("Could not add doctor",e);req.setAttribute("error","Could not save doctor.");doGet(req,res);}
 }
 private String v(HttpServletRequest r,String k){String s=r.getParameter(k);return s==null?"":s.trim();}
}
