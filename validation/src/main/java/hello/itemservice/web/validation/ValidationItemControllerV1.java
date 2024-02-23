package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v1/items")
@RequiredArgsConstructor
public class ValidationItemControllerV1 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v1/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v1/addForm";
    }

    /**
     * 필드 검증
     * 상품명: 필수, 공백X
     * 가격: 1000원 이상, 1백만원 이하
     * 수량: 최대 9999
     *
     * 특정 필드의 범위를 넘어서는 검증
     * 가격 * 수량의 합은 10,000원 이상
     */
    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes, Model model) {

        // 검증 오류 결과를 보관
        Map<String, String> errors = new HashMap<>();

        // 검증 로직
        if (!StringUtils.hasText(item.getItemName())) {
            errors.put("itemName", "상품 이름은 필수입니다.");
        }
        if (item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 100000) {
            errors.put("price", "가격은 1,000 ~ 1,000,000 까지 허용합니다.");
        }
        if (item.getQuantity() == null || item.getQuantity() > 9999) {
            errors.put("quantity", "수량은 최대 9,999까지 허용합니다.");
        }

        // 특정 필드가 아닌 복합 룰 검증
        if (item.getPrice() != null && item.getQuantity() != null) {
            int resultSum = item.getPrice() * item.getQuantity();
            if (resultSum < 10000) {
                errors.put("globalError", "가격 * 수량의 합은 10,000원 이상이어야 합니다. 현재 값 = " + resultSum);
            }
        }

        // 검증에 실패하면 다시 입력 폼으로
        if (!errors.isEmpty()) {
            log.info("errors={}", errors);
            model.addAttribute("errors", errors);
            return "validation/v1/addForm";
            /**
             * @Post add함수에서 파라미터로 @ModelAttribute Item item 을 받고 있다.
             * @ModelAttribute 는 자동으롤 model.addAttribute("item", item) 을 해주기에
             * addForm 으로 넘어가서도 기존 입력 값들을 그대로 표시 가능
             * 
             * addForm.html 에서는 th:object=${item}으로 model에 있는 item 값을 받아오고
             * th:field=*{oooo} 으로 표시
             */
        }

        // 검증 성공 시
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v1/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v1/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v1/items/{itemId}";
    }

}

