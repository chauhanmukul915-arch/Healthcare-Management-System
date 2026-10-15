<%@ page contentType="text/html;charset=UTF-8" %><% String pageTitle="Overview"; %><%@ include file="/WEB-INF/jspf/header.jspf" %>
<section class="welcome-panel"><div><p class="eyebrow light">YOUR HEALTHCARE WORKSPACE</p><h1>Good to see you, <%=com.healthcare.util.Html.escape(session.getAttribute("user"))%>.</h1><p>Here's what's happening across your clinic today.</p><a class="btn light-btn" href="<%=request.getContextPath()%>/appointments">Manage appointments <span>→</span></a></div><div class="welcome-icon">✚</div></section>
<div class="section-heading"><div><h3>Clinic overview</h3><p class="muted">A quick snapshot of your records.</p></div><span class="pill">Live totals</span></div>
<div class="stats-grid">
<div class="stat-card"><div class="stat-top"><span class="stat-icon blue">♙</span><span class="stat-label">PATIENTS</span></div><strong><%=request.getAttribute("patientCount")%></strong><small>Total registered patients</small></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon green">✚</span><span class="stat-label">DOCTORS</span></div><strong><%=request.getAttribute("doctorCount")%></strong><small>Available doctor records</small></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon violet">▣</span><span class="stat-label">APPOINTMENTS</span></div><strong><%=request.getAttribute("appointmentCount")%></strong><small>Appointments in the system</small></div>
<div class="stat-card"><div class="stat-top"><span class="stat-icon amber">＄</span><span class="stat-label">BILLS</span></div><strong><%=request.getAttribute("billCount")%></strong><small>Billing records created</small></div>
</div>
<div class="section-heading"><div><h3>Quick actions</h3><p class="muted">Jump straight into your workflow.</p></div></div>
<div class="quick-grid"><a class="quick-card" href="<%=request.getContextPath()%>/patients"><span class="quick-icon blue">＋</span><b>Register a patient</b><small>Add a new patient record</small><span class="quick-arrow">↗</span></a>
<a class="quick-card" href="<%=request.getContextPath()%>/doctors"><span class="quick-icon green">✚</span><b>Add a doctor</b><small>Maintain doctor directory</small><span class="quick-arrow">↗</span></a>
<a class="quick-card" href="<%=request.getContextPath()%>/appointments"><span class="quick-icon violet">▣</span><b>Book appointment</b><small>Schedule a consultation</small><span class="quick-arrow">↗</span></a>
<a class="quick-card" href="<%=request.getContextPath()%>/billing"><span class="quick-icon amber">＄</span><b>Create a bill</b><small>Record a patient charge</small><span class="quick-arrow">↗</span></a></div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
