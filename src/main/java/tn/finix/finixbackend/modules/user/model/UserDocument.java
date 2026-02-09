package tn.finix.finixbackend.modules.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDocument {
    private long id;
    private User user;
    private String typeDocuments;
    private String fileUrl;
    private Boolean isVerified;
    private LocalDateTime updatedAt;
    private User verificationAgent;

}
