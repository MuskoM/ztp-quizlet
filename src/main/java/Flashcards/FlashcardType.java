package Flashcards;

import java.awt.*;

public class FlashcardType {

    public static  final String ANSWER_CHOICE = "choice";
    public static  final String ANSWER_WRITTEN = "written";

    private Color color;
    private int x;
    private int y;
    private int lvl;
    private String inputType;

    FlashcardType( int _x, int _y, int _lvl, Color _color){
        x = _x;
        y = _y;
        color = _color;
        lvl = _lvl;
        if(lvl<4){
            inputType = ANSWER_CHOICE;
        }else{
            inputType = ANSWER_WRITTEN;
        }

    }

    public Color getColor() {
        return color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getLvl() {
        return lvl;
    }

    public String getInputType() {
        return inputType;
    }

    @Override
    public String toString() {
        return "FlashcardType{" +
                "color=" + color +
                ", x=" + x +
                ", y=" + y +
                ", lvl=" + lvl +
                ", inputType='" + inputType + '\'' +
                '}';
    }
}
