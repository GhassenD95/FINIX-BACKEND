package tn.finix.finixbackend.modules.credit.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreditDocument {

    private Long id;

    private CreditRequest creditRequest;

    private CreditDocumentType type;
    private String fileUrl;
    private boolean verified;

    private LocalDateTime uploadedAt;
    private String verificationComment;
}
