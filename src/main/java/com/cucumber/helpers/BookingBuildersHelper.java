package com.cucumber.helpers;

import com.cucumber.dto_classes.BookingDatesDto;
import com.cucumber.dto_classes.BookingItemDetailsDto;

public class BookingBuildersHelper {

    public static BookingItemDetailsDto buildValidBooking() {
        return BookingItemDetailsDto.builder()
                .firstname("Jim")
                .lastname("Brown")
                .totalprice(111)
                .depositpaid(true)
                .bookingdates(BookingDatesDto.builder()
                        .checkin("2018-01-01")
                        .checkout("2019-01-01")
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }
}
