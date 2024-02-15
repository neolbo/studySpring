package hello.itemservice.domain.item;

import hello.itemservice.domain.item.DTO.ItemParamDTO;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class itemRepositoryTest {

    ItemRepository itemRepository = new ItemRepository();

    @AfterEach
    void afterEach() {
        itemRepository.clearAll();
    }

    @Test
    void save() {
        //given
        Item item = new Item("itemA", 1000, 10);

        //when
        itemRepository.save(item);

        //then
        Item findItem = itemRepository.findById(item.getId());
        assertThat(item).isEqualTo(findItem);
    }

    @Test
    void findAll() {
        //given
        Item item1 = new Item("itemA", 1000, 10);
        Item item2 = new Item("itemB", 2000, 20);
        itemRepository.save(item1);
        itemRepository.save(item2);

        //when
        List<Item> itemList = itemRepository.findAll();

        //then
        assertThat(itemList.size()).isEqualTo(2);
        assertThat(itemList).contains(item1, item2);
    }

    @Test
    void update() {
        //given
        Item item1 = new Item("itemA", 1000, 10);
        Item savedItem = itemRepository.save(item1);
        Long savedItemId = savedItem.getId();

        //when
        ItemParamDTO itemParam = new ItemParamDTO("itemB", 2000, 20);
        itemRepository.update(savedItemId, itemParam);

        //then
        Item result = itemRepository.findById(savedItemId);
        assertThat(result.getItemName()).isEqualTo(itemParam.getItemName());
        assertThat(result.getPrice()).isEqualTo(itemParam.getPrice());
        assertThat(result.getQuantity()).isEqualTo(itemParam.getQuantity());
    }
}