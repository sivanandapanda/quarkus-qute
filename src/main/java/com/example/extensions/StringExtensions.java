package com.example.extensions;

import java.time.format.DateTimeFormatter;

import com.example.model.Entry;

import io.quarkus.qute.TemplateExtension;

@TemplateExtension
public class StringExtensions {

    public static String toDateStr(Entry entry) {
        return entry.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm:ss"));
    }

    public static String itemsAsStr(Entry entry) {
        return String.join(",", entry.items);
    }

}
