package org.rbc.controller;


import org.junit.jupiter.api.Test;
import org.rbc.entity.Holiday;
import org.rbc.exception.HolidayException;
import org.rbc.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
// Configures MockMvc automatically
@AutoConfigureMockMvc
class HolidayControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    public static HolidayRepository holidayRepository;



    @Test
    void when_getListOfFederalHolidays_byCountry() throws Exception {

        Holiday holidayA=new Holiday("Chirstmas Day","Canada","12-25",null);
        Holiday holidayB=new Holiday("Canada Day","Canada","07-01",null);
        List<Holiday> holidayTestList=new ArrayList<>();
        holidayTestList.add(holidayA);holidayTestList.add(holidayB);
        Optional<List<Holiday>> ophol=Optional.of(holidayTestList);

        when(holidayRepository.findAllHolidayByCountry(any(String.class))).thenReturn(ophol);

        mockMvc.perform(get("/api/fedhol/country-list/{name}","canada")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

    }

    @Test
    void when_ADD_Holiday_Throws_error() throws Exception {
        String jsonPayload = """
            {
              "name": "Chirstmas Day",
              "date": "12-25",
              "country": "USA"
            }
            """;

        Holiday holiday=new Holiday("Chirstmas Day","12-25","USA",null);
        Optional<Holiday> ophol=Optional.of(holiday);
        when(holidayRepository.findHoliday(any(String.class), any(String.class))).thenReturn(ophol);
        mockMvc.perform(post("/api/fedhol/add-Holiday")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof HolidayException));
        ;
    }


    @Test
    void when_invalid_request_error() throws Exception {
        String jsonPayload = """
            {
              "name": "Chirstmas Day",
              "date": "12//25",
              "country": "123"
            }
            """;

       /* Holiday holiday=new Holiday("Chirstmas Day","12-25","USA",null);
        Optional<Holiday> ophol=Optional.of(holiday);
        when(holidayRepository.findHoliday(any(String.class), any(String.class))).thenReturn(ophol);*/
        mockMvc.perform(post("/api/fedhol/add-Holiday")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof MethodArgumentNotValidException));
        ;
    }

    @Test
    void when_ADD_SUCCESSFUL() throws Exception {
        String jsonPayload = """
            {
              "name": "Chirstmas Day",
              "date": "12-25",
              "country": "USA"
            }
            """;

        Holiday holiday=new Holiday("Chirstmas Day","USA","12-25",null);
        Optional<Holiday> ophol=Optional.empty();
        when(holidayRepository.findHoliday(any(String.class), any(String.class))).thenReturn(ophol);
        when(holidayRepository.save(any(Holiday.class))).thenReturn(holiday);
        mockMvc.perform(post("/api/fedhol/add-Holiday")
                        .content(jsonPayload)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.date").value("December,25"));

    }
}