package uz.brogrammers.clinic.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.department.model.entity.Analysis;
import uz.brogrammers.clinic.department.repository.AnalysisRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnalysisService {

    private final AnalysisRepository analysisRepository;

    public Analysis save(Analysis analysis) {
        return analysisRepository.save(analysis);
    }

    public void deleteById(Integer id) {
        analysisRepository.deleteById(id);
    }


    public Optional<Analysis> findById(Integer id) {
        return analysisRepository.findById(id);
    }

}
