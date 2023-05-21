package galuba.autoservice.repository;

import java.util.List;
import galuba.autoservice.model.Goods;
import galuba.autoservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository  extends JpaRepository<Goods, Long> {
    List<Goods> findByIdIn(List<Long> ids);

    List<Goods> findAllByOrder_Id(Long id);
}
