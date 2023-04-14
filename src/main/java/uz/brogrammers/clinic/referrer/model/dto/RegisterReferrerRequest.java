package uz.brogrammers.clinic.referrer.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterReferrerRequest {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private String uniqueCode;
    @NotNull
    private String phone;
    @NotNull
    private Integer percentage;

}
