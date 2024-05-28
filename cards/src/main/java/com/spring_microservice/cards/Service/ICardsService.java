package com.spring_microservice.cards.Service;

import com.spring_microservice.cards.dto.CardsDto;

public interface ICardsService {

    /**
     *
     * @param mobileNumber - Mobile NUmber of the customer
     */
    void createCard(String mobileNumber);

    /**
     *
     * @param mobileNumber - Mobile Number of the customer
     * @return Card Details based on the given mobile number
     */
    CardsDto fetchCard(String mobileNumber);

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of the card details was success
     */
    boolean updateCard(CardsDto cardsDto);

    /**
     *
     * @param mobileNumber - Mobile Number of Customer
     * @return boolean indicating if the delete operation was success
     */
    boolean deleteCard(String mobileNumber);
}
