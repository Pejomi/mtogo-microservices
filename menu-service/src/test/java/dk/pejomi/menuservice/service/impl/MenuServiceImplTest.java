package dk.pejomi.menuservice.service.impl;

import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.dto.MenuItemDto;
import dk.pejomi.menuservice.model.Menu;
import dk.pejomi.menuservice.model.MenuItem;
import dk.pejomi.menuservice.repository.MenuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MenuServiceImplTest {

    @Mock
    private MenuRepository menuRepository;

    @InjectMocks
    private MenuServiceImpl menuService;

    private Menu menu;
    private MenuDto menuDTO;

    private List<MenuItem> menuItems;
    private List<MenuItemDto> menuItemDtos;

    @BeforeEach
    void setUp() {
        menuDTO = MenuDto.builder()
                .id(1L)
                .restaurantId(1L)
                .build();

        menu = Menu.builder()
                .id(1L)
                .restaurantId(1L)
                .build();

        menuItems = List.of(
                MenuItem.builder()
                        .id(1L)
                        .name("Pizza Margherita")
                        .price(100)
                        .menu(menu)
                        .build(),
                MenuItem.builder()
                        .id(2L)
                        .name("Pizza Pepperoni")
                        .price(100)
                        .menu(menu)
                        .build()
        );

        menuItemDtos = List.of(
                MenuItemDto.builder()
                        .id(1L)
                        .name("Pizza Margherita")
                        .price(100)
                        .build(),
                MenuItemDto.builder()
                        .id(2L)
                        .name("Pizza Pepperoni")
                        .price(100)
                        .build()
        );
    }

    @Test
    void should_return_menu_when_creating_menu() {
        //Arrange
        when(menuRepository.save(any(Menu.class))).thenReturn(menu);

        //Act
        MenuDto actual = menuService.createMenu(menuDTO);

        //Assert
        assertEquals(menuDTO.toString(), actual.toString());
    }

    @Test
    void should_catch_exception_when_creating_menu() {
        //Arrange
        when(menuRepository.save(any(Menu.class))).thenThrow(DataIntegrityViolationException.class);
        //Act
        RuntimeException exception = assertThrows(RuntimeException.class, () -> menuService.createMenu(menuDTO));

        //Assert
        assertEquals("Error occurred while saving menu", exception.getMessage());
    }

    @Test
    void should_return_menu_when_getting_all_menus_by_restaurant_id() {
        //Arrange
        List<Menu> menus = List.of(menu);
        when(menuRepository.findAllByRestaurantId(any(Long.class))).thenReturn(menus);

        //Act
        List<MenuDto> actual = menuService.getAllMenusByRestaurantId(menuDTO.getRestaurantId());

        //Assert
        assertEquals(menuDTO.toString(), actual.get(0).toString());
    }

    @Test
    void should_catch_runtime_exception_when_getting_all_menus_by_restaurant_id() {
        //Arrange
        when(menuRepository.findAllByRestaurantId(any(Long.class))).thenReturn(List.of());

        //Act
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> menuService.getAllMenusByRestaurantId(menuDTO.getRestaurantId()));

        //Assert
        assertEquals("No menus found", exception.getMessage());
    }

    @Test
    void should_return_menu_when_getting_menu_by_id() {
        //Arrange
        when(menuRepository.findById(any(Long.class))).thenReturn(java.util.Optional.ofNullable(menu));

        //Act
        MenuDto actual = menuService.getMenuById(menu.getId());

        //Assert
        assertEquals(menuDTO.toString(), actual.toString());
    }


    @Test
    void should_catch_runtime_exception_when_getting_menu_by_id() {
        //Arrange
        when(menuRepository.findById(any(Long.class))).thenReturn(java.util.Optional.empty());

        //Act
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> menuService.getMenuById(menu.getId()));

        //Assert
        assertEquals("Menu not found", exception.getMessage());
    }

}