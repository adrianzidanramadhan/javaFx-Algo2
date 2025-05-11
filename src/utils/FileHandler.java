package utils;

import model.Siswa;
import java.io.*;
import java.util.*;

public class FileHandler {

    public static List<Siswa> readFromCSV(String filename) {
        List<Siswa> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Siswa s = new Siswa(parts[0], parts[1],
                        Double.parseDouble(parts[2]),
                        Double.parseDouble(parts[3]),
                        Double.parseDouble(parts[4])
                    );
                    list.add(s);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void writeToCSV(String filename, List<Siswa> list) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Siswa s : list) {
                bw.write(String.join(",", 
                    s.getNama(), s.getNim(),
                    String.valueOf(s.getNilai1()),
                    String.valueOf(s.getNilai2()),
                    String.valueOf(s.getNilai3())
                ));
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
