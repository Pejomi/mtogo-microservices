package dk.pejomi.menuservice.repository;

import dk.pejomi.menuservice.model.Menu;
import dk.pejomi.menuservice.model.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class MenuRepositoryIT {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Test
    void should_find_menu_by_id() {
        // Arrange
        Menu menu = menuRepository.findAll().get(0);

        // Act
        Menu foundMenu = menuRepository.findById(menu.getId()).orElse(null);

        // Assert
        assertNotNull(foundMenu);
        assertEquals(foundMenu.getRestaurantId(), menu.getRestaurantId());
    }

    @Test
    void should_find_menu_by_restaurant_id() {
        // Arrange
        Menu menu = menuRepository.findAll().get(0);

        // Act
        Menu foundMenu = menuRepository.findAllByRestaurantId(menu.getRestaurantId()).get(0);

        // Assert
        assertNotNull(foundMenu);
        assertEquals(foundMenu.getRestaurantId(), menu.getRestaurantId());
    }

    @Test
    void should_find_menu_item_by_id() {
        // Arrange
        MenuItem menuItem = menuItemRepository.findAll().get(0);

        // Act
        MenuItem foundMenuItem = menuItemRepository.findById(menuItem.getId()).orElse(null);

        // Assert
        assertNotNull(foundMenuItem);
        assertEquals(foundMenuItem.getName(), menuItem.getName());
    }

    @Test
    void should_add_menu_item() {
        // Arrange
        Menu menu = menuRepository.findAll().get(0);
        MenuItem menuItem = MenuItem.builder()
                .name("Pizza Hawaii")
                .price(70)
                .menu(menu)
                .build();

        // Act
        MenuItem savedMenuItem = menuItemRepository.save(menuItem);

        // Assert
        assertNotNull(savedMenuItem);
        assertEquals(savedMenuItem.getName(), menuItem.getName());
    }

    @Test
    void should_delete_menu_item() {
        // Arrange
        MenuItem menuItem = menuItemRepository.findAll().get(0);
        int size = menuItemRepository.findAll().size();

        // Act
        menuItemRepository.deleteById(menuItem.getId());

        // Assert
        assertEquals(menuItemRepository.findAll().size(), size-1);
    }

}