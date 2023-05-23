package galuba.autoservice.service;

import galuba.autoservice.model.Goods;
import java.util.List;

public interface GoodsService {
    Goods save(Goods goods);

    List<Goods> findByIdIn(List<Long> ids);

    Goods findById(Long id);
}
