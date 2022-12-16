package com.example.appwarehouse.controller;

import com.example.appwarehouse.payload.ClientDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;

    @PostMapping
    public Result addClient(@RequestBody ClientDto clientDto) {

        Result result = clientService.addClient(clientDto);
        return result;
    }

    @GetMapping
    public Result getClients() {

        Result result = clientService.getClients();
        return result;
    }

    @PutMapping("/{id}")
    public Result updateClient(@PathVariable Integer id, @RequestBody ClientDto clientDto) {

        Result result = clientService.updateClient(id, clientDto);
        return result;
    }

    @DeleteMapping("/{id}")
    public Result deleteClient(@PathVariable Integer id) {
        Result result = clientService.deleteClient(id);
        return result;
    }
}

