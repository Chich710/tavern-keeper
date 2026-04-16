package ru.product.dto.response;

import lombok.AllArgsConstructor;
import lombok.Setter;

//@Setter
//@AllArgsConstructor
public class ResponseDto {
    private String message;
    private String objectSuperPuper;

    public String getMessage() {
        return message;
    }

    public String getObjectSuperPuper() {
        return objectSuperPuper;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setObjectSuperPuper(String objectSuperPuper) {
        this.objectSuperPuper = objectSuperPuper;
    }
}
