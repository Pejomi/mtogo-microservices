package dk.pejomi.menuservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MenuController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class MenuControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MenuService menuService;

    @Autowired
    private ObjectMapper objectMapper;

    private MenuDto menuDto;

    private List<MenuDto> menuDtoList;

    @BeforeEach
    public void init() {
        menuDto = MenuDto.builder()
                .restaurantId(1L)
                .build();

        MenuDto menuDto2 = MenuDto.builder()
                .restaurantId(1L)
                .build();

        menuDtoList = List.of(menuDto, menuDto2);
    }

    @Test
    void should_return_ok_when_getMenuById() throws Exception {
        when(menuService.getMenuById(any())).thenReturn(menuDto);

        ResultActions resultActions = mockMvc.perform(get("/api/menu/1"));

        resultActions.andExpect(status().isOk());

        MenuDto actual = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), MenuDto.class);

        assertEquals(menuDto, actual);
    }

    @Test
    void should_return_not_found_when_getMenuById() throws Exception {
        when(menuService.getMenuById(any())).thenThrow(new EntityNotFoundException());

        ResultActions resultActions = mockMvc.perform(get("/api/menu/1"));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void should_return_ok_when_getMenuByRestaurantId() throws Exception {
        when(menuService.getAllMenusByRestaurantId(any())).thenReturn(menuDtoList);

        ResultActions resultActions = mockMvc.perform(get("/api/menu/restaurant/1"));

        resultActions.andExpect(status().isOk());

        List actual = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), List.class);

        assertEquals(menuDtoList.size(), actual.size());
    }

    @Test
    void should_return_not_found_when_getMenuByRestaurantId() throws Exception {
        when(menuService.getAllMenusByRestaurantId(any())).thenThrow(new EntityNotFoundException());

        ResultActions resultActions = mockMvc.perform(get("/api/menu/restaurant/1"));

        resultActions.andExpect(status().isNotFound());
    }

    @Test
    void should_return_ok_when_createMenu() throws Exception {
        when(menuService.createMenu(any())).thenReturn(menuDto);

        ResultActions resultActions = mockMvc.perform(post("/api/menu")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(menuDto)));

        resultActions.andExpect(status().isOk());

        MenuDto actual = objectMapper.readValue(resultActions.andReturn().getResponse().getContentAsString(), MenuDto.class);

        assertEquals(menuDto, actual);
    }

    @Test
    void should_return_bad_request_when_createMenu() throws Exception {
        when(menuService.createMenu(any())).thenThrow(new RuntimeException());

        ResultActions resultActions = mockMvc.perform(post("/api/menu")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(menuDto)));

        resultActions.andExpect(status().isBadRequest());
    }

}