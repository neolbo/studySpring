package hello.itemservice;

import hello.itemservice.web.validation.ItemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}


/*
	글로벌 설정 /// 전체 컨트롤러에 적용(InitBinder, itemValidator Autowired 안받아도 됨)
	implements WebMvcConfigurer

	@Override
	public Validator getValidator() {
		return new ItemValidator();
	}
	*/
}
