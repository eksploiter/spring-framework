package com.ssafy.shopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.IProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/product")
@RequiredArgsConstructor
@Controller
public class ProductController {

	private final IProductService productService;

	@GetMapping("/list")
	protected String getProducts(Model model) throws Exception {
		model.addAttribute("products", productService.getAllProducts());
		return "list"; // jsp 호출
	}

	@PostMapping("/delete")
	protected String deleteProduct(@RequestParam int productId) throws Exception {
		productService.deleteProduct(productId);
		return "redirect:/product/list"; // controller 메서드 호출
	}

	@PostMapping("/insert")
	protected String insertProduct(@ModelAttribute ProductDTO productDto) throws Exception {
		productService.insertProduct(productDto);
		return "redirect:/product/list";
	}

	@GetMapping("/detail/{productId}") // 각각의 목록의 디테일을 보기 위함 list.jsp
	protected String getDetailProduct(@PathVariable int productId, Model model) throws Exception {
		// @RequestParam int productId, Model model
		// Model model -> model.addAttribute
		model.addAttribute("product", productService.getProductById(productId));
		return "detail";
	}

	@PostMapping("/modify") // detail.jsp
	protected String modifyProduct(@ModelAttribute ProductDTO productDto) throws Exception {
		// @ModelAttribute ProductDTO productDto
		productService.updateProduct(productDto);
		return "redirect:/product/list";
		// return "redirect:/product/detail?productId" + productDto.getProductId();
	}

	@GetMapping("/register")
	protected String registerForm() throws Exception {
		return "register";
	}
	
	@ExceptionHandler(Exception.class) // 마치 catch 처럼
	public String error(Exception e, Model model) {
		log.error(e.getMessage(), e);
		model.addAttribute("errorMsg", e.getMessage());
		return "common/product_error";
	}

}
