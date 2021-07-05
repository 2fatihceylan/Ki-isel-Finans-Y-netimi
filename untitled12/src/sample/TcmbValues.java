package sample;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TcmbValues {
    public TcmbValues(){


        LocalDate today=LocalDate.now();
        URL url=null;

        try {
            url = new URL("http://www.tcmb.gov.tr/kurlar/today.xml");


            Scanner input = new Scanner(url.openStream());
            StringBuilder sb = new StringBuilder();
            while (input.hasNext()){
                sb.append(input.nextLine()+"\n");

            }

            Pattern p1 = Pattern.compile("(<Isim>)(.*?)(</Isim>)");
            Pattern p2 = Pattern.compile("(<CurrencyName>)(.*?)(</CurrencyName>)");
            Pattern p3 = Pattern.compile("(<ForexBuying>)(.*?)(</ForexBuying>)");
            Pattern p4 = Pattern.compile("(<ForexSelling>)(.*?)(</ForexSelling>)");
            Pattern p5 = Pattern.compile("(<BanknoteBuying>)(.*?)(</BanknoteBuying>)");
            Pattern p6 = Pattern.compile("(<BanknoteSelling>)(.*?)(</BanknoteSelling>)");
            Pattern p7=Pattern.compile("(<Unit>)(.*?)(</Unit>)");

            Matcher m1=p1.matcher(sb);
            Matcher m2=p2.matcher(sb);
            Matcher m3=p3.matcher(sb);
            Matcher m4=p4.matcher(sb);
            Matcher m5=p5.matcher(sb);
            Matcher m6=p6.matcher(sb);
            Matcher m7=p7.matcher(sb);

            String sorgu=("insert into veritabani7.currency (currency,currency_code,forex_buying,forex_selling,banknote_buying,banknote_selling,date) values (?,?,?,?,?,?,?)");
            ResultSet rs;
            try {
                PreparedStatement ps2=DbConnect.dbConnect().prepareStatement("select * from veritabani7.currency where date = ? ");
                ps2.setString(1,today.toString());
                rs=ps2.executeQuery();

                if (!rs.next()){
                    PreparedStatement ps;
                    ps=DbConnect.dbConnect().prepareStatement(sorgu);



                    while (m7.find()) {


                        if (m1.find())
                            ps.setString(1, m1.group(2));

                        if (m2.find())
                            ps.setString(2, m2.group(2));

                        if (m3.find())
                            ps.setString(3, m3.group(2));

                        if (m4.find())
                            ps.setString(4, m4.group(2));

                        if(m5.find())
                            ps.setString(5, m5.group(2));

                        if(m6.find())
                            ps.setString(6, m6.group(2));

                        ps.setString(7,today.toString());
                        ps.execute();
                    }


                }
                else {
                   // throw new Exception("hata");
                }

            }
            catch (Exception e){
                e.printStackTrace();
            }




            m1.reset();
            m2.reset();
            m3.reset();
            m4.reset();
            m5.reset();
            m6.reset();




            DbConnect.dbConnect().close();




        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
