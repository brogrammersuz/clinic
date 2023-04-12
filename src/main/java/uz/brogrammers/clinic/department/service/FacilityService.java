package uz.brogrammers.clinic.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.department.model.entity.Facility;
import uz.brogrammers.clinic.department.repository.FacilityRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository repository;

    public Facility save(Facility facility) {
        return repository.save(facility);
    }

    public Optional<Facility> findById(Integer id) {
        return repository.findById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }


}
