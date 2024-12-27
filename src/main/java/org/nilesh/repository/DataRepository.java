package org.nilesh.repository;



import org.nilesh.model.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataRepository extends DBConnection{

    public DBConnection db;

    public DataRepository() {
        db = DBConnection.getInstance();
        connection = db.getConnection();
    }

    // Get all data records
    public List<Data> getAllData() {
        List<Data> dataList = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            String sql = "SELECT * FROM Data";
            pstmt = connection.prepareStatement(sql);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Data data = new Data(
                    rs.getInt("customer_id"),
                    rs.getString("customer_name"),
                    rs.getString("customer_address"),
                    rs.getString("customer_contact"),
                    rs.getString("customer_crop"),
                    rs.getInt("crop_weight"),
                    rs.getInt("crop_highest_rate"),
                    rs.getInt("payment"),
                    rs.getDate("date")
                );
                dataList.add(data);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return dataList;
    }
}
