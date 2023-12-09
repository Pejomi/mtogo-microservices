package dk.pejomi.menuservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.dto.MenuItemDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
public class MenuControllerIT {

    private final MockMvc mockMvc;
    private final String URL = "/api/menu";

    @Autowired
    public MenuControllerIT(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void should_return_ok_when_getMenuById_given_valid_id() throws Exception {
        // Arrange
        Long id = 1L;

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(id));
    }

    @Test
    void should_return_not_found_when_getMenuById_given_invalid_id() throws Exception {
        // Arrange
        Long id = 999L;

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL +"/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void should_return_bad_request_when_getMenuById_given_invalid_id() throws Exception {
        // Arrange
        String id = "abc";

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void should_return_ok_when_getMenuByRestaurantId_given_valid_id() throws Exception {
        // Arrange
        Long id = 1L;

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/restaurant/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[0].restaurantId").value(id));
    }

    @Test
    void should_return_not_found_when_getMenuByRestaurantId_given_invalid_id() throws Exception {
        // Arrange
        Long id = 999L;

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/restaurant/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void should_return_bad_request_when_getMenuByRestaurantId_given_invalid_id() throws Exception {
        // Arrange
        String id = "abc";

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.get(URL + "/restaurant/" + id))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void should_return_ok_when_createMenu_given_valid_menu() throws Exception {
        // Arrange
        List<MenuItemDto> menuItemDtoList = List.of(MenuItemDto.builder()
                .name("Cake")
                .price(40.0)
                .build());

        Long restaurantId = 1L;

        MenuDto menuDto = MenuDto.builder()
                .restaurantId(restaurantId)
                .menuItemsDto(menuItemDtoList)
                .build();

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpectAll(
                        MockMvcResultMatchers.jsonPath("$.restaurantId").value(restaurantId),
                        MockMvcResultMatchers.jsonPath("$.menuItemsDto.[0].name").value("Cake"),
                        MockMvcResultMatchers.jsonPath("$.menuItemsDto.[0].price").value(40.0)
                );
    }

    @Test
    void should_return_bad_request_when_createMenu_given_invalid_menu() throws Exception {
        // Arrange
        List<MenuItemDto> menuItemDtoList = List.of(MenuItemDto.builder()
                .name("Cake")
                .price(40.0)
                .build());

        Long restaurantId = 1L;

        MenuDto menuDto = MenuDto.builder()
                // no restaurantId set
                .menuItemsDto(menuItemDtoList)
                .build();

        // Act & Assert
        mockMvc
                .perform(MockMvcRequestBuilders.post(URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuDto)))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
