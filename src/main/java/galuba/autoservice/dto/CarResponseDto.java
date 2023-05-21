package galuba.autoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarResponseDto {
    private Long id;
    private String manufacturer;
    private String model;
    private int year;
    private String carNumber;
    private Long carOwnerId;
}
