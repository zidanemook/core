package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;

import java.lang.annotation.*;

//직접만든 어노테이션

//어노테이션에 Qualifier 를 사용해서 Bean 객체가 동일한 부모 상속시 어떤 Bean 객체를 인스턴스로 생성할것인지 지정하기 위해 만든 어노테이션이다
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy
{
}
