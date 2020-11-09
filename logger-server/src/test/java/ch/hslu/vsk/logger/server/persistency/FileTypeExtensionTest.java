package ch.hslu.vsk.logger.server.persistency;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    @Test
    void testGetEnumXml() {
        final FileType expected = FileType.Xml;
        final FileType actual = FileTypeExtension.getEnum("xml");
        assertEquals(expected, actual);
    }

    @Test
    void testGetEnumJson() {
        final FileType expected = FileType.Json;
        final FileType actual = FileTypeExtension.getEnum("json");
        assertEquals(expected, actual);
    }
}
