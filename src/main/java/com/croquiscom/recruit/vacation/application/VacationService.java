package com.croquiscom.recruit.vacation.application;

import com.croquiscom.recruit.vacation.domain.Vacation;
import com.croquiscom.recruit.vacation.domain.VacationRepository;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationRepository vacationRepository;

    @Transactional
    public VacationResponse createVacation(VacationRequest request) {
        Vacation persistVacation = vacationRepository.save(request.toVacation());
        return VacationResponse.of(persistVacation);
    }

    public List<VacationResponse> findAllByMemberId(String memberId) {
        return vacationRepository.findAllByMemberId(memberId)
                .stream()
                .map(VacationResponse::of)
                .collect(Collectors.toList());
    }

}
