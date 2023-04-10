package uz.brogrammers.clinic.department.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "facilities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name",  nullable = false)
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "facility_analysis",
    joinColumns = @JoinColumn(name = "analysis_id", referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "facility_id", referencedColumnName = "id"))
    private Set<Analysis> analyses = new HashSet<>();

}
