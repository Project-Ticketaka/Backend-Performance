package com.ticketaka.performance.feign;


import com.ticketaka.performance.dto.ReservationDTO;
import com.ticketaka.performance.dto.response.BaseResponse;
import feign.HeaderMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name="ReservationFeignClient", url="${member.url}:"+"${member.port}", path = "/reservation")
public interface ReservationFeignClient {
    @PostMapping(value = "/create")
    BaseResponse createReservation(@RequestHeader("x-istio-jwt-sub") String sub, @RequestBody ReservationDTO reservationDTO);
}
