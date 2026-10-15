package com.healthcare.servlet;
import com.healthcare.dao.HealthcareDAO;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/appointments")
public class AppointmentsServlet extends HttpServlet {
 private final HealthcareDAO dao=new HealthcareDAO();
 @Override protected void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{req.setAttribute("appointments",dao.appointments());req.setAttribute("patients",dao.patients());req.setAttribute("doctors",dao.doctors());req.getRequestDispatcher("/appointments.jsp").forward(req,res);}
  catch(Exception e){throw new jakarta.servlet.ServletException(e);}
 }
 @Override protected void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,jakarta.servlet.ServletException{
  try{
   long pid=Long.parseLong(req.getParameter("patientId"));long did=Long.parseLong(req.getParameter("doctorId"));
   String date=req.getParameter("appointmentDate");
   if(date==null||date.isBlank())throw new IllegalArgumentException();
   dao.addAppointment(pid,did,date,req.getParameter("reason")==null?"":req.getParameter("reason").trim());
   res.sendRedirect(req.getContextPath()+"/appointments?success=Appointment+booked");
  }catch(Exception e){getServletContext().log("Could not book appointment",e);req.setAttribute("error","Could not book appointment. Add a patient and doctor first, and check the date.");doGet(req,res);}
 }
}
