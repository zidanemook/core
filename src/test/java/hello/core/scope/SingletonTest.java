package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest
{
    @Test
    void singletonBeanFind()
    {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(SingletonBean.class);

        SingletonBean SingletonBean1 = ac.getBean(SingletonBean.class);
        SingletonBean SingletonBean2 = ac.getBean(SingletonBean.class);
        System.out.println("SingletonBean1 = " + SingletonBean1);
        System.out.println("SingletonBean2 = " + SingletonBean2);
        assertThat(SingletonBean1).isSameAs(SingletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class SingletonBean
    {
        @PostConstruct
        public void init()
        {
            System.out.println("SingletonBean.init");
        }

        @PreDestroy
        public void destory()
        {
            System.out.println("SingletonBean.destroy");
        }
    }
}
