package com.cucumber.helpers;

import com.cucumber.dto_classes.BookingItemDetailsDto;
import org.junit.Assert;

public class AssertsHelper {

    public static void verifyDetailsFor(BookingItemDetailsDto expectedBooking, BookingItemDetailsDto createdBooking) {
        Assert.assertEquals(expectedBooking.getFirstname(), createdBooking.getFirstname());
        Assert.assertEquals(expectedBooking.getLastname(), createdBooking.getLastname());
        Assert.assertEquals(expectedBooking.getTotalprice(), createdBooking.getTotalprice());
        Assert.assertEquals(expectedBooking.isDepositpaid(), createdBooking.isDepositpaid());
        Assert.assertEquals(expectedBooking.getBookingdates().getCheckin(), createdBooking.getBookingdates().getCheckin());
        Assert.assertEquals(expectedBooking.getBookingdates().getCheckout(), createdBooking.getBookingdates().getCheckout());
        Assert.assertEquals(expectedBooking.getAdditionalneeds(), createdBooking.getAdditionalneeds());
    }
}
