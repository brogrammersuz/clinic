package uz.brogrammers.clinic.referrer.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateReferrerRequest extends RegisterReferrerRequest {
    @NotNull
    private Integer id;

}
