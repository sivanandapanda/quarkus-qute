package com.example.lifecycle;

import java.time.LocalDateTime;
import java.util.Arrays;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.example.model.Entry;
import com.example.service.EntryService;

import org.jboss.logging.Logger;

import io.quarkus.runtime.StartupEvent;

@ApplicationScoped
public class AppLifeCycleBean {

    private static final Logger LOGGER = Logger.getLogger("ListenerBean");

    @Inject
    EntryService entryService;

    void onStart(@Observes StartupEvent event) {
        LOGGER.info("The application is starting...");

        Entry entry = new Entry();
        entry.name = "Startup entry";
        entry.dateTime = LocalDateTime.now();
        entry.items = Arrays.asList("startup", "app lifecycle");
        entryService.addEntry(entry);
    }
}
