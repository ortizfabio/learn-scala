package com.bytetrend.sandbox.java.challenge;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * based on Knuth-Morris-Pratt search
 *
 */
public class FileSearch {

    // Size of internal character buffer
    private static final int BUFFER_SIZE = 1024; // change to 1024;

    static public List<Long> indexesOf(Path path, String searchString) throws IOException {
        List<Long> found = new ArrayList<>();
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_SIZE);
        try (FileChannel reader = (new FileInputStream(path.toFile()).getChannel());) {
            int numCharsRead;
            int count = 0;
            long lastPosition = 0;
            while ((numCharsRead = reader.read(buffer)) > 0) {
                for (int c = 0; c < numCharsRead; c++) {
                    if (buffer.get(c) == searchString.charAt(count))
                        count++;
                    else
                        count = 0;
                    if (count == searchString.length()) {
                        found.add(lastPosition + (c - searchString.length()));
                        count = 0;
                    }
                }
                //Set the position so in case that the string runs between two reads
                //It can still be found.
                long newPosition = reader.position() - (searchString.length() - 1);
                if(newPosition > 0)
                    reader.position(newPosition);
                lastPosition = reader.position();
            }
        }
        return found;
    }
}
