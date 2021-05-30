package com.croquiscom.recruit.vacation.application;

import com.croquiscom.recruit.member.domain.MemberSetting;
import com.croquiscom.recruit.member.domain.MemberSettingRepository;
import com.croquiscom.recruit.vacation.domain.Vacation;
import com.croquiscom.recruit.vacation.domain.VacationDateRange;
import com.croquiscom.recruit.vacation.domain.VacationDateRanges;
import com.croquiscom.recruit.vacation.domain.VacationRepository;
import com.croquiscom.recruit.vacation.dto.VacationRequest;
import com.croquiscom.recruit.vacation.dto.VacationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VacationService {

    private final VacationRepository vacationRepository;
    private final MemberSettingRepository memberSettingRepository;

    @Transactional
    public VacationResponse createVacation(String memberId, VacationRequest request) {
        VacationDateRanges vacationDateRanges = new VacationDateRanges(findAllByMemberId(memberId).stream()
                .filter(VacationResponse::isDateRangeTarget)
                .map(VacationDateRange::new)
                .collect(Collectors.toList()));
        vacationDateRanges.checkOverlap(request);
        MemberSetting persistMemberSetting = memberSettingRepository.findById(memberId).orElseThrow();
        persistMemberSetting.useVacationDays(request);
        Vacation persistVacation = vacationRepository.save(request.toVacation(memberId));
        return VacationResponse.of(persistVacation)
                .setRemainingUsedDays(persistMemberSetting);
    }

    public List<VacationResponse> findAllByMemberId(String memberId) {
        return vacationRepository.findAllByMemberId(memberId, Sort.by(Sort.Direction.DESC, "createdDate"))
                .stream()
                .map(VacationResponse::of)
                .collect(Collectors.toList());
    }

    @Transactional
    public VacationResponse cancelVacation(String memberId, Long vacationId) {
        Vacation persistVacation = vacationRepository.findById(vacationId).orElseThrow();
        MemberSetting persistMemberSetting = memberSettingRepository.findById(memberId).orElseThrow();
        persistMemberSetting.cancelVacationDays(persistVacation);
        return VacationResponse.of(persistVacation.cancel())
                .setRemainingUsedDays(persistMemberSetting);

    }

}
