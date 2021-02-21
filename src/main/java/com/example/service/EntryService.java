package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.inject.Singleton;

import com.example.model.Entry;

@Singleton
public class EntryService {

    private List<Entry> entries = new ArrayList<>();

    public List<Entry> getEntries() {
        return entries;
    }

    public void addEntry(Entry entry) {
        entries.add(entry);
    }

    public Optional<Entry> getEntry(String name) {
        return entries.stream().filter(e -> e.name.equals(name)).findAny();
    }
}
