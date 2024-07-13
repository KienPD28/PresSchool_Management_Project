/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controller.Admin;

import DAO.FoodDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 *
 * @author hidung
 */
@WebServlet(name = "UpdateFood", urlPatterns = {"/admin/update-food"})
public class UpdateFood extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int foodId = Integer.parseInt(request.getParameter("foodid"));
        String foodName = request.getParameter("fname");
        FoodDBContext fooddb = new FoodDBContext();
        try {
            boolean isUpdated = fooddb.updateFood(foodId, foodName);
            if (isUpdated) {
                request.getRequestDispatcher("/food").forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("errorMessage", "An error occurred while adding food.");
            request.getRequestDispatcher("/food").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
