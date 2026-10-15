<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.healthcare.dao.HealthcareDAO.Appointment,com.healthcare.dao.HealthcareDAO.Patient,com.healthcare.dao.HealthcareDAO.Doctor" %><% String pageTitle="Appointments"; %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><h3>Appointment schedule</h3><p class="muted">Book and review patient consultations.</p></div><button class="btn primary" type="button" data-toggle="appointment-form">＋ Book appointment</button></div>
<section class="panel form-panel" id="appointment-form"><div class="panel-heading"><div><h3>New appointment</h3><p class="muted">Choose a patient, doctor and time.</p></div></div>
<% List<Patient> patients=(List<Patient>)request.getAttribute("patients"); List<Doctor> doctors=(List<Doctor>)request.getAttribute("doctors"); %>
<% if(patients.isEmpty()||doctors.isEmpty()){ %><div class="alert error">Add at least one patient and one doctor before booking an appointment.</div><% } %>
<form method="post" action="<%=ctx%>/appointments" class="form-grid">
<label>Patient *<select name="patientId" required><option value="">Choose patient…</option><% for(Patient p:patients){ %><option value="<%=p.id()%>"><%=com.healthcare.util.Html.escape(p.name())%> (#<%=p.id()%>)</option><% } %></select></label>
<label>Doctor *<select name="doctorId" required><option value="">Choose doctor…</option><% for(Doctor d:doctors){ %><option value="<%=d.id()%>"><%=com.healthcare.util.Html.escape(d.name())%> — <%=com.healthcare.util.Html.escape(d.specialization())%></option><% } %></select></label>
<label>Appointment date & time *<input type="datetime-local" name="appointmentDate" required></label><label>Reason<input name="reason" maxlength="255" placeholder="Consultation reason"></label>
<div class="form-actions span-2"><button class="btn primary" type="submit" <%=patients.isEmpty()||doctors.isEmpty()?"disabled":""%>>Book appointment</button></div></form></section>
<section class="panel"><div class="panel-heading"><div><h3>All appointments</h3><p class="muted"><%=((List<Appointment>)request.getAttribute("appointments")).size()%> records</p></div><input class="search-input" data-table-search="appointment-table" placeholder="Search appointments…"></div>
<div class="table-wrap"><table id="appointment-table"><thead><tr><th>Patient</th><th>Doctor</th><th>Date & time</th><th>Reason</th><th>Status</th></tr></thead><tbody>
<% for(Appointment a:(List<Appointment>)request.getAttribute("appointments")){ %><tr><td><b><%=com.healthcare.util.Html.escape(a.patient())%></b></td><td><%=com.healthcare.util.Html.escape(a.doctor())%></td><td><%=com.healthcare.util.Html.escape(a.date())%></td><td><%=com.healthcare.util.Html.escape(a.reason())%></td><td><span class="status-badge"><%=com.healthcare.util.Html.escape(a.status())%></span></td></tr><% } %>
<% if(((List<Appointment>)request.getAttribute("appointments")).isEmpty()){ %><tr><td colspan="5" class="empty-state">No appointments have been booked.</td></tr><% } %>
</tbody></table></div></section>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
