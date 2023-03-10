package com.ticketaka.performance.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TicketPriceDTO {
    private String seatType;
    private int price;

    public List<TicketPriceDTO> from(String prfTicketPrice) {
        if(prfTicketPrice == null) {
            return null;
        }
        String[] seats = prfTicketPrice.split(", ");
        List<TicketPriceDTO> ticketPriceDTOS = new ArrayList<>();
        Arrays.stream(seats).forEach(seat -> {
            String [] s = seat.split("\\s");
            if(s.length > 1) {
                ticketPriceDTOS.add(new TicketPriceDTO(s[0],Integer.parseInt(s[1].replaceAll("[^0-9]",""))));
            } else {
                ticketPriceDTOS.add(new TicketPriceDTO(s[0],0));
            }
        });

        return ticketPriceDTOS;
    }
}
