package authservice.eventProducer;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Getter
@Setter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfoEvent {

    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String userId;
    
}
