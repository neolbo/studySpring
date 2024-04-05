package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {
    @Autowired
    MessageSource ms;

    @Test
    public void helloMessage() {
        String message = ms.getMessage("hello", null, null);
        assertThat(message).isEqualTo("안녕");
    }

    @Test
    public void notFoundMessageCode() {
        assertThatThrownBy(() -> ms.getMessage("no_code", null, null))
                .isInstanceOf(NoSuchMessageException.class);
    }

    @Test
    public void notFoundMessageCodeDefaultMessage() {
        String message = ms.getMessage("no_code", null, "기본 메시지", null);
        assertThat(message).isEqualTo("기본 메시지");
    }

    @Test
    public void argumentMessage() {
        String message = ms.getMessage("hello.name", new Object[]{"Spring!"}, null);
        assertThat(message).isEqualTo("안녕 Spring!");
    }

    @Test
    public void defaultLang() {
        assertThat(ms.getMessage("hello", null, null)).isEqualTo("안녕");
        assertThat(ms.getMessage("hello", null, Locale.KOREA)).isEqualTo("안녕");
    }

    @Test
    public void enLang() {
        String message = ms.getMessage("hello", null, Locale.ENGLISH);
        assertThat(message).isEqualTo("hello");
    }
}
