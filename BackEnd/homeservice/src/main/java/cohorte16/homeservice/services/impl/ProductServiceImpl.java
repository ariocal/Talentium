package cohorte16.homeservice.services.impl;

import cohorte16.homeservice.dtos.ProductCreatedDTO;
import cohorte16.homeservice.dtos.ProductPutDTO;
import cohorte16.homeservice.dtos.ProductResponseDTO;
import cohorte16.homeservice.mappers.ProductMapper;
import cohorte16.homeservice.models.Product;
import cohorte16.homeservice.models.Professional;
import cohorte16.homeservice.repositories.ProductRepository;
import cohorte16.homeservice.repositories.ProfessionalRepository;
import cohorte16.homeservice.services.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProfessionalRepository professionalRepository;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepository productRepository, ProfessionalRepository professionalRepository, ProductMapper productMapper){
        this.productRepository = productRepository;
        this.professionalRepository = professionalRepository;
        this.productMapper = productMapper;
    }

    @Override
    public ProductResponseDTO findByid(Long id) throws Exception {
        try{
            Optional<Product> productOptional = productRepository.findById(id);
            if (productOptional.isEmpty()){
                throw new EntityNotFoundException("Product not found");
            }
            Product product = productOptional.get();
            return productMapper.productToProductResponseDTO(product);
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public List<ProductResponseDTO> findAll() throws Exception {
        try{
            List<Product> productList = productRepository.findAll();
            return productList.stream().map(productMapper::productToProductResponseDTO).toList();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public ProductCreatedDTO createdProduct(ProductCreatedDTO createdProductDTO) throws Exception {
        try {
            Professional professional = professionalRepository.findById(createdProductDTO.profesionalId())
                    .orElseThrow(() -> new EntityNotFoundException("Professional not found"));

            Product productDb = Product.builder()
                    .professional(professional)
                    .name(createdProductDTO.name())
                    .price(createdProductDTO.price())
                    .description(createdProductDTO.description())
                    .quantity(createdProductDTO.quantity())
                    .photo(createdProductDTO.photo())
                    .active(true)
                    .build();
            Product product = productRepository.save(productDb);
            return new ProductCreatedDTO(product);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    @Override
    public ProductResponseDTO updateProduct(Long id, ProductPutDTO product) throws Exception {
        try{
            Product productExisting = productRepository.findById(id)
                    .orElseThrow(()-> new EntityNotFoundException("User not found with id: " + id));
            Product productEntity = updateProductFromProduct(productExisting, product);
            Product savedProduct = productRepository.save(productEntity);
            return productMapper.productToProductResponseDTO(savedProduct);
        }catch (EntityNotFoundException e){
            throw e;
        }catch (Exception e){
            throw new ServiceException("Error ocurred while updating product with id " + id, e);
        }
    }

    @Override
    public boolean deleteProduct(Long id) throws Exception {
        try {
            if(productRepository.existsById(id)){
                Product product = productRepository.findById(id).get();
                product.setActive(false);
                productRepository.save(product);
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    private static Product updateProductFromProduct(Product productExisting, ProductPutDTO product){
        productExisting.setName(product.name());
        productExisting.setPrice(product.price());
        productExisting.setDescription(product.description());
        productExisting.setQuantity(product.quantity());
        productExisting.setPhoto(product.photo());
        return productExisting;
    }
}
