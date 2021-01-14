import Flashcards.Flashcard;
import javax.swing.table.AbstractTableModel;
import javax.xml.crypto.Data;
import java.util.List;

public class TableModelAdapter extends AbstractTableModel {

    private List<Flashcard> flashcards;
    private Database database;
    private String collection;
    private String searchField;

    public TableModelAdapter(List<Flashcard> flashcards, Database database, String _collection){
        this.flashcards = flashcards;
        this.database = database;
        this.collection = _collection;
    }

    @Override
    public int getRowCount() {
        return flashcards.size();
    }

    public void setFlashcards(List<Flashcard> flashcards, String _collection) {
        this.flashcards = flashcards;
        this.collection = _collection;
        fireTableStructureChanged();
    }


    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if (columnIndex == 0){
            searchField = flashcards.get(rowIndex).getLanguageWord();
            return searchField;
        }else {
            searchField = flashcards.get(rowIndex).getTranslatedWord();
            return searchField;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public String getColumnName(int column) {
        if (column == 0){
            return "LanguageWord";
        }else{
            return "TranslatedWord";
        }
    }


    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        if(columnIndex == 0){
            Flashcard f = flashcards.get(rowIndex);
            f.setLanguageWord(aValue.toString());
            flashcards.set(rowIndex,f);
            database.editFlashcard("languageWord",searchField,aValue.toString(),collection);

        }else{
            Flashcard f = flashcards.get(rowIndex);
            f.setTranslatedWord(aValue.toString());
            flashcards.set(rowIndex,f);
            database.editFlashcard("translatedWord",searchField,aValue.toString(),collection);
        }

    }
}
