package com.cucumber.helpers;

import com.cucumber.dto_classes.BookingDatesDto;
import com.cucumber.dto_classes.BookingItemDetailsDto;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;


public class BookingBuildersHelper {
    private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final Faker faker = new Faker();

    public static BookingItemDetailsDto buildValidBooking() {
        LocalDate checkinDate = faker.date().future(30, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate checkoutDate = checkinDate.plusDays(faker.number().numberBetween(1, 7));

        return BookingItemDetailsDto.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .totalprice(faker.number().randomDigit())
                .depositpaid(true)
                .bookingdates(BookingDatesDto.builder()
                        .checkin(dateFormatter.format(checkinDate))
                        .checkout(dateFormatter.format(checkoutDate))
                        .build())
                .additionalneeds("Breakfast")
                .build();
    }

    public static BookingItemDetailsDto buildPartialBooking() {
        return BookingItemDetailsDto.builder()
                .firstname(faker.name().firstName())
                .lastname(faker.name().lastName())
                .build();
    }
}
