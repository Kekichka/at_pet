package task16;

//Make restAssured Test Cases using scenario from Task_15
//The same using any another API client.
//Add Request and Response clases for each unique endpoints.
//Validate Response Object using your own class comparator.

import at.task16.ConfigReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.UUID;

import static io.restassured.RestAssured.given;

public class Task16Test {

    @BeforeTest
    public void setUp(){
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    public void RestAssuredTest(){
        //Step 1: Create Board
        String boardName = UUID.randomUUID().toString().substring(0, 10);
        Response response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("name", boardName)
                .when()
                .post("https://api.trello.com/1/boards/?name={name}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), boardName, "Unexpected board name");
        String boardId = response.path("id");

        //Step 2: Create List
        String listName = UUID.randomUUID().toString().substring(0, 10);
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("boardId", boardId)
                .pathParam("list_name", listName)
                .when()
                .post("https://api.trello.com/1/lists?name={list_name}&idBoard={boardId}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), listName, "Unexpected list name");
        String listId = response.path("id");

        // Step 3: Create Card
        String cardName = UUID.randomUUID().toString().substring(0, 10);
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("listId", listId)
                .pathParam("name", cardName)
                .when()
                .post("https://api.trello.com/1/cards?idList={listId}&name={name}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), cardName, "Unexpected card name");
        String cardId = response.path("id");

        // Step 4: Create Checklist
        String checklistName = "Checklist_" + UUID.randomUUID().toString().substring(0, 6);
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("cardId", cardId)
                .pathParam("name", checklistName)
                .when()
                .post("https://api.trello.com/1/checklists?idCard={cardId}&name={name}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), checklistName, "Unexpected checklist name");
        String checklistId = response.path("id");

        // Step 5: Add Item to Checklist
        String itemName = "Item_" + UUID.randomUUID().toString().substring(0, 6);
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("checklistId", checklistId)
                .pathParam("name", itemName)
                .when()
                .post("https://api.trello.com/1/checklists/{checklistId}/checkItems?name={name}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), itemName, "Unexpected checklist item name");

        // Step 6: Update Checklist Name
        String updatedName = "Updated_" + UUID.randomUUID().toString().substring(0, 6);
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("checklistId", checklistId)
                .pathParam("name", updatedName)
                .when()
                .put("https://api.trello.com/1/checklists/{checklistId}?name={name}&key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertEquals(response.path("name"), updatedName, "Checklist name not updated");

        // Step 7: Delete Checklist
        response = given()
                .pathParam("trello_key", ConfigReader.getProp("trello_key"))
                .pathParam("trello_token", ConfigReader.getProp("trello_token"))
                .pathParam("checklistId", checklistId)
                .when()
                .delete("https://api.trello.com/1/checklists/{checklistId}?key={trello_key}&token={trello_token}")
                .then()
                .assertThat()
                .statusCode(200)
                .extract().response();

        Assert.assertNull(response.path("id"), "Checklist was not deleted properly");
    }
}
