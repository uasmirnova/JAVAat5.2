package ru.netology.testinfo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Info {

    private String login;
    private String password;
    private String status;
}
