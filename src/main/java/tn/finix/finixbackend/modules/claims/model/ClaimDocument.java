package tn.finix.finixbackend.modules.claims.model;

import lombok.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ClaimDocument {
    private long id;
    private ClaimDocumentType documentType;
    private String fileUrl;
    private LocalDateTime uploadedAt;

    private Claim claim;
}
