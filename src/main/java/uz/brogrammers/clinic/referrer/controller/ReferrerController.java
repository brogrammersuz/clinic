package uz.brogrammers.clinic.referrer.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.referrer.model.dto.RegisterReferrerRequest;
import uz.brogrammers.clinic.referrer.model.dto.UpdateReferrerRequest;
import uz.brogrammers.clinic.referrer.model.entity.Referrer;
import uz.brogrammers.clinic.referrer.service.ReferrerService;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;

import java.util.Comparator;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/referrer")
public class ReferrerController {

    private final ReferrerService referrerService;

    @GetMapping("/list")
    public List<Referrer> getAll() {
        return referrerService.findAll().stream()
                .sorted(Comparator.comparingInt(Referrer::getId))
                .toList();
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerReferrer(@RequestBody RegisterReferrerRequest request) {

        var uniqueCodeExists = referrerService.findByUniqueCode(request.getUniqueCode());
        if (uniqueCodeExists.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bu kod royxatdan o'tgan", null));
        }

        var referrer = Referrer.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .uniqueCode(request.getUniqueCode())
                .phone(request.getPhone())
                .percentage(request.getPercentage())
                .build();

        referrerService.save(referrer);

        return ResponseEntity.ok().body(new ApiResponse(true, "Tavsiyachi saqlandi", referrerService.findAll()));
    }


    @PostMapping("/update")
    public ResponseEntity<?> updateReferrer(@RequestBody UpdateReferrerRequest request) {

        var uniqueCodeExists = referrerService.findByUniqueCode(request.getUniqueCode());
        if (uniqueCodeExists.isPresent()) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Bu kod royxatdan o'tgan", null));
        }

        var referrer = Referrer.builder()
                .id(request.getId())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .uniqueCode(request.getUniqueCode())
                .phone(request.getPhone())
                .percentage(request.getPercentage())
                .build();

        referrerService.save(referrer);

        return ResponseEntity.ok().body(new ApiResponse(true, "Tavsiyachi saqlandi", referrerService.findAll()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteReferrer(@PathVariable("id") Integer id) {
        referrerService.deleteById(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Tavsiyachi o'chirildi", referrerService.findAll()));
    }

}
