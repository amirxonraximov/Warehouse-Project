package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.ProductDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping
    public Result addProduct(@RequestBody ProductDto productDto) {

        Result result = productService.addProduct(productDto);
        return result;
    }

    @GetMapping
    public Result getProducts() {

        Result result = productService.getProducts();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateProduct(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        Result result = productService.updateProduct(id, productDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteProduct(@PathVariable Integer id) {
        Result result = productService.deleteProduct(id);
        return result;
    }
}
