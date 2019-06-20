package kr.or.ddit.ioc;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"kr.or.ddit"} , useDefaultFilters = true) 
//DefaultFilter : @ controller, @service, @Repository, @component
//useDefaultFiter를 false로 선언하면 개발자가 원하는 이노테이션만 스캔 가능
// ex: @Controller만 스캔
public class ApplicationIocBeanScanConfig {

}
