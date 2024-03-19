package cohorte16.homeservice.services;

import cohorte16.homeservice.dtos.ProductCreatedDTO;
import cohorte16.homeservice.dtos.ProductPutDTO;
import cohorte16.homeservice.dtos.ProductResponseDTO;
import cohorte16.homeservice.models.Product;

import java.util.List;

public interface ProductService {

    ProductResponseDTO findByid(Long id) throws Exception;
    List<ProductResponseDTO> findAll() throws Exception;
    ProductCreatedDTO createdProduct(ProductCreatedDTO createdProductDTO) throws Exception;
    ProductResponseDTO updateProduct(Long id, ProductPutDTO product) throws Exception;
    boolean deleteProduct(Long id) throws Exception;
}
