package uz.brogrammers.clinic.department.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class UpdateAnalysisRequest {
    @NotNull
    Integer id;
    @NotNull
    String name;
    @NotNull
    BigDecimal price;
    @NotNull
    Integer departmentId;
    @NotNull
    Integer facilityId;
    String norms;
    String measurement;
}
