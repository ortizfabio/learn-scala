package com.bytetrend.sandbox.java.challenge;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.*;

import static com.bytetrend.sandbox.java.challenge.FileSearch.indexesOf;

public class FileStringSearch {
    static private int TO_MB = 1024 * 1024;
    private ExecutorService executorService;
    private Path startFolder;
    private ConcurrentLinkedQueue<Future<?>> futuresList = new ConcurrentLinkedQueue<>();
    private Queue<Callable<?>> rejected = new ConcurrentLinkedQueue();
    private String searchText;
    private SearchReport report = new SearchReport();

    public FileStringSearch(int threadCount, String searchText, String rootDir) {

        executorService = new ThreadPoolExecutor(0, threadCount,
                1L, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2 * threadCount));
        startFolder = Paths.get(rootDir);
        this.searchText = searchText;
    }

    private void processResult(SearchResult result) {
        report.add(result.getFilename(), Optional.of(result));
    }

    private void printMemoryFootprint() {
        Runtime runtime = Runtime.getRuntime();
        System.out.println(String.format("Used Memory: %10d MB - Free Memory: %10d MB"
                , (runtime.totalMemory() - runtime.freeMemory()) / TO_MB, runtime.freeMemory() / TO_MB));
    }

    public void doWork() {
        printMemoryFootprint();
        report.start();
        futuresList.add(executorService.submit(new FindFilesTask(startFolder)));
        while (futuresList.size() > 0) {
            try {
                for (Future<?> f : futuresList) {
                    if (f.isDone()) {
                        try {
                            futuresList.remove(f);
                            Object result = f.get(10L, TimeUnit.MILLISECONDS);
                             if (result instanceof Optional) {
                                ((Optional) result).ifPresent(x -> processResult((SearchResult) x));
                            }
                        } catch (TimeoutException | InterruptedException | ExecutionException e) {
                        }

                    }
                    while (rejected.size() > 0) {
                        for (Callable<?> task : rejected) {
                            rejected.remove(task);
                            tryTaskSubmit(task);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        printMemoryFootprint();
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            System.out.println("Executor service terminating now.");
        } finally {
            if (!executorService.isTerminated()) {
                System.out.println("canceling non-finished tasks");
            }
            executorService.shutdownNow();
//            System.out.println("shutdown finished");
        }
        report.end();
    }


    private void tryTaskSubmit(Callable task) {
        try {
            futuresList.add(executorService.submit(task));
        } catch (RejectedExecutionException r) {
            if (task != null)
                rejected.add(task);
        }
    }


    class FindFilesTask implements Callable<String> {
        private Path path;

        public FindFilesTask(Path p) {
            path = p;
        }

        @Override
        public String call() throws Exception {
            File file;
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(path)) {
                for (Path entry : stream) {

                    report.incrementFileCount();
                    file = entry.toFile();
                    if (file.canRead()) {
                        if (file.isDirectory()) {
                            tryTaskSubmit(new FindFilesTask(entry));
                        } else {
                            //                           report.add(entry, Optional.empty());
                            tryTaskSubmit(new TextSearch(entry));
                        }
                    } else {
                        report.incrementUnaccessibleCount();
                    }
                }
            } catch (AccessDeniedException ade) {
                report.incrementUnaccessibleCount();
                // System.out.println("Skipping directory " + ade.getFile() + " reason: " + ade.getMessage());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return "Done " + path;
        }
    }

    public class TextSearch implements Callable<Optional<SearchResult>> {
        private Path path;

        public TextSearch(Path path) {
            this.path = path;
        }

        @Override
        public Optional<SearchResult> call() throws Exception {
            long start = System.currentTimeMillis();
            try {
                return Optional.of(new SearchResult(path.toString(), path.toFile().length(),
                        System.currentTimeMillis() - start, indexesOf(path, searchText)));
            } catch (IOException e) {
                report.incrementUnaccessibleCount();
                report.remove(path);
                //System.out.println(path.toFile().getName() + ": " + e.getMessage());
            }
            return Optional.empty();
        }
    }

    static public void main(String[] args) {
        FileStringSearch search = new FileStringSearch(Integer.parseInt(args[0]), args[1], args[2]);
        search.doWork();
        search.report.printReport();
        System.out.println("Exiting");
    }

}
