package com.example.client;

import org.springframework.data.domain.Page;

import java.util.List;

public interface IClientService  {

    public Client saveClient (Client client);

    public Client updateClient (Client client);

    public void deleteClient (Integer clientId);

    public boolean checkIfIdExists (Integer clientId);

    public Client findClientByEmail (String email);

    public List<Client> findClientByLastName (String lastName);

    public Client findClientById (Integer clientId);

    public Page<Client> getPaginatedClientsList(int begin, int end);



}
