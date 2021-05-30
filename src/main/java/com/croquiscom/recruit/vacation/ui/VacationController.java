package com.croquiscom.recruit.vacation.ui;

import com.croquiscom.recruit.vacation.application.VacationService;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
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
        VacationResponse vacationResponse = vacationService.createVacation(authentication.getName(), vacationRequest);
        return ResponseEntity.created(URI.create("/vacations/" + vacationResponse.getVacationId())).body(vacationResponse);
    }

    @GetMapping
    public ResponseEntity<List<VacationResponse>> getVacations(
            Authentication authentication) {
        return ResponseEntity.ok().body(vacationService.findAllByMemberId(authentication.getName()));
    }

    @PutMapping("cancel/{id}")
    public ResponseEntity<VacationResponse> cancelVacation(
            Authentication authentication,
            @PathVariable("id") Long vacationId) {
        return ResponseEntity.ok().body(vacationService.cancelVacation(authentication.getName(), vacationId));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity handleIllegalArgsException(IllegalStateException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
