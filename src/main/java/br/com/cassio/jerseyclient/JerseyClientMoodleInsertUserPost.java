package br.com.cassio.jerseyclient;

/**
 *
 * @author cassioseffrin
 */
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.core.MultivaluedMap;

public class JerseyClientMoodleInsertUserPost {

    public static void main(String[] args) {
        try {

            Client client = Client.create();

            String token = "the token string";
            // You can obtain this token with this simple get request
            // http://yourdomain.com/moodle/login/token.php?username=admin&password=90Zpt89dt7@&service=usuarios

            String domainName = "http://yourdomain.com/moodle";
            String functionName = "core_user_create_users";
            String functionEnrol = "enrol_manual_enrol_users";

            String restformat = "json";

            if (restformat.equals("json")) {
                restformat = "&moodlewsrestformat=" + restformat;
            } else {
                restformat = "";
            }

            WebResource webResource = client
                    .resource("http://webservice.arpasistemas.com.br/moodle/webservice/rest/server.php" + "?wstoken=" + token + "&wsfunction="
                            + functionName + restformat);

            MultivaluedMap<String, String> paramlist = new MultivaluedMapImpl();

            paramlist.add("users[0][username]", "testusername78");
            paramlist.add("users[0][password]", "testuser#Ename7");
            paramlist.add("users[0][firstname]", "cassios7");
            paramlist.add("users[0][lastname]", "seffin27");
            paramlist.add("users[0][email]", "seffrin778@gmail.com");

            webResource.queryParams(paramlist);

            ClientResponse response = webResource.queryParams(paramlist).accept("application/json")
                    .post(ClientResponse.class);

            if (response.getStatus() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + response.getStatus());
            }

            String output = response.getEntity(String.class);

            System.out.println("Output from Server .... \n");
            System.out.println(output);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
