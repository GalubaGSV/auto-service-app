package galuba.autoservice.model;

import lombok.Getter;

@Getter
public enum PaymentStatus {
    PAID("Paid"),
    NOT_PAID("Not_paid");

    private String value;

    PaymentStatus(String value) {
        this.value = value;
    }
}
