/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.InsuranceProduct;
import service.product.IProductService;
import service.product.ProductService;

/**
 *
 * @author TH Computer
 */
@WebServlet(name = "HomePageController", urlPatterns = {"/homepage"})
public class HomePageController extends HttpServlet{
     private final IProductService productService = new ProductService();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<InsuranceProduct> topProducts = productService.getTopProducts(6);
        request.setAttribute("topProducts", topProducts);
        request.getRequestDispatcher("homePage.jsp").forward(request, response);
    }
}
