package cohorte16.homeservice.dtos;

import cohorte16.homeservice.enums.Profession;

public record ProfessionalPutDTO(
        String name,
        String lastname,
        String phone,
        String cuit,
        String cbu,
        Integer rating,
        Profession profession,
        DirectionDTO direction,
        UserDTO user
) {
}
