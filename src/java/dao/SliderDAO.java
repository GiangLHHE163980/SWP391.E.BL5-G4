/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author ADMIN
 */
import connection.DBContext;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Slider;

public class SliderDAO{
    private Connection conn;

    public SliderDAO() {
        DBContext db = new DBContext();
        this.conn = db.getConnection();
    }
    public List<Slider> getAllSliders() throws SQLException {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM Slider where Publish = 1";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            sliders.add(slider);
        }
        return sliders;
    }
    
    public List<Slider> getAllSlidersAdmin() throws SQLException {
        List<Slider> sliders = new ArrayList<>();
        String query = "SELECT * FROM Slider";
        PreparedStatement ps = conn.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            sliders.add(slider);
        }
        return sliders;
    }

    public Slider getSliderById(int id) throws SQLException {
        String query = "SELECT * FROM Slider WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Slider slider = new Slider();
            slider.setSliderID(rs.getInt("SliderID"));
            slider.setImageUrl(rs.getString("ImageUrl"));
            slider.setTitle(rs.getString("Title"));
            slider.setBackLink(rs.getString("BackLink"));
            slider.setDescription(rs.getString("Description"));
            slider.setPublish(rs.getBoolean("Publish"));
            return slider;
        }
        return null;
    }

    public void addSlider(Slider slider) throws SQLException {
        String query = "INSERT INTO Slider (ImageUrl, Title, BackLink, Description, Publish) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, slider.getImageUrl());
        ps.setString(2, slider.getTitle());
        ps.setString(3, slider.getBackLink());
        ps.setString(4, slider.getDescription());
        ps.setBoolean(5, slider.isPublish());
        ps.executeUpdate();
    }

    public void updateSlider(Slider slider) throws SQLException {
        String query = "UPDATE Slider SET ImageUrl = ?, Title = ?, BackLink = ?, Description = ?, Publish = ? WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, slider.getImageUrl());
        ps.setString(2, slider.getTitle());
        ps.setString(3, slider.getBackLink());
        ps.setString(4, slider.getDescription());
        ps.setBoolean(5, slider.isPublish());
        ps.setInt(6, slider.getSliderID());
        ps.executeUpdate();
    }

    public void deleteSlider(int sliderID) throws SQLException {
        String query = "DELETE FROM Slider WHERE SliderID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, sliderID);
        ps.executeUpdate();
    }
}
