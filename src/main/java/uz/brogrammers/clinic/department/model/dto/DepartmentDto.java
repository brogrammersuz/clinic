package uz.brogrammers.clinic.department.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.brogrammers.clinic.department.model.entity.Department;
import uz.brogrammers.clinic.department.model.entity.Facility;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class DepartmentDto {

    private Integer id;
    private String name;
    private List<FacilityDto> facilities;

    public static DepartmentDto build(Department department, List<FacilityDto> facilityDtos){
        var sortedFacilitiesDtos = facilityDtos.stream().sorted(Comparator.comparingInt(FacilityDto::getId)).toList();

        return new DepartmentDto(department.getId(), department.getName(), sortedFacilitiesDtos);
    }

}
