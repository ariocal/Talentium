package cohorte16.homeservice.models;

import cohorte16.homeservice.dtos.ProductCreatedDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Builder
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank
    private String name;


    @Column(name = "precio")
    @DecimalMin(value = "0.00")
    private BigDecimal price;

    @Column(name = "descripcion")
    private String description;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "foto")
    private byte[] photo;

    @ManyToOne
    @JoinColumn(name = "profesionales_id", referencedColumnName = "id")
    private Professional professional;

    @Column(name = "activo")
    private boolean active = Boolean.TRUE;

    public Product(ProductCreatedDTO createdProductDTO){
        this.professional.setId(createdProductDTO.profesionalId());
        this.name = createdProductDTO.name();
        this.price = createdProductDTO.price();
        this.quantity = createdProductDTO.quantity();
        this.photo = createdProductDTO.photo();
    }
}
