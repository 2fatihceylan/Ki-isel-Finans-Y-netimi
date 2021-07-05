package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Finance {

    LocalDate today=LocalDate.now();
    public Finance(Stage stage, String kullaniciadi, int id){

        VBox vBox1=new VBox();
        HBox hBox1=new HBox();
        HBox hBox2=new HBox();
        vBox1.setStyle("-fx-background-color: #F0F8FF;");
        HBox hBoxgeri=new HBox();
       // hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(20,10,10,40));
        hBox1.setMinHeight(520);

        vBox1.getChildren().addAll(hBox1,hBox2,hBoxgeri);
        VBox vBoxLeft=new VBox();
        VBox vBoxCenter=new VBox();
        VBox vBoxRight=new VBox();
        hBox1.getChildren().addAll(vBoxLeft,vBoxCenter,vBoxRight);

        ListView listView = new ListView();

        listView.getItems().add("Kur Adı      ");
        listView.getItems().add("Türk Lirası: ");
        listView.getItems().add("ABD Doları: ");
        listView.getItems().add("Avustralya Doları: " );
        listView.getItems().add("Danimarka Kronu: ");
        listView.getItems().add("Euro: " );
        listView.getItems().add("İngiliz Sterlini: ");
        listView.getItems().add("İsviçre Frangı: " );
        listView.getItems().add("İsveç Kronu: " );
        listView.getItems().add("Kanada Doları: " );
        listView.getItems().add("Kuveyt Dinarı: ");
        listView.getItems().add("Norveç Kronu: " );
        listView.getItems().add("Suudi Arabistan Riyali: ");
        listView.getItems().add("Japon Yeni: " );
        listView.getItems().add("Bulgar Levası: ");
        listView.getItems().add("Rumen Leyi: " );
        listView.getItems().add("Rus Rublesi: " );
        listView.getItems().add("İran Riyali: ");
        listView.getItems().add("Çin Yuanı: ");
        listView.getItems().add("Pakistan Rupisi: " );
        listView.getItems().add("Katar Riyali: " );

        listView.setMaxWidth(160);
        listView.setMinHeight(500);

        ListView listView2 = new ListView();

        listView2.getItems().add("Sahip Olunan");
        listView2.getItems().add(valueOfMoney(id,"turk_lirasi")  );
        listView2.getItems().add(valueOfMoney(id,"abd_dolari") );
        listView2.getItems().add(valueOfMoney(id,"avustralya_dolari") );
        listView2.getItems().add(valueOfMoney(id,"danimarka_kronu") );
        listView2.getItems().add(valueOfMoney(id,"euro"));
        listView2.getItems().add(valueOfMoney(id,"ingiliz_sterlini") );
        listView2.getItems().add(valueOfMoney(id,"isvicre_frangi"));
        listView2.getItems().add(valueOfMoney(id,"isvec_kronu") );
        listView2.getItems().add(valueOfMoney(id,"kanada_dolari") );
        listView2.getItems().add(valueOfMoney(id,"kuveyt_dinari") );
        listView2.getItems().add(valueOfMoney(id,"norvec_kronu"));
        listView2.getItems().add(valueOfMoney(id,"suudi_arabistan_riyali") );
        listView2.getItems().add(valueOfMoney(id,"japon_yeni"));
        listView2.getItems().add(valueOfMoney(id,"bulgar_levasi") );
        listView2.getItems().add(valueOfMoney(id,"rumen_leyi"));
        listView2.getItems().add(valueOfMoney(id,"rus_rublesi") );
        listView2.getItems().add(valueOfMoney(id,"iran_riyali") );
        listView2.getItems().add(valueOfMoney(id,"cin_yuani") );
        listView2.getItems().add(valueOfMoney(id,"pakistan_rupisi") );
        listView2.getItems().add(valueOfMoney(id,"katar_riyali") );

        listView2.setMaxWidth(100);
        listView2.setMinHeight(500);

        ListView listView3 = new ListView();

        listView3.getItems().add("   Kur  ");
        listView3.getItems().add(" " );
        listView3.getItems().add(valueOfCurr("ABD DOLARI"));
        listView3.getItems().add(valueOfCurr("AVUSTRALYA DOLARI"));
        listView3.getItems().add(valueOfCurr("DANİMARKA KRONU"));
        listView3.getItems().add(valueOfCurr("EURO") );
        listView3.getItems().add(valueOfCurr("İNGİLİZ STERLİNİ") );
        listView3.getItems().add(valueOfCurr("İSVİÇRE FRANGI") );
        listView3.getItems().add(valueOfCurr("İSVEÇ KRONU") );
        listView3.getItems().add(valueOfCurr("KANADA DOLARI") );
        listView3.getItems().add(valueOfCurr("KUVEYT DİNARI") );
        listView3.getItems().add(valueOfCurr("NORVEÇ KRONU") );
        listView3.getItems().add(valueOfCurr("SUUDİ ARABİSTAN RİYALİ") );
        listView3.getItems().add(valueOfCurr("JAPON YENİ") );
        listView3.getItems().add(valueOfCurr("BULGAR LEVASI") );
        listView3.getItems().add(valueOfCurr("RUMEN LEYİ") );
        listView3.getItems().add(valueOfCurr("RUS RUBLESİ") );
        listView3.getItems().add(valueOfCurr("İRAN RİYALİ") );
        listView3.getItems().add(valueOfCurr("ÇİN YUANI") );
        listView3.getItems().add(valueOfCurr("PAKİSTAN RUPİSİ") );
        listView3.getItems().add(valueOfCurr("KATAR RİYALİ") );


        listView3.setMaxWidth(100);
        listView3.setMinHeight(500);

        Button buttongeri=new Button("Geri");
        buttongeri.setOnAction(event -> {
            Chart obje=new Chart(kullaniciadi,id,stage);
        });

        vBoxLeft.getChildren().add(listView);
        vBoxCenter.getChildren().add(listView2);
        vBoxRight.getChildren().add(listView3);
        hBoxgeri.getChildren().add(buttongeri);
        stage.setScene(new Scene(vBox1,460,620));
        stage.setTitle("Hesabım");
        stage.centerOnScreen();
        stage.show();
    }

    public int valueOfMoney(int id,String moneytype){
        String sorgu=("select * from veritabani7.usertable where id = ?");
        try {
            PreparedStatement ps = DbConnect.dbConnect().prepareStatement(sorgu);
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();

            int a=-1;

            if (rs.next()){
                a= rs.getInt(moneytype);
            }

            return a;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;


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
