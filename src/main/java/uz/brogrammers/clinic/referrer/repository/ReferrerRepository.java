package uz.brogrammers.clinic.referrer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.clinic.referrer.model.entity.Referrer;

import java.util.Optional;

@Repository
public interface ReferrerRepository extends JpaRepository<Referrer, Integer> {

    Optional<Referrer> findByUniqueCode(String uniqueCode);

}
