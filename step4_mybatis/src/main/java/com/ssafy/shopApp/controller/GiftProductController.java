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

import com.ssafy.shopApp.model.dto.GiftProductDTO;
import com.ssafy.shopApp.model.service.IGiftProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/gift")
@Controller
public class GiftProductController {

	private final IGiftProductService giftProductService;

	// 전체 목록
	@GetMapping("/list")
	protected String getGiftProducts(Model model) {
		model.addAttribute("giftProducts", giftProductService.getAllGiftProducts());
		return "giftProducts"; // JSP 이름
	}

	// 등록 폼
	@GetMapping("/register")
	public String registerForm() {
	    return "registerGiftProduct"; // → /WEB-INF/views/registerGiftProduct.jsp
	}

	// 등록 처리
	@PostMapping("/insert")
	protected String insertGiftProduct(@ModelAttribute GiftProductDTO giftProduct) {
		giftProductService.insertGiftProduct(giftProduct);
		return "redirect:/gift/list";
	}

	// 상세 보기
	@GetMapping("/detail/{giftId}")
	public String getDetailGiftProduct(@PathVariable int giftId, Model model) {
	    model.addAttribute("gift", giftProductService.getGiftProductById(giftId));
	    return "giftProductDetail"; // 뷰 이름
	}

	// 수정 처리
	@PostMapping("/modify")
	protected String modifyGiftProduct(@ModelAttribute GiftProductDTO giftProduct) {
	    giftProductService.updateGiftProduct(giftProduct);
	    return "redirect:/gift/list"; 
	}

	// 삭제 처리
	@PostMapping("/delete")
	protected String deleteGiftProduct(@RequestParam int giftId) {
	    giftProductService.deleteGiftProduct(giftId);
	    return "redirect:/gift/list";
	}

	// 공통 예외 처리
	@ExceptionHandler(Exception.class)
	public String error(Exception e, Model model) {
		log.error(e.getMessage(), e);
		model.addAttribute("errorMsg", e.getMessage());
		return "common/gift_error";
	}
	
}
