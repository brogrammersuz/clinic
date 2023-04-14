package uz.brogrammers.clinic.referrer.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "referrers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Referrer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "unique_code", nullable = false, unique = true)
    private String uniqueCode;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "percentage", nullable = false)
    private Integer percentage;

}
