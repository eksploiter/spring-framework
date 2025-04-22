package com.ssafy.shopApp.controller;

import java.util.List;

import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.IProductService;
import com.ssafy.shopApp.model.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ProductController implements Controller {
	
	private IProductService productService = new ProductService();
	
	public Object handleRequest(String action, HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		return switch (action) {
			case "delete" -> deleteProduct(request,response);
			case "insert" -> insertProduct(request,response); 
			case "list" -> getProducts(request,response);
			case "detail" -> getDetailProduct(request,response);
			case "modify" -> modifyProduct(request,response);
			case "insertForm" -> "forward:/register.jsp";												
			default -> throw new IllegalArgumentException("처리할 컨트롤러 로직이 존재하지 않습니다.");
		};
	}
	
	protected String deleteProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int productId = Integer.parseInt(request.getParameter("productId"));
		productService.deleteProduct(productId);

		return "redirect:/main?action=product_list";
	}

	protected String insertProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1. get parameter
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));

		// 2. call model
		productService.insertProduct(new ProductDTO(name, category, brand, price, stockQuantity));
		// view
		return "redirect:/main?action=product_list";
	}

	protected String getProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List<ProductDTO> products = productService.getAllProducts();
		request.setAttribute("products", products);

		return "forward:/list.jsp";
	}

	protected String getDetailProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		int productId = Integer.parseInt(request.getParameter("productId"));
		request.setAttribute("product", productService.getProductById(productId));
		return "forward:/detail.jsp";
	}

	protected String modifyProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 1. get parameter
		int productId = Integer.parseInt(request.getParameter("productId"));
		String name = request.getParameter("name");
		String brand = request.getParameter("brand");
		String category = request.getParameter("category");
		int price = Integer.parseInt(request.getParameter("price"));
		int stockQuantity = Integer.parseInt(request.getParameter("stockQuantity"));

		// 2. call model
		productService.updateProduct(new ProductDTO(productId, name, category, brand, price, stockQuantity));
		// view
		return "redirect:/main?action=product_detail&productId=" + productId;
	}
}
