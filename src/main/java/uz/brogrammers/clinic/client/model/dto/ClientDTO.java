package uz.brogrammers.clinic.client.model.dto;

import lombok.Builder;
import lombok.Data;
import uz.brogrammers.clinic.department.model.entity.Analysis;

import java.util.List;

@Data
@Builder
public class ClientDTO {

    private Integer id;

    private String firstName;

    private String lastName;

    private String middleName;

    private String dateOfBirth;

    private String created;

    private String phone;

    private String gender;

    private String referralCode;

    private String departmentName;

    private List<Analysis> analyses;

    private Integer price;

    private Boolean isPaid;
}
