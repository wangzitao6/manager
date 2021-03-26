package com.wzt.demo.example.ctrl;

import com.wzt.demo.example.entity.WotMetaSchemaSnapshot;
import com.wzt.demo.example.service.WotMetaSchemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/meta")
public class WotMetaSchemaController {
    @Autowired
    private final WotMetaSchemaRepository wotMetaSchemaRepository;

    public WotMetaSchemaController(WotMetaSchemaRepository wotMetaSchemaRepository) {
        this.wotMetaSchemaRepository = wotMetaSchemaRepository;
    }

    @GetMapping("/getAll")
    public List<WotMetaSchemaSnapshot> getAllUsers() {
        return wotMetaSchemaRepository.findAll();
    }


    @PostMapping("/add")
    public WotMetaSchemaSnapshot addNewUser(@RequestBody WotMetaSchemaSnapshot user) {
        return wotMetaSchemaRepository.save(user);
    }


    @PutMapping("/update")
    public WotMetaSchemaSnapshot update(@RequestBody WotMetaSchemaSnapshot user) {
        return wotMetaSchemaRepository.save(user);
    }
}
