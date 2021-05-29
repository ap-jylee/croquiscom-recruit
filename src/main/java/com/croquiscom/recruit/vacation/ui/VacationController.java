package com.croquiscom.recruit.vacation.ui;

import com.croquiscom.recruit.vacation.application.VacationService;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("vacations")
@RequiredArgsConstructor
public class VacationController {

    private final VacationService vacationService;

    @PostMapping
    public ResponseEntity createVacation(
            Authentication authentication,
            @RequestBody VacationRequest vacationRequest) {
        VacationResponse vacationResponse = vacationService.createVacation(vacationRequest.setMemberIdFromAuthentication(authentication));
        return ResponseEntity.created(URI.create("/vacations/" + vacationResponse.getVacationId())).body(vacationResponse);
    }

}
