package cohorte16.homeservice.dtos;

public record ClientPutDTO(
        String name,
        String lastname,
        String phone,
        String dni,
        Integer rating,
        UserDTO user,
        DirectionDTO direction
) {
}
