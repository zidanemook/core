package hello.core.autowired;

import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest
{
    @Test
    void AutowiredOption()
    {
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean
    {
        @Autowired(required = false)//Member 빈등록 안되어 있는경우 required = false 이면 호출이 안된다. true이면 오류난다
        public void setNoBean1(Member noBean1)
        {
            System.out.println("noBean1 = " + noBean1);
        }

        @Autowired//Member 빈등록 안되어 있는경우. noBean2 가 없으면 Null 로 호출은 된다
        public void setNoBean2(@Nullable Member noBean2)
        {
            System.out.println("noBean2 = " + noBean2);
        }

        @Autowired//Member 빈등록 안되어 있는경우. Optional.empty 로 값이 들어온다
        public void setNoBean3(Optional<Member> noBean3)
        {
            System.out.println("noBean3 = " + noBean3);;
        }
    }
}
