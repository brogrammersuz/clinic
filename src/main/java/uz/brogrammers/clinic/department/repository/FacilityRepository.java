package uz.brogrammers.clinic.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.clinic.department.model.entity.Facility;

@Repository
public interface FacilityRepository extends JpaRepository<Facility, Integer> {
}
