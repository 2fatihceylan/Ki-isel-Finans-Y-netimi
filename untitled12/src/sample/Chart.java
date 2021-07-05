package sample;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class Chart {

    private ObservableList<PieChart.Data> veri= FXCollections.observableArrayList();

    private PieChart pasta;

    LocalDate today=LocalDate.now();

    private int id;

    public Chart(String kullaniciadi,int id,Stage stage){

        this.id=id;
        VBox vBox=new VBox();
        HBox pane2=new HBox();
        VBox vBox1=new VBox();
        VBox vBox2=new VBox();
        HBox pane4=new HBox();
        HBox pane3=new HBox();
        vBox.setStyle("-fx-background-color: #F0F8FF;");

        pane2.setPadding(new Insets(5,50,10,20));
        pane2.setSpacing(10);
        pane2.setMinHeight(600);
        pane2.setMinWidth(500);
        pane3.setPadding(new Insets(5,10,10,50));
        pane3.setSpacing(10);
        pane4.setPadding(new Insets(40,50,10,70));

        vBox.getChildren().add(pane2);
        pane2.getChildren().addAll(vBox1,vBox2);
        vBox2.getChildren().add(pane4);
        vBox.getChildren().add(pane3);


        Button btn=new Button("Günlük Kur Bilgileri");
        Button btn2=new Button("Hesap Yönetimi");
        Button btn3=new Button("Hesabım");
        Button btn4=new Button("Kur Çevirici");


        veri.addAll(
                new PieChart.Data("TL-"+(double)valueOfMoney(id,kullaniciadi,"turk_lirasi")+"tl",(double)valueOfMoney(id,kullaniciadi,"turk_lirasi")),
                new PieChart.Data("ABD Doları-"+(double)valueOfMoney(id,kullaniciadi,"abd_dolari"),(double)valueOfMoney(id,kullaniciadi,"abd_dolari")),
                new PieChart.Data("Avustralya Doları-"+(double)valueOfMoney(id,kullaniciadi,"avustralya_dolari"),(double)valueOfMoney(id,kullaniciadi,"avustralya_dolari")),
                new PieChart.Data("Danimarka Kronu-"+(double)valueOfMoney(id,kullaniciadi,"danimarka_kronu"),(double)valueOfMoney(id,kullaniciadi,"danimarka_kronu")),
                new PieChart.Data("EURO-"+(double)valueOfMoney(id,kullaniciadi,"euro"),(double)valueOfMoney(id,kullaniciadi,"euro")),
                new PieChart.Data("İngiliz Sterlini-"+(double)valueOfMoney(id,kullaniciadi,"ingiliz_sterlini"),(double)valueOfMoney(id,kullaniciadi,"ingiliz_sterlini")),
                new PieChart.Data("İsviçre Frangı-"+(double)valueOfMoney(id,kullaniciadi,"isvicre_frangi"),(double)valueOfMoney(id,kullaniciadi,"isvicre_frangi")),
                new PieChart.Data("İsveç Kronu-"+(double)valueOfMoney(id,kullaniciadi,"isvec_kronu"),(double)valueOfMoney(id,kullaniciadi,"isvec_kronu")),
                new PieChart.Data("Kanada Doları-"+(double)valueOfMoney(id,kullaniciadi,"kanada_dolari"),(double)valueOfMoney(id,kullaniciadi,"kanada_dolari")),
                new PieChart.Data("Kuveyt Dinarı-"+(double)valueOfMoney(id,kullaniciadi,"kuveyt_dinari"),(double)valueOfMoney(id,kullaniciadi,"kuveyt_dinari")),
                new PieChart.Data("Norveç Kronu-"+(double)valueOfMoney(id,kullaniciadi,"norvec_kronu"),(double)valueOfMoney(id,kullaniciadi,"norvec_kronu")),
                new PieChart.Data("Suudi Arabistan Riyali-"+(double)valueOfMoney(id,kullaniciadi,"suudi_arabistan_riyali"),(double)valueOfMoney(id,kullaniciadi,"suudi_arabistan_riyali")),
                new PieChart.Data("Japon Yeni-"+(double)valueOfMoney(id,kullaniciadi,"japon_yeni"),(double)valueOfMoney(id,kullaniciadi,"japon_yeni")),
                new PieChart.Data("Bulgar Levası-"+(double)valueOfMoney(id,kullaniciadi,"bulgar_levasi"),(double)valueOfMoney(id,kullaniciadi,"bulgar_levasi")),
                new PieChart.Data("Rumen Leyi-"+(double)valueOfMoney(id,kullaniciadi,"rumen_leyi"),(double)valueOfMoney(id,kullaniciadi,"rumen_leyi")),
                new PieChart.Data("Rus Rublesi-"+(double)valueOfMoney(id,kullaniciadi,"rus_rublesi"),(double)valueOfMoney(id,kullaniciadi,"rus_rublesi")),
                new PieChart.Data("İran Riyali-"+(double)valueOfMoney(id,kullaniciadi,"iran_riyali"),(double)valueOfMoney(id,kullaniciadi,"iran_riyali")),
                new PieChart.Data("Çin Yuanı-"+(double)valueOfMoney(id,kullaniciadi,"cin_yuani"),(double)valueOfMoney(id,kullaniciadi,"cin_yuani")),
                new PieChart.Data("Pakistan Rupisi-"+(double)valueOfMoney(id,kullaniciadi,"pakistan_rupisi"),(double)valueOfMoney(id,kullaniciadi,"pakistan_rupisi")),
                new PieChart.Data("Katar Riyali-"+(double)valueOfMoney(id,kullaniciadi,"katar_riyali"),(double)valueOfMoney(id,kullaniciadi,"katar_riyali"))
        );

        pasta=new PieChart();
        pasta.setData(veri);
        pasta.setTitle("Hesabım");
        pasta.setLegendSide(Side.BOTTOM);
        pasta.setLabelsVisible(true);
        pasta.setClockwise(false);
        pasta.setPrefSize(600,500);

        ComboBox<String> kur=new ComboBox<>();
        kur.setPromptText("Hangi Kur");
        kur.getItems().addAll("turk_lirasi","ABD DOLARI","AVUSTRALYA DOLARI","DANİMARKA KRONU","EURO","İNGİLİZ STERLİNİ","İSVİÇRE FRANGI","İSVEÇ KRONU","KANADA DOLARI","KUVEYT DİNARI","NORVEÇ KRONU","SUUDİ ARABİSTAN RİYALİ","JAPON YENİ","BULGAR LEVASI","RUMEN LEYİ","RUS RUBLESİ","İRAN RİYALİ","ÇİN YUANI","PAKİSTAN RUPİSİ","KATAR RİYALİ");
        kur.setMinWidth(300);

        CategoryAxis xAxis=new CategoryAxis();
        xAxis.setLabel("Year");
        NumberAxis yAxis=new NumberAxis();
        yAxis.setLabel("Euro");


        LineChart<String,Number> lineChart=new LineChart<String,Number>(xAxis,yAxis);


        kur.setOnAction(event -> {
            //(Number)getCurrencyValue(kur.getValue(),today.minusDays(4))

            XYChart.Series<String,Number> data=new XYChart.Series<>();


            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(18).toString(),getCurrencyValue(kur.getValue(),today.minusDays(18))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(17).toString(),getCurrencyValue(kur.getValue(),today.minusDays(17))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(16).toString(),getCurrencyValue(kur.getValue(),today.minusDays(16))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(15).toString(),getCurrencyValue(kur.getValue(),today.minusDays(15))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(14).toString(),getCurrencyValue(kur.getValue(),today.minusDays(14))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(13).toString(),getCurrencyValue(kur.getValue(),today.minusDays(13))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(12).toString(),getCurrencyValue(kur.getValue(),today.minusDays(12))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(11).toString(),getCurrencyValue(kur.getValue(),today.minusDays(11))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(10).toString(),getCurrencyValue(kur.getValue(),today.minusDays(10))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(9).toString(),getCurrencyValue(kur.getValue(),today.minusDays(9))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(8).toString(),getCurrencyValue(kur.getValue(),today.minusDays(8))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(7).toString(),getCurrencyValue(kur.getValue(),today.minusDays(7))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(6).toString(),getCurrencyValue(kur.getValue(),today.minusDays(6))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(5).toString(),getCurrencyValue(kur.getValue(),today.minusDays(5))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(4).toString(),getCurrencyValue(kur.getValue(),today.minusDays(4))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(3).toString(),getCurrencyValue(kur.getValue(),today.minusDays(3))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(2).toString(),getCurrencyValue(kur.getValue(),today.minusDays(2))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.minusDays(1).toString(),getCurrencyValue(kur.getValue(),today.minusDays(1))));
            data.getData().addAll(new XYChart.Data<String, Number>(today.toString(),getCurrencyValue(kur.getValue(),today)));

            lineChart.setTitle(kur.getValue());

            lineChart.getData().add(data);

        });




        vBox2.getChildren().add(lineChart);


        vBox1.getChildren().add(pasta);
        pane3.getChildren().addAll(btn,btn3,btn2,btn4);


        pane4.getChildren().add(kur);



        btn.setOnAction(event -> {
            TcmbKur obje=new TcmbKur(kullaniciadi,id,stage,today);
        });
        btn2.setOnAction(event -> {
            ValueEntry obje=new ValueEntry(stage,kullaniciadi,id);
        });
        btn3.setOnAction(event -> {
            Finance obje=new Finance(stage,kullaniciadi,id);
        });
        btn4.setOnAction(event -> {
            CurrencyConverter obje=new CurrencyConverter(stage,kullaniciadi,id);
        });





        stage.setScene(new Scene(vBox,1200,670));
        stage.setTitle("Ana Ekran");
        stage.centerOnScreen();
        stage.show();


    }

    public int valueOfMoney(int id,String kullaniciadi,String moneytype){
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

    public double getCurrencyValue(String kur, LocalDate date){
        String sorgu=("select * from veritabani7.currency where date = ? and currency = ?");
        try{
            PreparedStatement ps=DbConnect.dbConnect().prepareStatement(sorgu);
            ps.setString(1,date.toString());
            ps.setString(2,kur);
            ResultSet rs=ps.executeQuery();

            if (rs.next()){
                double result = Double.valueOf(rs.getString("forex_buying"));


                return result;
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return 1;

    }

}
