package cohorte16.homeservice.mappers;

import cohorte16.homeservice.dtos.*;
import cohorte16.homeservice.models.Product;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {
    public ProductResponseDTO productToProductResponseDTO(Product product){
        return new ProductResponseDTO(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getPhoto(),
                new ProfessionalMiniDTO(
                        product.getProfessional().getId(),
                        product.getProfessional().getName(),
                        product.getProfessional().getLastname(),
                        product.getProfessional().getPhone(),
                        product.getProfessional().getProfession(),
                        product.getProfessional().getRating()
                )
        );
    }
}
