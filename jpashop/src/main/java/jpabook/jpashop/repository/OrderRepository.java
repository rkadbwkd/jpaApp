package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order){
        em.persist(order);
    }
    
    public Order findOne(Long id){
        return em.find(Order.class,id)
        
    }
    // 동적쿼리 추후 수정예쩡
    // public List<Order> findAll(OrderSearch orderSearch){}
    
    
}
