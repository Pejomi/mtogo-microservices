package dk.pejomi.menuservice.repository;

import dk.pejomi.menuservice.model.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long> {
}
