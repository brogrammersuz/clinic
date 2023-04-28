package uz.brogrammers.clinic.client.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import uz.brogrammers.clinic.department.model.entity.Analysis;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @CreatedDate
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    @CreatedDate
    @Column(name = "created")
    private ZonedDateTime created;

    @Column(name = "phone")
    private String phone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @JoinColumn(name = "referrer_id", referencedColumnName = "id")
    private Integer referrerId;

    @JoinColumn(name = "department_id", referencedColumnName = "id", nullable = false)
    private Integer departmentId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "client_analyses",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "analysis_id", referencedColumnName = "id"))
    private List<Analysis> analyses = new ArrayList<>();

    @Column(name = "price", nullable = false)
    private Integer price;

    @Column(name = "paid", nullable = false)
    private Boolean isPaid;

    @Column(name = "result")
    private String result;
}
