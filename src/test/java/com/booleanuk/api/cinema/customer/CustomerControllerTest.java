package com.booleanuk.api.cinema.customer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private CustomerRepository repository;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testCreateCustomer() throws Exception {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("Chris Wolstenholme");
        customer.setPhone("1829473");
        customer.setEmail("chris@muse.mu");
        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        customer.setCreatedAt(now);
        customer.setUpdatedAt(now);

        given(repository.save(ArgumentMatchers.any())).willReturn(customer);

        ResultActions response = mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customer))
        );

        response.andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
