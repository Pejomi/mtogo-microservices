package dk.pejomi.restaurantservice.mapper;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper(RestaurantMapper.class);

    @Mappings({
            @Mapping(target = "userId", source = "id"),
            @Mapping(target = "id", ignore = true)
    })
    Restaurant toRestaurant(RestaurantDto restaurantDTO);

    RestaurantDto toRestaurantDTO(Restaurant restaurant);

    List<RestaurantDto> toRestaurantDTOs(List<Restaurant> restaurants);

}
