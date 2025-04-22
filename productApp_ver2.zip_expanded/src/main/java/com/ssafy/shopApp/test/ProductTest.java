package com.ssafy.shopApp.test;

import com.ssafy.shopApp.model.dto.ProductDTO;
import com.ssafy.shopApp.model.service.IProductService;
import com.ssafy.shopApp.model.service.ProductService;

class ProductTest {
    public static void main(String[] args){
        try {
            IProductService productService = new ProductService();

            // Insert Product

            ProductDTO newProduct = new ProductDTO();
            newProduct.setName("로봇 청소기");
            newProduct.setCategory("가전");
            newProduct.setBrand("싸피클린");
            newProduct.setPrice(299);
            newProduct.setStockQuantity(10);
            productService.insertProduct(newProduct);
            System.out.println("상품 등록 : 성공 - "+newProduct.getProductId());

            {
            	System.out.println("=====상품 목록=====");
            	for (ProductDTO p: productService.getAllProducts()) {
					System.out.println(p);
				}

            }
           
            // Get Product by ID
            ProductDTO fetchedProduct = productService.getProductById(1);
            System.out.println("상품 조회 : " + fetchedProduct);

            // Update Product
            fetchedProduct.setPrice(1100);
            productService.updateProduct(fetchedProduct);
            System.out.println("상품 가격 수정후 조회 : " + productService.getProductById(1));

            // Delete Product
            productService.deleteProduct(newProduct.getProductId());
            System.out.println("상품 삭제 : 성공");
            {
            	System.out.println("=====상품 목록=====");
            	for (ProductDTO p: productService.getAllProducts()) {
					System.out.println(p);
				}

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
