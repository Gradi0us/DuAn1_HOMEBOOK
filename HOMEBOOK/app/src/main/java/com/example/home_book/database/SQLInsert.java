package com.example.home_book.database;

public class SQLInsert {

    public static final String adminstrator_Values = "insert into adminstrator_tb(avatar,money_reciever) values ('0','0')"; //1

    public static final String user_Values = "insert into user_tb(avatar,fullname,email,password,role,birthday,phonenumber,money) " +
            "values ('0','Lai','lai@gmail.com','lai','0','01/04/2003','0987654321','100000000'), " + //1
            "('0','Hiếu','hieu@gmail.com','hieu','1','13/02/2003','0123456789','100000000'), " + //2
            "('0','Trần Thành Trung','khach@gmail.com','khach','1','20/02/1999','0728492102','100000000')"; //3

    public static final String room_Values = "insert into room_tb(fullname, category_name , location , rate, beds,number_people,wifi,ac,parking, minibar,pool,buffet, cost, status,image, note) " +
            "values ('Phòng nghìn sao','Hotel','Hải Phòng','4','1','10','1','1','1','1','1','1','1','1','1',null), " + //1
//            "('Phòng Cạnh Biển Menora Grand','Khách Sạn','Sơn Trà, Đà Nẵng','3','2','1','1','1','1','1','1','550000','5','0',null), " + //2
            "('Nhà số 4,ngách 41/3 Trần Duy Hưng','Apartment','Cầu Giấy, Hà Nội','3','2','10','1','1','1','1','1','1','550000','5','0','Free PC, điện đôi khi chập chờn'), " + //3
            "('Nhà số 11,ngõ 521 Trương Định gần bờ sông Sét','Homestay','Hoàng Mai, Hà Nội','3','2','10','1','1','1','1','1','1','550000','5','0',null)"; //4


}
