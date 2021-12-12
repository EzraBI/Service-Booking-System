package net.salon.booking.Utilities;

import javax.servlet.http.HttpServletRequest;

public class UtilityPassword {
    public static String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}
