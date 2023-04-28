package uz.brogrammers.clinic.client.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ClientsResponse {

    List<ClientDTO> content;
    Long totalElements;
    Integer size;
    Integer number;

}
