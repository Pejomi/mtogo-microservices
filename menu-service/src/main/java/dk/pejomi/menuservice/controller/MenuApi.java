package dk.pejomi.menuservice.controller;

import dk.pejomi.menuservice.dto.MenuDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Menu", description = "MTOGO menu controller")
public interface MenuApi {

    @Operation(
            summary = "Fetch a menu by id",
            description = "fetches a menu entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
    })
    public ResponseEntity<MenuDto> getMenuById(Long id);
    @Operation(
            summary = "Fetch all menus by restaurant id",
            description = "fetches all menu entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful"),
            @ApiResponse(responseCode = "404", description = "Menu not found"),
    })
    public ResponseEntity<List<MenuDto>> getMenuByRestaurantId(Long id);
    @Operation(
            summary = "Create a menu",
            description = "creates a menu entity and saves it to data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Menu created"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
    })
    public ResponseEntity<MenuDto> createMenu(MenuDto menuDto);
}
