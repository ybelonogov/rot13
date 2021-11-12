package com.company;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;

public class Reader {

    static final HashMap<String, String> readConfig(String configFile) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();
        try (FileReader reader = new FileReader(configFile)) {
            BufferedReader bReader = new BufferedReader(reader);
            String line = bReader.readLine();
            int flag = 1;
            while (line != null) {

                line = line.trim();
                String[] words = line.split(":=");
                if (words.length == 2)
                    result.put(words[0], words[1]);
                line = bReader.readLine();
            }
            bReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return result;
    }

    static void read(final HashMap<String, String> CONFIG) throws FileNotFoundException {
        try (FileInputStream fin = new FileInputStream(CONFIG.get("input"));
             FileOutputStream fos = new FileOutputStream(CONFIG.get("output"), false)) {
//            byte[] buffer = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
            while (fin.available() != 0) {
                byte[] buffer = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
                fin.read(buffer, 0, Math.min(buffer.length, fin.available()));
                System.out.println();
                System.out.println();
                for (byte b : buffer) {
                    System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
                }
//                System.out.println();
                fos.write(rotate(CONFIG,buffer), 0, buffer.length);
//            реализация 2
                BitSet data = BitSet.valueOf(buffer);
//                fos.write(rotate(CONFIG, data).toByteArray(), 0, buffer.length);

                System.out.println();
//                for (byte b : rotate(CONFIG, data).toByteArray()) {
////                    System.out.print(String.format("%x", b));
//                    System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//                }
            }
//            if (fin.available() >0){
//                byte[] buffer = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
//                fin.read(buffer, 0, fin.available());
//                System.out.println(Arrays.toString(buffer));
//                fos.write(buffer, 0, buffer.length);
//            }

        } catch (IOException ex) {

            System.out.println(ex.getMessage());
        }
    }



    static byte[] rotate(final HashMap<String, String> CONFIG, byte[] data) throws IOException {
        int len = Integer.parseInt(CONFIG.get("bufferSize")) * 8;
        int shift = Integer.parseInt(CONFIG.get("shiftSize")) % len;
        System.out.println();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] result = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
        shift=shift+len;
        for (int i=0;i<len;i++){
            int num=i/8;
            int bit=7-(i)%8;
            int newBit=(i + shift) % len;
            int newNum=newBit/8;
            newBit=7-newBit%8;
            if(get(data[num],bit)){
                result[newNum]|= 1<<newBit;
            }
//            for (byte b : result) {
//                System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
//            }
//            System.out.println();
        }
        for (byte b : result) {
            System.out.print(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return result;
    }
    static boolean get(byte b,int pos){
        return (b & (1 << pos)) != 0;
    }
    static void set(byte b,int pos){
        b |= 1 << pos;

    }
}
