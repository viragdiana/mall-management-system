package com.example.mallmanagementapplication.model;

/**
 * Personal de securitate – extinde Staff.
 */
public class SecurityStaff extends Staff {
    private String badgeNo;

    public SecurityStaff() {super(); }

    public SecurityStaff(/*String id,*/ String name, String badgeNo) {
        super(name);
        this.badgeNo = badgeNo;
    }

    public String getBadgeNo() { return badgeNo; }
    public void setBadgeNo(String badgeNo) { this.badgeNo = badgeNo; }
}
