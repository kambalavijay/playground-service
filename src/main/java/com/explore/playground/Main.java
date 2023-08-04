package com.explore.playground;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    private String prop;

    public static void main(String[] args) throws InterruptedException, IOException {
        String s = "{0B4E8000B915F976}8yJl0w8COgZcXRZUXDyscV8UC+1MrDkCqmuZivOm1GY=";
        Pattern p = Pattern.compile("\\{(.*)}(.*)");
        Matcher m = p.matcher(s);
        if (m.find()) {
            String IV = m.group(1);
            System.out.println(IV);
            System.out.println(m.group(2));
        }
    }

}
