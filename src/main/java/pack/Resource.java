package pack;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import com.google.gson.*;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

@Path("")
public class Resource {
    @PUT
    @Path("/{file}")

    /**
     * function accepting json files
     * @param file
     * @param stream
     */
    public Response checkFile(@PathParam("file") String file, InputStream stream) {

        final String jsonString = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Object result;
        int request_id = 0;
        try {
            result = gson.fromJson(jsonString, Object.class);
        } catch (JsonSyntaxException e) {
            String[] str = e.getCause().getMessage().split(".+: | at ");
            return Response.status(200).entity(gson.toJson(makeError(str[0], str[1], file, request_id, e.hashCode()))).build();
        } finally {
        request_id++;
        }
        return Response.status(200).entity(gson.toJson(result)).build();
    }


    private Map<String, String> makeError(String errorMessage, String errorPlace, String fileName, int requestID, int hashCode) {
        Map<String, String> map = new HashMap<>();
        map.put("errorMessage", errorMessage);
        map.put("errorPlace", errorPlace);
        map.put("resource", fileName);
        map.put("requestID", String.valueOf(requestID));
        map.put("errorCode", String.valueOf(hashCode));
        return map;
    }
}
