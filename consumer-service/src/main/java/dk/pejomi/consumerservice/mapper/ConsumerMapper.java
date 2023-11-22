package dk.pejomi.consumerservice.mapper;

import dk.pejomi.consumerservice.dto.ConsumerDTO;
import dk.pejomi.consumerservice.model.Consumer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ConsumerMapper {

        ConsumerMapper INSTANCE = Mappers.getMapper( ConsumerMapper.class );

        @Mapping(target = "id", ignore = true)
        @Mapping(target = "password", ignore = true)
        Consumer toConsumer(ConsumerDTO consumerDTO);

        ConsumerDTO toConsumerDTO(Consumer consumer);
}
