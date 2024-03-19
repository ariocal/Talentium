package cohorte16.homeservice.models;

import cohorte16.homeservice.enums.Profession;
import jakarta.persistence.*;
import jakarta.persistence.criteria.Fetch;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Builder
@Entity
@Table(name="Profesionales")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Professional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre")
    @NotBlank
    private String name;

    @Column(name = "apellido")
    @NotBlank
    private String lastname;

    @Column(name = "telefono")
    @NotBlank
    private String phone;

    @Column(name = "cuit")
    @NotBlank
    private String cuit;

    @Column(name = "cbu")
    @NotBlank
    private String cbu;

    @Column(name = "clasificacion")
    private Integer rating;

    @Column(name = "PROFESION")
    @Enumerated(value = EnumType.STRING)
    private Profession profession;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profesional_direccion_id")
    private Direction direction;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profesional_usuario_id")
    private User user;

    @OneToMany(mappedBy = "professional", cascade = CascadeType.ALL)
    private List<Order> orderList = Collections.emptyList();

    @Column(name = "activo")
    private boolean active = Boolean.TRUE;

    @Column(name = "billetera")
    @DecimalMin(value = "0.00")
    private BigDecimal wallet;

}
