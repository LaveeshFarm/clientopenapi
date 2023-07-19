package ru.hxh.clientopenapi.service;

import ru.hxh.clientopenapi.api.model.Client;
import ru.hxh.clientopenapi.api.model.ClientDTO;

import java.util.List;

public interface ClientApiService {
    List<ClientDTO> show();
    Client show(Long clientId);
    void create(ClientDTO client);
}
