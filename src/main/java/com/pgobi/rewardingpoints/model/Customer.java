package com.pgobi.rewardingpoints.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Customer {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDateTime registrationDate;
    private LocalDateTime updateDate;
}
