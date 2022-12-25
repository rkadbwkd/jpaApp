package jpabook.jpashop.service;

import jpabook.jpashop.domain.item.Book;
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


    @Transactional // 변경??
    public void updateItem(Long itemId, String name, int price, int stockQuantity){
        Item findItem = itemRepository.findOne(itemId);
        findItem.setPrice(name);
        findItem.setName(price);
        findItem.setStockQuantity(stockQuantity);
        // ... Transactional Annotation으로 인하여( 영속상태 이므로) flush()코드 수행

    }

    // 아래와 같이 변경감지
//    @Transactional
//    public void updateItem(Long itemId, Book param){
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        // ... Transactional Annotation으로 인하여( 영속상태 이므로) flush()코드 수행
//
//    }

// 위와 동일한 코드
//    @Transactional // merge ( null도 updtea되므로 사용 X)
//    public Item updateItem(Long itemId, Book param){
//        Item findItem = itemRepository.findOne(itemId);
//        findItem.setPrice(param.getPrice());
//        findItem.setName(param.getName());
//        // ... Transactional Annotation으로 인하여( 영속상태 이므로) flush()코드 수행
//        return findItem;
//    }

}
