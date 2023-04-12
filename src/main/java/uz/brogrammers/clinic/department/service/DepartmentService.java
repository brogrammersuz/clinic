package uz.brogrammers.clinic.department.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.department.model.entity.Department;
import uz.brogrammers.clinic.department.repository.DepartmentRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    public List<Department> getAll() {
        return departmentRepository.findAll().stream()
                .sorted(Comparator.comparing(Department::getId))
                .toList();
    }

    public Optional<Department> findById(Integer id) {
        return departmentRepository.findById(id);
    }

    public Department save(Department department) {
        return departmentRepository.save(department);
    }

}
