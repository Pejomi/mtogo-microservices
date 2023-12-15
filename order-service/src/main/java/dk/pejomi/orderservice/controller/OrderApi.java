package dk.pejomi.orderservice.controller;

import dk.pejomi.orderservice.dto.OrderDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

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
}
