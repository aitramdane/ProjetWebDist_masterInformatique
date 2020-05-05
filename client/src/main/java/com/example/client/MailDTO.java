package com.example.client;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "Mail Model")
public class MailDTO {

    @ApiModelProperty(value = "Mail sender address")
    public final String MAIL_FROM = "noreply.htl7@gmail.com";

    @ApiModelProperty(value = "Client receiver id")
    private Integer clientId;

    @ApiModelProperty(value = "Email subject")
    private String emailSubject;

    @ApiModelProperty(value = "Email content")
    private String emailContent;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getEmailContent() {
        return emailContent;
    }

    public void setEmailContent(String emailContent) {
        this.emailContent = emailContent;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        this.emailSubject = emailSubject;
    }

}
