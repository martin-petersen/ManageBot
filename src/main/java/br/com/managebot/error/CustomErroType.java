package br.com.managebot.error;

public class CustomErroType {
    private String errorMessage;

    public CustomErroType(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
