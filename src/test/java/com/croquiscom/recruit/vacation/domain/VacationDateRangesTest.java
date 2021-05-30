package com.croquiscom.recruit.vacation.domain;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("vacation date ranges test")
public class VacationDateRangesTest {

    private final VacationDateRanges ranges = new VacationDateRanges(Lists.newArrayList(
            new VacationDateRange(LocalDate.of(2021, 6, 1), LocalDate.of(2021, 6, 4)),
            new VacationDateRange(LocalDate.of(2021, 6, 8), LocalDate.of(2021, 6, 16))
    ));

    @DisplayName("is overlap - true")
    @Test
    public void isOverlapTrue() {
        VacationDateRange range = new VacationDateRange(LocalDate.of(2021, 5, 25), LocalDate.of(2021, 6, 4));
        assertThat(ranges.isOverlap(range)).isTrue();
    }

    @DisplayName("is overlap - true(edge)")
    @Test
    public void isOverlapTrueEdge() {
        VacationDateRange range = new VacationDateRange(LocalDate.of(2021, 5, 25), LocalDate.of(2021, 6, 1));
        assertThat(ranges.isOverlap(range)).isTrue();
    }

    @DisplayName("is overlap - false")
    @Test
    public void isOverlapFalse() {
        VacationDateRange range = new VacationDateRange(LocalDate.of(2021, 5, 1), LocalDate.of(2021, 5, 4));
        assertThat(ranges.isOverlap(range)).isFalse();
    }

}
