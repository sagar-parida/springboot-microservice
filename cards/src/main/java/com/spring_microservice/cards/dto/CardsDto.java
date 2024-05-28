package com.spring_microservice.cards.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Schema(
        name = "Cards",
        description = "Schema of the Card Information"
)
@Data
public class CardsDto {

    @NotEmpty(message = "Mobile number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message="Mobile Number must be 10 digits.")
    @Schema(
            description = "Mobile Number of Customer", example = "1234567890"
    )
    private String mobileNumber;

    @NotEmpty(message = "Card number cannot be null or empty")
    @Pattern(regexp = "(^$|[0-9]{12})", message="Mobile Number must be 12 digits.")
    @Schema(
            description = "Mobile Number of Customer", example = "1"
    )
    private String cardNumber;

    @NotEmpty(message = "Card Type cannot be null or empty")
    @Schema(
            description = "Type of card", example = "Credit Card"
    )
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    @Schema(
            description = "Total amount available against a card", example = "10000"
    )
    private int totalLimit;

    @PositiveOrZero(message = "Total amount used should be greater than or equal to zero")
    @Schema(
            description = "Total amount used by a card", example = "1000"
    )
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be greater than or equal to zero")
    @Schema(
            description = "Total available amoutn against a card", example = "90000"
    )
    private int availableAmount;

}
