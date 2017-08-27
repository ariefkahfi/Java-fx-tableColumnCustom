package sample;

import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.prism.NGNode;
import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Callback;
import sample.model.Person;

import java.util.ArrayList;
import java.util.List;

class CustomComboBox extends ComboBoxListCell<String> implements Callback<ListView<String>, ListCell<String>> {

    @Override
    public ListCell<String> call(ListView<String> param) {
        return new ComboBoxListCell<>(getItems());
    }

    public CustomComboBox(ObservableList<String> items) {
        super(items);
    }
}


class  MyProfile<L extends  Label , R extends Rectangle> extends GridPane{

    public MyProfile(L l , R r) {
        add(l,0,0);
        add(r,1,0);

    }
}

class CustomListCell  implements Callback<ListView<String>, ListCell<String>> {



    @Override
    public ListCell<String> call(ListView<String> param) {
        return  new ListCell<String>(){

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item,empty);

                if(item!=null){
                    Rectangle rect = new Rectangle(100,20);
                    rect.setFill(Color.web(item));

                    MyProfile<Label,Rectangle> my = new MyProfile<>(new Label(item),rect);

                    setGraphic(my);

                }
            }

        };
    }
}



public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        //ObservableList<String> obs = FXCollections.observableArrayList("data","Room");

        //ObservableList<String> rect = FXCollections.observableArrayList("blue","yellow","red");





        TableView<Person> tableView = new TableView<>();


        TableColumn<Person,Integer> colId = new TableColumn<>("ID");
        TableColumn<Person,String> colNama = new TableColumn<>("Nama");
        TableColumn<Person,Boolean> colBox = new TableColumn<>("Login Status");

        TableColumn buttonTest = new TableColumn("Action");

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));


        colNama.setCellValueFactory(new PropertyValueFactory<>("name"));

        colNama.setCellFactory(TextFieldTableCell.forTableColumn());

        colNama.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<Person, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<Person, String> event) {

                Person p = event.getRowValue();
                String newValue = event.getNewValue();

                p.setName(newValue);

                System.err.println(p.toString());
                System.err.println(newValue);

            }
        });

        tableView.setEditable(true);

        colNama.setEditable(true);

        colBox.setEditable(true);



        colBox.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Person, Boolean>, ObservableValue<Boolean>>() {
            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Person, Boolean> param) {
                Person p = param.getValue();

                SimpleBooleanProperty b = new SimpleBooleanProperty(p.isStatus());
                b.addListener(new ChangeListener<Boolean>() {
                    @Override
                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                        p.setStatus(newValue);
                    }
                });
                return b;
            }
        });
        colBox.setCellFactory(new Callback<TableColumn<Person, Boolean>, TableCell<Person, Boolean>>() {
            @Override
            public TableCell<Person, Boolean> call(TableColumn<Person, Boolean> param) {
                CheckBoxTableCell<Person,Boolean> cek = new CheckBoxTableCell<>();

                cek.setAlignment(Pos.CENTER);
                return cek;
            }
        });

        List<Person> data = new ArrayList<>();

        data.add(new Person(1,"Arief",true));
        data.add(new Person(2,"Putro",false));
        data.add(new Person(3,"Zack",false));

        tableView.getItems().addAll(data);



        buttonTest.setCellFactory(new Callback<TableColumn, TableCell>() {
            @Override
            public TableCell call(TableColumn param) {
                return new TableCell(){
                    @Override
                    protected void updateItem(Object item, boolean empty) {
                        super.updateItem(item, empty);

                        if(!empty){
                            Button delete =new Button("Delete");
                            delete.setOnAction(e->{
                                System.err.println("Aku diklik");
                            });
                            setAlignment(Pos.CENTER);
                            setGraphic(delete);
                        }
                    }
                };
            }
        });


        tableView.getColumns().addAll(colId,colNama,colBox,buttonTest);


        /*list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(newValue.equals("Room")){
                    list.setEditable(false);
                    list.setCellFactory(null);
                }else if(list.getSelectionModel().getSelectedIndex() == 0){
                    list.setEditable(true);
                    list.setCellFactory(new CustomComboBox(name));
                }
            }
        });*/

        BorderPane root = new BorderPane(tableView);
        primaryStage.setScene(new Scene(root,600,600));
        primaryStage.setResizable(false);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
