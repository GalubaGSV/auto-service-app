package galuba.autoservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarRequestDto {
    private String manufacturer;
    private String model;
    private int year;
    private String carNumber;
    private Long carOwnerId;
}
