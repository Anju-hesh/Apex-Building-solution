package com.ijse.apexbuildingsolution.apex_building_solution.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAccount {
    private String userId;
    private String fullName;
    private String userName;
    private String password;
    private String employeeId;
    private String email;
}
