import javax.swing.*;
import java.util.List;

public class ListModelAdapter extends AbstractListModel {

    private List<String> collections;



    public ListModelAdapter(List<String> collections) {

        this.collections = collections;
    }



    @Override
    public int getSize() {
        return collections.size();
    }

    @Override
    public Object getElementAt(int index) {
        return collections.get(index);
    }




}
