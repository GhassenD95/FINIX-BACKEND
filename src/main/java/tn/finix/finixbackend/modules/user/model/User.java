package tn.finix.finixbackend.modules.user.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User {
    //comment
    private long id;
    private String name;
    private String lastName;
    private String email;
    private String password;
    private String telephone;
    private String address;
    private RoleType role;
    private StatusType status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Integer profileCompletion = 0; //0..100


}
