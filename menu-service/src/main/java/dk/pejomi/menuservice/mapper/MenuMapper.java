package dk.pejomi.menuservice.mapper;

import dk.pejomi.menuservice.dto.MenuDto;
import dk.pejomi.menuservice.dto.MenuItemDto;
import dk.pejomi.menuservice.model.Menu;
import dk.pejomi.menuservice.model.MenuItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MenuMapper {
    MenuMapper INSTANCE = Mappers.getMapper(MenuMapper.class);


    @Mapping(source = "menuItems", target = "menuItemsDto")
    MenuDto menuToMenuDto(Menu menu);


    @Mapping(source = "menuItemsDto", target = "menuItems")
    Menu menuDtoToMenu(MenuDto menuDto);

    MenuItemDto menuItemToMenuItemDto(MenuItem menuItem);

    MenuItem menuItemDtoToMenuItem(MenuItemDto menuItemDto);

}