package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Tag(name = "Order", description = "MTOGO order controller")
public interface OrderApi {

    @Operation(
            summary = "Create an order",
            description = "creates an order entity and its data in data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Order created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto);

    @Operation(
            summary = "Get an order by id",
            description = "retrieves an order entity by its id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order retrieved"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDto> getOrderById(Long id);

    @Operation(
            summary = "Approve an order",
            description = "approves an order entity by its id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order approved"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDto> approveOrder(Long id);

    @Operation(
            summary = "Decline an order",
            description = "declines an order entity by its id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Order declined"),
            @ApiResponse(responseCode = "404", description = "Order not found")
    })
    public ResponseEntity<OrderDto> declineOrder(Long id);

    @Operation(
            summary = "Get all orders by restaurant id",
            description = "retrieves all orders by restaurant id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved"),
            @ApiResponse(responseCode = "404", description = "Orders not found")
    })
    public ResponseEntity<List<OrderDto>> getAllOrdersByRestaurantId(Long id);

    @Operation(
            summary = "Get all created orders by restaurant id",
            description = "retrieves all created orders by restaurant id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved"),
            @ApiResponse(responseCode = "404", description = "Orders not found")
    })
    public ResponseEntity<List<OrderDto>> getAllCreatedOrdersByRestaurantId(Long id);

    @Operation(
            summary = "Get all active orders by restaurant id",
            description = "retrieves all active orders by restaurant id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved"),
            @ApiResponse(responseCode = "404", description = "Orders not found")
    })
    public ResponseEntity<List<OrderDto>> getAllActiveOrdersByRestaurantId(Long id);

    @Operation(
            summary = "Get all declined orders by restaurant id",
            description = "retrieves all declined orders by restaurant id from data source")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Orders retrieved"),
            @ApiResponse(responseCode = "404", description = "Orders not found")
    })
    public ResponseEntity<List<OrderDto>> getAllDeclinedOrdersByRestaurantId(Long id);

}
