package com.example.ProjectLib.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IClientDao extends JpaRepository<Client, Integer> {

    public List<Client> findClientByLastNameIgnoreCase (@Param("lastName") String  lastName);

    public Client findClientByEmailIgnoreCase (@Param("email")String email);
}
