package io.dongvelop.messageinternationalization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageSourceController {

    private final MessageSource messageSource;

    @GetMapping("/say/hello")
    public String getMessage() {
        return getMessage("say.hello");
    }

    private String getMessage(final String code) {
        return getMessage(code, null);
    }

    private String getMessage(final String code, final Object[] args) {
        System.out.println("현재 Locale 정보 : " + LocaleContextHolder.getLocale());
        return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
    }
}