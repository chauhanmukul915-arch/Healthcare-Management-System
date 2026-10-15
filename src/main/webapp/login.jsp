<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html><html lang="en"><head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
<title>Sign in · CarePoint</title><link rel="stylesheet" href="<%=request.getContextPath()%>/assets/css/style.css"></head>
<body class="login-page"><main class="login-card">
<div class="brand-mark">✚</div><p class="eyebrow">CAREPOINT HEALTH</p><h1>Welcome back</h1><p class="muted">Sign in to manage your healthcare workspace.</p>
<% if(request.getAttribute("error")!=null){ %><div class="alert error"><%=com.healthcare.util.Html.escape(request.getAttribute("error"))%></div><% } %>
<form method="post" action="<%=request.getContextPath()%>/login" class="form-stack">
<label>Username<input name="username" autocomplete="username" required maxlength="60"></label>
<label>Password<input type="password" name="password" autocomplete="current-password" required></label>
<button class="btn primary full" type="submit">Sign in <span>→</span></button>
</form><div class="login-note">Local starter login: <b>admin</b> / <b>ChangeMe123!</b><br>Change this password before using the app beyond local testing.</div>
</main><script src="<%=request.getContextPath()%>/assets/js/app.js"></script></body></html>
