package ar.com.flexibility.examen.app.rest;


import ar.com.flexibility.examen.domain.model.Product;
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
@WebMvcTest(ProductController.class)

public class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private ProductRepository productRepository;

    ObjectMapper mapper = new ObjectMapper ();

    @Test
    public void whenProductValid_thenReturn200() throws Exception {
        Product product = new Product ( 1L, "bolo", "coco", 10 );

        when ( productRepository.save ( any ( Product.class ) ) ).thenReturn ( product );

        mockMvc.perform ( post ( "/products" )
                .contentType ( MediaType.APPLICATION_JSON )
                .content ( mapper.writeValueAsString ( product ) ) )
                .andExpect ( status ().isOk () )
                .andExpect ( content ().string ( "PRODUCT 1 CREATED" ) );

    }


    @Test
    public void whenProductDescriptionIsEmpty_thenReturn406() throws Exception {

        Product product = new Product ();
        product.setDescription ( "" );

        mockMvc.perform ( post ( "/products" )
                .contentType ( MediaType.APPLICATION_JSON )
                .content ( mapper.writeValueAsString ( product ) ) )
                .andExpect ( status ().isNotAcceptable () )
                .andExpect ( content ().string ( "MISSING INFO" ) );
    }

}
