package knu.ialab.sponsor.handler;

import org.json.JSONObject;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginFailHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setHeader("code", "999");
        JSONObject obj = new JSONObject();
        obj.put("code", "999");
        obj.put("msg", "Fail login");
        PrintWriter out = httpServletResponse.getWriter();
        out.println(obj);
        out.flush();
    }
}
