package com.dere.codesvalidate.models;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "code")
public class Code {
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    @Id
    @Length(min = 5, max = 5, message = "Invalid code Length")
    private String code;
    private boolean isUsed;

    public Code(){

    }
}
