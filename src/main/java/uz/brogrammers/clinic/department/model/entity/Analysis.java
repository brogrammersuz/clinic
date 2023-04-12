package uz.brogrammers.clinic.department.model.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "analysis")
@Builder
public class Analysis {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Integer departmentId;

    @Column(name = "norms")
    private String norms;

    @Column(name = "measurment")
    private String measurment;


}
