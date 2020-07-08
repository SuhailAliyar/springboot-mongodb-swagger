package com.suhail.inventorymanagement.controller;

import com.suhail.inventorymanagement.model.Product;
import com.suhail.inventorymanagement.service.ProductService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productservice;

    @PostMapping
    @ApiOperation(
            value = "Add a new product"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 201,
                    message = "Product Created",
                    response = Product.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Unsuccessful Operation"
            )
    })
    public ResponseEntity addProduct(@Valid @RequestBody Product product) {
        return productservice.addProducts(product);
    }

    @GetMapping
    @ApiOperation(
            value = "Get list of products"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successful Operation",
                    responseContainer = "List",
                    response = Product.class
            ),
            @ApiResponse(
                    code = 500,
                    message = "Unsuccessful Operation"
            )
    })
    public ResponseEntity getAllProducts() {
        return productservice.getAllProducts();

    }

    @GetMapping("/{id}")
    @ApiOperation(
            value = "Get product with id"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 200,
                    message = "Successful Operation",
                    response = Product.class
            ),
            @ApiResponse(
                    code = 403,
                    message = "Given product is invalid"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Unsuccessful Operation"
            )
    })
    public ResponseEntity getProduct(@PathVariable(value = "id") String id) {
        return productservice.getProduct(id.trim());
    }

    @PutMapping("/{id}")
    @ApiOperation(
            value = "Update a product"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Successful Operation"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Product id does not exists"
            ),
            @ApiResponse(
                    code = 500,
                    message = "Unsuccessful Operation"
            )
    })
    public ResponseEntity updateProduct(@PathVariable(value = "id") String id, @RequestBody Product productUpdate) {
        return productservice.updateProduct(id.trim(), productUpdate);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(
            value = "Delete a product"
    )
    @ApiResponses({
            @ApiResponse(
                    code = 204,
                    message = "Successful Operation"
            ),
            @ApiResponse(
                    code = 404,
                    message = "Product id does not exists"
            )
    })
    public ResponseEntity deleteProduct(@PathVariable(value = "id") String id) {
        return productservice.deleteProduct(id.trim());
    }

}
