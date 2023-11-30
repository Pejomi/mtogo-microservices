package dk.pejomi.restaurantservice.mapper;

import dk.pejomi.basedomain.dto.RestaurantDto;
import dk.pejomi.restaurantservice.model.Restaurant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RestaurantMapper {

    RestaurantMapper INSTANCE = Mappers.getMapper( RestaurantMapper.class );

    @Mapping(target = "userId", source = "id")
    Restaurant toRestaurant(RestaurantDto restaurantDTO);

    RestaurantDto toRestaurantDTO(Restaurant restaurant);

}
