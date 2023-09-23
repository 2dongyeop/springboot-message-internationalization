package io.dongvelop.messageinternationalization;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

import java.util.Locale;

/**
 * MessageSourceControllerTestV2
 * HTTP Request Header에 Accept-Language를 지정해서 국제화하는 방식으로 테스트할 경우,
 * 아래의 @Configuration 제외해주기
 */
//@Configuration
public class MessageConfig implements WebMvcConfigurer {

    /* Springboot 사용시 자동 등록되어 생략 가능 */
//    @Bean
//    public MessageSource messageSource(
//            @Value("${spring.messages.basename}") String basename,
//            @Value("${spring.messages.encoding}") String encoding) {
//        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename(basename);        // "classpath:i18n/messages/message"
//
//        messageSource.setDefaultEncoding(encoding); // "UTF-8"
//        messageSource.setAlwaysUseMessageFormat(true);
//        messageSource.setUseCodeAsDefaultMessage(true);
//        messageSource.setFallbackToSystemLocale(true);
//        return messageSource;
//    }

//    @Bean
    public LocaleResolver localeResolver() {

        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        localeResolver.setDefaultLocale(Locale.KOREA); //ko_KR

        return localeResolver;
    }

    // [GET] /say/hello?lang=ko_KR
    // [GET] /say/hello?lang=en_US
//    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        final LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");

        return localeChangeInterceptor;
    }

    @Override
    public void addInterceptors(final InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
