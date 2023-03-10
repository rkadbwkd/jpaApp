package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){ // 신규 등록
                em.persist(item);
        } else{
            em.merge(item); // update?
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){ // 여러개 찾는것은 JPQL 작성해야함
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();

    }




}
