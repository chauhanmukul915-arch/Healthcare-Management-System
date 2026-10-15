package com.healthcare.servlet;
import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/patients")
public class PatientsServlet extends HttpServlet {
 private final HealthcareDAO dao=new HealthcareDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{req.setAttribute("patients",dao.patients());req.getRequestDispatcher("/patients.jsp").forward(req,res);}
  catch(Exception e){throw new jakarta.servlet.ServletException(e);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  String name=trim(req.getParameter("fullName"));
  if(name.isBlank()){req.setAttribute("error","Patient name is required.");doGet(req,res);return;}
  try{dao.addPatient(name,trim(req.getParameter("dob")),trim(req.getParameter("gender")),trim(req.getParameter("phone")),
   trim(req.getParameter("email")),trim(req.getParameter("address")),trim(req.getParameter("notes")));
   res.sendRedirect(req.getContextPath()+"/patients?success=Patient+added");
  }catch(Exception e){getServletContext().log("Could not add patient",e);req.setAttribute("error","Could not save patient. Check the details.");doGet(req,res);}
 }
 private String trim(String s){return s==null?"":s.trim();}
}
