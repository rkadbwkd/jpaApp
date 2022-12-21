package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
public class Address {

    private  String city;
    private String street;
    private String zipcode;

    protected Address() { // JPA 스팩상 설정

    }
    //PROTECTED : 같은 패키지내에선 접근 제한 없음 / 다른패키지에선 자식패키지만 허용
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
