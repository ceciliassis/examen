package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.domain.model.Customer;
import ar.com.flexibility.examen.domain.repository.CustomerRepository;
import ar.com.flexibility.examen.domain.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ProductRepository productRepository;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    public void whenCustomerValid_thenReturn200() throws Exception {
        Customer customer = new Customer(1L, "Cecilia", "cyassis@gmail.com");

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customer)))
                .andExpect(status().isOk())
                .andExpect(content().string("CUSTOMER 1 CREATED"));
    }

    @Test
    public void whenCustomerEmailEmpty_thenReturn406() throws Exception {
        Customer customer = new Customer();
        customer.setEmail("");

        mockMvc.perform(post("/customers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapper.writeValueAsString(customer)))
                .andExpect(status().isNotAcceptable())
                .andExpect(content().string("MISSING INFO"));
    }
}