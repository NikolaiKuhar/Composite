import lt.esdc.text.exception.TextException;
import lt.esdc.text.reader.TextReader;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class TextReaderExceptionTest {

    private final TextReader reader = new TextReader();

    @Test
    void testThrowsOnMissingFile() {
        Path fakePath = Path.of("nonexistent_file.txt");
        assertThrows(TextException.class, () -> reader.read(fakePath));
    }
}
