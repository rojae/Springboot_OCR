package knu.ialab.sponsor.handler;

import org.json.JSONObject;
import org.springframework.security.core.Authentication;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setHeader("code", "200");
        JSONObject obj = new JSONObject();
        obj.put("code", "200");
        obj.put("msg", "Success Login");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(obj);
        out.flush();
    }
}
