package com.cucumber.dto_classes;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.cucumber.core.internal.com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookingIdDto {
    @JsonProperty("bookingid")
    private int bookingid;
}
