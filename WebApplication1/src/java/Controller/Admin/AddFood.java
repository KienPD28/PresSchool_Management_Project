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

/**
 *
 * @author hidung
 */
@WebServlet(name = "AddFood", urlPatterns = {"/admin/add-food"})
public class AddFood extends BaseRBACController {

    private static final long serialVersionUID = 1L;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        try {
            String fname = request.getParameter("fname");
            String caloStr = request.getParameter("calo");
            int calo;

            try {
                calo = Integer.parseInt(caloStr);
                if (calo < 0) {
                    request.setAttribute("errorMessage", "Calories cannot be negative.");
                    request.getRequestDispatcher("/admin/food").forward(request, response);
                    return;
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Calories must be an integer.");
                request.getRequestDispatcher("/admin/food").forward(request, response);
                return;
            }

            Food newFood = new Food();
            newFood.setFname(fname);

            FoodDBContext foodDBContext = new FoodDBContext();
            if (foodDBContext.foodExists(fname)) {
                request.setAttribute("errorMessage", "Food already exists.");
                request.getRequestDispatcher("/admin/food").forward(request, response);
                return;
            }

            foodDBContext.addFood(newFood);

            // Redirect to the FoodController servlet after adding the food
            response.sendRedirect(request.getContextPath() + "/admin/food");
        } catch (Exception e) {
            request.setAttribute("errorMessage", "An error occurred while adding food.");
            request.getRequestDispatcher("/admin/food").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
