// This file was generated with the help of AI (GPT-4)
//
// ---

package quizzify.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.FileWriter;
import java.io.FileReader;

public class FileUtils {
    // Helper methods for file operations (read/write JSON)
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public static void writeJsonToFile(Object obj, String filePath) throws Exception {
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(obj, writer);
        }
    }

    public static <T> T readJsonFromFile(String filePath, Class<T> clazz) throws Exception {
        try (FileReader reader = new FileReader(filePath)) {
            return gson.fromJson(reader, clazz);
        }
    }
} 