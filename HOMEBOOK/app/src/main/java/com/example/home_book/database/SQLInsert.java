package com.example.home_book.database;

public class SQLInsert {

    public static final String adminstrator_Values = "insert into adminstrator_tb(avatar,money) values ('0','0')"; //1

    public static final String user_Values = "insert into user_tb(avatar,fullname,email,password,role,birthday,phonenumber,money) " +
            "values ('0','Lai','lai@gmail.com','lai','0','01/04/2003','0987654321','100000000'), " + //1
            "('0','Hiếu','hieu@gmail.com','hieu','1','13/02/2003','0123456789','100000000'), " + //2
            "('0','Trần Thành Trung','khach@gmail.com','khach','1','20/02/1999','0728492102','100000000')"; //3

    public static final String room_Values = "insert into room_tb(fullname,brand_name,category_name,location,collaborate_id,rate,max_people,beds,rooms,note,size,service,cost,status) " +
            "values ('Phòng Giường Đôi','Monte Carlo','Khách sạn','Đằng Hải, Hải Phòng','1','4','3','2','2',null,'Vừa','Wifi,Điều hòa,Bình nóng lạnh,Thang máy','650000','0'), " + //1
            "('Phòng Cạnh Biển','Menora Grand','Khách Sạn','Sơn Trà, Đà Nẵng','1','5','4','2','2',null,'To','Wifi,Điều hòa,Bình nóng lạnh,Thang máy','550000','0'), " + //2
            "('Nhà số 4,ngách 41/3 Trần Duy Hưng','Trần Duy Hưng','Nhà trọ','Cầu Giấy, Hà Nội','1','2','3','1','3','Free PC, điện đôi khi chập chờn','Vừa','Wifi,Điều hòa,Bình nóng lạnh','67000','1'), " + //3
            "('Nhà số 11,ngõ 521 Trương Định gần bờ sông Sét','Trương Định','Căn hộ','Hoàng Mai, Hà Nội','1','4','10','5','8','Nhà to, rộng, cả căn','To','Wifi,Điều hòa,Bình nóng lạnh','110000','1')"; //4


}
