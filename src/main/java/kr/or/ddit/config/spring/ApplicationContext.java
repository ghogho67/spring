package kr.or.ddit.config.spring;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.BeanNameViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

import kr.or.ddit.view.ExcelDownloadView;
import kr.or.ddit.view.ProfileView;

/*
<context:component-scan base-package="kr.or.ddit" use-default-filters="false">
<context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
<context:include-filter type="annotation" expression="org.springframework.web.bind.annotation.ControllerAdvice"/>
</context:component-scan>
*/
@ComponentScan(basePackages = {"kr.or.ddit"}, useDefaultFilters = false,
            includeFilters = {@Filter(type = FilterType.ANNOTATION,
                                 classes = {Controller.class, ControllerAdvice.class})})
@EnableWebMvc      //<mvc:annotation-driven/>
@Configuration
public class ApplicationContext extends WebMvcConfigurerAdapter{

   //<mvc:default-servlet-handler/>
   @Override
   public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
      configurer.enable();
   }
   
   /*
      <bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
      <property name="prefix" value="/WEB-INF/views/"/>   <!-- 접두 -->
      <property name="suffix" value=".jsp"/>   <!-- 접미 -->
      <property name="order" value="3"></property>
   */
   
   @Bean
   public ViewResolver internalResourceViewResolver() {
      InternalResourceViewResolver irvr = new InternalResourceViewResolver();
      irvr.setPrefix("/WEB-INF/views/");
      irvr.setSuffix(".jsp");
      //InternalResourceViewResolver irvr = new InternalResourceViewResolver("/WEB-INF/views/", ".jsp");
      irvr.setOrder(3);
      
      return irvr;
   }
   
   /*
   <bean class="org.springframework.web.servlet.view.BeanNameViewResolver">
      <property name="order" value="2"/>
      
   </bean>
   */
   @Bean
   public ViewResolver beanNameViewResolver() {
      BeanNameViewResolver bnvr = new BeanNameViewResolver();
      bnvr.setOrder(2);
      
      return bnvr;
   }
   
   /*
      <bean class="org.springframework.web.servlet.view.tiles3.TilesConfigurer">
      <property name="definitions">
         <list>
            <value>classpath:kr/or/ddit/config/tiles/tiles-config.xml</value>
         </list>
      </property>
   </bean>
   */
   
   @Bean
   public TilesConfigurer tilesConfiguer() {
      TilesConfigurer tilesConfiguer = new TilesConfigurer();
      tilesConfiguer.setDefinitions("classpath:kr/or/ddit/config/tiles/tiles-config.xml");
      return tilesConfiguer;
      
   }
   
   /*
   <bean class="org.springframework.web.servlet.view.tiles3.TilesViewResolver">
      <property name="order" value="1"></property>
      <property name="viewClass" value="org.springframework.web.servlet.view.tiles3.TilesView"></property>
   </bean>
   */
   @Bean
   public ViewResolver tilesViewResolver() {
      TilesViewResolver tvr = new TilesViewResolver();
      tvr.setOrder(1);
      tvr.setViewClass(TilesView.class);
      return tvr;
   }

//   <!-- controller에서 model객체에 넣은 속성을 이용하여json으로 만들어주는 view -->
//   <bean id="jsonView" class="org.springframework.web.servlet.view.json.MappingJackson2JsonView"/>
   @Bean
   public View jsonView() {
      return new MappingJackson2JsonView();
      
   }
//      <!-- profile이미지를 처리해주는view -->
//      <bean id="profileView" class="kr.or.ddit.view.ProfileView"/>
   @Bean
   public View profileView() {
      return new ProfileView();
   }
   
   
//      <!-- 사용자 엑셀정보를 처리해주는view -->
//      <bean id="userExcelView" class="kr.or.ddit.view.ExcelDownloadView"/>
   @Bean
   public View userExcelView() {
      return new ExcelDownloadView();
   }
      
      
   /*
   <bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
      <property name="maxUploadSizePerFile" value="3145728"/>
      <property name="maxUploadSize" value="15728640"/>
   </bean>   
   */
   @Bean 
   public CommonsMultipartResolver multipartResolver() {
      CommonsMultipartResolver cmr = new CommonsMultipartResolver();
      cmr.setMaxUploadSizePerFile(1024*1024*3);
      cmr.setMaxUploadSize(1024*1024*3*5);
      
      return cmr;
   }
   
   /*
   <bean id="messageSource" class="org.springframework.context.support.ReloadableResourceBundleMessageSource">
      <property name="basenames">
      <list>
         <value>classpath:kr/or/ddit/msg/error</value>
         <value>classpath:kr/or/ddit/msg/msg</value>
      </list>
      </property>
   </bean>
   */
   @Bean
   public MessageSource messageSource() {
      ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
      messageSource.setBasenames("classpath:kr/or/ddit/msg/error","classpath:kr/or/ddit/msg/msg");
      return messageSource;
   }
   
   /*
   <bean id="localeResolver" class="org.springframework.web.servlet.i18n.SessionLocaleResolver"/>
   */
   @Bean
   public LocaleResolver localeResolver() {
      return new SessionLocaleResolver();
   }
   
   
}













