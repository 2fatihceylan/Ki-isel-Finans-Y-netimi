package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.sql.*;

public class Main extends Application {
    PreparedStatement ps;
    ResultSet rs;
    private int id;

    @Override
    public void start(Stage primaryStage) throws Exception{
        VBox pane=new VBox();
        pane.setPadding(new Insets(100));
        pane.setSpacing(20);
        Label label2=new Label("Kaydol");
        label2.setTextFill(Color.RED);
        label2.setStyle("-fx-font-weight: bold;");



        login(pane,primaryStage,label2);

        VBox pane1=new VBox();
        pane1.setPadding(new Insets(100));
        pane1.setSpacing(20);

        label2.setOnMouseClicked(event -> {
            register(pane1,primaryStage);
        });
    }




    public void login(Pane pane, Stage primaryStage,Label label2){

        Label label=new Label("");
        TextField isim=new TextField();
        isim.setFocusTraversable(false);//otomatik imleç için
        isim.setMaxWidth(200);
        isim.setMinHeight(45);
        isim.setPromptText("Kullanıcı Adı");
        isim.setStyle("-fx-background-color: #F0F8FF;");
        PasswordField parola=new PasswordField();
        parola.setFocusTraversable(false);
        parola.setMaxWidth(200);
        parola.setMinHeight(45);
        parola.setPromptText("Parolanız");
        parola.setStyle("-fx-background-color: #F0F8FF;");
        Button btn=new Button("Giriş");
        btn.setMaxWidth(200);
        btn.setMinHeight(35);



        String sorgu=("select * from veritabani7.usertable where kullaniciadi = ? and parola = ?");

        btn.setOnAction(event -> {
            try {
                ps = DbConnect.dbConnect().prepareStatement(sorgu);
                ps.setString(1, isim.getText().trim());
                ps.setString(2, parola.getText().trim());
                rs = ps.executeQuery();

                String kullaniciadi,ad,soyad;
                if (rs.next()) {
                    kullaniciadi=rs.getString("kullaniciadi");
                    id=rs.getInt("id");

                    Chart obje=new Chart(kullaniciadi,id,primaryStage);
                }
                else {
                    label.setText("hatalı giriş");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        });




        VBox pane1=new VBox();
        pane1.setPadding(new Insets(100));
        pane1.setSpacing(20);
        label2.setText("Kaydol");
        label2.setOnMouseClicked(event -> {
            register(pane1,primaryStage);
        });
        pane.getChildren().addAll(isim,parola,btn,label,label2);
        primaryStage.setScene(new Scene(pane,400,400));
        primaryStage.setTitle("Giriş");
        primaryStage.show();

    }
    public void register(Pane pane,Stage stage){
        TextField isim2=new TextField();
        isim2.setMaxWidth(200);
        isim2.setMinHeight(45);
        isim2.setPromptText("Kullanıcı Adı Oluşturun");
        isim2.setFocusTraversable(false);

        TextField ad=new TextField();
        ad.setMaxWidth(200);
        ad.setMinHeight(45);
        ad.setPromptText("İsim");
        ad.setFocusTraversable(false);

        TextField soyad=new TextField();
        soyad.setMaxWidth(200);
        soyad.setMinHeight(45);
        soyad.setPromptText("Soyisim");
        soyad.setFocusTraversable(false);

        TextField parola2=new TextField();
        parola2.setMaxWidth(200);
        parola2.setMinHeight(45);
        parola2.setPromptText("Parola Girin");
        parola2.setFocusTraversable(false);

        Button btn2=new Button("Kayıt Ol");
        btn2.setMaxWidth(200);
        btn2.setMinHeight(35);

        Label label=new Label("");
        Label label1=new Label("");
        Label label2=new Label("Giriş Yap");



        String sorgu=("insert into veritabani7.usertable (kullaniciadi,ad,soyad,parola) values (?,?,?,?)");

        btn2.setOnAction(event -> {
            try {
                PreparedStatement ps2=DbConnect.dbConnect().prepareStatement("select * from veritabani7.usertable where kullaniciadi = ?");
                ps2.setString(1,isim2.getText());
                rs=ps2.executeQuery();

                if ((!rs.next())&&(!isim2.getText().isEmpty())&&(!ad.getText().isEmpty())&&(!soyad.getText().isEmpty())&&(!parola2.getText().isEmpty())) {
                    ps = DbConnect.dbConnect().prepareStatement(sorgu);
                    ps.setString(1, isim2.getText().trim());
                    ps.setString(2, ad.getText().trim());
                    ps.setString(3, soyad.getText().trim());
                    ps.setString(4, parola2.getText().trim());
                    ps.execute();

                    label.setText("Kayıt Edildi...");

                    DbConnect.dbConnect().close();

                }
                else {
                    label.setText("Kullanıcı adı alınmış olabilir..");
                    label1.setText("Boş yer bırakmayın...");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }

        });


        VBox pane2=new VBox();
        pane2.setPadding(new Insets(100));
        pane2.setSpacing(20);


        label2.setOnMouseClicked(event -> {
            login(pane2,stage,label2);
        });


        pane.getChildren().addAll(isim2,ad,soyad,parola2,btn2,label,label1,label2);

        stage.setScene(new Scene(pane,400,600));
        stage.setTitle("Kayıt");
        stage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }



}
