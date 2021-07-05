package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class CurrencyConverter {
    LocalDate today=LocalDate.now();



    public CurrencyConverter(Stage stage, String kullaniciadi, int id){
        VBox vBox1=new VBox();
        HBox hBox1=new HBox();
        HBox hBox2=new HBox();
        HBox hBox3=new HBox();
        HBox hBox4=new HBox();
        HBox hBox5=new HBox();
        vBox1.setStyle("-fx-background-color: #F0F8FF;");
        vBox1.getChildren().addAll(hBox1,hBox2,hBox3,hBox4,hBox5);
        hBox1.setPadding(new Insets(50,10,10,90));
        hBox1.setSpacing(10);
        hBox2.setPadding(new Insets(50,10,10,90));
        hBox2.setSpacing(10);
        hBox3.setPadding(new Insets(50,10,10,90));
        hBox3.setSpacing(10);
        hBox4.setPadding(new Insets(50,10,10,90));
        hBox4.setSpacing(10);

        ComboBox<String> kur=new ComboBox<>();
        kur.setPromptText("Çevrilecek Kur");
        kur.getItems().addAll("Türk Lirası","ABD DOLARI","AVUSTRALYA DOLARI","DANİMARKA KRONU","EURO","İNGİLİZ STERLİNİ","İSVİÇRE FRANGI","İSVEÇ KRONU","KANADA DOLARI","KUVEYT DİNARI","NORVEÇ KRONU","SUUDİ ARABİSTAN RİYALİ","JAPON YENİ","BULGAR LEVASI","RUMEN LEYİ","RUS RUBLESİ","İRAN RİYALİ","ÇİN YUANI","PAKİSTAN RUPİSİ","KATAR RİYALİ");
        kur.setMinWidth(300);

        ComboBox<String> kur2=new ComboBox<>();
        kur2.setPromptText("Hedef Kur");
        kur2.getItems().addAll("Türk Lirası","ABD DOLARI","AVUSTRALYA DOLARI","DANİMARKA KRONU","EURO","İNGİLİZ STERLİNİ","İSVİÇRE FRANGI","İSVEÇ KRONU","KANADA DOLARI","KUVEYT DİNARI","NORVEÇ KRONU","SUUDİ ARABİSTAN RİYALİ","JAPON YENİ","BULGAR LEVASI","RUMEN LEYİ","RUS RUBLESİ","İRAN RİYALİ","ÇİN YUANI","PAKİSTAN RUPİSİ","KATAR RİYALİ");
        kur2.setMinWidth(300);

        TextField textField=new TextField();
        textField.setMinWidth(190);
        Button button=new Button("Çevir");
        button.setMinWidth(100);
        Label label=new Label();
        label.setTextFill(Color.RED);
        label.setStyle("-fx-font-weight: bold;");


        button.setOnAction(event -> {

            if (kur.getValue()=="Türk Lirası"){
                double b=valueOfCurr(kur2.getValue());
                double result = Double.parseDouble(textField.getText());

                double c=result/b;
                label.setText(result+" Türk Lirası : "+String.valueOf(c)+" " +kur2.getValue());

            }
            else if (kur2.getValue()=="Türk Lirası"){
                double a=valueOfCurr(kur.getValue());
                double result = Double.parseDouble(textField.getText());

                double c=result*a;
                label.setText(result+" "+kur.getValue()+": "+String.valueOf(c)+" Türk Lirası");
            }
            else {
                double a=valueOfCurr(kur.getValue());
                double b=valueOfCurr(kur2.getValue());

                double result = Double.parseDouble(textField.getText());

                double c=result*a/b;
                label.setText(result+" "+kur.getValue()+": "+String.valueOf(c)+" "+kur2.getValue());
            }


        });

        Button buttongeri=new Button("Geri");
        buttongeri.setOnAction(event -> {
            Chart obje=new Chart(kullaniciadi,id,stage);
        });

        hBox1.getChildren().add(kur);
        hBox2.getChildren().add(kur2);
        hBox3.getChildren().addAll(textField,button);
        hBox4.getChildren().add(label);
        hBox5.getChildren().add(buttongeri);

        stage.setScene(new Scene(vBox1,480,450));
        stage.setTitle("Kur Çevirici");
        stage.centerOnScreen();
        stage.show();

    }

    public double valueOfCurr(String kur){

        try {
            PreparedStatement ps=DbConnect.dbConnect().prepareStatement("select * from veritabani7.currency where currency = ? and date= ?");
            ps.setString(1,kur);
            ps.setString(2,today.toString());
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                String miktar=rs.getString("forex_buying");
                double result = Double.parseDouble(miktar);

                return result;
            }

        } catch (Exception e) {
            return 0.0;
        }
        return 0.0;


    }

}
