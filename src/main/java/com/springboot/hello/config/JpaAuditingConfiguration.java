package com.springboot.hello.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  //HelloApplication에서 사용해도 되지만 @WebMvcTest 같은 테스트 코드에서 예외가 발생할 수 있음
public class JpaAuditingConfiguration {

}
