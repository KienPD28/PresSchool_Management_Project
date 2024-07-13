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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hidung
 */
@WebServlet(name = "SearchFood", urlPatterns = {"/admin/search-food"})
public class SearchFood extends BaseRBACController {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        String searchInput = request.getParameter("searchInput"); // Correct parameter name

        FoodDBContext fooddb = new FoodDBContext();

// Search by foodid
        List<Food> searchResultsById = fooddb.searchFoodByID(searchInput);

// Search by fname
        List<Food> searchResultsByName = fooddb.searchFoodByName(searchInput);

// Combine search results
        List<Food> combinedResults = new ArrayList<>();
        combinedResults.addAll(searchResultsById);
        combinedResults.addAll(searchResultsByName);

// Forward the combined search results to the JSP page
        request.setAttribute("searchResults", combinedResults);
        //request.getRequestDispatcher("/food").forward(request, response);
        request.getRequestDispatcher("/FE_Admin/CRUD_Food.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        processRequest(request, response, account);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
