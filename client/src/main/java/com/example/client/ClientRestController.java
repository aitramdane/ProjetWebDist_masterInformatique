package com.example.client;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/rest/client/api")
@Api(value = "Client Rest Controller: contains all operations for managing customers")
public class ClientRestController {

    public static final Logger LOGGER = LoggerFactory.getLogger(ClientRestController.class);

    @Autowired
    private ClientServiceImpl clientService;

    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * Ajoute un nouveau client dans la base de donnée H2. Si le client existe déjà, on retourne un code indiquant que la création n'a pas abouti.
     * @param clientDTORequest
     * @return
     */
    @PostMapping("/addClient")
    @ApiOperation(value = "Add a new Client in the Hotel", response = ClientDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 409, message = "Conflict: the client already exist"),
            @ApiResponse(code = 201, message = "Created: the client is successfully inserted"),
            @ApiResponse(code = 304, message = "Not Modified: the client is unsuccessfully inserted") })


    public ResponseEntity<ClientDTO> createNewClient(@RequestBody ClientDTO clientDTORequest) {

        Client existingClient = clientService.findClientByEmail(clientDTORequest.getEmail());
        if (existingClient != null) {
            return new ResponseEntity<ClientDTO>(HttpStatus.CONFLICT);
        }
        Client clientRequest = mapClientDTOToClient(clientDTORequest);
        clientRequest.setCreationDate(LocalDate.now());
        Client clientResponse = clientService.saveClient(clientRequest);
        if (clientResponse != null) {
            ClientDTO clientDTO = mapClientToClientDTO(clientResponse);
            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.CREATED);
        }
        return new ResponseEntity<ClientDTO>(HttpStatus.NOT_MODIFIED);
    }



    /**
     * Met à jour les données d'un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne un code indiquant que la mise à jour n'a pas abouti.
     * @param clientDTORequest
     * @return
     */
    @PutMapping("/updateClient")
    @ApiOperation(value = "Update/Modify an existing client in the Library", response = ClientDTO.class)
    @ApiResponses(value = { @ApiResponse(code = 404, message = "Not Found : the client does not exist"),
            @ApiResponse(code = 200, message = "Ok: the client is successfully updated"),
            @ApiResponse(code = 304, message = "Not Modified: the client is unsuccessfully updated") })
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO clientDTORequest) {

        if (!clientService.checkIfIdExists(clientDTORequest.getId())) {
            return new ResponseEntity<ClientDTO>(HttpStatus.NOT_FOUND);
        }
        Client clientRequest = mapClientDTOToClient(clientDTORequest);
        Client clientResponse = clientService.updateClient(clientRequest);
        if (clientResponse != null) {
            ClientDTO clientDTO = mapClientToClientDTO(clientResponse);
            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ClientDTO>(HttpStatus.NOT_MODIFIED);
    }



    /**
     * Supprime un client dans la base de donnée H2. Si le client n'est pas retrouvé, on retourne le Statut HTTP NO_CONTENT.
     * @param clientId
     * @return
     */
    @DeleteMapping("/deleteClient/{clientId}")
    @ApiOperation(value = "Delete a client in the hotel, if the client does not exist, nothing is done", response = String.class)
    @ApiResponses( value =  {
            @ApiResponse(code = 204, message = "No Content: client successfully deleted")

    } )

    public ResponseEntity<String> deleteClient(@PathVariable Integer clientId) {
        clientService.deleteClient(clientId);
        return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
    }


    @GetMapping("/paginatedSearch")
    @ApiOperation(value="List clients  of the hotel in a paginated way", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfully listed"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<ClientDTO>> searchClients(@RequestParam("beginPage") int beginPage,
                                                         @RequestParam("endPage") int endPage) {

        Page<Client> clients = clientService.getPaginatedClientsList(beginPage, endPage);
        if (clients != null) {
            List<ClientDTO> clientDTOs = clients.stream().map(client -> {
                return mapClientToClientDTO(client);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<ClientDTO>>(clientDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<ClientDTO>>(HttpStatus.NO_CONTENT);
    }



    /**
     * Retourne le client ayant l'adresse email passé en paramètre.
     * @param email
     * @return
     */
    @GetMapping("/searchByEmail")
    @ApiOperation(value="Search a client in the hotel by its email", response = ClientDTO.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<ClientDTO> searchCustomerByEmail(@RequestParam("email") String email) {

        Client client = clientService.findClientByEmail(email);
        if (client != null) {
            ClientDTO clientDTO = mapClientToClientDTO(client);
            return new ResponseEntity<ClientDTO>(clientDTO, HttpStatus.OK);
        }
        return new ResponseEntity<ClientDTO>(HttpStatus.NO_CONTENT);
    }


    /**
     * Retourne la liste des clients ayant le nom passé en paramètre.
     * @param lastName
     * @return
     */
    @GetMapping("/searchByLastName")
    @ApiOperation(value="Search a client in the hotel by its Last name", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: successfull research"),
            @ApiResponse(code = 204, message = "No Content: no result founded"),
    })
    public ResponseEntity<List<ClientDTO>> searchClientByLastName(@RequestParam("lastName") String lastName) {

        List<Client> clients = clientService.findClientByLastName(lastName);
        if (clients != null && !CollectionUtils.isEmpty(clients)) {
            List<ClientDTO> clientDTOs = clients.stream().map(client -> {
                return mapClientToClientDTO(client);
            }).collect(Collectors.toList());
            return new ResponseEntity<List<ClientDTO>>(clientDTOs, HttpStatus.OK);
        }
        return new ResponseEntity<List<ClientDTO>>(HttpStatus.NO_CONTENT);
    }



    /**
     * Envoi un mail à un client. L'objet MailDTO contient l'identifiant et l'email du client concerné, l'objet du mail et le contenu du message.
     * @param resMailDto
     * @param uriComponentBuilder
     * @return
     */
    @PutMapping("/sendEmailToClient")
    @ApiOperation(value="Send an email to client of the hotel", response = String.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Ok: Email successfully sent"),
            @ApiResponse(code = 404, message = "Not Found: no customer found, or wrong email"),
            @ApiResponse(code = 403, message = "Forbidden: Email cannot be sent")

    })
    public ResponseEntity<Boolean> sendMailToClient(@RequestBody MailDTO resMailDto, UriComponentsBuilder uriComponentBuilder) {

        Client client = clientService.findClientById(resMailDto.getClientId());
        if (client == null) {
            String errorMessage = "The selected Client for sending email is not found in the database";
            LOGGER.info(errorMessage);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        } else if (client != null && StringUtils.isEmpty(client.getEmail())) {
            String errorMessage = "No existing email for the selected Customer for sending email to";
            LOGGER.info(errorMessage);
            return new ResponseEntity<Boolean>(false, HttpStatus.NOT_FOUND);
        }

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setFrom(resMailDto.MAIL_FROM);
        mail.setTo(client.getEmail());
        mail.setSentDate(new Date());
        mail.setSubject(resMailDto.getEmailSubject());
        mail.setText(resMailDto.getEmailContent());

        try {
            javaMailSender.send(mail);
        } catch (MailException e) {
            return new ResponseEntity<Boolean>(false, HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<Boolean>(true, HttpStatus.OK);
    }


    /**
     * Transforme une entité client  en un Plain old java object  ClientDTO
     *
     * @param client
     * @return
     */
    private ClientDTO mapClientToClientDTO(Client client) {
        ModelMapper mapper = new ModelMapper();
        ClientDTO clientDTO = mapper.map(client, ClientDTO.class);
        return clientDTO;
    }


    /**
     * Transforme un Plain Old Java Object  ClientDTO en en entity Client
     *
     * @param clientDTO
     * @return
     */
    private Client mapClientDTOToClient(ClientDTO clientDTO) {
        ModelMapper mapper = new ModelMapper();
        Client client = mapper.map(clientDTO, Client.class);
        return client;
    }




}
