package uz.brogrammers.clinic.client.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import uz.brogrammers.clinic.client.model.entity.Client;
import uz.brogrammers.clinic.client.repository.ClientRepository;

import java.time.ZonedDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Optional<Client> findById(Integer id) {
        return clientRepository.findById(id);
    }

    public Page<Client> findAll(Integer pageNumber, Integer pageSize) {
        return clientRepository.findAll(createPage(pageNumber, pageSize));
    }

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Page<Client> findAllByDepartmentId(Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByDepartmentId(departmentId, createPage(pageNumber, pageSize));
    }

    private PageRequest createPage(Integer pageNumber, Integer pageSize) {
        return PageRequest.of(pageNumber, pageSize, Sort.Direction.DESC, "id");
    }

    public Page<Client> findByFirstName(String firstName, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLike(firstName, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndDepartmentId(String firstName, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndDepartmentId(firstName, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastName(String lastName, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLike(lastName, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndDepartmentId(String lastName, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndDepartmentId(lastName, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerId(Integer referrerId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerId(referrerId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndDepartmentId(Integer referrerId, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndDepartmentId(referrerId, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFromDate(ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedAfter(from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFromDateAndDepartmentId(ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedAfterAndDepartmentId(from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByToDate(ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedBefore(to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByToDateAndDepartmentId(ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedBeforeAndDepartmentId(to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastName(String firstName, String lastName, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLike(firstName, lastName, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndDepartmentId(String firstName, String lastName, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndDepartmentId(firstName, lastName, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndReferrerId(String firstName, Integer referrerId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndReferrerId(firstName, referrerId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndReferrerIdAndDepartmentId(String firstName, Integer referrerId, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndReferrerIdAndDepartmentId(firstName, referrerId, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndFromDate(String firstName, ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndCreatedAfter(firstName, from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndFromDateAndDepartmentId(String firstName, ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndCreatedAfterAndDepartmentId(firstName, from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndToDate(String firstName, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndCreatedBefore(firstName, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndToDateAndDepartmentId(String firstName, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndCreatedBeforeAndDepartmentId(firstName, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndReferrerId(String lastName, Integer referrerId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndReferrerId(lastName, referrerId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndReferrerIdAndDepartmentId(String lastName, Integer referrerId, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndReferrerIdAndDepartmentId(lastName, referrerId, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndFromDate(String lastName, ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndCreatedAfter(lastName, from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndFromDateAndDepartmentId(String lastName, ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndCreatedAfterAndDepartmentId(lastName, from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndToDate(String lastName, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndCreatedBefore(lastName, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByLastNameAndToDateAndDepartmentId(String lastName, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByLastNameIsLikeAndCreatedBeforeAndDepartmentId(lastName, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndFromDate(Integer referrerId, ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedAfter(referrerId, from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndFromDateAndDepartmentId(Integer referrerId, ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedAfterAndDepartmentId(referrerId, from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndToDate(Integer referrerId, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedBefore(referrerId, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndToDateAndDepartmentId(Integer referrerId, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedBeforeAndDepartmentId(referrerId, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndFromDateAndToDate(Integer referrerId, ZonedDateTime from, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedBetween(referrerId, from, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByReferrerIdAndFromDateAndToDateAndDepartmentId(Integer referrerId, ZonedDateTime from, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByReferrerIdAndCreatedBetweenAndDepartmentId(referrerId, from, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFromDateAndToDate(ZonedDateTime from, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedBetween(from, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFromDateAndToDateAndDepartmentId(ZonedDateTime from, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByCreatedBetweenAndDepartmentId(from, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerId(String firstName, String lastName, Integer referrerId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerId(firstName, lastName, referrerId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerIdAndDepartmentId(String firstName, String lastName, Integer referrerId, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndDepartmentId(firstName, lastName, referrerId, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndFromDate(String firstName, String lastName, ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedAfter(firstName, lastName, from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndFromDateAndDepartmentId(String firstName, String lastName, ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedAfterAndDepartmentId(firstName, lastName, from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndToDate(String firstName, String lastName, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBefore(firstName, lastName, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndToDateAndDepartmentId(String firstName, String lastName, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBeforeAndDepartmentId(firstName, lastName, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerIdAndFromDate(String firstName, String lastName, Integer referrerId, ZonedDateTime from, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedAfter(firstName, lastName, referrerId, from, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerIdAndFromDateAndDepartmentId(String firstName, String lastName, Integer referrerId, ZonedDateTime from, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedAfterAndDepartmentId(firstName, lastName, referrerId, from, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerIdAndToDate(String firstName, String lastName, Integer referrerId, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBefore(firstName, lastName, referrerId, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByFirstNameAndLastNameAndReferrerIdAndToDateAndDepartmentId(String firstName, String lastName, Integer referrerId, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBeforeAndDepartmentId(firstName, lastName, referrerId, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByAllFieldsWithoutReferrerIdAndDepartment(String firstName, String lastName, ZonedDateTime from, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBetween(firstName, lastName, from, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByAllFieldsWithoutReferrerId(String firstName, String lastName, ZonedDateTime from, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndCreatedBetweenAndDepartmentId(firstName, lastName, from, to, departmentId, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByAllFieldsWithoutDepartment(String firstName, String lastName, Integer referrerId, ZonedDateTime from, ZonedDateTime to, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBetween(firstName, lastName, referrerId, from, to, createPage(pageNumber, pageSize));
    }

    public Page<Client> findByAllFields(String firstName, String lastName, Integer referrerId, ZonedDateTime from, ZonedDateTime to, Integer departmentId, Integer pageNumber, Integer pageSize) {
        return clientRepository.findAllByFirstNameIsLikeAndLastNameIsLikeAndReferrerIdAndCreatedBetweenAndDepartmentId(firstName, lastName, referrerId, from, to, departmentId, createPage(pageNumber, pageSize));
    }


}
