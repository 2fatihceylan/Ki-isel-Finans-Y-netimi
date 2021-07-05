package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TcmbKur {
    LocalDate today=LocalDate.now();
    ResultSet rs;
    ObservableList<Currency> data;



    public TcmbKur(String kullaniciadi,int id,Stage stage,LocalDate date){

        VBox pane=new VBox();
        HBox hBox1=new HBox();
        HBox hBox2=new HBox();
        HBox hBox3=new HBox();
        pane.getChildren().add(hBox1);
        pane.getChildren().add(hBox3);
        pane.getChildren().add(hBox2);

        hBox3.setSpacing(20);
        hBox3.setPadding(new Insets(10,10,100,260));

        pane.setStyle("-fx-background-color: #F0F8FF;");

        TableView<Currency> tableView=new TableView<>();



        TableColumn curr = new TableColumn("Döviz Cinsi");
        curr.setMinWidth(200);
        curr.setCellValueFactory(
                new PropertyValueFactory<>("currency"));

        TableColumn ccode = new TableColumn("Döviz Kodu");
        ccode.setMinWidth(200);
        ccode.setCellValueFactory(
                new PropertyValueFactory<>("currencycode"));

        TableColumn forexb = new TableColumn("Döviz Alış");
        forexb.setMinWidth(70);
        forexb.setCellValueFactory(
                new PropertyValueFactory<>("forexbuying"));

        TableColumn forexs = new TableColumn("Döviz Satış");
        forexs.setMinWidth(70);
        forexs.setCellValueFactory(
                new PropertyValueFactory<>("forexselling"));

        TableColumn bankb = new TableColumn("Efektif Alış");
        bankb.setMinWidth(70);
        bankb.setCellValueFactory(
                new PropertyValueFactory<>("banknotebuying"));

        TableColumn banks = new TableColumn("Efektif Satış");
        banks.setMinWidth(70);
        banks.setCellValueFactory(
                new PropertyValueFactory<>("banknoteselling"));


        DatePicker datePicker=new DatePicker();





        String sorgu=("select * from veritabani7.currency where date = ?");
        try {
            //PreparedStatement ps = getConnection().prepareStatement(sorgu);
            PreparedStatement ps = DbConnect.dbConnect().prepareStatement(sorgu);

            ps.setString(1,date.toString());

            rs=ps.executeQuery();


            data=FXCollections.observableArrayList();
            while (rs.next()) {

                data.add(new Currency(rs.getString("currency").toString(), rs.getString("currency_code"), rs.getString("forex_buying"), rs.getString("forex_selling"), rs.getString("banknote_buying"), rs.getString("banknote_selling")));

            }


            tableView.setItems(data);

            tableView.setPrefSize( 750, 510 );

            tableView.getColumns().addAll(curr,ccode,forexb,forexs,bankb,banks);





        } catch (Exception e) {
            e.printStackTrace();
        }

        Label lblDate=new Label("-");
        datePicker.setOnAction(event -> {
            TcmbKur obje=new TcmbKur(kullaniciadi,id,stage,datePicker.getValue());
            //datePicker.getEditor().setText(datePicker.getValue().toString());
            //datePicker.setAccessibleText(datePicker.getValue().toString());
           // lblDate.setText(datePicker.getValue().toString());
            //datePicker.setValue(datePicker.getValue());

        });




        Button btnkurblgr=new Button("günlük kur bilgilerini çek");
        btnkurblgr.setOnAction(event -> {
            TcmbValues a=new TcmbValues();
            TcmbKur obje=new TcmbKur(kullaniciadi,id,stage,today);
        });
        Button buttongeri=new Button("Geri");
        buttongeri.setOnAction(event -> {
            Chart obje=new Chart(kullaniciadi,id,stage);
        });


        hBox1.getChildren().add(tableView);
        hBox3.getChildren().addAll(datePicker);
        hBox2.getChildren().addAll(buttongeri,btnkurblgr);
        stage.setScene(new Scene(pane,710,800));
        stage.setTitle("Tcmb Kur");
        stage.centerOnScreen();
        stage.show();
    }


}
