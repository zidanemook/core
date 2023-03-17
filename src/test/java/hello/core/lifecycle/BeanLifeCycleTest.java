package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest
{
    @Test
    public void lifeCycleTest()
    {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig
    {
        //PostContruct PreDestroy 로 대체
        //@Bean(initMethod = "init", destroyMethod = "close")//초기화 함수 이름, 파괴하기전에 호출될 close 함수 이름 매칭

        @Bean
        public NetworkClient networkClient()
        {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello.dev");
            return networkClient;
        }
    }
}
