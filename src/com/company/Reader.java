package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class Reader {
    final static String IN="input";
    final static String OUT="output";
    final static String BUFFER="bufferSize";
    final static String SHIFT="shiftSize";
    final static String SPLIT_CHAR=":=";


    static HashMap<String, String> readConfig(String configFile) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();
        FileReader reader = new FileReader(configFile);
        BufferedReader bReader = new BufferedReader(reader);
        String line = bReader.readLine();
        while (line != null) {
            line = line.trim();
            String[] words = line.split(SPLIT_CHAR);
            if (words.length == 2)
                result.put(words[0], words[1]);
            line = bReader.readLine();
        }
        bReader.close();
        if (Integer.parseInt(result.get(BUFFER))<=0) {
            System.out.println("buffer size < 1");
            System.exit(1);
        }
        return result;
    }

    static void read(final HashMap<String, String> CONFIG) throws IOException {
        try (FileInputStream fin = new FileInputStream(CONFIG.get(IN));
             FileOutputStream fos = new FileOutputStream(CONFIG.get(OUT), false)) {
            while (fin.available() != 0) {
                byte[] buffer = new byte[Integer.parseInt(CONFIG.get(BUFFER))];
                fin.read(buffer, 0, Math.min(buffer.length, fin.available()));
//                System.out.println();
//                System.out.println();
//                for (byte b : buffer) {
//                    System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//                }
                fos.write(rotate(CONFIG, buffer), 0, buffer.length);
//                System.out.println();
            }

        }
    }


    static byte[] rotate(final HashMap<String, String> CONFIG, byte[] data) throws IOException {
        int len = Integer.parseInt(CONFIG.get(BUFFER)) * 8;
        int shift = Integer.parseInt(CONFIG.get(SHIFT)) % len;

        System.out.println();
        byte[] result = new byte[Integer.parseInt(CONFIG.get(BUFFER))];
        shift = shift + len;
        for (int i = 0; i < len; i++) {
            int num = i / 8;
            int bit = 7 - (i) % 8;
            int newBit = (i + shift) % len;
            int newNum = newBit / 8;
            newBit = 7 - newBit % 8;
            if (get(data[num], bit)) {
                result[newNum] |= 1 << newBit;
            }
        }
//        for (byte b : result) {
//            System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//        }
        return result;
    }

    static boolean get(byte b, int pos) {
        return (b & (1 << pos)) != 0;
    }
//
//    static void set(byte b, int pos) {
//        b |= 1 << pos;
//    }
}
