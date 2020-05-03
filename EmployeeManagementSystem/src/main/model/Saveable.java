package model;

import java.io.IOException;

public interface Saveable {
    static void save() throws IOException {
        System.out.println("Saving...");
    }
}
