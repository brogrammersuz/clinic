package uz.brogrammers.clinic.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.brogrammers.clinic.department.model.dto.DepartmentDto;
import uz.brogrammers.clinic.department.model.dto.FacilityDto;
import uz.brogrammers.clinic.department.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/list")
    public List<DepartmentDto> getAllDepartments() {
        var departmentDtos = new ArrayList<DepartmentDto>();
        departmentService.getAll().stream()
                .forEach(department -> {
                    List<FacilityDto> facilities = new ArrayList<>();
                    department.getFacilities().stream()
                            .forEach(facility -> {
                                facilities.add(FacilityDto.build(facility));
                            });

                    departmentDtos.add(DepartmentDto.build(department, facilities));
                });

        return departmentDtos;
    }

}
