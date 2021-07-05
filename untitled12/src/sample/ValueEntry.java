package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.sql.*;
import java.time.LocalDate;


public class ValueEntry {
    LocalDate today=LocalDate.now();
    public ValueEntry(Stage stage, String kullaniciadi,int id){
        VBox border=new VBox();
        border.setStyle("-fx-background-color: #F0F8FF;");
        HBox hBox=new HBox();
        hBox.setSpacing(10);
        hBox.setPadding(new Insets(30));
        HBox hBox1=new HBox();
        hBox1.setPadding(new Insets(5,10,10,50));
        hBox1.setSpacing(10);
        HBox hBox2=new HBox();
        hBox2.setSpacing(10);
        hBox2.setPadding(new Insets(20,10,10,40));
        HBox hBox3=new HBox();
        hBox3.setSpacing(10);
        hBox3.setPadding(new Insets(20,10,10,40));
        HBox hBox4=new HBox();
        hBox4.setSpacing(10);
        hBox4.setPadding(new Insets(20,10,10,40));
        HBox hBox5=new HBox();
        hBox5.setPadding(new Insets(5,10,10,50));
        hBox5.setSpacing(10);
        border.getChildren().add(hBox2);
        border.getChildren().add(hBox);
        border.getChildren().add(hBox1);
        border.getChildren().add(hBox3);
        border.getChildren().add(hBox4);
        border.getChildren().add(hBox5);
        HBox hBox6=new HBox();
        border.getChildren().add(hBox6);


        Label label3=new Label("Hesaba Para Yatırma ve Çekme");
        label3.setFont(new Font(20));
        label3.setStyle("-fx-border-color: #aaaaaa; -fx-background-color: #dddddd;");
        hBox2.getChildren().add(label3);
        Label label4=new Label("Kur Arası Dönüşüm");
        label4.setFont(new Font(20));
        label4.setStyle("-fx-border-color: #aaaaaa; -fx-background-color: #dddddd;");
        hBox3.getChildren().add(label4);
        Label label=new Label("-");
        Label lblmiktr=new Label("-");

        ComboBox<String> islem=new ComboBox<>();
        islem.setPromptText("Yapmak İstediğiniz İşlemi Seçin");
        islem.getItems().addAll("Para Yatırma","Para Çekme");
        islem.setMinWidth(300);



        ComboBox<String> kur=new ComboBox<>();
        kur.setPromptText("Hangi Kur");
        kur.getItems().addAll("turk_lirasi","abd_dolari","avustralya_dolari","danimarka_kronu","euro","ingiliz_sterlini","isvicre_frangi","isvec_kronu","kanada_dolari","kuveyt_dinari","norvec_kronu","suudi_arabistan_riyali","japon_yeni","bulgar_levasi","rumen_leyi","rus_rublesi","iran_riyali","cin_yuani","pakistan_rupisi","katar_riyali");
        kur.setMinWidth(300);


        TextField miktar=new TextField();
        miktar.setPromptText("Miktar");
        Button button=new Button("Tamam");
        Label label1=new Label("-");

        //------------------------------------------------------------
        kur.setOnAction(event -> {
            int curr=valueOfMoney(id,kur.getValue());
            label.setText(String.valueOf(curr));
        });

        button.setOnAction(event -> {
            if (islem.getValue()=="Para Yatırma"){
                paraYatirma(id,miktar.getText(),kur.getValue());//kur.getValue()
                label1.setText("İşlem Başarılı");
            }
            else if(islem.getValue()=="Para Çekme"){
                paraCekme(id,miktar.getText(),kur.getValue());
                label1.setText("İşlem Başarılı");
            }
            lblmiktr.setText("-");

            int curr=valueOfMoney(id,kur.getValue());
            label.setText(String.valueOf(curr));

        });
        //--------------------------------------------------------------



        Label label5=new Label("-");
        ComboBox<String> thisCurr=new ComboBox<>();
        thisCurr.setPromptText("Bu Kurdan");
        thisCurr.getItems().addAll("turk_lirasi","abd_dolari","avustralya_dolari","danimarka_kronu","euro","ingiliz_sterlini","isvicre_frangi","isvec_kronu","kanada_dolari","kuveyt_dinari","norvec_kronu","suudi_arabistan_riyali","japon_yeni","bulgar_levasi","rumen_leyi","rus_rublesi","iran_riyali","cin_yuani","pakistan_rupisi","katar_riyali");
        thisCurr.setMinWidth(300);

        thisCurr.setOnAction(event -> {
            label5.setText(thisCurr.getValue());
        });
        ComboBox<String> targetCurr=new ComboBox<>();
        targetCurr.setPromptText("Hedef Kur");
        targetCurr.getItems().addAll("turk_lirasi","abd_dolari","avustralya_dolari","danimarka_kronu","euro","ingiliz_sterlini","isvicre_frangi","isvec_kronu","kanada_dolari","kuveyt_dinari","norvec_kronu","suudi_arabistan_riyali","japon_yeni","bulgar_levasi","rumen_leyi","rus_rublesi","iran_riyali","cin_yuani","pakistan_rupisi","katar_riyali");
        targetCurr.setMinWidth(300);

        TextField miktar2=new TextField();
        miktar2.setPromptText("Miktar");
        Button button2=new Button("Tamam");
        Label label16=new Label("-");

        //--------------------------------------------------------------
        button2.setOnAction(event -> {
            if (thisCurr.getValue()=="turk_lirasi"){
                double b=valueOfCurr(targetCurr.getValue());
                double result = Double.parseDouble(miktar2.getText());

                double c=result/b;

                paraCekme(id,miktar2.getText(),thisCurr.getValue());


               // paraYatirma(id,c,targetCurr.getValue());

               // label.setText(result+" Türk Lirası : "+String.valueOf(c)+" " +targetCurr.getValue());

            }
            else if (targetCurr.getValue()=="Türk Lirası"){
                double a=valueOfCurr(kur.getValue());
                double result = Double.parseDouble(miktar2.getText());

                double c=result*a;
               // label.setText(result+" "+kur.getValue()+": "+String.valueOf(c)+" Türk Lirası");
            }
            else {
                double a=valueOfCurr(thisCurr.getValue());
                double b=valueOfCurr(targetCurr.getValue());

                double result = Double.parseDouble(miktar2.getText());

                double c=result*a/b;

               // label.setText(result+" "+kur.getValue()+": "+String.valueOf(c)+" "+kur2.getValue());
            }


        });
        //---------------------------------------------------------------------






        Button btngeri=new Button("Geri");
        btngeri.setOnAction(event -> {
            Chart obje=new Chart(kullaniciadi,id,stage);
        });


        hBox4.getChildren().addAll(thisCurr,label5,targetCurr);
        hBox5.getChildren().addAll(miktar2,button2,label16);
        hBox.getChildren().addAll(kur,label,islem,lblmiktr);
        hBox1.getChildren().addAll(miktar,button,label1);

        hBox6.getChildren().addAll(btngeri);
        stage.setScene(new Scene(border,800,600));
        stage.setTitle("Hesap Yönetimi");
        stage.centerOnScreen();
        stage.show();
    }

    public int valueOfMoney(int id,String kur){

        try {
            PreparedStatement ps=DbConnect.dbConnect().prepareStatement("select * from veritabani7.usertable where id="+id+"");
            ResultSet rs=ps.executeQuery();
            if (rs.next()){return rs.getInt(kur);}

        } catch (Exception e) {
            return 0;
        }
        return 0;


    }



    public void paraYatirma(int id,String miktar,String kur){

        int result = Integer.parseInt(miktar);

        PreparedStatement ps = null;
        try {
            result=result+valueOfMoney(id,kur);

            ps=DbConnect.dbConnect().prepareStatement("update veritabani7.usertable set "+kur+" = ("+result+") where id = "+id+"");

            ps.executeUpdate();


            DbConnect.dbConnect().close();
        } catch (Exception e) {

            e.printStackTrace();

        }



    }
    public void paraCekme(int id,String miktar,String kur){

        int result = Integer.parseInt(miktar);
        PreparedStatement ps = null;
        try {
            result=(valueOfMoney(id,kur)-result);
            if (result<0){result=0;}

            ps=DbConnect.dbConnect().prepareStatement("update veritabani7.usertable set "+kur+" = ("+result+") where id = "+id+"");

            ps.executeUpdate();


            DbConnect.dbConnect().close();
        } catch (Exception e) {

            e.printStackTrace();

        }

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
