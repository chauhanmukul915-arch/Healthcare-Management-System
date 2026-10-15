package com.healthcare.util;

public final class Html {
    private Html() {}
    public static String escape(Object value) {
        if (value == null) return "";
        return value.toString().replace("&", "&amp;").replace("<", "&lt;")
            .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#x27;");
    }
}
