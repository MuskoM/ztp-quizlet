import Flashcards.FlashcardFactory;
import Flashcards.FlashcardType;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import  org.junit.Assert;

import static org.junit.Assert.assertEquals;
public class FlyweightTests {

    static FlashcardFactory flashcardFactory;

    @BeforeClass
    public static void createFlashcardFactory(){

        flashcardFactory = new FlashcardFactory();
        flashcardFactory.createFlashcardType("test",3, Color.BLACK,12,10);
        flashcardFactory.createFlashcardType("test2",1,Color.RED,10,10);

    }

    @Test
    public void checkIfTypesExist(){
        Assert.assertNotNull(flashcardFactory.getFlashcardTypes());
    }

    @Test
    public void createExistingFlashcardType(){

        flashcardFactory.getFlashcardTypes().forEach((s, flashcardType) -> System.out.println(flashcardType.getColor()));

        FlashcardType f =  flashcardFactory.createFlashcardType("test",2,Color.CYAN,5,5);

        System.out.println(f.getColor());

        flashcardFactory.getFlashcardTypes().forEach((s, flashcardType) -> System.out.println(flashcardType.getColor()));

    }

}

