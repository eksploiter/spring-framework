package com.ssafy.shopApp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.StringTokenizer;

import com.ssafy.shopApp.controller.helper.ControllerMapping;
import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.ProductService;
import com.ssafy.shopApp.model.service.UserService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//  /main?action=product_delete
@WebServlet(urlPatterns = "/main",loadOnStartup = 1)
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ControllerMapping helper;
	
	@Override
	public void init() throws ServletException {
		ServletContext application = getServletContext();
		application.setAttribute("root", application.getContextPath());
		helper = ControllerMapping.getInstance();
		
		System.out.println("MainServlet init....");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);

	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		process(request, response);
	}
	private void process(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String domainAction = request.getParameter("action"); // product_list
		String domain="home",action="index";
		if(domainAction!=null) {
			StringTokenizer st = new StringTokenizer(domainAction,"_");
			if(st.countTokens()==2) {
				domain = st.nextToken();
				action = st.nextToken();
			}else {
				action = st.nextToken();
			}
		}

		Object result;
		try {
			// 요청 전처리
			
			// helper를 통해 해당요청을 처리할 서브 컨트롤러 얻기
			Controller subController = helper.getController(domain);
			result = subController.handleRequest(action, request, response);
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("errorMsg", e.getMessage());
			result = "forward:/common/error.jsp";
		}
		if(result instanceof String) {
			StringTokenizer st = new StringTokenizer((String)result, ":");
			boolean isForward = st.nextToken().equals("forward")?true:false;
			String url = st.nextToken();
			System.out.println("url : "+url);
			if(isForward) request.getRequestDispatcher(url).forward(request, response); 
			else  response.sendRedirect(request.getContextPath()+url);
		}
	}
}
