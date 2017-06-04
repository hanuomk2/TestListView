/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestListView;

import javafx.scene.image.Image;

/**
 *
 * @author KM
 */
public class ImageSet {
    private Image image;
    private String filename;
    private boolean dirflag;
    
    public String getFilename() {
        return filename;
    }
    
    public void setFilename(String filename) {
        this.filename = filename;
    }
    
    public Image getImage() {
        return image;
    }
    
    public void setImage(Image image) {
        this.image = image;
    }

    public boolean getDirflag() {
        return dirflag;
    }
    
    public void setDirflag(boolean dirflag) {
        this.dirflag = dirflag;
    }
  
    public ImageSet(Image image, String filename, boolean dirflag) {
        this.image = image;
        this.filename = filename;
        this.dirflag = dirflag;
    }
}
