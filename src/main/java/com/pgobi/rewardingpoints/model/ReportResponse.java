package com.pgobi.rewardingpoints.model;
import lombok.*;

@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class ReportResponse {
    private Long customerId;
    private Long totalRewards;
}
