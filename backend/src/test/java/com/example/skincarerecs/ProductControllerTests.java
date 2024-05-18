package com.example.skincarerecs;

import com.example.skincarerecs.config.JwtService;
import com.example.skincarerecs.controller.ProductController;
import com.example.skincarerecs.controller.dto.ProductRatingSummaryDto;
import com.example.skincarerecs.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
@AutoConfigureMockMvc(addFilters = false)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private JwtService jwtService;
    @Test
    public void testGetProductsByTags() throws Exception {
        ProductRatingSummaryDto summary1 = new ProductRatingSummaryDto();
        ProductRatingSummaryDto summary2 = new ProductRatingSummaryDto();
        List<ProductRatingSummaryDto> summaries = Arrays.asList(summary1, summary2);

        when(productService.getProductsByTags()).thenReturn(summaries);

        mockMvc.perform(get("/api/v1/products/getByUserTags"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
