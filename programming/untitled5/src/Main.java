import client.Client;
import client.util.ClientInitializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import server.LocalDateTypeAdapter;
import server.business.LabWork;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main
{
    public static void main(String[] args)
    {

        String json = "[\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"2\",\n" +
                "      \"coordinates\": {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 3\n" +
                "      },\n" +
                "      \"creationDate\": \"2024-04-17\",\n" +
                "      \"minimalPoint\": 4,\n" +
                "      \"tunedInWorks\": 5,\n" +
                "      \"difficulty\": \"INSANE\",\n" +
                "      \"discipline\": {\n" +
                "        \"name\": \"6\",\n" +
                "        \"labsCount\": 7\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"2\",\n" +
                "      \"coordinates\": {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 3\n" +
                "      },\n" +
                "      \"creationDate\": \"2024-04-17\",\n" +
                "      \"minimalPoint\": 4,\n" +
                "      \"tunedInWorks\": 5,\n" +
                "      \"difficulty\": \"INSANE\",\n" +
                "      \"discipline\": {\n" +
                "        \"name\": \"6\",\n" +
                "        \"labsCount\": 7\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"2\",\n" +
                "      \"coordinates\": {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 3\n" +
                "      },\n" +
                "      \"creationDate\": \"2024-04-17\",\n" +
                "      \"minimalPoint\": 4,\n" +
                "      \"tunedInWorks\": 5,\n" +
                "      \"difficulty\": \"INSANE\",\n" +
                "      \"discipline\": {\n" +
                "        \"name\": \"6\",\n" +
                "        \"labsCount\": 7\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"2\",\n" +
                "      \"coordinates\": {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 3\n" +
                "      },\n" +
                "      \"creationDate\": \"2024-04-17\",\n" +
                "      \"minimalPoint\": 4,\n" +
                "      \"tunedInWorks\": 5,\n" +
                "      \"difficulty\": \"INSANE\",\n" +
                "      \"discipline\": {\n" +
                "        \"name\": \"6\",\n" +
                "        \"labsCount\": 7\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"2\",\n" +
                "      \"coordinates\": {\n" +
                "        \"x\": 3,\n" +
                "        \"y\": 3\n" +
                "      },\n" +
                "      \"creationDate\": \"2024-04-17\",\n" +
                "      \"minimalPoint\": 4,\n" +
                "      \"tunedInWorks\": 5,\n" +
                "      \"difficulty\": \"INSANE\",\n" +
                "      \"discipline\": {\n" +
                "        \"name\": \"6\",\n" +
                "        \"labsCount\": 7\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n";

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateTypeAdapter())
                .create();
        Type listType = new TypeToken<ArrayList<LabWork>>(){}.getType();
        List<LabWork> yourClassList = gson.fromJson(json, listType);


        System.out.println(yourClassList);


        Client client = ClientInitializer.initializeClient();
        client.start();


    }
}