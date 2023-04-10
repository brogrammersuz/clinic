package uz.brogrammers.clinic.user.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
    private Object data;

}
