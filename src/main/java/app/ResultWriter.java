package app;
import java.io.*;
import java.nio.file.Path;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
public class ResultWriter {
    private final Path outFile;
    private final boolean writeHeaders;
    public ResultWriter(Path outFile) throws IOException {
        this.outFile = outFile; this.writeHeaders = !outFile.toFile().exists();
        if (writeHeaders) {
            try (BufferedWriter w = java.nio.file.Files.newBufferedWriter(outFile, StandardCharsets.UTF_8)) {
                w.write("File,Vertex,Component,ShortestDistance,OptimalPath,CriticalPathLength,CriticalPath\n");
            }
        }
    }
    public synchronized void append(String file, int vertex, int component, String shortestDistance, String optimalPath, String criticalLen, String criticalPath) throws IOException {
        String line = String.format("%s,%d,%d,%s,%s,%s,%s\n",
                escape(file), vertex, component, escape(shortestDistance), escape(optimalPath), escape(criticalLen), escape(criticalPath));
        try (BufferedWriter w = java.nio.file.Files.newBufferedWriter(outFile, StandardCharsets.UTF_8, java.nio.file.StandardOpenOption.APPEND)) {
            w.write(line);
        }
    }
    private String escape(String s) { if (s == null) return ""; return '"' + s.replace("\"","'" ) + '"'; }
}
