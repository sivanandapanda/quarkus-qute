package com.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.example.model.Entry;
import com.example.service.EntryService;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;

@Path("/")
public class EntryResource {

    @Inject
    Template index;

    @Inject
    EntryService service;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance hello() {
        List<Entry> entries = service.getEntries();
        return index.data("entries", entries);
    }

    @POST
    @Path("entry")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addEntry(Entry entry, @Context UriInfo uriInfo) {
        entry.dateTime = LocalDateTime.now();
        service.addEntry(entry);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        uriBuilder.path(entry.name);
        return Response.created(uriBuilder.build()).build();
    }

    @GET
    @Path("entry")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Entry> getEntries() {
        return service.getEntries();
    }

    @GET
    @Path("entry/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getEntry(@PathParam("name") String name) {
        Optional<Entry> entry = service.getEntry(name);

        if (entry.isPresent()) {
            return entry;
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}