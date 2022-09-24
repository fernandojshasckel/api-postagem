package br.edu.unisep.tads.apipostagem.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class ErrorResponse {
    
    private Date timestamp;
    private String status;
    private String mensagem;
    private String detalhes;

    public ErrorResponse(Date timestamp, String status, String mensagem, String detalhes) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensagem = mensagem;
        this.detalhes = detalhes;
    }
}
