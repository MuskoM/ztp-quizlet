import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlashcardFactory {

    private Map<String,FlashcardType> flashcardTypes;

    public FlashcardFactory(){

        flashcardTypes = new HashMap<>();

    }

    public FlashcardType createFlashcardType(String name,int lvl, Color color, int x, int y){

        if(flashcardTypes.containsKey(name)){
            return flashcardTypes.get(name);
        }else {

            FlashcardType flashcardType = new FlashcardType(x, y, lvl, color);
            flashcardTypes.put(name, flashcardType);

            return flashcardType;
        }
    }

    public Map<String, FlashcardType> getFlashcardTypes() {
        return flashcardTypes;
    }

}
