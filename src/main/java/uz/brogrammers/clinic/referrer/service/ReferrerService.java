package uz.brogrammers.clinic.referrer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.referrer.model.entity.Referrer;
import uz.brogrammers.clinic.referrer.repository.ReferrerRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReferrerService {

    private final ReferrerRepository referrerRepository;

    public List<Referrer> findAll() {
        return referrerRepository.findAll();
    }

    public Referrer save(Referrer referrer) {
        return referrerRepository.save(referrer);
    }

    public Optional<Referrer> findByUniqueCode(String uniqueCode) {
        return referrerRepository.findByUniqueCode(uniqueCode);
    }

    public Optional<Referrer> findById(Integer id) {
        return referrerRepository.findById(id);
    }

    public void deleteById(Integer id) {
        referrerRepository.deleteById(id);
    }


}
