package com.croquiscom.recruit.vacation.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Long> {

    List<Vacation> findAllByMemberId(String memberId);

}
