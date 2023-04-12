package uz.brogrammers.clinic.department.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.clinic.department.model.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
}
