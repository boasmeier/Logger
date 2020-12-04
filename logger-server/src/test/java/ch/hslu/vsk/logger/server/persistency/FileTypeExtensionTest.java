package ch.hslu.vsk.logger.server.persistency;

import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class FileTypeExtensionTest {

    @Test
    void testGetEnumAny() {
        final FileType expected = FileType.Basic;
        final FileType actual = FileTypeExtension.getEnum(UUID.randomUUID().toString());
        assertEquals(expected, actual);
    }

    @Test
    void testGetEnumBasic() {
        final FileType expected = FileType.Basic;
        final FileType actual = FileTypeExtension.getEnum("basic");
        assertEquals(expected, actual);
    }

    @Test
    void testGetEnumEnhanced() {
        final FileType expected = FileType.Enhanced;
        final FileType actual = FileTypeExtension.getEnum("enhanced");
        assertEquals(expected, actual);
    }
}
