package dk.pejomi.menuservice.controller;

import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.service.MenuService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Slf4j
public class MenuController implements MenuApi {

    private final MenuService menuService;

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenuById(Long id) {
        try {
            return ResponseEntity.ok(menuService.getMenuById(id));
        } catch (EntityNotFoundException e) {
            log.error("Menu not found with id [{}]", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @GetMapping("/restaurant/{id}")
    public ResponseEntity<List<MenuDto>> getMenuByRestaurantId(Long id) {
        try {
            return ResponseEntity.ok(menuService.getAllMenusByRestaurantId(id));
        } catch (EntityNotFoundException e) {
            log.error("Menu not found with id [{}]", id);
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<MenuDto> createMenu(MenuDto menuDto) {
        try {
            return ResponseEntity.ok(menuService.createMenu(menuDto));
        } catch (RuntimeException e) {
            log.error("Error occurred while saving menu", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
