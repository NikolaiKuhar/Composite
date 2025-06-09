package lt.esdc.text.reader;

import lt.esdc.text.exception.TextException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextReader {

    private static final Logger logger = LogManager.getLogger(TextReader.class);

    public String read(Path path) throws TextException {
        try {
            logger.info("Чтение файла: {}", path);
            String content = Files.readString(path);
            logger.debug("Прочитано {} символов", content.length());
            return content;
        } catch (IOException e) {
            logger.error("Ошибка при чтении файла: {}", path, e);
            throw new TextException("Ошибка чтения файла: " + path, e);
        }
    }
}
