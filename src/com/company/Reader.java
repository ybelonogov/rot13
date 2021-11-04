package com.company;

import java.io.*;
import java.util.Arrays;
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
            while (fin.available() !=0) {
                byte[] buffer = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
                fin.read(buffer, 0, Math.min(buffer.length,fin.available()));
                fos.write(rotate(CONFIG,buffer), 0, buffer.length);
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
    static byte[] rotate(final HashMap<String, String> CONFIG,byte[] data) {

        byte[] result = new byte[Integer.parseInt(CONFIG.get("bufferSize"))];
//        List<Byte> result_list=Arrays.asList(result);
        int shift = Integer.parseInt(CONFIG.get("shiftSize")) % Integer.parseInt(CONFIG.get("bufferSize"));
        byte[] result2 = Arrays.copyOfRange(data, 0, shift);
        byte[] result1 = Arrays.copyOfRange(data, shift, Integer.parseInt(CONFIG.get("bufferSize")));
        for (int i = 0; i < Integer.parseInt(CONFIG.get("bufferSize")); i++) {
            if (i < Integer.parseInt(CONFIG.get("bufferSize")) - shift)
                result[i] = result1[i];
            else
                result[i] = result2[i - (Integer.parseInt(CONFIG.get("bufferSize")) - shift)];
        }
    return result;
    }
}
