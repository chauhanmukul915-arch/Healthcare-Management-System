<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.healthcare.dao.HealthcareDAO.Patient" %><% String pageTitle="Patients"; %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><h3>Patient directory</h3><p class="muted">Register patients and keep contact details organised.</p></div><button class="btn primary" type="button" data-toggle="patient-form">＋ Add patient</button></div>
<section class="panel form-panel" id="patient-form"><div class="panel-heading"><div><h3>New patient</h3><p class="muted">Fields marked * are required.</p></div></div>
<form method="post" action="<%=ctx%>/patients" class="form-grid">
<label class="span-2">Full name *<input name="fullName" required maxlength="120" placeholder="Patient's full name"></label>
<label>Date of birth<input type="date" name="dob"></label><label>Gender<select name="gender"><option value="">Choose…</option><option>Female</option><option>Male</option><option>Non-binary</option><option>Prefer not to say</option></select></label>
<label>Phone<input name="phone" maxlength="30" placeholder="+91…"></label><label>Email<input type="email" name="email" maxlength="120" placeholder="name@example.com"></label>
<label class="span-2">Address<input name="address" maxlength="255"></label><label class="span-2">Medical notes<textarea name="notes" rows="2" placeholder="Optional notes — avoid unnecessary sensitive details"></textarea></label>
<div class="form-actions span-2"><button class="btn primary" type="submit">Save patient</button><button class="btn secondary" type="reset">Clear</button></div></form></section>
<section class="panel"><div class="panel-heading"><div><h3>All patients</h3><p class="muted"><%=((List<Patient>)request.getAttribute("patients")).size()%> records</p></div><input class="search-input" data-table-search="patient-table" placeholder="Search patients…"></div>
<div class="table-wrap"><table id="patient-table"><thead><tr><th>Patient</th><th>Phone</th><th>Email</th><th>Gender</th><th>Date of birth</th></tr></thead><tbody>
<% for(Patient p:(List<Patient>)request.getAttribute("patients")){ %><tr><td><div class="person-cell"><span class="table-avatar"><%=com.healthcare.util.Html.escape(p.name().isEmpty()?"?":p.name().substring(0,1).toUpperCase())%></span><b><%=com.healthcare.util.Html.escape(p.name())%></b><small>#<%=p.id()%></small></div></td><td><%=com.healthcare.util.Html.escape(p.phone())%></td><td><%=com.healthcare.util.Html.escape(p.email())%></td><td><%=com.healthcare.util.Html.escape(p.gender())%></td><td><%=com.healthcare.util.Html.escape(p.dob())%></td></tr><% } %>
<% if(((List<Patient>)request.getAttribute("patients")).isEmpty()){ %><tr><td colspan="5" class="empty-state">No patients yet. Use “Add patient” to create your first record.</td></tr><% } %>
</tbody></table></div></section>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
