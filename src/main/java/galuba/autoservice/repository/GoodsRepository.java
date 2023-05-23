package galuba.autoservice.repository;

import galuba.autoservice.model.Goods;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsRepository extends JpaRepository<Goods, Long> {
    List<Goods> findByIdIn(List<Long> ids);

    List<Goods> findAllByOrder_Id(Long id);
}
