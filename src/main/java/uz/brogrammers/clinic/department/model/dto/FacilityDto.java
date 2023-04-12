package uz.brogrammers.clinic.department.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.brogrammers.clinic.department.model.entity.Analysis;
import uz.brogrammers.clinic.department.model.entity.Facility;

import java.util.Comparator;
import java.util.List;

@Data
@AllArgsConstructor
public class FacilityDto {

    private Integer id;
    private String name;
    private List<Analysis> analysis;

    public static FacilityDto build(Facility facility) {
        var analysis = facility.getAnalyses().stream().toList();
        analysis.stream().sorted(Comparator.comparing(Analysis::getId));

        return new FacilityDto(facility.getId(), facility.getName(), analysis);
    }

}
