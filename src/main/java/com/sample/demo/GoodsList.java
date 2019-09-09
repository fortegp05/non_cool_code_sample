package com.sample.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GoodsList extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter pw = response.getWriter();
        DbUtil dbUtil = new DbUtil();
        String html = "";

        // DB接続
        String message = dbUtil.connect();
        if (message != null) {
            errorHtml(pw, "connect:" + message);
            return;
        }

        // データ取得して一覧用のHTMLを生成する
        String goodsData = "";
        Statement stm = null;
        ResultSet rs = null;
        try {
            stm = dbUtil.getCon().createStatement();
            String sql = "select * from goods order by id;";
            rs = stm.executeQuery(sql);

            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int price = rs.getInt("price");

                goodsData += "<tr>";

                goodsData += "<td>" + rs.getInt("id") + "</td>";
                goodsData += "<td>" + rs.getString("name") + "</td>";
                goodsData += "<td>" + rs.getInt("price") + "</td>";

                goodsData += "</tr>";
            }
        } catch (SQLException e) {
            errorHtml(pw, "exec:" + e.getMessage());
            return;
        }finally {
            try {
                rs.close();
                stm.close();
                dbUtil.close();
            } catch (SQLException e) {

            }
        }

        // html生成

        html += "";
        html += "<!DOCTYPE html>";
        html += "<html lang=\"en\">";
        html += "<head>";
        html += "    <meta charset=\"UTF-8\">";
        html += "    <title>商品一覧</title>";
        html += "</head>";
        html += "<body>";
        html += "   <table border='1'>";
        html += "       <tr>";
        html += "           <th>商品id</th>";
        html += "           <th>名称</th>";
        html += "           <th>価格</th>";
        html += "       </tr>";
        html += goodsData;
        html += "   </table>";
        html += "</body>";
        html += "</html>";

        request.setAttribute("html", html);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/goods.jsp");

        dispatcher.forward(request, response);
    }

    /**
     *
     * @param pw
     */
    private void errorHtml(PrintWriter pw, String message) {
        String html = "";
        html += "<!DOCTYPE html>";
        html += "<html lang=\"en\">";
        html += "<head>";
        html += "    <meta charset=\"UTF-8\">";
        html += "    <title>Demo</title>";
        html += "</head>";
        html += "<body>";
        html += "エラーが発生しました。";
        html += message;
        html += "</body>";
        html += "</html>";
        pw.println(html);
    }
}
