package pl.put.poznan.buildings.utils;

import javax.swing.*;
import java.io.*;

/**
 * Class with static functions, used to manipulate files in uniform way
 */
public class FileManager {

    /**
     * static function to read content of selected file
     *
     * @param file absolute or non-absolute path to to file
     * @return content of a file in String
     * @throws IOException in situation when file does not exists or there was an error while trying to read data
     */
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

    /**
     * static function that saves content to selected file, removes any data inside the file
     * @param file Instance of File selected to save content inside of it
     * @param content of a file to be written to a file in form of a String
     */
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
