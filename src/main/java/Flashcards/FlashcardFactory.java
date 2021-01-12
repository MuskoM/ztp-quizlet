package Flashcards;

import Flashcards.FlashcardType;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class FlashcardFactory {

    private static Map<String, FlashcardType> flashcardTypes = new HashMap<>();

    public static FlashcardType createFlashcardType(String name,int lvl, Color color, int x, int y){

        if(flashcardTypes.containsKey(name)){
            return flashcardTypes.get(name);
        }else {

            FlashcardType flashcardType = new FlashcardType(x, y, lvl, color);
            flashcardTypes.put(name, flashcardType);

            return flashcardType;
        }
    }

    public static Map<String, FlashcardType> getFlashcardTypes() {
        return flashcardTypes;
    }

}
