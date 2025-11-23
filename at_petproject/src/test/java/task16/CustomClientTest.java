package task16;

import at.task16.*;
import org.testng.annotations.Test;

import static at.task16.TrelloBO.card_ID;

public class CustomClientTest {

    @Test
    void trelloTest(){
        String name = "task15";

        TrelloBO trelloBO = new TrelloBO();

        TrelloCreateChecklistResModel actualRes = trelloBO.createChecklist(name);

        TrelloCreateChecklistResModel expectedRes = new TrelloCreateChecklistResModel();
        expectedRes.setName(name);
        expectedRes.setIdBoard(card_ID);
        trelloBO.validateCreateChecklistRes(actualRes, expectedRes);



        String addItemName = "task15AddItemTest";

        TrelloAddItemResModel actualAddItemRes = trelloBO.addItem(actualRes.getId(), addItemName);

        TrelloAddItemResModel expectedAddItemRes = new TrelloAddItemResModel();
        expectedAddItemRes.setName(addItemName);
        trelloBO.validateAddItemRes(actualAddItemRes, expectedAddItemRes);



        String newChecklistName = "UpdatedTask15";

        TrelloUpdateChecklistNameResModel actualUpdatedChecklistRes = trelloBO.updateCkecklistName(actualRes.getId(), newChecklistName);

        TrelloUpdateChecklistNameResModel expectedUpdatedChecklistRes  = new TrelloUpdateChecklistNameResModel();
        expectedUpdatedChecklistRes.setName(newChecklistName);
        trelloBO.validateUpdateChecklistNameRes(actualUpdatedChecklistRes, expectedUpdatedChecklistRes);



        trelloBO.deleteChecklist(actualRes.getId());
    }


}