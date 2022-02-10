package com.example.proyecto.Email;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class ValidadorEmail implements Predicate<String> {
    public boolean test(String s){
        return true;
    }

}
