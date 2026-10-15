<%@ page contentType="text/html;charset=UTF-8" %><%@ page import="java.util.List,com.healthcare.dao.HealthcareDAO.Bill,com.healthcare.dao.HealthcareDAO.Patient" %><% String pageTitle="Billing"; %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<div class="page-intro"><div><h3>Billing & payments</h3><p class="muted">Create simple patient billing records and track payment status.</p></div><button class="btn primary" type="button" data-toggle="bill-form">＋ Create bill</button></div>
<section class="panel form-panel" id="bill-form"><div class="panel-heading"><div><h3>New bill</h3><p class="muted">This records a charge only; no payment gateway is connected.</p></div></div>
<% List<Patient> patients=(List<Patient>)request.getAttribute("patients"); %>
<% if(patients.isEmpty()){ %><div class="alert error">Add a patient before creating a bill.</div><% } %>
<form method="post" action="<%=ctx%>/billing" class="form-grid">
<label>Patient *<select name="patientId" required><option value="">Choose patient…</option><% for(Patient p:patients){ %><option value="<%=p.id()%>"><%=com.healthcare.util.Html.escape(p.name())%> (#<%=p.id()%>)</option><% } %></select></label>
<label>Amount (₹) *<input type="number" name="amount" required min="0" step="0.01" placeholder="0.00"></label><label class="span-2">Description *<input name="description" required maxlength="255" placeholder="Consultation fee, lab test…"></label>
<div class="form-actions span-2"><button class="btn primary" type="submit" <%=patients.isEmpty()?"disabled":""%>>Save bill</button></div></form></section>
<section class="panel"><div class="panel-heading"><div><h3>Billing records</h3><p class="muted"><%=((List<Bill>)request.getAttribute("bills")).size()%> records</p></div><input class="search-input" data-table-search="bill-table" placeholder="Search billing…"></div>
<div class="table-wrap"><table id="bill-table"><thead><tr><th>Bill</th><th>Patient</th><th>Description</th><th>Amount</th><th>Payment</th><th>Created</th></tr></thead><tbody>
<% for(Bill b:(List<Bill>)request.getAttribute("bills")){ %><tr><td>#<%=b.id()%></td><td><b><%=com.healthcare.util.Html.escape(b.patient())%></b></td><td><%=com.healthcare.util.Html.escape(b.description())%></td><td>₹<%=String.format(java.util.Locale.ROOT,"%.2f",b.amount())%></td><td><span class="status-badge"><%=com.healthcare.util.Html.escape(b.status())%></span></td><td><%=com.healthcare.util.Html.escape(b.createdAt())%></td></tr><% } %>
<% if(((List<Bill>)request.getAttribute("bills")).isEmpty()){ %><tr><td colspan="6" class="empty-state">No bills yet.</td></tr><% } %>
</tbody></table></div></section>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
