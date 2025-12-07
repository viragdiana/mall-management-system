package com.example.mallmanagementapplication.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "security_staff")
public class SecurityStaff extends Staff {

    @NotBlank
    @Size(min = 3, max = 50)
    private String badgeNo;

    public SecurityStaff() {}

    public SecurityStaff(String name, String badgeNo) {
        super(name);
        this.badgeNo = badgeNo;
    }

    public String getBadgeNo() { return badgeNo; }
    public void setBadgeNo(String badgeNo) { this.badgeNo = badgeNo; }
}
