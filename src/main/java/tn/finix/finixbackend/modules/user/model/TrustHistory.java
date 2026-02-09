package tn.finix.finixbackend.modules.user.model;


import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TrustHistory {
    private String id;
    private User user;
    private Integer oldScore;
    private Integer newScore;
    private String scoreUpdateReason;
    private LocalDateTime scoreUpdateDate;
}
