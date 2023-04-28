package uz.brogrammers.clinic.client.model.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class UpdateClientRequest {
    @NotNull
    private Integer id;
    @NotNull
    private Boolean isPaid;
    @NotNull
    private String result;

}
