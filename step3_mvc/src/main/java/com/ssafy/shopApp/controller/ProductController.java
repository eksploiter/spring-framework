package com.ssafy.shopApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.IProductService;

import lombok.RequiredArgsConstructor;

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

	@DeleteMapping("/delete")
	protected String deleteProduct(@RequestParam int productId) throws Exception {
		productService.deleteProduct(productId);
		return "redirect:/"; // controller 메서드 호출
	}

	@PostMapping("/insert")
	protected String insertProduct(@RequestParam ProductDTO productDto) throws Exception {
		productService.insertProduct(productDto);
		return "redirect:/product/list";
	}

	@GetMapping("/detail/{productId}") // 각각의 목록의 디테일을 보기 위함
	protected String getDetailProduct(@PathVariable int productId, Model model) throws Exception {
		model.addAttribute("product", productService.getProductById(productId));
		return "detail";
	}

	@PostMapping("/modify")
	protected String modifyProduct(@RequestParam ProductDTO productDto) throws Exception {
		productService.updateProduct(productDto);
		return "redirect:/product/detail/" + productDto.getProductId();
	}

	@GetMapping("/register")
	protected String registerForm() throws Exception {
		return "register";
	}

}
