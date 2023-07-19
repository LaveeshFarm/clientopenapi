package ru.hxh.clientopenapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import ru.hxh.clientopenapi.api.ClientsApi;
import ru.hxh.clientopenapi.api.model.Client;
import ru.hxh.clientopenapi.api.model.ClientDTO;
import ru.hxh.clientopenapi.service.Impl.ClientApiServiceImpl;

import java.util.List;

@RestController
public class ClientApiController implements ClientsApi {

    @Autowired
    ClientApiServiceImpl clientApiService;


    @Override
    public ResponseEntity<List<ClientDTO>> clientList() {
        return ResponseEntity.ok(clientApiService.show());
    }

    @Override
    public ResponseEntity<Void> createClient(ClientDTO client) {
        clientApiService.create(client);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Client> showClientById(Long clientId) {
        return ResponseEntity.ok(clientApiService.show(clientId));
    }
}
