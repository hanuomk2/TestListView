/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestListView;

/**
 *
 * @author KM
 */
public class selectedItem {
    private String filepath;
    private boolean dirflag;
    private boolean fileflag;
    
    public String getfilepath() {
        return this.filepath;
    }
    
    public void setfilepath(String filepath) {
        this.filepath = filepath;
    }
    
    public boolean getdirflag() {
        return this.dirflag;
    }
    
    public void setdirflag(boolean dirflag) {
        this.dirflag = dirflag;
    }
    
    public boolean getfileflag() {
        return this.fileflag;
    }
    
    public void setfileflag(boolean fileflag) {
        this.fileflag = fileflag;
    }
    
    public void setitem(String filepath, boolean dirflag, boolean fileflag) {
        this.filepath = filepath;
        this.dirflag = dirflag;
        this.fileflag = fileflag;        
    }
    
    public selectedItem(String filepath, boolean dirflag, boolean fileflag) {
        this.filepath = filepath;
        this.dirflag = dirflag;
        this.fileflag = fileflag;
    }
}
