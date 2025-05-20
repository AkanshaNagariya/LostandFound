package lostandfound.laf_backend.controller;

import lostandfound.laf_backend.Model.Item;
import lostandfound.laf_backend.Model.User;
import lostandfound.laf_backend.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    @Autowired
    private ItemRepository itemRepository;

    @GetMapping
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @PostMapping
    @PreAuthorize("hasRole('STUDENT') or hasRole('STAFF')")
    public Item createItem(@RequestBody Item item, @AuthenticationPrincipal User user) {
        item.setReporter(user);
        item.setDateReported(LocalDateTime.now());
        return itemRepository.save(item);
    }
}
