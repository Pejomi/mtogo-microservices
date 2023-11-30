package dk.pejomi.basedomain.event;

import dk.pejomi.basedomain.dto.ConsumerDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateConsumerEvent {

    private String message;
    private ConsumerDto consumerDTO;
}
