package dk.pejomi.restaurantservice.controller;

import dk.pejomi.basedomain.dto.RestaurantDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Restaurant", description = "MTOGO restaurant controller")
public interface RestaurantApi {

    @Operation(
            summary = "Fetch a restaurant by id",
            description = "fetches a restaurant entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
    public ResponseEntity<RestaurantDto> getRestaurantById(@PathVariable Long id);

    @Operation(
            summary = "Fetch all restaurants by zip code",
            description = "fetches all restaurant entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByZipCode(@PathVariable String zipCode);

    @Operation(
            summary = "Fetch all restaurants by city",
            description = "fetches all restaurant entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
    public ResponseEntity<List<RestaurantDto>> getRestaurantsByCity(@PathVariable String city);


    @Operation(
            summary = "Fetch all active restaurants by zip code",
            description = "fetches all active restaurant entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
    public ResponseEntity<List<RestaurantDto>> getActiveRestaurantsByZipCode(@PathVariable String zipCode);

    @Operation(
            summary = "Fetch all active restaurants by city",
            description = "fetches all active restaurant entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
    public ResponseEntity<List<RestaurantDto>> getActiveRestaurantsByCity(@PathVariable String city);

    @Operation(
            summary = "Fetch all pending restaurants",
            description = "fetches all pending restaurant entities and their data from data source. Use pagination to limit the result set")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetch successful")
    })
public ResponseEntity<List<RestaurantDto>> getPendingRestaurants();

    @Operation(
            summary = "Approve a restaurant",
            description = "Approves a restaurant entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Approve successful")
    })
    public ResponseEntity<RestaurantDto> approveRestaurant(@PathVariable Long restaurantId);

    @Operation(
            summary = "Reject a restaurant",
            description = "Rejects a restaurant entity and its data from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reject successful")
    })
    public ResponseEntity<RestaurantDto> rejectRestaurant(@PathVariable Long restaurantId, @Parameter(description = "Reason for rejection") String reason);


}
