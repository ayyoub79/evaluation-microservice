package com.emsi.pfa.restclient;


import com.emsi.pfa.dto.PassengerDTO;

public interface AccountRestClient {
    PassengerDTO getPassengerByPublicId(String passengerPublicId);
}
