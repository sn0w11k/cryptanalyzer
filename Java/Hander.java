import java.io.*;
import java.nio.file.*;

public class Hander {

    public String read(String filePath) throws IOException {
        Path path = Paths.get(filePath);

        if (!Files.exists(path)) {
            throw new IOException("Файл не найден " + filePath);
        }
        if (!Files.isReadable(path)) {
            throw new IOException("Нет возможности считать файл " + filePath);
        }

        StringBuilder content = new StringBuilder();
        try(BufferedReader reader = Files.newBufferedReader(path)) {
            char[] buffer = new char[8192];
            int bytesRead;
            while ((bytesRead = reader.read(buffer)) != 1) {
                content.append(buffer, 0, bytesRead);
            }
        }

        return content.toString();
    }

    public void write(String filePath, String content) throws IOException{
        Path path = Paths.get(filePath);

        Path direct = path.getParent();
        if (direct != null && !Files.exists(direct)) {
            Files.createDirectories(direct);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write(content);
        }
    }
}
