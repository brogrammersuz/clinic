package uz.brogrammers.clinic.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.clinic.client.model.dto.ClientDTO;
import uz.brogrammers.clinic.client.model.dto.ClientsResponse;
import uz.brogrammers.clinic.client.model.dto.RegisterClientRequest;
import uz.brogrammers.clinic.client.model.dto.UpdateClientRequest;
import uz.brogrammers.clinic.client.model.entity.Client;
import uz.brogrammers.clinic.client.service.ClientService;
import uz.brogrammers.clinic.department.model.entity.Analysis;
import uz.brogrammers.clinic.department.service.AnalysisService;
import uz.brogrammers.clinic.department.service.DepartmentService;
import uz.brogrammers.clinic.referrer.service.ReferrerService;
import uz.brogrammers.clinic.user.model.dto.response.ApiResponse;
import uz.brogrammers.clinic.user.service.UserService;
import uz.brogrammers.clinic.util.Utils;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/client")
public class ClientController {

    private final ClientService clientService;
    private final DepartmentService departmentService;
    private final AnalysisService analysisService;
    private final ReferrerService referrerService;
    private final UserService userService;

    @GetMapping("/list")
    public ResponseEntity getAllClients(@RequestParam(name = "userId") Integer userId,
                                        @RequestParam(name = "page", defaultValue = "0") int page,
                                        @RequestParam(name = "size", defaultValue = "50") int size) {

        var userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Foydalanuvchi топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var isAdmin = Utils.isAdmin(userOptional);

        if (isAdmin) {
            return getAll(page, size);
        }

        var departmentOptional = departmentService.findById(userOptional.get().getDepartmentId());

        if (!departmentOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Бўлим топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        return getByDepartmentId(departmentOptional.get().getId(), page, size);
    }

    @GetMapping("/list/search")
    public ResponseEntity getClientsBySearch(@RequestParam(name = "userId") Integer userId,
                                             @RequestParam(name = "page", defaultValue = "0") int page,
                                             @RequestParam(name = "size", defaultValue = "50") int size,
                                             @RequestParam(name = "firstName", required = false) String firstName,
                                             @RequestParam(name = "lastName", required = false) String lastName,
                                             @RequestParam(name = "referrer", required = false) Integer referrerId,
                                             @RequestParam(name = "department", required = false) Integer department,
                                             @RequestParam(name = "fromDate", required = false) String fromDate,
                                             @RequestParam(name = "toDate", required = false) String toDate) {

        var userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Foydalanuvchi топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var userDpOptional = departmentService.findById(userOptional.get().getDepartmentId());
        if (!userDpOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Foydalanuvchi bo'limi топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var isAdmin = Utils.isAdmin(userOptional);
        var userDepartment = userDpOptional.get();
        Integer departmentId = null;

        if (department != null) {
            var dpOptional = departmentService.findById(department);
            if (!dpOptional.isPresent()) {
                return new ResponseEntity<>(new ApiResponse(false, "Бўлим топилмади!", null), HttpStatus.BAD_REQUEST);
            }

            departmentId = dpOptional.get().getId();

        } else if (departmentId == null && !(isAdmin)) {

            departmentId = userDepartment.getId();
        }


        if (firstName != null && lastName == null && referrerId == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstName(firstName, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && departmentId != null && lastName == null && referrerId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndDepartmentId(firstName, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && firstName == null && referrerId == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByLastName(lastName, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && departmentId != null && firstName == null && referrerId == null && fromDate == null && toDate == null) {
            var result = clientService.findByLastNameAndDepartmentId(lastName, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && firstName == null && lastName == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByReferrerId(referrerId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && departmentId != null && firstName == null && lastName == null && fromDate == null && toDate == null) {
            var result = clientService.findByReferrerIdAndDepartmentId(referrerId, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (department != null && firstName == null && lastName == null && referrerId == null && fromDate == null && toDate == null) {
            var result = clientService.findAllByDepartmentId(departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (fromDate != null && firstName == null && lastName == null && referrerId == null && departmentId == null && toDate == null) {
            var result = clientService.findByFromDate(ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (fromDate != null && departmentId != null && firstName == null && lastName == null && referrerId == null && toDate == null) {
            var result = clientService.findByFromDateAndDepartmentId(ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (toDate != null && firstName == null && lastName == null && referrerId == null && departmentId == null && fromDate == null) {
            var result = clientService.findByToDate(ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (toDate != null && departmentId != null && firstName == null && lastName == null && referrerId == null && fromDate == null) {
            var result = clientService.findByToDateAndDepartmentId(ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastName(firstName, lastName, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && departmentId != null && referrerId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndDepartmentId(firstName, lastName, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && referrerId != null && lastName == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndReferrerId(firstName, referrerId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && referrerId != null && departmentId != null && lastName == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndReferrerIdAndDepartmentId(firstName, referrerId, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && fromDate != null && lastName == null && referrerId == null && departmentId == null && toDate == null) {
            var result = clientService.findByFirstNameAndFromDate(firstName, ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && fromDate != null && departmentId != null && lastName == null && referrerId == null && toDate == null) {
            var result = clientService.findByFirstNameAndFromDateAndDepartmentId(firstName, ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && toDate != null && lastName == null && referrerId == null && departmentId == null && fromDate == null) {
            var result = clientService.findByFirstNameAndToDate(firstName, ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && toDate != null && departmentId != null && lastName == null && referrerId == null && fromDate == null) {
            var result = clientService.findByFirstNameAndToDateAndDepartmentId(firstName, ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && referrerId != null && firstName == null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByLastNameAndReferrerId(lastName, referrerId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && referrerId != null && departmentId != null && firstName == null && fromDate == null && toDate == null) {
            var result = clientService.findByLastNameAndReferrerIdAndDepartmentId(lastName, referrerId, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && fromDate != null && firstName == null && referrerId == null && departmentId == null && toDate == null) {
            var result = clientService.findByLastNameAndFromDate(lastName, ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && fromDate != null && departmentId != null && firstName == null && referrerId == null && toDate == null) {
            var result = clientService.findByLastNameAndFromDateAndDepartmentId(lastName, ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && toDate != null && firstName == null && referrerId == null && departmentId == null && fromDate == null) {
            var result = clientService.findByLastNameAndToDate(lastName, ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (lastName != null && toDate != null && departmentId != null && firstName == null && referrerId == null && fromDate == null) {
            var result = clientService.findByLastNameAndToDateAndDepartmentId(lastName, ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && fromDate != null && firstName == null && lastName == null && departmentId == null && toDate == null) {
            var result = clientService.findByReferrerIdAndFromDate(referrerId, ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && fromDate != null && departmentId != null && lastName == null && firstName == null && toDate == null) {
            var result = clientService.findByReferrerIdAndFromDateAndDepartmentId(referrerId, ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && toDate != null && firstName == null && lastName == null && departmentId == null && fromDate == null) {
            var result = clientService.findByReferrerIdAndToDate(referrerId, ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && toDate != null && departmentId != null && firstName == null && lastName == null && fromDate == null) {
            var result = clientService.findByReferrerIdAndToDateAndDepartmentId(referrerId, ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && fromDate != null && toDate != null && firstName == null && lastName == null && departmentId == null) {
            var result = clientService.findByReferrerIdAndFromDateAndToDate(referrerId, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (referrerId != null && fromDate != null && toDate != null && departmentId != null && firstName == null && lastName == null) {
            var result = clientService.findByReferrerIdAndFromDateAndToDateAndDepartmentId(referrerId, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (fromDate != null && toDate != null && firstName == null && lastName == null && referrerId == null && departmentId == null) {
            var result = clientService.findByFromDateAndToDate(ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (fromDate != null && toDate != null && departmentId != null && firstName == null && lastName == null && referrerId == null) {
            var result = clientService.findByFromDateAndToDateAndDepartmentId(ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId == null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerId(firstName, lastName, referrerId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId != null && fromDate == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerIdAndDepartmentId(firstName, lastName, referrerId, departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && fromDate != null && referrerId == null && departmentId == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndFromDate(firstName, lastName, ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && fromDate != null && departmentId != null && referrerId == null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndFromDateAndDepartmentId(firstName, lastName, ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && toDate != null && referrerId == null && departmentId == null && fromDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndToDate(firstName, lastName, ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && toDate != null && departmentId != null && referrerId == null && fromDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndToDateAndDepartmentId(firstName, lastName, ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId == null && fromDate != null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerIdAndFromDate(firstName, lastName, referrerId, ZonedDateTime.parse(fromDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId != null && fromDate != null && toDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerIdAndFromDateAndDepartmentId(firstName, lastName, referrerId, ZonedDateTime.parse(fromDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId == null && toDate != null && fromDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerIdAndToDate(firstName, lastName, referrerId, ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId != null && toDate != null && fromDate == null) {
            var result = clientService.findByFirstNameAndLastNameAndReferrerIdAndToDateAndDepartmentId(firstName, lastName, referrerId, ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId == null && departmentId == null && fromDate != null && toDate != null) {
            var result = clientService.findByAllFieldsWithoutReferrerIdAndDepartment(firstName, lastName, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId == null && departmentId != null && fromDate != null && toDate != null) {
            var result = clientService.findByAllFieldsWithoutReferrerId(firstName, lastName, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId == null && fromDate != null && toDate != null) {
            var result = clientService.findByAllFieldsWithoutDepartment(firstName, lastName, referrerId, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), page, size);
            return getResponseEntity(page, size, result);
        }

        if (firstName != null && lastName != null && referrerId != null && departmentId != null && fromDate != null && toDate != null) {
            var result = clientService.findByAllFields(firstName, lastName, referrerId, ZonedDateTime.parse(fromDate), ZonedDateTime.parse(toDate), departmentId, page, size);
            return getResponseEntity(page, size, result);
        }


        var res = new ClientsResponse(new ArrayList<>(), 0L, size, page);
        return new ResponseEntity<>(new ApiResponse(true, "Mijoz topilmadi!", res), HttpStatus.OK);
    }

    private ResponseEntity<ApiResponse> getAll(Integer pageNumber, Integer pageSize) {
        var result = clientService.findAll(pageNumber, pageSize);

        return getResponseEntity(pageNumber, pageSize, result);
    }

    private ResponseEntity<ApiResponse> getByDepartmentId(Integer departmentId, Integer pageNumber, Integer pageSize) {
        var result = clientService.findAllByDepartmentId(departmentId, pageNumber, pageSize);

        return getResponseEntity(pageNumber, pageSize, result);
    }

    private ResponseEntity getResponseEntity(@RequestParam(name = "page", defaultValue = "0") int page, @RequestParam(name = "size", defaultValue = "50") int size, Page<Client> result) {
        var list = result.stream()
                .map(client -> buildClientDto(client))
                .sorted((c1, c2) -> Integer.compare(c2.getId(), c1.getId()))
                .collect(Collectors.toList());

        var res = new ClientsResponse(list, result.getTotalElements(), size, page);

        return new ResponseEntity<>(new ApiResponse(true, "Topilgan mijozlar!", res), HttpStatus.OK);
    }

    private ClientDTO buildClientDto(Client client) {

        String pattern = "dd/MM/yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String dateOfBirth = simpleDateFormat.format(client.getDateOfBirth());
        String refCode = null;

        if (client.getReferrerId() != null && referrerService.findById(client.getReferrerId()).isPresent()) {
            refCode = referrerService.findById(client.getReferrerId()).get().getUniqueCode();
        }

        return ClientDTO.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .middleName(client.getMiddleName())
                .dateOfBirth(dateOfBirth)
                .phone(client.getPhone())
                .gender(client.getGender())
                .referralCode(refCode)
                .departmentName(departmentService.findById(client.getDepartmentId()).get().getName())
                .analyses(client.getAnalyses())
                .price(client.getPrice())
                .isPaid(client.getIsPaid())
                .build();

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerClient(@RequestBody RegisterClientRequest request) {

        var departmentOptional = departmentService.findById(request.getDepartmentId());

        if (departmentOptional.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Бўлим топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        List<Analysis> analyses = new ArrayList<>();

        for (Integer id : request.getAnalysesIds()) {
            var analysisOptional = analysisService.findById(id);
            if (analysisOptional.isEmpty()) {
                return new ResponseEntity<>(new ApiResponse(false, "Анализ топилмади!", null), HttpStatus.BAD_REQUEST);
            }
            analyses.add(analysisOptional.get());
        }

        Integer totalPrice = analyses.stream()
                .mapToInt(a -> a.getPrice().intValue())
                .reduce(0, Integer::sum);

        var client = Client.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .dateOfBirth(Date.from(Instant.parse(request.getDateOfBirth())))
                .created(ZonedDateTime.now())
                .phone(request.getPhone())
                .gender(request.getGender())
                .referrerId(request.getReferrerId())
                .departmentId(request.getDepartmentId())
                .analyses(analyses)
                .price(totalPrice)
                .isPaid(request.getIsPaid())
                .build();

        var savedClient = clientService.save(client);

        return new ResponseEntity<>(new ApiResponse(true, "Мижоз сақланди!", savedClient), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getClientById(@PathVariable(name = "id") Integer id, @RequestParam(name = "userId") Integer userId) {

        var clientOptional = clientService.findById(id);

        if (clientOptional.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Order топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var userOptional = userService.findById(userId);

        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ApiResponse(false, "Foydalanuvchi топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var isAdmin = Utils.isAdmin(userOptional);
        var isSameDepartment = clientOptional.get().getDepartmentId() == userOptional.get().getDepartmentId();

        if (!isAdmin && !isSameDepartment) {
            return new ResponseEntity<>(new ApiResponse(false, "Siz bu orderni ocholmaysiz!", null), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(new ApiResponse(true, "Topilgan order", clientOptional.get()), HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity updateClient(@RequestBody UpdateClientRequest request) {

        var clientOptional = clientService.findById(request.getId());

        if (clientOptional.isEmpty()) {
            return new ResponseEntity<>(new ApiResponse(false, "Mijoz топилмади!", null), HttpStatus.BAD_REQUEST);
        }

        var existingClient = clientOptional.get();

        var client = Client.builder()
                .id(request.getId())
                .firstName(existingClient.getFirstName())
                .lastName(existingClient.getLastName())
                .middleName(existingClient.getMiddleName())
                .dateOfBirth(existingClient.getDateOfBirth())
                .created(existingClient.getCreated())
                .phone(existingClient.getPhone())
                .gender(existingClient.getGender())
                .referrerId(existingClient.getReferrerId())
                .departmentId(existingClient.getDepartmentId())
                .analyses(existingClient.getAnalyses())
                .price(existingClient.getPrice())
                .isPaid(request.getIsPaid())
                .result(request.getResult())
                .build();

        var savedClient = clientService.save(client);

        return new ResponseEntity<>(new ApiResponse(true, "Мижоз сақланди!", savedClient), HttpStatus.OK);
    }

}
