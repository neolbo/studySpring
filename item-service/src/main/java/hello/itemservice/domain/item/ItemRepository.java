package hello.itemservice.domain.item;

import hello.itemservice.domain.item.DTO.ItemParamDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class ItemRepository {
    private final static Map<Long, Item> store = new ConcurrentHashMap<>();
    private static AtomicLong sequence = new AtomicLong(0L);

    public Item save(Item item) {
        item.setId(sequence.incrementAndGet());
        store.put(item.getId(), item);
        return item;
    }

    public Item findById(Long id) {
        return store.get(id);
    }

    public List<Item> findAll() {
        return new ArrayList<>(store.values());
    }

    public void update(Long itemID, ItemParamDTO updateParam) {
        Item findItem = store.get(itemID);
        findItem.setItemName(updateParam.getItemName());
        findItem.setPrice(updateParam.getPrice());
        findItem.setQuantity(updateParam.getQuantity());
    }

    public void clearAll() {
        store.clear();
    }
}
