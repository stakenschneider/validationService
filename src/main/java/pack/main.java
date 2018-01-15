package pack;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

import java.net.URI;

/**
 * JSON Validation Service
 * @author masha
 */

public class main {

    public static final String BASE_URI = "http://0.0.0.0:80";

    public static HttpServer startServer() {
        ResourceConfig rc = new ResourceConfig().packages("pack");
        rc.register(MultiPartFeature.class);
        rc.register(Resource.class);

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) {
        startServer();
    }
}
