package uz.brogrammers.clinic.client.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.Set;

@Data
@JsonSerialize(include= JsonSerialize.Inclusion.NON_NULL)
public class RegisterClientRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    private String middleName;
    @NotNull
    private String dateOfBirth;
    private String phone;
    @NotNull
    private String gender;
    private Integer referrerId;
    @NotNull
    private Integer departmentId;
    @NotNull
    private Set<Integer> analysesIds = new HashSet<>();
    @NotNull
    private Boolean isPaid;
}
