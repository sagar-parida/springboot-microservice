package com.springmicroservice.accounts.controller;

import com.springmicroservice.accounts.constants.AccountsConstants;
import com.springmicroservice.accounts.dto.CustomerDto;
import com.springmicroservice.accounts.dto.ResponseDto;
import com.springmicroservice.accounts.service.IAccountsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "CRUD REST API ENDPOINTS FOR ACCOUNTS",
        description = "CREATE READ UPDATE DELETE"
)
@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
public class AccountsController {

    private IAccountsService iAccountsService;

    @Operation(
            summary = "Create Account",
            description = "API to create new customer and account"
    )
    @ApiResponse(
            responseCode = "201",
            description = "HTTP Status created"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createAccount(@Valid @RequestBody CustomerDto customerDto){
        iAccountsService.createAccount(customerDto);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(AccountsConstants.STATUS_201, AccountsConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Fetch Account Details",
            description = "API to fetch customer and account details by providing the mobile number"
    )
    @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
    )
    @GetMapping("/fetch")
    public ResponseEntity<CustomerDto> fetchAccountDetails(
        @RequestParam
        @Pattern(regexp =  "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits")
        String mobileNumber
    ){
        CustomerDto customerDto = iAccountsService.fetchAccount(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK).body(customerDto);
    }

    @Operation(
            summary = "Update Account Details",
            description = "API to Update customer and account details by providing the account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateAccountDetails(@Valid @RequestBody CustomerDto customerDto){
        boolean isUpdated = iAccountsService.updateAccount(customerDto);
        if(isUpdated){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    AccountsConstants.STATUS_200,
                    AccountsConstants.MESSAGE_200
            ));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(
                    AccountsConstants.STATUS_500,
                    AccountsConstants.MESSSAGE_500
            ));
        }
    }

    @Operation(
            summary = "Delete Account Details",
            description = "API to Delete customer and account details by providing the account number"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error"
            )
    })
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteAccount(
        @RequestParam
        @Pattern(regexp =  "(^$|[0-9]{10})", message = "Mobile Number should be 10 digits")
        String mobileNumber
    ){
        boolean isDeleted = iAccountsService.deleteAccount(mobileNumber);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto(
                    AccountsConstants.STATUS_200,
                    AccountsConstants.MESSAGE_200
            ));
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto(
                            AccountsConstants.STATUS_500,
                            AccountsConstants.MESSSAGE_500
            ));
        }
    }
}
