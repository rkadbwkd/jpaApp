package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderSimpleQueryDto {



        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public OrderSimpleQueryDto( Long orderId, String name, LocalDateTime orderDate, OrderStatus orderStatus, Address address) {

            this.orderId = orderId;
            this.name = name;
            this.orderDate = orderDate;
            this.orderStatus = orderStatus;
            this.address = address;
//            orderId = order.getId();
//            name = order.getMember().getName(); // Lazy 초기화 (영속성 컨텍스트에 없으므로 쿼리를 날림)
//            orderDate = order.getOrderDate();
//            orderStatus = order.getStatus();
//            address = order.getDelivery().getAddress(); // Lazy 초기화 (영속성 컨텍스트에 없으므로 쿼리를 날림)
            //2개의 주문을 조회 하기 위해서
            // ORDER 조회 > SQL 1번 실행 > 결과 주문수가 2개 나옴
            // 1. member_ID 조회                1
            // 2. Order , Delivery 조회         N
            // 3. Order , Delivery 조회         N
        }


}
