package cohorte16.homeservice.controllers;

import cohorte16.homeservice.dtos.ProductCreatedDTO;
import cohorte16.homeservice.dtos.ProductPutDTO;
import cohorte16.homeservice.models.Product;
import cohorte16.homeservice.services.impl.ProductServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@CrossOrigin("*")
public class ProductController {
    private final ProductServiceImpl productService;

    public ProductController(ProductServiceImpl productService) {
        this.productService = productService;
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findByid(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Product not found");
        }
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<?> getAllProducts() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }

    @PostMapping(consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> createProduct(@RequestBody @Valid ProductCreatedDTO createdProductDTO) {
        try {
            ProductCreatedDTO createdProduct = productService.createdProduct(createdProductDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error! Something went wrong");
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json",produces = "application/json")
    public ResponseEntity<?> updateProduct( @Valid  @PathVariable Long id, @RequestBody ProductPutDTO product) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.updateProduct(id, product));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error! Something went wrong");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(productService.deleteProduct(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error! Something went wrong");
        }
    }
}

