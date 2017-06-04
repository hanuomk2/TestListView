/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestListView;

import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 *
 * @author KM
 */
public class ImageSetCell extends ListCell<ImageSet>{
    private final VBox vbox;
    private final ImageView imageview;
    private final Text filename;
    
    public ImageSetCell() {
        vbox = new VBox();
        imageview = new ImageView();
        filename = new Text();
        
        imageview.setFitWidth(96);
        imageview.setFitHeight(96);

        VBox.setVgrow(imageview, Priority.NEVER);
        VBox.setVgrow(filename, Priority.NEVER);
        
        vbox.getChildren().addAll(imageview, filename); 
    }
    
    @Override
    protected void updateItem(ImageSet item, boolean empty) {
        super.updateItem(item, empty);
        
        if(empty) {
            setText(null);
            setGraphic(null);
        } else {
            imageview.setImage(item.getImage());
            filename.setText(item.getFilename());
            setGraphic(vbox);
        }
    }
}
