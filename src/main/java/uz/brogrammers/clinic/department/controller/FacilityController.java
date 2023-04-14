package uz.brogrammers.clinic.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.department.model.dto.RegisterFacilityDto;
import uz.brogrammers.clinic.department.model.dto.UpdateFacilityRequest;
import uz.brogrammers.clinic.department.model.entity.Facility;
import uz.brogrammers.clinic.department.service.DepartmentService;
import uz.brogrammers.clinic.department.service.FacilityService;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;

import java.util.TreeSet;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/facility")
public class FacilityController {

    private final DepartmentService departmentService;
    private final FacilityService facilityService;

    @PostMapping("/register")
    public ResponseEntity<?> addFacility(@RequestBody RegisterFacilityDto request) {

        var optionalDepartment = departmentService.findById(request.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bo'lim topilmadi", null));
        }

        var department = optionalDepartment.get();

        var facilityWithSameNameExists = department.getFacilities().stream()
                .filter(facility -> facility.getName().equals(request.getName()))
                .findAny();

        if (facilityWithSameNameExists.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bu xizmat bo'limda topildi", null));
        }

        var newFacility = Facility.builder()
                .name(request.getName())
                .analyses(new TreeSet<>())
                .build();

        var savedFacility = facilityService.save(newFacility);
        department.getFacilities().add(savedFacility);
        departmentService.save(department);
        return ResponseEntity.ok().body(new ApiResponse(true, "Xizmat saqlandi", departmentService.getAll()));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateFacility(@RequestBody UpdateFacilityRequest request) {

        var optionalDepartment = departmentService.findById(request.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bo'lim topilmadi", null));
        }

        var department = optionalDepartment.get();

        var facilityWithSameNameExists = department.getFacilities().stream()
                .filter(facility -> facility.getName().equals(request.getName()))
                .findAny();

        if (facilityWithSameNameExists.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bu xizmat bo'limda topildi", null));
        }

        var newFacility = Facility.builder()
                .id(request.getId())
                .name(request.getName())
                .analyses(new TreeSet<>())
                .build();

        facilityService.save(newFacility);
        return ResponseEntity.ok().body(new ApiResponse(true, "Xizmat saqlandi", departmentService.getAll()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteFacility(@PathVariable("id") Integer id) {
        facilityService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Xizmat o'chirildi", departmentService.getAll()));
    }


}
