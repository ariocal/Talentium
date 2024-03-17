package cohorte16.homeservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "productos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private byte[] foto;

    @ManyToOne
    @JoinColumn(name = "profesional_id", referencedColumnName = "id")
    private Professional professional;

    @Column(name = "activo")
    private boolean active = Boolean.TRUE;
}
