package com.ticketaka.performance.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ticketaka.performance.dto.request.ReservationRequest;
import com.ticketaka.performance.dto.request.WaitingListRequest;
import com.ticketaka.performance.service.ReservationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationControllerTest {
    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ReservationService reservationService;

    @Test
    void 예약_가능여부_확인() throws Exception {
        Map<String,String> header = new HashMap<>();
        header.put("memberId","1");
        String content = objectMapper.writeValueAsString(new WaitingListRequest(2, 3));
        mvc.perform(post("/performance/rsv/check")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 예약_생성_성공() throws Exception {
        Map<String,String> header = new HashMap<>();
        header.put("memberId","1");
        WaitingListRequest request = new WaitingListRequest(2, 3);
        reservationService.insertUserInWaitingList(header,request);
        String content = objectMapper.writeValueAsString(new ReservationRequest(
                "user1@email.com",
                "PF132236",
                "http://www.kopis.or.kr/upload/pfmPoster/PF_PF132236_160704_142630.gif",
                2,
                10000));
        mvc.perform(post("/performance/rsv/create")
                        .header("header",header)
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }
}