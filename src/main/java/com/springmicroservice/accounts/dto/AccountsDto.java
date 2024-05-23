package com.springmicroservice.accounts.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccountsDto {

    @NotEmpty(message = "Account number can not be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private Long accountNumber;

    @NotEmpty(message = "Account Type cannot be null or empty")
    private String accountType;

    @NotEmpty(message = "Branch Address cannot be null or empty")
    private String branchAddress;
}
