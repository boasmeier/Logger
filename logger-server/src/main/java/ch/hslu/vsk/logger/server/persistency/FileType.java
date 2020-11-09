package ch.hslu.vsk.logger.server.persistency;

public enum FileType {
    Basic,
    Enhanced,
    Xml,
    Json;
}

class FileTypeExtension {
    static FileType getEnum(String type) {
        switch (type) {
            case "enhanced": return FileType.Enhanced;
            case "xml": return FileType.Xml;
            case "json": return FileType.Json;
            default: return FileType.Basic;
        }
    }
}
