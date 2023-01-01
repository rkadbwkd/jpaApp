package jpabook.jpashop.api;


import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.repository.OrderRepository;
import jpabook.jpashop.repository.OrderSearch;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * XToOne(ManyToOne, OneToOne)
 * Order
 * Order -> Member
 * Order -> Delivery ( Order가 Member와 Delivery만 연관관계를 걸리게함)
 * */
@RestController
@RequiredArgsConstructor
public class OrderSimpleApiController {

    private final OrderRepository orderRepository;

    @GetMapping("/api/v1/simple-orders")
    public List<Order> orderV1(){
        List<Order> all = orderRepository.findAllByString(new OrderSearch());
        for (Order order : all) {
            order.getMember().getName(); //Lazy 강제 초기화
            order.getDelivery().getAddress();

        }
        return all; // Order > Member , Member > Order 무한로프에 빠짐
    }



    @GetMapping("/api/v2/simple-orders")
    public List<SimpleOrderDto> ordersV2(){
        List<Order> orders = orderRepository.findAllByString(new OrderSearch());
        //2개 조회 2개면 2번 루프가 돔
        // 처음돌때 APA
        // N + 1 문제 orders 조회 = 1+ 결과 2
        //                  =>   1 + 회원 N + 배송 N
        // 지연로딩은 영속성 컨텍스트에서 조회
        List<SimpleOrderDto> result = orders.stream()
                .map(o -> new SimpleOrderDto(o))
                .collect(Collectors.toList());

        return result;

        //Order 조회시 3개의 테이블 ( Member ,Order , Delivery 조회필요)
    }

    @Data
    static class SimpleOrderDto{

        private Long orderId;
        private String name;
        private LocalDateTime orderDate;
        private OrderStatus orderStatus;
        private Address address;

        public SimpleOrderDto(Order order) {
            orderId = order.getId();
            name = order.getMember().getName(); // Lazy 초기화 (영속성 컨텍스트에 없으므로 쿼리를 날림)
            orderDate = order.getOrderDate();
            orderStatus = order.getStatus();
            address = order.getDelivery().getAddress(); // Lazy 초기화 (영속성 컨텍스트에 없으므로 쿼리를 날림)
            //2개의 주문을 조회 하기 위해서
            // ORDER 조회 > SQL 1번 실행 > 결과 주문수가 2개 나옴
               // 1. member_ID 조회                1
               // 2. Order , Delivery 조회         N
               // 3. Order , Delivery 조회         N
        }
    }






}
