package at.task16;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class TrelloBO {

    public static final String trello_KEY = "1ae0022ff76ef9314eea76c54aeee81f";
    public static final String trello_TOKEN = "ATTAc16b935228809b4ae8a8e04b0e351d11eeb31233748caaed8863596907785ee24F3A0CF8";
    public static final String card_ID = "68fe40174ab4083ca315a987";

    public TrelloCreateChecklistResModel createChecklist(String name) {
        HttpRequest createChecklistReq = HttpRequest
                .newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("https://api.trello.com/1/checklists?key=" + trello_KEY + "&token=" + trello_TOKEN + "&idCard=" + card_ID + "&name=" + name))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse createChecklistRes = client.send(createChecklistReq, HttpResponse.BodyHandlers.ofString());

            System.out.println(createChecklistRes);
            System.out.println("\nbody: " + createChecklistRes.body());


            TrelloCreateChecklistResModel trelloCreateCheck = new ObjectMapper().readValue(createChecklistRes.body().toString(), TrelloCreateChecklistResModel.class);
            System.out.println("\nbody obj: " +trelloCreateCheck);

            return trelloCreateCheck;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateCreateChecklistRes(TrelloCreateChecklistResModel actualRes, TrelloCreateChecklistResModel expectedRes) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(actualRes.getId());
        softAssert.assertEquals(actualRes.getName(), expectedRes.getName());
        softAssert.assertNotNull(actualRes.getIdBoard());

        softAssert.assertAll();
    }

    public TrelloAddItemResModel addItem(String checklist_ID, String AddItemName){
        HttpRequest addItemReq = HttpRequest
                .newBuilder()
                .POST(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("https://api.trello.com/1/checklists/" + checklist_ID + "/checkItems?name=" + AddItemName + "&key=" + trello_KEY + "&token=" + trello_TOKEN))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse addItemRes = client.send(addItemReq, HttpResponse.BodyHandlers.ofString());

            System.out.println(addItemRes);
            System.out.println("\nbody: " + addItemRes.body());

            TrelloAddItemResModel trelloAddItem = new ObjectMapper().readValue(addItemRes.body().toString(), TrelloAddItemResModel.class);
            System.out.println("\nbody obj: " + trelloAddItem);

            return trelloAddItem;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateAddItemRes(TrelloAddItemResModel actualRes, TrelloAddItemResModel expectedRes) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(actualRes.getId());

        softAssert.assertEquals(actualRes.getName(), expectedRes.getName());

        softAssert.assertAll();
    }

    public TrelloUpdateChecklistNameResModel updateCkecklistName(String checklist_ID, String newChecklistName) {
        HttpRequest updateChecklistNameReq = HttpRequest
                .newBuilder()
                .PUT(HttpRequest.BodyPublishers.noBody())
                .uri(URI.create("https://api.trello.com/1/checklists/" + checklist_ID + "?key=" + trello_KEY + "&token=" + trello_TOKEN + "&name=" + newChecklistName))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse updateChecklistNameRes = client.send(updateChecklistNameReq, HttpResponse.BodyHandlers.ofString());

            System.out.println(updateChecklistNameRes);
            System.out.println("\nbody: " + updateChecklistNameRes.body());

            TrelloUpdateChecklistNameResModel trelloUpdateChecklistName = new ObjectMapper().readValue(updateChecklistNameRes.body().toString(), TrelloUpdateChecklistNameResModel.class);
            System.out.println("\nbody obj: " + trelloUpdateChecklistName);

            return trelloUpdateChecklistName;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public void validateUpdateChecklistNameRes(TrelloUpdateChecklistNameResModel actualRes, TrelloUpdateChecklistNameResModel expectedRes) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(actualRes.getId());

        softAssert.assertEquals(actualRes.getName(), expectedRes.getName());

        softAssert.assertAll();
    }

    public void deleteChecklist(String checklist_ID) {
        HttpRequest deleteChecklistReq = HttpRequest
                .newBuilder()
                .DELETE()
                .uri(URI.create("https://api.trello.com/1/checklists/" + checklist_ID +
                        "?key=" + trello_KEY +
                        "&token=" + trello_TOKEN))
                .build();

        HttpClient client = HttpClient.newHttpClient();

        try {
            HttpResponse<String> deleteChecklistRes =
                    client.send(deleteChecklistReq, HttpResponse.BodyHandlers.ofString());

            System.out.println(deleteChecklistRes);
            System.out.println("\nbody: " + deleteChecklistRes.body());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}