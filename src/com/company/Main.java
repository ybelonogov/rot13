package com.company;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.io.Reader;
import java.util.HashMap;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Wrong config name format ");
            System.exit(1);
        }

        try {
            final HashMap<String, String> CONFIG = Reader.readConfig(args[0]);
            try {
                Reader.read(CONFIG);
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("Wrong config, rule of config: output:='выходной фаил' ");
                System.out.println(" input:='входной фаил' ");
                System.out.println(" shiftSize:='размер сдвига' ");
                System.out.println(" bufferSize:='размер буфера' ");
                System.exit(1);
            } catch (FileNotFoundException e) {
                System.out.println("wrong input file name");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong buffer size, rule of config: bufferSize:='NUMBER bytes in buffer' ");
            System.exit(1);
        }catch (FileNotFoundException e) {
            System.out.println("wrong config file name");
            System.exit(1);
        }catch (IOException e) {
            System.out.println("что то с конфигом,input error ");
            System.exit(1);
        }
    }
}
