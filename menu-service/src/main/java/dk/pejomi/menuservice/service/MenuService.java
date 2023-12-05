package dk.pejomi.menuservice.service;

import dk.pejomi.menuservice.dto.MenuDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService {

    MenuDto createMenu(MenuDto menuDTO);
    List<MenuDto> getAllMenusByRestaurantId(Long restaurantId);
    MenuDto getMenuById(Long id);
}
