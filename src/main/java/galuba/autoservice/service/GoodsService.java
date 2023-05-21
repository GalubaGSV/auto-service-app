package galuba.autoservice.service;

import java.util.List;
import galuba.autoservice.model.CarOwner;
import galuba.autoservice.model.Goods;

public interface GoodsService {
    Goods save(Goods goods);

    List<Goods> findByIdIn(List<Long> ids);

    Goods findById(Long id);
}
