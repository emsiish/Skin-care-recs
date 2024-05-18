package com.example.skincarerecs;

import com.example.skincarerecs.controller.dto.ProductRatingSummaryDto;
import com.example.skincarerecs.entity.Product;
import com.example.skincarerecs.entity.Tag;
import com.example.skincarerecs.entity.User;
import com.example.skincarerecs.mapper.ProductMapper;
import com.example.skincarerecs.repository.ProductRatingRepository;
import com.example.skincarerecs.repository.ProductRepository;
import com.example.skincarerecs.repository.UserRepository;
import com.example.skincarerecs.service.impl.ProductServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ProductServiceImplTests {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductRatingRepository productRatingRepository;

    @Mock
    private ProductMapper productMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    AutoCloseable openMocks;

    @BeforeEach
    public void setUp() {
        openMocks =  MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetProductsByTags() {
        String userEmail = "test@example.com";
        User user = new User();
        user.setEmail(userEmail);
        Tag tag1 = new Tag();
        tag1.setName("tag1");
        Tag tag2 = new Tag();
        tag2.setName("tag2");
        user.setTags(Arrays.asList(tag1, tag2));

        Product product1 = new Product();
        product1.setId(1L);
        Product product2 = new Product();
        product2.setId(2L);

        ProductRatingSummaryDto productRatingSummaryDto1 = new ProductRatingSummaryDto();
        productRatingSummaryDto1.setId(1L);
        ProductRatingSummaryDto productRatingSummaryDto2 = new ProductRatingSummaryDto();
        productRatingSummaryDto2.setId(2L);

        Authentication authentication = mock(Authentication.class);
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(authentication.getName()).thenReturn(userEmail);
        SecurityContextHolder.setContext(securityContext);

        when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(user));
        when(productRepository.findByTags(Arrays.asList("tag1", "tag2"))).thenReturn(Arrays.asList(product1, product2));
        when(productMapper.mapToProductRatingSummaryDtoList(Arrays.asList(product1, product2))).thenReturn(Arrays.asList(productRatingSummaryDto1, productRatingSummaryDto2));
        when(productRatingRepository.getAverageRatingByProductId(1L)).thenReturn(4.5);
        when(productRatingRepository.getAverageRatingByProductId(2L)).thenReturn(3.8);
        when(productRatingRepository.countByProductId(1L)).thenReturn(10);
        when(productRatingRepository.countByProductId(2L)).thenReturn(8);

        List<ProductRatingSummaryDto> result = productService.getProductsByTags();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(1L, result.get(0).getId());
        assertEquals(2L, result.get(1).getId());
        assertEquals(4.5, result.get(0).getAverageRating());
        assertEquals(3.8, result.get(1).getAverageRating());
        assertEquals(10, result.get(0).getCount());
        assertEquals(8, result.get(1).getCount());

        verify(userRepository, times(1)).findByEmail(userEmail);
        verify(productRepository, times(1)).findByTags(Arrays.asList("tag1", "tag2"));
        verify(productRatingRepository, times(1)).getAverageRatingByProductId(1L);
        verify(productRatingRepository, times(1)).getAverageRatingByProductId(2L);
        verify(productRatingRepository, times(1)).countByProductId(1L);
        verify(productRatingRepository, times(1)).countByProductId(2L);
    }

    @AfterEach
    public void tearDown() throws Exception {
        openMocks.close();
    }
}
