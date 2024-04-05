package hello.typeconverter.converter;

import hello.typeconverter.type.IpPort;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.*;

public class conversionTest {
    @Test
    void ConversionTest() {
        DefaultConversionService conversionService = new DefaultConversionService();

        // 등록
        conversionService.addConverter(new IntegerToStringConverter());
        conversionService.addConverter(new StringToIntegerConverter());
        conversionService.addConverter(new StringToIpPortConverter());
        conversionService.addConverter(new IpPortToStringConverter());

        // 사용
        assertThat(conversionService.convert("10", Integer.class)).isEqualTo(10);
        assertThat(conversionService.convert(10, String.class)).isEqualTo("10");

        IpPort ipPort = new IpPort("127.0.0.1", 8080);
        assertThat(conversionService.convert(ipPort, String.class)).isEqualTo("127.0.0.1:8080");

        String ipPortString = "127.0.0.1:8080";
        assertThat(conversionService.convert(ipPortString, IpPort.class)).isEqualTo(new IpPort("127.0.0.1", 8080));
    }
}
