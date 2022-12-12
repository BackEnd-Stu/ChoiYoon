package com.example.discount;

import com.example.discount.RateDiscountPolicy;
import com.example.member.Grade;
import com.example.member.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RateDiscountPolicyTest {
	RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

	@Test
	void vip_10() {
		MemberDto memberDto = new MemberDto (1L, "memberVIP", Grade.VIP);
		int discount = discountPolicy.discount(memberDto, 10000);
		Assertions.assertThat(discount).isEqualTo(1000);
	}

	@Test
	void vix_x() {
		MemberDto memberDto = new MemberDto (2L, "memberBasic", Grade.BASIC);
		int discount = discountPolicy.discount(memberDto, 10000);
		Assertions.assertThat(discount).isEqualTo(0);
	}
}