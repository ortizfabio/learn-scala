package com.bytetrend.sandbox.java.challenge;

import java.util.ArrayList;
import java.util.List;

public class SearchResult {
    private String filename;
    private long size;
    private long durationInMillis;
    private List<Long> postitions = new ArrayList<>();

    SearchResult(String filename, long size, long durationInMillis, List<Long> positions) {
        if (positions != null)
            this.postitions = positions;
        this.filename = filename;
        this.size = size;
        this.durationInMillis = durationInMillis;
    }

    public String getFilename() {
        return filename;
    }

    public long getSize() {
        return size;
    }

    public long getDurationInMillis() {
        return durationInMillis;
    }

    public List<Long> getPostitions() {
        return postitions;
    }
    @Override
    public String toString(){
        return String.format("size:%d found: %d %s",size,postitions.size(),postitions.toString());
    }
}
