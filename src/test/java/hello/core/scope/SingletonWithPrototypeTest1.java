package hello.core.scope;

import ch.qos.logback.core.net.server.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.*;

public class SingletonWithPrototypeTest1
{
    @Test
    void prototypeFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);
    }

    @Test
    void singletonClientUserPrototype()
    {

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);
        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);

    }

    @Scope("singleton")
    static class ClientBean
    {
        //싱글톤스코프객체에 주입된 프로토타입스코프 객체는 싱글톤이 파괴되지 않는한 그래도 유지된다
        //이문제를 해결하려면 ObjectProvider 를 사용하면 된다
        //Provider(javax.inject) 도 있다 의존관계 추가 필요함 implementation 'javax.inject:javax.inject:1'
        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic()
        {
            PrototypeBean prototypeBean = prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    @Scope("prototype")
    static class PrototypeBean
    {
        private int count = 0;
        public void addCount()
        {
            count++;
        }
        public int getCount()
        {
            return count;
        }

        @PostConstruct
        public void init()
        {
            System.out.println("PrototypeBean.init " + this);
        }

        @PreDestroy
        public void destroy()
        {
            System.out.println("PrototypeBean.destroy");
        }
    }
}
