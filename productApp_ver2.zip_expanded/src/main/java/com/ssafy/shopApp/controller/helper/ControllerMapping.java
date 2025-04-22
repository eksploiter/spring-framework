package com.ssafy.shopApp.controller.helper;

import com.ssafy.shopApp.controller.Controller;
import com.ssafy.shopApp.controller.HomeController;
import com.ssafy.shopApp.controller.ProductController;
import com.ssafy.shopApp.controller.UserController;

public class ControllerMapping {

	private static ControllerMapping instance = new ControllerMapping();
	private ProductController productController = new ProductController();
	private UserController userController = new UserController();
	private HomeController homeController = new HomeController();
	
	private ControllerMapping() {}
	
	public Controller getController(String domain) {
		return switch (domain) {
		case "user" -> userController; 
		case "product" -> productController;
		case "home" -> homeController;		
		default -> throw new IllegalArgumentException("도메인에 해당하는 컨트롤러가 없습니다.");
		};
	}

	public static ControllerMapping getInstance() {
		return instance;
	}
}







