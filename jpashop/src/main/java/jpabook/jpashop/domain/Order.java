package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="orders")
@Getter @Setter
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    //ManyToOne은 Lazy로 바꿔줘야함
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;
    
    //JPQL select o From order o ; -> select * from order ( N + 1 ) 문제 발생 >> 지연로딩으로 변경을해야함
    // @ManytoOne vs @OnetoOne Lazy 방식이 다름

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    //cascade Option 추후 Study 필요

    // ex ) persisit(OrderItemA)
    // ex ) persisit(OrderItemB)
    // ex ) persisit(OrderItemC)
    // persist(order)
    // 각 entiity class는 persist 사용 필요 but cascadde사용시ㅣ
     // 자동으로 persist 진행
    // 이과정을 persist(order)로 한번에
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status; // 주문상태 ORDER,CANCEL

    //연관관계 펴의 메서드 //
    // 발생이유
    /*
    public static void main(String[] args) {
        Member member = new Member();
        Order order = new Order();
        member.getOrders().add(order);
        order.setmemmberr(member); // 뺴먹을수 잇ㅈ음
    }
    */
    public void setMember(Member member){
        this.member = member;
        member.getOrders().add(this);

    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery){
       this.delivery = delivery;
       delivery.setOrder(this);
    }
}
