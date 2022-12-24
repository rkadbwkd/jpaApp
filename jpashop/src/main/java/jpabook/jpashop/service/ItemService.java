package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Range;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional // Insert시 어노테이션 추가 필요
    public void saveItem(Item item){
        itemRepository.save(item);

    }

    public List<Item> findItems(){
        return itemRepository.findAll();

    }

    public Item findOne(Long itemId){
        return itemRepository.findOne(itemId);
    }


}
