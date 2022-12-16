package com.example.appwarehouse.service;

import com.example.appwarehouse.entity.Client;
import com.example.appwarehouse.payload.ClientDto;
import com.example.appwarehouse.payload.Result;
import com.example.appwarehouse.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Result addClient(ClientDto clientDto) {

        boolean existsClientByPhoneNumber = clientRepository.existsClientByPhoneNumber(clientDto.getPhoneNumber());
        if (existsClientByPhoneNumber) {
            return Result.fail("Such a Client already exists in system!");
        }

        Client client = new Client();
        client.setName(clientDto.getName());
        client.setPhoneNumber(clientDto.getPhoneNumber());

        clientRepository.save(client);
        return Result.success("Client added");
    }

    public Result getClients() {

        List<Client> clientList = clientRepository.getClientByActive(true);
        return Result.success("Success", clientList);
    }

    public Result updateClient(Integer id, ClientDto clientDto) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return Result.fail("Client not found");
        }
        Client updatingClient = optionalClient.get();
        updatingClient.setName(clientDto.getName());
        updatingClient.setPhoneNumber(clientDto.getPhoneNumber());

        clientRepository.save(updatingClient);
        return Result.success("Client edited");
    }

    public Result deleteClient(Integer id) {

        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) {
            return Result.fail("Client not found");
        }
        clientRepository.deleteById(id);
        return Result.success("Client deleted");
    }

}
