package app;
import common.Graph;
import common.GraphIO;
import graph.scc.TarjanSCC;
import graph.scc.Condensation;
import graph.topo.KahnTopo;
import graph.dagsp.DagShortest;
import graph.dagsp.DagLongest;
import metrics.SimpleMetrics;
import metrics.Metrics;
import java.nio.file.*;
import java.io.IOException;
import java.util.*;
public class Main {
    private static final Path DATA_DIR = Path.of("data");
    private static final Path OUTPUT_DIR = Path.of("output");
    private static final List<String> FILES = List.of(
            "graph_large_1.json","graph_large_2.json","graph_large_3.json",
            "graph_medium_1.json","graph_medium_2.json","graph_medium_3.json",
            "graph_small_1.json","graph_small_2.json","graph_small_3.json"
    );
    public static void main(String[] args) throws Exception {
        if (args.length == 0) { printUsage(); return; }
        Files.createDirectories(OUTPUT_DIR);
        switch (args[0]) {
            case "generate":
                generator.DatasetGenerator.generateExample(DATA_DIR);
                System.out.println("Generated datasets under /data");
                break;
            case "run-all":
                runAll();
                break;
            case "run":
                if (args.length < 2) { System.err.println("run <filename>"); return; }
                runSingle(DATA_DIR.resolve(args[1]));
                break;
            default: printUsage();
        }
    }
    private static void printUsage() {
        System.out.println("Usage: java -jar ... generate | run-all | run <file>");
    }
    private static void runAll() throws Exception {
        Path outCsv = OUTPUT_DIR.resolve("result.csv");
        ResultWriter writer = new ResultWriter(outCsv);
        for (String f : FILES) {
            Path p = DATA_DIR.resolve(f);
            if (!Files.exists(p)) { System.err.println("Missing: " + p); continue; }
            runAndWrite(p, writer);
        }
        System.out.println("All done. Results -> " + outCsv);
    }
    private static void runAndWrite(Path p, ResultWriter writer) {
    }
    private static void runSingle(Path path) throws Exception {
        Path outCsv = OUTPUT_DIR.resolve("result.csv");
        ResultWriter writer = new ResultWriter(outCsv);
        runAndWrite(path, writer);
        System.out.println("Done for " + path);
    }
}