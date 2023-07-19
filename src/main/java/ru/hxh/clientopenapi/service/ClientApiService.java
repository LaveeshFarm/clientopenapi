package ru.hxh.clientopenapi.service;

import org.springframework.stereotype.Service;
import ru.hxh.clientopenapi.api.model.Client;
import ru.hxh.clientopenapi.api.model.ClientDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class ClientApiService {

    List<Client> db = new ArrayList<>();

    public Client show(Long clientId) {
        if(db.isEmpty())
            fillDB();
        return db.stream().filter(client -> client.getId().equals(clientId)).findFirst().get();
    }

    public Void create(ClientDTO client) {
        if(db.isEmpty())
            fillDB();
        Client newClient = new Client().name(client.getName()).number(client.getNumber()).tag(client.getTag());
        HashMap<String, Object> hm = new HashMap<>();
        hm.put("Id", 1L);
        hm.put("Contains", 0);
        db.forEach(oldClient -> {
            Long oldId = oldClient.getId();
            Long newId = (Long) hm.get("Id");
            if(oldId > newId)
                newId = oldId;
            if(Objects.equals(oldClient.getName(), newClient.getName()) &&
                    Objects.equals(oldClient.getNumber(), newClient.getNumber()) &&
                    Objects.equals(oldClient.getTag(), newClient.getTag())) {
                hm.put("Contains", 1);
            }
            hm.put("Id", newId);
        });
        if((Integer) hm.get("Contains") != 1)
            db.add(newClient.id(((Long) hm.get("Id")) + 1L));
        return null;
    }

    public List<ClientDTO> show() {
        if(db.isEmpty())
            fillDB();
        List<ClientDTO> clientsList = new ArrayList<>();
        db.forEach(client -> {
            clientsList.add(new ClientDTO()
                    .name(client.getName())
                    .tag(client.getTag())
                    .number(client.getNumber()));
        });
        return clientsList;
    }
    private void fillDB() {
        db.add(new Client().id(1L).name("Andrey").number("AAA111").tag("client"));
        db.add(new Client().id(2L).name("Misha").number("AAA112").tag("client"));
        db.add(new Client().id(3L).name("Misha").number("BBB111").tag("client"));
        db.add(new Client().id(4L).name("Gera").number("AAB133").tag("client"));
    }
}
