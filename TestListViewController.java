/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TestListView;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeSet;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author KM
 */
public class TestListViewController implements Initializable {

    /**
     * Initializes the controller class.
     */
    //@FXML ListView<String> list;
    @FXML ListView<ImageSet> list;
    @FXML TextField text;
    @FXML ImageView imageview;
    
    private final ObservableList<ImageSet> items = FXCollections.observableArrayList();
    String holderPATH = "C:\\Users\\Public\\Pictures\\Sample Pictures"; 
    selectedItem item = new selectedItem(null, false, false);
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        setImageSetCell(holderPATH);
        text.setText(holderPATH);

        // リストビューが選択された時の処理
        list.getSelectionModel().selectedItemProperty().addListener(
            new ChangeListener<ImageSet>() {
                @Override
                public void changed(ObservableValue observable, ImageSet oldVal, ImageSet newVal) {
                    String str = text.getText() + "\\" + newVal.getFilename();
                    if (newVal.getDirflag() == true) item.setitem(str, true, false);
                    else item.setitem(str, false, true);
                }
            }
        );        
    }
    
    public void setImageSetCell(String dirname) {
        // TODO       
        //取得ディレクトリの有無確認
        File dir = new File(dirname);
        if(dir.isDirectory()) {
            System.out.println("\n取得ディレクトリ: " + dirname);
            holderPATH = dirname;
            items.clear();
        } else {
            // text.setText(dirNAME);
            return;
        }
        
        //取得拡張子の設定
        String[] filetype = {"bmp", "gif", "jpg", "jpeg", "png"};      
        
        //取得ディレクトリの子ディレクトリ・ファイルリストの取得
        File[] filelist = dir.listFiles();
        
        //子ディレクトリリストを作成
        Set<String> dirlist = new TreeSet();
        System.out.println("\ndirlist:");
        for(int j = 0; j < filelist.length; ++j) {
            if(filelist[j].isDirectory()) {
                String str = filelist[j].getName();                
                dirlist.add(str);
                System.out.println(str);
            }
        }
        
        //拡張子ごとにファイルリストを作成
        int n = filetype.length;
        Set<String>[] extfilelist = new TreeSet[n];
        for(int i = 0; i < n; ++i) {
            extfilelist[i] = new TreeSet<String>();
            System.out.println("\n" + filetype[i] + ":");
            for(int j = 0; j < filelist.length; ++j) {
                String str = filelist[j].getName();
                if(str.endsWith(filetype[i])) {
                    extfilelist[i].add(str);
                    System.out.println(str);
                }
            }
        }
        /*リストビューにアイテムを挿入*/
        list.setItems(items);
        list.setCellFactory(param -> new ImageSetCell());

        //子ディレクトリをImageSetとして挿入
        Image dirimg = new Image("file:C:\\Users\\...\\Documents\\NetBeansProjects\\JavaFX\\src\\TestListView\\Folder.png");
        for(String str : dirlist) {
            items.add(new ImageSet(dirimg, str, true));
        }
        
        //画像ファイルをImageSetとして挿入
        for(int i = 0; i < n; ++i) {
            for(String str : extfilelist[i]) {
                Image img = new Image("file:" + dirname + "\\" + str);
                items.add(new ImageSet(img, str, false));
            }
        }        
    }
     
    @FXML
    public void handleENTER(ActionEvent event) {
        //[ENTER]ボタンがクリックされた時の動作
        String str = text.getText();
        setImageSetCell(str);
    }
    
    @FXML
    public void handleHOLDERUP(ActionEvent event) {
        //[HOLDERUP]ボタンがクリックされた時の動作
        String str = holderPATH;
        String[] strArray = str.split("\\\\");
        str = "";
        for(int i = 0; i < strArray.length - 1; ++i) {
            str += strArray[i] + "\\";
        }      
        setImageSetCell(str);
        text.setText(holderPATH);         
    }

    @FXML
    public void handleHOLDEROPEN(ActionEvent event) {
        //[HOLDERON]ボタンがクリックされた時の動作
        String str = holderPATH;
        File dir = new File(str);       
        DirectoryChooser directoryChooser = new DirectoryChooser();
        
        directoryChooser.setInitialDirectory(dir);
        dir = directoryChooser.showDialog(null);
        if(dir != null) {
            str = dir.getPath();
            setImageSetCell(str);
            text.setText(holderPATH);         
        }
    }
    
    @FXML
    public void handleKEY1(KeyEvent event) {
        switch(event.getCode()) {
            //listviewで[ENTER]キーが押下された時の動作
            case ENTER:
                String str = item.getfilepath();
                if (str != null) {
                    if (item.getdirflag() == true) {
                        //選択しているのがフォルダーの場合
                        text.setText(str);
                        setImageSetCell(str);
                    }
                    else {
                        //選択しているのがファイル(画像)の場合
                        Image img = new Image("file:" + str);
                        imageview.setImage(img);
                    }
                }
            }
        }
       
    @FXML
    public void handleKEY2(KeyEvent event) {
        switch(event.getCode()) {
            //textfieldで[ENTER]キーが押下された時の動作
            case ENTER:
                String str = text.getText();
                setImageSetCell(str);        
                break;
            default:
        }
    }
    
    @FXML
    public void handleCLICK(MouseEvent event) {
        //listviewでマウスクリックされた場合
        String str = item.getfilepath();
        if (str != null) {
            if (item.getdirflag() == true) {
                //選択しているのがフォルダーの場合
                text.setText(str);
                setImageSetCell(str);
            }
            else {
                //選択しているのがファイル(画像)の場合
                Image img = new Image("file:" + str);
                imageview.setImage(img);
            }
        }
    }
}
