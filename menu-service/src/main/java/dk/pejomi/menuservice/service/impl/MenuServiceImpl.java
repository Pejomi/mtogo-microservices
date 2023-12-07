package dk.pejomi.menuservice.service.impl;

import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.mapper.MenuMapper;
import dk.pejomi.menuservice.model.Menu;
import dk.pejomi.menuservice.repository.MenuRepository;
import dk.pejomi.menuservice.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    @Override
    public MenuDto createMenu(MenuDto menuDTO) {
        try {
            Menu menu = menuRepository.save(MenuMapper.INSTANCE.menuDtoToMenu(menuDTO));

            return MenuMapper.INSTANCE.menuToMenuDto(menu);

        } catch (DataAccessException e) {
            log.error("Error occurred while saving menu", e);
            throw new RuntimeException("Error occurred while saving menu");
        }
    }

    @Override
    public List<MenuDto> getAllMenusByRestaurantId(Long restaurantId) {

        List<Menu> menus = menuRepository.findAllByRestaurantId(restaurantId);
        if (menus.isEmpty()) throw new EntityNotFoundException("No menus found");
        return menus.stream()
                .map(MenuMapper.INSTANCE::menuToMenuDto)
                .toList();


    }

    @Override
    public MenuDto getMenuById(Long id) {
        Menu menu = menuRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Menu not found"));

        return MenuMapper.INSTANCE.menuToMenuDto(menu);
    }
}
