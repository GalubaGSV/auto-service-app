package galuba.autoservice.model;

import lombok.Getter;

@Getter
public enum OrderStatus {
    ACCEPTED("Accepted"),
    IN_PROGRESS("In_progress"),
    SUCCESSFULLY_COMPLETED("Successfully_completed"),
    NOT_SUCCESSFULLY_COMPLETED("Not_successfully_completed"),
    PAID("Paid");

    private String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
