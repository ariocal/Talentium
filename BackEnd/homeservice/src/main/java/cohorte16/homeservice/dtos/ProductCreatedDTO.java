package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Product;

import java.math.BigDecimal;

public record ProductCreatedDTO(
        Long profesionalId,
        String name,
        BigDecimal price,
        String description,
        int quantity,
        byte[] photo
) {
    public ProductCreatedDTO(Product product) {
        this(
                product.getProfessional().getId(),
                product.getName(),
                product.getPrice(),
                product.getDescription(),
                product.getQuantity(),
                product.getPhoto()
        );
    }
}
