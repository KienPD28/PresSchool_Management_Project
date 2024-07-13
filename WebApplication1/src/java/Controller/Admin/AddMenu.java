package Controller.Admin;

import Authentication.BaseRBACController;
import DAO.AgeDBContext;
import DAO.FoodDBContext;
import DAO.MealTimeDBContext;
import DAO.MenuDBContext;
import Entity.Account;
import Entity.AgeCategory;
import Entity.Food;
import Entity.MealTime;
import Entity.Menu;
import Entity.MenuFood;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "AddMenu", urlPatterns = {"/admin/menu"})
public class AddMenu extends BaseRBACController {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dateFormat2 = new SimpleDateFormat("dd/MM/yyyy");
        FoodDBContext foodDB = new FoodDBContext();
        MealTimeDBContext mealDB = new MealTimeDBContext();
        AgeDBContext ageDB = new AgeDBContext();
        MenuDBContext menuDB = new MenuDBContext();

        List<Food> listFood = foodDB.getAllFood();
        List<MealTime> listMeal = mealDB.getAllMealTime();
        List<AgeCategory> listAge = ageDB.getAllAgeCategory();
        List<Menu> listMenu = menuDB.getMenuByDate(dateFormat.format(currentDate));

        request.setAttribute("listFood", listFood);
        session.setAttribute("listMeal", listMeal);
        session.setAttribute("listAgeCategory", listAge);
        session.setAttribute("listMenu", listMenu);

        List<MenuFood> listMenuFood = (List<MenuFood>) session.getAttribute("listMenuFood");
        if (listMenuFood == null) {
            listMenuFood = new ArrayList<>();
            session.setAttribute("listMenuFood", listMenuFood);
        }

        String foodid = request.getParameter("foodid");
        if (foodid != null) {
            request.setAttribute("fid", Integer.parseInt(foodid));
        }
        request.setAttribute("dateN", dateFormat2.format(currentDate));
        request.getRequestDispatcher("/FE_Admin/Menu.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response, Account account)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        String mealID = request.getParameter("mealID");
        String foodid = request.getParameter("foodid");
        String save = request.getParameter("save");
        String age_raw = request.getParameter("ageid");
        String age = (String) session.getAttribute("ageid");

        if (age_raw != null) {
            age = age_raw;
            session.setAttribute("ageid", age_raw);
        }

        MenuDBContext menuDB = new MenuDBContext();
        MealTimeDBContext mealDB = new MealTimeDBContext();
        FoodDBContext foodDB = new FoodDBContext();
        
        List<MealTime> listMeal = mealDB.getAllMealTime();
        List<MenuFood> listMenuFood = (List<MenuFood>) session.getAttribute("listMenuFood");

        if (listMenuFood == null) {
            listMenuFood = new ArrayList<>();
        }

        session.setAttribute("Err", "");
        session.setAttribute("Mess", "");

        if ("1".equals(save) && listMenuFood != null && !listMenuFood.isEmpty()) {
            saveMenu(listMenuFood, age, dateFormat.format(currentDate), listMeal, menuDB, session, request);
            response.sendRedirect("searchMenu?date=" + dateFormat.format(currentDate));
        } else if (mealID != null && foodid != null && !foodid.equals("0")) {
            addFoodToMenu(mealID, foodid, listMenuFood, foodDB, session);
     
        }
if (!"1".equals(save)){
        response.sendRedirect("menu");
}
    }

    private void saveMenu(List<MenuFood> listMenuFood, String age, String currentDate, List<MealTime> listMeal, MenuDBContext menuDB, HttpSession session, HttpServletRequest request) {
        if (age != null && !age.equalsIgnoreCase("0")) {
            List<Menu> existingMenus = menuDB.getMenuByAgeAndDate(Integer.parseInt(age), currentDate);

            for (MealTime mealTime : listMeal) {
                StringBuilder menuBuilder = new StringBuilder();
                for (MenuFood menuFood : listMenuFood) {
                    if (menuFood.getMealid() == mealTime.getMealID()) {
                        Food food = menuFood.getFood();
                        if (food != null) {
                            menuBuilder.append(food.getFname()).append(", ");
                        }
                    }
                }
                if (menuBuilder.length() > 0 && menuBuilder.toString().endsWith(", ")) {
                    menuBuilder.setLength(menuBuilder.length() - 2);
                }

                if (!menuBuilder.toString().isEmpty()) {
                    boolean menuExists = false;
                    for (Menu menu : existingMenus) {
                        if (menu.getMealID().getMealID() == mealTime.getMealID()) {
                            menuDB.update(currentDate, menuBuilder.toString(), Integer.parseInt(age), mealTime.getMealID());
                            session.setAttribute("Mess", "Cập nhật thành công bữa ăn.");
                            menuExists = true;
                            break;
                        }
                    }
                    if (!menuExists) {
                        menuDB.insertMenu(Integer.parseInt(age), currentDate, menuBuilder.toString(), mealTime.getMealID());
                        session.setAttribute("Mess", "Thêm thành công bữa ăn");
                    }
                }
            }
            listMenuFood.clear();
        } else {
            session.setAttribute("Err", "Bạn chưa nhập độ tuổi.");
        }
    }

    private void addFoodToMenu(String mealID, String foodid, List<MenuFood> listMenuFood, FoodDBContext foodDB, HttpSession session) {
        Food selectedFood = foodDB.getFoodById(Integer.parseInt(foodid));
        if (selectedFood != null) {
            boolean foodExists = listMenuFood.stream()
                .anyMatch(menuFood -> menuFood.getMealid() == Integer.parseInt(mealID) && menuFood.getFood().getFoodid() == selectedFood.getFoodid());

            if (foodExists) {
                session.setAttribute("Err", "Món ăn đã tồn tại trong danh sách!");
            } else {
                listMenuFood.add(new MenuFood(Integer.parseInt(mealID), selectedFood));
                session.setAttribute("listMenuFood", listMenuFood);
            }
        }
    }

    public String convertToStandardFormat(String dateString) {
        List<String> dateFormats = Arrays.asList(
                "yyyy-MM-dd", "dd-MM-yyyy", "MM-dd-yyyy", "dd/MM/yyyy", 
                "MM/dd/yyyy", "yyyy/MM/dd", "yyyy.MM.dd", "dd.MM.yyyy", 
                "MM.dd.yyyy"
        );

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (String format : dateFormats) {
            try {
                LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern(format));
                return date.format(outputFormatter);
            } catch (DateTimeParseException e) {
                // Ignore and try the next format
            }
        }

        return null; // If no format matches
    }

    private final List<DateTimeFormatter> DATE_FORMATTERS = Arrays.asList(
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd")
    );

    public LocalDateTime parseDate(String dateString) {
        for (DateTimeFormatter formatter : DATE_FORMATTERS) {
            try {
                return LocalDate.parse(dateString, formatter).atStartOfDay();
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
            try {
                return LocalDateTime.parse(dateString, formatter);
            } catch (DateTimeParseException e) {
                // Continue to next formatter
            }
        }
        throw new IllegalArgumentException("Date format not recognized: " + dateString);
    }

    public int compareDates(String dateStr1, String dateStr2) {
        try {
            LocalDateTime date1 = parseDate(dateStr1);
            LocalDateTime date2 = parseDate(dateStr2);
            return date1.compareTo(date2);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return 0; // or handle error appropriately
        }
    }

    @Override
    public String getServletInfo() {
        return "Menu management servlet";
    }
}
