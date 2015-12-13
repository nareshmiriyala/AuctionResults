package com.dellnaresh.biz;

import com.dellnaresh.enums.Constants;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.text.ParseException;
import java.util.List;
import static java.nio.file.StandardWatchEventKinds.*;
/**
 * Created by nareshm on 30/11/2015.
 */
public abstract class FileListener {

    public void registerWatcher(String dir) throws IOException, ParseException {
        WatchService watchService = FileSystems.getDefault().newWatchService();
        Path path = Paths.get(dir);

        WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE,StandardWatchEventKinds.ENTRY_MODIFY);
        for (;;) {

            WatchKey key;

            try {
                System.out.println("Waiting for file change to be signalled...");
                key = watchService.take();
            }
            catch (InterruptedException ex) {
                System.out.println("Interrupted Exception");
                return;
            }

            List<WatchEvent<?>> eventList = key.pollEvents();
            System.out.println("Process the pending events for the key: " + eventList.size());

            for (WatchEvent<?> genericEvent: eventList) {

                WatchEvent.Kind<?> eventKind = genericEvent.kind();
                System.out.println("Event kind: " + eventKind);

                if (eventKind == OVERFLOW) {

                    continue; // pending events for loop
                }

                WatchEvent pathEvent = (WatchEvent) genericEvent;
                Path file = (Path) pathEvent.context();
                System.out.println("File name: " + file.toString());
                try {
                    doProcessing(file);
                }catch (Exception e){
                    e.printStackTrace();
                }

            }

            boolean validKey = key.reset();
            System.out.println("Key reset");
            System.out.println("");

            if (! validKey) {
                System.out.println("Invalid key");
                break; // infinite for loop
            }

        } // end infinite for loop

        watchService.close();
        System.out.println("Watch service closed.");
    }

    public abstract void doProcessing(Path filePath) throws IOException, ParseException;


}
