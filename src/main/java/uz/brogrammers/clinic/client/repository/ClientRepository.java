package uz.brogrammers.clinic.client.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.brogrammers.clinic.client.model.entity.Client;

import java.time.ZonedDateTime;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findAllByDepartmentId(Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLike(String firstName, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndDepartmentId(String firstName, Integer departmentId, Pageable pageable);

    Page<Client> findAllByLastNameIsLike(String lastName, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndDepartmentId(String lastName, Integer departmentId, Pageable pageable);

    Page<Client> findAllByReferrerId(Integer referrerId, Pageable pageable);

    Page<Client> findAllByReferrerIdAndDepartmentId(Integer referrerId, Integer departmentId, Pageable pageable);

    Page<Client> findAllByCreatedAfter(ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByCreatedAfterAndDepartmentId(ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByCreatedBefore(ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByCreatedBeforeAndDepartmentId(ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLike(String firstName, String lastName, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndDepartmentId(String firstName, String lastName, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndReferrerId(String firstName, Integer referrerId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndReferrerIdAndDepartmentId(String firstName, Integer referrerId, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndCreatedAfter(String firstName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndCreatedAfterAndDepartmentId(String fistName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndCreatedBefore(String firstName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndCreatedBeforeAndDepartmentId(String fistName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndReferrerId(String lastName, Integer referrerId, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndReferrerIdAndDepartmentId(String lastName, Integer referrerId, Integer departmentId, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndCreatedAfter(String lastName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndCreatedAfterAndDepartmentId(String lastName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndCreatedBefore(String lastName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByLastNameIsLikeAndCreatedBeforeAndDepartmentId(String lastName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedAfter(Integer referrerId, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedAfterAndDepartmentId(Integer referrerId, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedBetween(Integer referrerId, ZonedDateTime from, ZonedDateTime to, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedBetweenAndDepartmentId(Integer referrerId, ZonedDateTime from, ZonedDateTime to, Integer departmentId, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedBefore(Integer referrerId, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByReferrerIdAndCreatedBeforeAndDepartmentId(Integer referrerId, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByCreatedBetween(ZonedDateTime from, ZonedDateTime to, Pageable pageable);

    Page<Client> findAllByCreatedBetweenAndDepartmentId(ZonedDateTime from, ZonedDateTime to, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerId(String firstName, String lastName, Integer referrerId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndDepartmentId(String firstName, String lastName, Integer referrerId, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedAfter(String firstName, String lastName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedAfterAndDepartmentId(String firstName, String lastName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBefore(String firstName, String lastName, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBeforeAndDepartmentId(String firstName, String lastName, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedAfter(String firstName, String lastName, Integer referrerId, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedAfterAndDepartmentId(String firstName, String lastName, Integer referrerId, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBefore(String firstName, String lastName, Integer referrerId, ZonedDateTime created, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBeforeAndDepartmentId(String firstName, String lastName, Integer referrerId, ZonedDateTime created, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBetween(String firstName, String lastName, Integer referrerId, ZonedDateTime from, ZonedDateTime to, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBetweenAndDepartmentId(String firstName, String lastName, Integer referrerId, ZonedDateTime created, ZonedDateTime to, Integer departmentId, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBetween(String firstName, String lastName, ZonedDateTime from, ZonedDateTime to, Pageable pageable);

    Page<Client> findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBetweenAndDepartmentId(String firstName, String lastName, ZonedDateTime created, ZonedDateTime to, Integer departmentId, Pageable pageable);

}
