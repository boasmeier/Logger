package ch.hslu.vsk.logger.server.persistency;

public enum FileType {
    Basic,
    Enhanced,
    Xml,
    Json;
}

final class FileTypeExtension {

    static FileType getEnum(final String type) {
        switch (type) {
            case "enhanced":
                return FileType.Enhanced;
            default:
                return FileType.Basic;
        }
    }
}
