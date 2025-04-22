package com.ssafy.live.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// TODO: 10-2. @Controller로 변경하고 /index, / 요청에 대한 handler 메서드를 추가하세요.
@WebServlet("/main")
public class MainController extends HttpServlet implements ControllerHelper {

    private static final long serialVersionUID = 1L;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = preProcessing(request, response);
        switch (action) {
        case "index" -> forward(request, response, "/index.jsp");
        default -> response.sendError(HttpServletResponse.SC_NOT_FOUND);
        }
    }

}
