package com.ssafy.shopApp.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.IProductService;
import com.ssafy.shopApp.model.service.ProductService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductController {
	
	private final IProductService productService;
	
	//product_list
	
	@GetMapping("/list")
	protected ModelAndView getProducts() throws Exception {

		ModelAndView mav = new ModelAndView();
		List<ProductDTO> products = productService.getAllProducts();
		mav.addObject("products", products);
		mav.setViewName("list");

		return mav;
	}
	
	@GetMapping("/list2")
	protected String getProducts2(Model model) throws Exception {

		List<ProductDTO> products = productService.getAllProducts();
		model.addAttribute("products", products);

		return "list";
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
