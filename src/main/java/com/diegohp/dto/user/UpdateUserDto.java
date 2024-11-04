package com.diegohp.dto.user;

public class UpdateUserDto extends CreateUserDto {
    private Boolean isActive;
    private String newPassword;

    public UpdateUserDto(String firstName, String lastName, Boolean isActive, String newPassword) {
        super(firstName, lastName);
        this.isActive = isActive;
        this.newPassword = newPassword;
    }

    public Boolean getActive() {
        return isActive;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
