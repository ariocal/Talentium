package cohorte16.homeservice.dtos;

import java.math.BigDecimal;

public record ProductPutDTO(
        String name,
        BigDecimal price,
        String description,
        int quantity,
        byte[] photo
) {
}
