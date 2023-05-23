package galuba.autoservice.service.impl;

import galuba.autoservice.model.Goods;
import galuba.autoservice.repository.GoodsRepository;
import galuba.autoservice.service.GoodsService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class GoodsServiceImpl implements GoodsService {
    private final GoodsRepository goodsRepository;

    @Override
    public Goods save(Goods goods) {
        return goodsRepository.save(goods);
    }

    @Override
    public List<Goods> findByIdIn(List<Long> ids) {
        return goodsRepository.findByIdIn(ids);
    }

    @Override
    public Goods findById(Long id) {
        return goodsRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Can't find goods by id" + id));
    }
}
