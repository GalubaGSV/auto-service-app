package galuba.autoservice.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    @JsonIgnore
    private Car car;
    private String problemDescription;
    private LocalDate startDate;
    @OneToMany(mappedBy = "id")
    @Column(name = "maintenances_id")
    private List<Maintenance> maintenances;
    @OneToMany(mappedBy = "id")
    @Column(name = "goods_id")
    private List<Goods> goods;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private BigDecimal finalPrice;
    private LocalDate finalDate;
}


/**
 * • Машина
 * • Описание проблемы с которой обратились
 * • Дата принятия
 * • Услуги включенные в заказ
 * • Товары включенные в заказ
 * • Статус (принято, в процессе, успешно завершено, не успешно завершено,
 * оплачено)
 * • Итоговая стоимость для клиента
 * • Дата завершения
 */