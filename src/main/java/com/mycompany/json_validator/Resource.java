package com.mycompany.json_validator;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;
import java.util.Map;
import java.util.HashMap;
import javax.ws.rs.core.Response;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.stream.Collectors;

@Path("")
public class Resource {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)

    public Response checkFile(@FormDataParam("file") InputStream stream,
            @FormDataParam("file") FormDataContentDisposition fileDetail) throws Exception {
        final String json = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        return Response.status(200).entity(gson.toJson(json)).build();
    }

    private Map<String, String> makeError(JsonSyntaxException e, FormDataContentDisposition fileDetail) {
        String messageDetail = e.getCause().getMessage();
        Map<String, String> error = new HashMap<>();
        String[] arrayMessage = messageDetail.split("at", 2);
        error.put("resource", fileDetail.getFileName());
        error.put("errorPlace", arrayMessage[1]);
        error.put("errorMessage", arrayMessage[0]);
        return error;
    }
}
