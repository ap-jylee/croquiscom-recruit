package com.croquiscom.recruit.vacation.ui;

import com.croquiscom.recruit.vacation.application.VacationService;
import com.croquiscom.recruit.vacation.domain.VacationRepository;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("vacations")
@RequiredArgsConstructor
public class VacationController {

    private final VacationService vacationService;

    @PostMapping
    public ResponseEntity<VacationResponse> createVacation(
            Authentication authentication,
            @RequestBody VacationRequest vacationRequest) {
        VacationResponse vacationResponse = vacationService.createVacation(vacationRequest.setMemberIdFromAuthentication(authentication));
        return ResponseEntity.created(URI.create("/vacations/" + vacationResponse.getVacationId())).body(vacationResponse);
    }

    @GetMapping
    public ResponseEntity<List<VacationResponse>> getVacations(
            Authentication authentication) {
        return ResponseEntity.ok().body(vacationService.findAllByMemberId(authentication.getName()));
    }

}
