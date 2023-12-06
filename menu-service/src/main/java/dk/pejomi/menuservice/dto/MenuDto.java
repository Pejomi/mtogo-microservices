package dk.pejomi.menuservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class MenuDto {
    private Long id;
    private Long restaurantId;
    private List<MenuItemDto> menuItemsDto;
}
