package com.example.singleton;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.example.choi.AppConfig;
import com.example.member.MemberService;

public class SingletonTest2 {
	//1. static 영역에 객체를 딱 1개만 생성해둔다.
	private static final SingletonTest2 instance = new SingletonTest2();

	//2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록
	//허용한다.
	public static SingletonTest2 getInstance() {
	return instance;
	}

	//3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
	private SingletonTest2() {
	}

	public void logic() {
	System.out.println("싱글톤 객체 로직 호출");
	}
	
	//private으로 생성자를 막아두었다. 컴파일 오류가 발생한다.
	//new SingletonService();

	@Test
	@DisplayName("싱글톤 패턴을 적용한 객체 사용")
	void singletonServiceTest() {
		//1. 조회: 호출할 때 마다 같은 객체를 반환
		SingletonTest2 singletonService1 = SingletonTest2.getInstance();
		//2. 조회: 호출할 때 마다 같은 객체를 반환
		SingletonTest2 singletonService2 = SingletonTest2.getInstance();

		//참조값이 같은 것을 확인
		System.out.println("singletonService1 = " + singletonService1);
		System.out.println("singletonService2 = " + singletonService2);

		// singletonService1 == singletonService2
		assertThat(singletonService1).isSameAs(singletonService2);

		singletonService1.logic();
	}
	
	@Test
	@DisplayName("스프링 컨테이너와 싱글톤")
	void springContainer() {

	ApplicationContext ac = new
	AnnotationConfigApplicationContext(AppConfig.class);

	//1. 조회: 호출할 때 마다 같은 객체를 반환
	MemberService memberService1 = ac.getBean("memberService",
	MemberService.class);

	//2. 조회: 호출할 때 마다 같은 객체를 반환

	MemberService memberService2 = ac.getBean("memberService",
	MemberService.class);

	//참조값이 같은 것을 확인
	System.out.println("memberService1 = " + memberService1);
	System.out.println("memberService2 = " + memberService2);

	//memberService1 == memberService2
	assertThat(memberService1).isSameAs(memberService2);
	}
	
}
