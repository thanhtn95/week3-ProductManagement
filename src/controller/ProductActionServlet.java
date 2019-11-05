package controller;

import model.product;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(urlPatterns = "/product")
public class ProductActionServlet extends javax.servlet.http.HttpServlet {
    private Connection getConnection() {
        Connection conn = null;
        try {
            conn = DBConnection.initializeDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        Connection conn = getConnection();
        String name = "", description = "";
        double price;
        int quantity;
        switch (action) {
            case "Edit":
                int id = Integer.parseInt(request.getParameter("id"));
                name = request.getParameter("name");
                price = Double.parseDouble(request.getParameter("price"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                description = request.getParameter("description");
                try {
                    PreparedStatement ps = conn.prepareStatement("update tbl_product set name=?,price=?,quantity=?,description=? where id=?");
                    ps.setString(1, name);
                    ps.setDouble(2, price);
                    ps.setInt(3, quantity);
                    ps.setString(4, description);
                    ps.setInt(5, id);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("product?action=view");
                break;

            case "Add":
                name = request.getParameter("name");
                price = Double.parseDouble(request.getParameter("price"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                description = request.getParameter("description");
                try {
                    PreparedStatement ps = conn.prepareStatement("insert into tbl_product(name,price,quantity,description) values (?,?,?,?)");
                    ps.setString(1, name);
                    ps.setDouble(2, price);
                    ps.setInt(3, quantity);
                    ps.setString(4, description);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("product?action=view");
                break;
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action = request.getParameter("action");
        Connection conn = getConnection();
        switch (action) {
            case "view":
                ArrayList<product> productList = new ArrayList<>();
                try {
                    Statement st = conn.createStatement();
                    String query = "select * from tbl_product";
                    ResultSet rs = st.executeQuery(query);
                    while (rs.next()) {
                        product newProduct = new product();
                        newProduct.setId(rs.getInt(1));
                        newProduct.setName(rs.getString(2));
                        newProduct.setPrice(rs.getDouble(3));
                        newProduct.setQuantity(rs.getInt(4));
                        newProduct.setDescription(rs.getString(5));
                        productList.add(newProduct);
                    }
                    rs.close();
                    st.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                request.setAttribute("productList", productList);
                request.getRequestDispatcher("index.jsp").forward(request, response);
                break;

            case "delete":
                int id = Integer.parseInt(request.getParameter("id"));
                try {
                    PreparedStatement ps = conn.prepareStatement("delete from tbl_product where id=?");
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    ps.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                response.sendRedirect("product?action=view");
                break;

            case "getSelected":
                int selectedId = Integer.parseInt(request.getParameter("id"));
                try {
                    PreparedStatement ps = conn.prepareStatement("select * from tbl_product where id=?");
                    ps.setInt(1, selectedId);
                    ResultSet rs = ps.executeQuery();
                    product selectedProduct = new product();
                    while (rs.next()) {
                        selectedProduct.setId(rs.getInt(1));
                        selectedProduct.setName(rs.getString(2));
                        selectedProduct.setPrice(rs.getDouble(3));
                        selectedProduct.setQuantity(rs.getInt(4));
                        selectedProduct.setDescription(rs.getString(5));
                    }
                    request.setAttribute("selectedProduct", selectedProduct);
                    request.getRequestDispatcher("EditProductForm.jsp").forward(request, response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }
}
