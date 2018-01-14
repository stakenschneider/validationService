package pack;

import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.BufferedReader;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Path("")
public class Resource {
    @PUT
    @Path("/{file}")

    /**
     * function accepting json files
     * @param stream
     * @param fileDetail data content disposition
     */
    public String checkFile(@PathParam("file") String file, InputStream stream) {

        final String jsonString = new BufferedReader(new InputStreamReader(stream)).lines().collect(Collectors.joining());

        JSONParser parser = new JSONParser();
        try {
            parser.parse(jsonString);
        } catch (ParseException  ex) {
            return makeError(ex.getErrorType(), ex.toString(), ex.getPosition(), file);
        }
        return jsonString;

//        JSONObject result = new JSONObject();
//        try {
//            result = (JSONObject) parser.parse(jsonString);
//        } catch (ParseException ex) {
//            try {
//                return Response.status(200).entity(parser.parse(makeError(ex.getErrorType(), ex.toString(), ex.getPosition(), file))).build();
//            }
//            catch (ParseException e){}
//        }
//        return Response.status(200).entity(result).build();
    }


    private  String makeError(int errorCode, String errorMessage, int errorPlace, String fileName) {
        JSONObject jsonObject = new JSONObject();
//        Map<String, String> jsonObject = new HashMap<>();
        jsonObject.put("errorCode", Integer.toString(errorCode));
        jsonObject.put("errorMessage", errorMessage);
        jsonObject.put("errorPlace", Integer.toString(errorPlace));
        jsonObject.put("resource", fileName);

        return jsonObject.toJSONString();
    }
}
