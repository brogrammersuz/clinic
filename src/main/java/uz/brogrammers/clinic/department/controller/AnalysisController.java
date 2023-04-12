package uz.brogrammers.clinic.department.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.department.model.dto.RegisterAnalysisRequest;
import uz.brogrammers.clinic.department.model.entity.Analysis;
import uz.brogrammers.clinic.department.service.AnalysisService;
import uz.brogrammers.clinic.department.service.DepartmentService;
import uz.brogrammers.clinic.department.service.FacilityService;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/analysis")
public class AnalysisController {

    private final DepartmentService departmentService;
    private final FacilityService facilityService;
    private final AnalysisService analysisService;


    @PostMapping("/register")
    public ResponseEntity<?> addAnalysis(@RequestBody RegisterAnalysisRequest request) {

        var optionalDepartment = departmentService.findById(request.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Bo'lim topilmadi", null), HttpStatus.BAD_REQUEST);
        }

        var optionalFacility = facilityService.findById(request.getFacilityId());

        if (!optionalFacility.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Xizmat topilmadi", null), HttpStatus.BAD_REQUEST);
        }

        var facility = optionalFacility.get();

        var analysisWithSameNameExits = facility.getAnalyses().stream()
                .filter(analysis -> analysis.getName().equals(request.getName()))
                .findAny()
                .orElse(null);

        if (analysisWithSameNameExits != null) {
            return new ResponseEntity<>(new ApiResponse(false, "Bu analiz xizmatda topildi !", null), HttpStatus.BAD_REQUEST);
        }

        var newAnalysis = Analysis.builder()
                .name(request.getName())
                .price(request.getPrice())
                .departmentId(request.getDepartmentId())
                .norms(request.getNorms())
                .measurment(request.getMeasurement())
                .build();

        var savedAnalysis = analysisService.save(newAnalysis);
        facility.getAnalyses().add(savedAnalysis);
        facilityService.save(facility);

        return ResponseEntity.ok().body(new ApiResponse(true, "Analiz saqlandi", departmentService.getAll()));

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateAnalysis(@RequestBody RegisterAnalysisRequest request) {

        var optionalDepartment = departmentService.findById(request.getDepartmentId());
        if (!optionalDepartment.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Bo'lim topilmadi", null), HttpStatus.BAD_REQUEST);
        }

        var optionalFacility = facilityService.findById(request.getFacilityId());

        if (!optionalFacility.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Xizmat topilmadi", null), HttpStatus.BAD_REQUEST);
        }

        var facility = optionalFacility.get();

        var analysisWithSameNameExits = facility.getAnalyses().stream()
                .filter(analysis -> analysis.getName().equals(request.getName()))
                .findAny()
                .orElse(null);

        if (analysisWithSameNameExits != null) {
            return new ResponseEntity<>(new ApiResponse(false, "Bu analiz xizmatda topildi !", null), HttpStatus.BAD_REQUEST);
        }

        var newAnalysis = Analysis.builder()
                .name(request.getName())
                .price(request.getPrice())
                .departmentId(request.getDepartmentId())
                .norms(request.getNorms())
                .measurment(request.getMeasurement())
                .build();

        analysisService.save(newAnalysis);
        return ResponseEntity.ok().body(new ApiResponse(true, "Analiz saqlandi", departmentService.getAll()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
        analysisService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Analiz oçhirildi", departmentService.getAll()));
    }

}
