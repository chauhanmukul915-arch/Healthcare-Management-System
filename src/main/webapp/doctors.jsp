<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.healthcare.dao.HealthcareDAO.Doctor" %><% String pageTitle="Doctors"; %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><h3>Doctor directory</h3><p class="muted">Manage your care team and specialisations.</p></div><button class="btn primary" type="button" data-toggle="doctor-form">＋ Add doctor</button></div>
<section class="panel form-panel" id="doctor-form"><div class="panel-heading"><div><h3>New doctor</h3><p class="muted">Add a clinician to the directory.</p></div></div>
<form method="post" action="<%=ctx%>/doctors" class="form-grid">
<label class="span-2">Full name *<input name="fullName" required maxlength="120"></label><label>Specialization *<input name="specialization" required maxlength="100" placeholder="e.g. Cardiology"></label>
<label>Phone<input name="phone" maxlength="30"></label><label>Email<input name="email" type="email" maxlength="120"></label><label>Availability<input name="availability" maxlength="160" placeholder="e.g. Mon–Fri, 10am–4pm"></label>
<div class="form-actions span-2"><button class="btn primary" type="submit">Save doctor</button><button class="btn secondary" type="reset">Clear</button></div></form></section>
<section class="panel"><div class="panel-heading"><div><h3>Care team</h3><p class="muted"><%=((List<Doctor>)request.getAttribute("doctors")).size()%> doctor records</p></div><input class="search-input" data-table-search="doctor-table" placeholder="Search doctors…"></div>
<div class="table-wrap"><table id="doctor-table"><thead><tr><th>Doctor</th><th>Specialization</th><th>Phone</th><th>Email</th><th>Availability</th></tr></thead><tbody>
<% for(Doctor d:(List<Doctor>)request.getAttribute("doctors")){ %><tr><td><div class="person-cell"><span class="table-avatar green-avatar">✚</span><b><%=com.healthcare.util.Html.escape(d.name())%></b></div></td><td><span class="pill"><%=com.healthcare.util.Html.escape(d.specialization())%></span></td><td><%=com.healthcare.util.Html.escape(d.phone())%></td><td><%=com.healthcare.util.Html.escape(d.email())%></td><td><%=com.healthcare.util.Html.escape(d.availability())%></td></tr><% } %>
<% if(((List<Doctor>)request.getAttribute("doctors")).isEmpty()){ %><tr><td colspan="5" class="empty-state">No doctors yet. Add your first doctor above.</td></tr><% } %>
</tbody></table></div></section>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
