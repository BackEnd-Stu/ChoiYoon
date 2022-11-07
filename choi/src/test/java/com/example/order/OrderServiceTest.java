package com.example.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.choi.AppConfig;
import com.example.member.Grade;
import com.example.member.MemberDto;
import com.example.member.MemberService;

public class OrderServiceTest {

	MemberService memberService;
	OrderService orderService;

	@BeforeEach
	public void beforeEach() {
		AppConfig appConfig = new AppConfig();
		memberService = appConfig.memberService();
		orderService = appConfig.orderService();
	}
	
	@Test
	void createOrder() {
	long memberId = 1L;
	MemberDto member = new MemberDto(memberId, "memberA", Grade.VIP);
	memberService.join(member);

	OrderDto order = orderService.createOrder(memberId, "itemA", 10000);
	Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
	}
}
