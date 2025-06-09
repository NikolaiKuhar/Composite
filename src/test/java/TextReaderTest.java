import lt.esdc.text.exception.TextException;
import lt.esdc.text.reader.TextReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderTest {

    private final TextReader reader = new TextReader();

    @Test
    void testReadValidFile() throws IOException, TextException {
        // Создаём временный файл
        Path tempFile = Files.createTempFile("test_text", ".txt");
        Files.writeString(tempFile, "Sample text for reading.");

        String result = reader.read(tempFile);
        assertNotNull(result);
        assertTrue(result.contains("Sample text"));

        Files.deleteIfExists(tempFile);
    }

    @Test
    void testReadInvalidFile() {
        Path fakePath = Path.of("nonexistent/file.txt");
        assertThrows(TextException.class, () -> reader.read(fakePath));
    }
}
