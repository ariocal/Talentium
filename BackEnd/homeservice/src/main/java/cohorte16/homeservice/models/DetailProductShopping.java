package cohorte16.homeservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalles_productos_compras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailProductShopping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "productos_id", referencedColumnName = "id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "compras_id", referencedColumnName = "id")
    private Shopping shopping;

    @Column(name = "cantidad")
    private int quantity;

    @Column(name = "descuento")
    @DecimalMin(value = "0.00")
    private BigDecimal discount;
}
