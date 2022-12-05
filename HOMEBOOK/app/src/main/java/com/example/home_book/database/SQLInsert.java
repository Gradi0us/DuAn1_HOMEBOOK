package com.example.home_book.database;

public class SQLInsert {

    public static final String adminstrator_Values = "insert into adminstrator_tb(username,password,money_reciever) values ('admin','admin','10000')"; //1

    public static final String user_Values = "insert into user_tb(avatar,fullname,email,password,role,birthday,phonenumber,money) " +
            "values ('0','Lai','lai@gmail.com','lai','0','01/04/2003','0987654321','100000000'), " + //1
            "('0','Hiếu','hieu@gmail.com','hieu','0','13/02/2003','0123456789','10000000'), " + //2
            "('0','Tùng','tung@gmail.com','tung','0','01/01/2003','0234235234','10000000'), " + //3
            "('0','Dương','duong@gmail.com','duong','0','01/01/2003','0234242342','10000000'), " + //4
            "('0','Hiếu','hieu2@gmail.com','hieu2','0','01/01/2001','0987654124','10000000')," + //5
            "('0','Trần Thành Trung','khach@gmail.com','khach','1','20/02/1999','0728492102','100000000')"; //6

    public static final String room_Values = "insert into room_tb(fullname, category_name , location , rate, beds,wifi,ac,parking, minibar,pool,buffet, cost, status,image, note) " +
            "values ('Phòng nghìn sao','Hotel','Hải Phòng','4','4','1','1','1','1','1','1','1','1','1',null), " + //1
//            "('Phòng Cạnh Biển Menora Grand','Khách Sạn','Sơn Trà, Đà Nẵng','3','2','1','1','1','1','1','1','550000','5','0',null), " + //2
            "('Nhà số 4,ngách 41/3 Trần Duy Hưng','Apartment','Cầu Giấy, Hà Nội','2','2','1','0','1','1','1','1','550000','5','0','Free PC, điện đôi khi chập chờn'), " + //3
            "('Nhà số 11,ngõ 521 Trương Định gần bờ sông Sét','Homestay','Hoàng Mai, Hà Nội','3','2','1','1','1','1','1','1','550000','5','0',null)"; //4

    public static final String favourite_Values = "insert into room_favourite_tb(room_id,user_id) " +
            "values ('2','1'),('3','1'),('2','2')";
}
