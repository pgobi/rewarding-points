package com.pgobi.rewardingpoints.model;

import lombok.*;


@Data
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private Long orderid;
    private String cartNumber;
}


