package uz.brogrammers.clinic.department.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateFacilityRequest {

    @NotNull
    Integer id;
    @NotNull
    String name;
    @NotNull
    Integer departmentId;

}
