package com.global.university.semester;

import com.global.university.common.DateRangeEntity;
import com.global.university.customValidation.ValidDateRange;
import com.global.university.validationGroup.Default;
import com.global.university.validationGroup.OnUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

@ValidDateRange(groups = Default.class)
public record SemesterRequest(
        @NotNull(groups = OnUpdate.class, message = "L id required for the update operation")
        Integer id,
        @NotBlank(message = "name is required", groups = Default.class)
        @NotNull(message = "name is required", groups = Default.class)
        String name,
        String description,
        @NotNull(message = "levelId is required", groups = Default.class)
        Integer levelId,
        @NotNull(message = "start date is required", groups = Default.class)
        LocalDate startDate,
        @NotNull(message = "end date is required", groups = Default.class)
        LocalDate endDate

) implements DateRangeEntity {


    @Override
    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return endDate;
    }
}
