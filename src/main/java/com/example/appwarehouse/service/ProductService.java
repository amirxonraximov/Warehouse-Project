package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Attachment;
import com.example.appwarehouse.entity.Category;
import com.example.appwarehouse.entity.Measurement;
import com.example.appwarehouse.entity.Product;
import com.example.appwarehouse.payload.ProductDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.AttachmentRepository;
import com.example.appwarehouse.repository.CategoryRepository;
import com.example.appwarehouse.repository.MeasurementRepository;
import com.example.appwarehouse.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    AttachmentRepository attachmentRepository;

    @Autowired
    MeasurementRepository measurementRepository;

    public Result addProduct(ProductDto productDto) {

        boolean existsByNameAndCategoryId = productRepository.existsByNameAndCategoryId(productDto.getName(), productDto.getCategoryId());
        if (existsByNameAndCategoryId)
            return Result.fail("This product is available in this category");

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty())
            return Result.fail("Catagory not found");

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty())
            return Result.fail("Photo not found");

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty())
            return Result.fail("Measurement not found");

        Product product = new Product();
        product.setName(productDto.getName());

        Optional<Integer> highestCode = productRepository.getProductWithHighestCode();

        if (highestCode.isEmpty()) {
            product.setCode(1);
        } else {
            product.setCode(highestCode.get() + 1);
        }

        product.setCategory(optionalCategory.get());
        product.setPhoto(optionalAttachment.get());
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);
        return Result.success("Product added");
    }

    public Result getProducts() {
        List<Product> productList = productRepository.getProductByActive(true);
        return Result.success("Success", productList);
    }

    public Result updateProduct(Integer id, @RequestBody ProductDto productDto) {

        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return Result.fail("Product not found");
        }
        Product product = optionalProduct.get();
        product.setName(productDto.getName());

        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategoryId());
        if (optionalCategory.isEmpty()){
            return Result.fail("Category not found");
        }
        product.setCategory(optionalCategory.get());

        Optional<Attachment> optionalAttachment = attachmentRepository.findById(productDto.getPhotoId());
        if (optionalAttachment.isEmpty()) {
            return Result.fail("Photo not found");
        }
        product.setPhoto(optionalAttachment.get());
        product.setCode(optionalProduct.get().getCode());

        Optional<Measurement> optionalMeasurement = measurementRepository.findById(productDto.getMeasurementId());
        if (optionalMeasurement.isEmpty()) {
            return Result.fail("Measurement not found");
        }
        product.setMeasurement(optionalMeasurement.get());
        productRepository.save(product);

        return Result.success("Product edited", product);
    }

    public Result deleteProduct(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            return Result.fail("Product not found");
        }
        productRepository.deleteById(id);
        return Result.success("Product deleted");
    }
}
