package hello.core;

import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan//@Bean 을 사용할 필요 없어짐 대신 등록할 대상의 클래스에서 @Component 를 사용해야함
        (
                //basePackages = "hello.core",//스캔 범위 지정
                //basePackageClasses = AutoAppConfig.class,//스캔 범위 지정. 범위지정하지 않으면 클래스가 위치한 패키지부터 탐색함
                //@Configuration 을 사용한 대상들(AppConfig, TestConfig) 가 등록되지 않게 하기 위함
                excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        )
public class AutoAppConfig
{
//        @Bean(name = "memoryMemberRepository")//동일한 이름으로 수봉빈등록 + 자동빈등록 할경우.
//        스프링에서는 수동 빈 등록은 자동 빈 등록을 덮어씌워버린다. 최신 스프링부트는 오류발생시킨다
//        MemoryMemberRepository memberRepository()
//        {
//                return new MemoryMemberRepository();
//        }
}
