package com.example.Bank_Management_System.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.Bank_Management_System.models.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class AppInterceptor  implements HandlerInterceptor {

    @Override
    public boolean preHandle(@SuppressWarnings("null")HttpServletRequest request, @SuppressWarnings("null") HttpServletResponse response,@SuppressWarnings("null") Object handler) throws Exception {
        System.out.println("In Pre Handle Interceptor Method");
        // CHECK REQUEST URI:
        if(request.getRequestURI().startsWith("/app")){
            // Get Session:
            HttpSession session = request.getSession();

            // Get Token Stored In Session:
            String token = (String) session.getAttribute("token");
            System.out.println(token);
            // Get User Object Stored In Session:
            User user = (User)session.getAttribute("user");
            // Check if Authenticated:
            // boolean isAuthenticated = (boolean) session.getAttribute("authenticated");

            // Validate Session Attributes / Objects:
            if(token == null || user == null){
                response.sendRedirect("/login");
                return false;
            }
            // End Of Validate Session Attributes / Objects.
        }
        // End Of Check Request URI.
        return true;
    }
    // End Of Pre Handle Method.


    @Override
    public void postHandle(@SuppressWarnings("null") HttpServletRequest request,@SuppressWarnings("null") HttpServletResponse response,@SuppressWarnings("null") Object handler,@SuppressWarnings("null") ModelAndView modelAndView) throws Exception {
        System.out.println("In Post Handle Interceptor Method");
    }


    @Override
    public void afterCompletion(@SuppressWarnings("null") HttpServletRequest request,@SuppressWarnings("null") HttpServletResponse response,@SuppressWarnings("null") Object handler,@SuppressWarnings("null") Exception ex) throws Exception {
        System.out.println("In After Completion Interceptor Method");
    }
}
// End Of Interceptor Class.
