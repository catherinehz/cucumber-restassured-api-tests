package com.cucumber.filter_params;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingFilters {
    private String firstname;
    private String lastname;
    private String checkin;
    private String checkout;
}
