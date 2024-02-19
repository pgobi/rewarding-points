package com.pgobi.rewardingpoints.model;


import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class OrderRequest {
    private List<Long> productIds;
    private List<Integer> quantities;

}


