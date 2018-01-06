package pl.put.poznan.buildings.utils;

import javax.swing.*;
import java.io.*;

public class FileManager {

    public static String readFile(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        String ls = System.getProperty("line.separator");
        try {
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append(ls);
            }

            return stringBuilder.toString();
        } finally {
            reader.close();
        }
    }

    public static void saveFile(File file, String content) {
        try {
            PrintWriter writer = new PrintWriter(file);
            writer.print(content);
            writer.close();
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, Constants.EXPORT_FAILED);
        }
    }
}
