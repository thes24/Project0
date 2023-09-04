package com.example.Project0.interceptor;

import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.example.Project0.entity.MemberEntity;
import com.example.Project0.services.MemberService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class MemberInterceptor implements HandlerInterceptor {

    @Autowired
    private MemberService memberService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (request.getMethod().equals("OPTIONS")) return true;

        HttpSession session = request.getSession();
        System.out.println("Attribute Check " + session.getAttribute("memberId"));
        if (session.getAttribute("memberId") instanceof Long) {
            System.out.println("Long");
        } else {
            response.sendError(401, "Bad 401");
            return false;
        }
        Long id = (Long) session.getAttribute("memberId");
        System.out.println("TypeCasting Check " + id);
        MemberEntity memberEntity = memberService.getMemberbyId(id);
        if (memberEntity == null) {
            response.sendError(401, "Bad 401");
            return false;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();

        System.out.println("Bean: " + handlerMethod.getBean());
        System.out.println("Method: " + method);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            @Nullable Exception ex) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
