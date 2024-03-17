package cohorte16.homeservice.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "compras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Shopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date date =  new Date();

    @ManyToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private Client client;


    @Column(name = "total")
    @DecimalMin(value = "0.00")
    private BigDecimal total;

    @Column(name = "descripcion")
    private String description;
}
