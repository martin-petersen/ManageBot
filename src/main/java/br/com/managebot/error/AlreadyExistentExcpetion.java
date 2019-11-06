package br.com.managebot.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ALREADY_REPORTED)
public class AlreadyExistentExcpetion extends RuntimeException {
    public AlreadyExistentExcpetion(String message) {
        super(message);
    }
}
