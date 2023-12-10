package dk.pejomi.consumerservice.mapper;

import dk.pejomi.basedomain.dto.ConsumerDto;
import dk.pejomi.consumerservice.model.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsumerMapper {

    ConsumerMapper INSTANCE = Mappers.getMapper(ConsumerMapper.class);

    @Mappings({
            @Mapping(target = "userId", source = "id"),
            @Mapping(target = "orderCount", ignore = true)
    })
    Consumer toConsumer(ConsumerDto consumerDTO);


    ConsumerDto toConsumerDTO(Consumer consumer);
}
