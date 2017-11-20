package com.bytetrend.sandbox.java.challenge;

import java.nio.file.Path;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import static com.bytetrend.sandbox.java.challenge.FileStringSearch.TO_MB;
public class SearchReport {
    private AtomicInteger totalFileCount = new AtomicInteger(0);
    private AtomicInteger deniedAccessFileCount = new AtomicInteger(0);
    private long start;
    private long end;
    private Map<String, Optional<SearchResult>> results;

    public SearchReport() {
        results = new ConcurrentHashMap<>();
    }

    public void add(Path path, Optional<SearchResult> result) {
        add(path.toFile().getName(), result);
    }

    public void add(String path, Optional<SearchResult> result) {
        results.put(path, result);
    }

    public void remove(Path path) {
        results.remove(path.toFile().getName());
    }


    public void start() {
        start = System.currentTimeMillis();
    }

    public void end() {
        end = System.currentTimeMillis();
    }

    public void incrementFileCount() {
        totalFileCount.incrementAndGet();
    }

    public void incrementUnaccessibleCount() {
        deniedAccessFileCount.incrementAndGet();
    }

    void printReport() {
        int foundTextCount = results.values().stream().map(a -> (a.isPresent() ? a.get().getPostitions().size():0))
                .reduce(0,((a,b)-> a + b));
        long totalBytesSearched = results.values().stream().map(a -> (a.isPresent() ? a.get().getSize() : 0))
                .reduce(0L, ((a, b) -> a + b));
        System.out.println(String.format("%20s: %d - %-20s: %d", "Time elapsed (seconds)", (end - start) / 1000,
                "Search Rate MB/sec: ",((totalBytesSearched/TO_MB)/((end-start)/1000))));
        System.out.println(String.format("%20s: %d", "Total files/directories found", totalFileCount.get()));
        System.out.println(String.format("%20s: %d", "Total files/directories un-accessible", deniedAccessFileCount.get()));
        System.out.println(String.format("%-20s: %d - %-20s: %d - %-20s: %d - %-20s: %d",
                "Files searched: ", results.size(),
                "Total MB searched ", totalBytesSearched/TO_MB,
                "Text found: ",foundTextCount,
                "Cores count ",Runtime.getRuntime().availableProcessors()));
        results.forEach((key, value) ->
                value.ifPresent(x -> {
                    if (x.getPostitions().size() > 0)
                        System.out.println(String.format("%s %s:", key, x.toString()));
                })
        );

    }
}
