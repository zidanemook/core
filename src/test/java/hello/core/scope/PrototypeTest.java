package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class PrototypeTest
{
    @Test
    void prototypeBeanFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        assertThat(prototypeBean1).isNotSameAs(prototypeBean2);
        ac.close();
    }

    @Scope("prototype")//프로토타입은 요청받으면 생성해서 던지고 스프링컨테이너에서는 관리안한다. 요청받을때마다 새로 생성된다
    static class PrototypeBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destory()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
