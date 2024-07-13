/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.FoodDBContext;
import Entity.Account;
import Entity.Food;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 *
 * @author hidung
 */
@WebServlet(name = "FoodController", urlPatterns = {"/admin/food"})
public class FoodController extends BaseRBACController {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        
    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        FoodDBContext fooddb = new FoodDBContext();
         List<Food> foodList = fooddb.getAllFood();
        
        // Set the food list in request scope
        request.setAttribute("foodList", foodList);
        request.getRequestDispatcher("/FE_Admin/CRUD_Food.jsp").forward(request, response);
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        FoodDBContext fooddb = new FoodDBContext();
         List<Food> foodList = fooddb.getAllFood();
        
        // Set the food list in request scope
        request.setAttribute("foodList", foodList);
        request.getRequestDispatcher("/FE_Admin/CRUD_Food.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
