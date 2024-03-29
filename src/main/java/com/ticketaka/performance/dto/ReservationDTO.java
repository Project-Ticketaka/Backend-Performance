package com.ticketaka.performance.dto;

import com.ticketaka.performance.domain.PrfSession;
import com.ticketaka.performance.dto.request.ReservationRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Builder
@NoArgsConstructor
public class ReservationDTO {
    private String performanceId;
    private String performanceTitle;
    private int reservationTicketCount;
    private LocalDate reservationDate;
    private String reservationTime;
    private int reservationPrice;
    private String reservationPoster;

    public ReservationDTO from(ReservationRequest request, int count, PrfSession prfSession) {
        return ReservationDTO.builder()
                .performanceId(request.getPerformanceId())
                .performanceTitle(request.getPrfTitle())
                .reservationTicketCount(count)
                .reservationDate(prfSession.getPrfSessionDate())
                .reservationTime(prfSession.getPrfSessionTime())
                .reservationPrice(request.getPrice())
                .reservationPoster(request.getPrfPoster())
                .build();
    }
}
