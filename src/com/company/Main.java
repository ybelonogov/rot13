package com.company;

import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.util.HashMap;

public class Main {
    public Main() {
    }

    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            System.exit(1);
        }

       final HashMap<String, String> CONFIG = Reader.readConfig(args[0]);
        Reader.read(CONFIG);

    }
}
