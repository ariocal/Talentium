package cohorte16.homeservice.dtos;

import cohorte16.homeservice.models.Professional;
import java.math.BigDecimal;

public record ProductResponseDTO(
        Long productId,
        String name,
        BigDecimal price,
        String description,
        int quantity,
        byte[] photo,
        ProfessionalMiniDTO professional
) {
}
