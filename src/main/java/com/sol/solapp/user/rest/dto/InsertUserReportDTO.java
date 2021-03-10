package com.sol.solapp.user.rest.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserReportDTO {
    long insertedCount;
    long updatedCount;
    long failedCount;
    long totalCount;
}
