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
//        String data = "";
//        System.out.println(CONFIG);
//        FileReader reader = new FileReader(args[0]);
//        Throwable var4 = null;
//        try {
//            int c;
//            try {
//                while((c = reader.read()) != -1) {
//                    data = data.concat(String.valueOf((char)c));
//                }
//            } catch (Throwable var13) {
//                var4 = var13;
//                throw var13;
//            }
//        } finally {
//            if (reader != null) {
//                if (var4 != null) {
//                    try {
//                        reader.close();
//                    } catch (Throwable var12) {
//                        var4.addSuppressed(var12);
//                    }
//                } else {
//                    reader.close();
//                }
//            }
//
//        }
//
    }
}
