/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entity.Food;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class FoodDBContext extends DBContext {

   public Food getFoodById(int id) {
        Food food = new Food();
        try {
            String sql = "SELECT [foodid]\n"
                    + "      ,[fname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Food]\n"
                    + "  Where foodid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                food.setFoodid(rs.getInt("foodid"));
                food.setFname(rs.getString("fname"));;
                return food;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }
    
    public List<Food> getAllFood() {
         List<Food> food = new ArrayList<>();
        try {
            String sql = "SELECT [foodid]\n"
                    + "      ,[fname]\n"
                    + "  FROM [SchoolManagement].[dbo].[Food]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Food f = new Food();
                f.setFoodid(rs.getInt("foodid"));
                f.setFname(rs.getString("fname"));
                food.add(f);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return food;
    }

    public void addFood(Food food) {
        try {
            String sql = "INSERT INTO [SchoolManagement].[dbo].[Food] ([fname]) VALUES (?)";
            PreparedStatement stm = connection.prepareStatement(sql);

            stm.setString(1, food.getFname());
            stm.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public boolean updateFood(int foodId, String foodName) throws SQLException {
        String sql = "UPDATE Food SET fname = ? WHERE foodid = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, foodName);
            pstmt.setInt(2, foodId);
            return pstmt.executeUpdate() > 0;
        }
    }

    public boolean deleteFood(int foodId) throws SQLException {
        String sql = "DELETE FROM Food WHERE foodid = ?";
        try (
                PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, foodId);
            return pstmt.executeUpdate() > 0;
        }
    }
    
    public boolean foodExists(String fname) {
        // Kết nối tới cơ sở dữ liệu và kiểm tra xem món ăn đã tồn tại hay chưa
        String sql = "SELECT COUNT(*) FROM Food WHERE fname = ?";
        try (
                PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, fname);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            // Xử lý lỗi cơ sở dữ liệu
        }
        return false;
    }

    public List<Food> searchFoodByID(String foodid) {
        List<Food> list = new ArrayList<>();
        try {
            String sql = "select * from Food where foodid = ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, foodid);

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodid(rs.getInt("foodid"));
                food.setFname(rs.getString("fname"));
                list.add(food);
            }
        } catch (SQLException ex) {
        }
        return list;
    }

    public List<Food> searchFoodByName(String fname) {
        List<Food> list = new ArrayList<>();
        try {
            String sql = "Select * from [SchoolManagement].[dbo].[Food] where fname like ?";

            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, '%' + fname + '%');

            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Food food = new Food();
                food.setFoodid(rs.getInt("foodid"));
                food.setFname(rs.getString("fname"));
                list.add(food);
            }
        } catch (SQLException ex) {
        }
        return list;
    }
}
