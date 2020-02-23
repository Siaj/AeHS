/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.app.aehs.web.commons;

import com.google.common.base.Charsets;
import com.google.common.hash.Hashing;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Iddrisu Sibdow SIAJ
 */
public class GenerateIDs {

    /*
    The SHA (Secure Hash Algorithm) is one of the popular cryptographic hash functions. 
A cryptographic hash can be used to make a signature for a text or a data file.

The SHA-256 algorithm generates an almost-unique, fixed-size 256-bit (32-byte) hash. 
This is a one-way function, so the result cannot be decrypted back to the original value.

Currently, SHA-2 hashing is widely used as it is being considered as the most secure hashing
algorithm in the cryptographic arena.
     */
    public static String generateHash(String password) {
        String output = Hashing.sha256().hashString(password, Charsets.UTF_8).toString();

        return output;
    }
    
     public static String generateUsername(String surname, String othername) {

        String initials = "";

        String[] splitOname = othername.split(" ");

        List<String> splittedName = new ArrayList<>();

        for (String s : splitOname) {
            if (!s.equals("")) {
                splittedName.add(s);
            }
        }

        for (String s : splittedName) {
            initials += s.substring(0, 1).toLowerCase();
        }

        return (initials.toLowerCase() + surname.toLowerCase());

    }
}
