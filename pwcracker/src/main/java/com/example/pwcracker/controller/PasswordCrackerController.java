package com.example.pwcracker.controller;

import com.example.pwcracker.model.CrackRequest;
import com.example.pwcracker.model.CrackResult;
import com.example.pwcracker.service.PasswordCrackerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crack")
@CrossOrigin(origins = "*")
public class PasswordCrackerController {

    @Autowired
    private PasswordCrackerService service;

    @PostMapping
    public CrackResult crack(@RequestBody CrackRequest req) {
        return service.crack(req.getHash(), req.getAlgoritmo(), req.getAtaque());
    }
}
