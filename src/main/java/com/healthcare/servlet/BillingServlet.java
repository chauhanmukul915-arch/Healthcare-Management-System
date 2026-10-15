package com.healthcare.servlet;
import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/billing")
public class BillingServlet extends HttpServlet {
 private final HealthcareDAO dao=new HealthcareDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{req.setAttribute("bills",dao.bills());req.setAttribute("patients",dao.patients());req.getRequestDispatcher("/billing.jsp").forward(req,res);}
  catch(Exception e){throw new jakarta.servlet.ServletException(e);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{
   long pid=Long.parseLong(req.getParameter("patientId"));double amount=Double.parseDouble(req.getParameter("amount"));
   String desc=req.getParameter("description")==null?"":req.getParameter("description").trim();
   if(amount<0||desc.isBlank())throw new IllegalArgumentException();
   dao.addBill(pid,amount,desc);res.sendRedirect(req.getContextPath()+"/billing?success=Bill+created");
  }catch(Exception e){getServletContext().log("Could not create bill",e);req.setAttribute("error","Could not create bill. Check patient, amount and description.");doGet(req,res);}
 }
}
