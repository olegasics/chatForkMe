package ru.forksme.client.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class DatabaseHandler extends Configs{
    Connection dbConnection;


    //подключаемся к базе данных
    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        //устанавливаем путь к серверу
        String connectionString = "jdbc:mysql://" + dbHost + ":"
                + dbPort + "/" + dbName + "?" +
                "useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                // переменные из класса Configs

        Class.forName("com.mysql.cj.jdbc.Driver");

        //соеденяем драйвер с сервером
        dbConnection = DriverManager.getConnection(connectionString,
                dbUser, dbPass);

        return dbConnection;
    }
    //вставляем значения в БД при регистрации  через SQL запрос
    public void signUpUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + ","
                + Const.USER_NAME + "," + Const.USER_PASS + "," + Const.USER_GENDER + "," + Const.USER_LOCATION + ","
                + Const.USER_PHONE + ")" + "VALUES(?,?,?,?,?,?,?)";

        //вставляем значение в метод VALUES(??????) вместо знаков вопроса
        try {
        PreparedStatement prSt = getDbConnection().prepareStatement(insert);
        prSt.setString(1, user.getFirstName());
        prSt.setString(2,user.getLastName());
        prSt.setString(3,user.getUserName());
        prSt.setString(4,user.getUserPassword());
        prSt.setString(5,user.getGender());
        prSt.setString(6,user.getLocation());
        prSt.setString(7,user.getPhone());


            prSt.executeUpdate(); // выполнить команду prSt
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE "
                + Const.USER_NAME + "=? AND " + Const.USER_PASS + "=?";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getUserName());
            prSt.setString(2,user.getUserPassword());

           resSet =  prSt.executeQuery(); // выполнить (получить) команду prSt
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resSet;
    }

}
