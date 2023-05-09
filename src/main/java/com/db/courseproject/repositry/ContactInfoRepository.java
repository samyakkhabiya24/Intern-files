package com.db.courseproject.repositry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.ContactInfo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ContactInfoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<ContactInfo> findAll() {
        String query = "SELECT * FROM contact_info";
        return jdbcTemplate.query(query, new ContactInfoRowMapper());
    }

    public void deleteById(int id) {
        String query = "DELETE FROM contact_info WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    public void insert(ContactInfo contactInfo) {
        String query = "INSERT INTO contact_info (parent_name, your_name, email, mobile, your_query) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, contactInfo.getParentName(), contactInfo.getYourName(), contactInfo.getEmail(), contactInfo.getMobile(), contactInfo.getYourQuery());
    }

    private static class ContactInfoRowMapper implements RowMapper<ContactInfo> {
        @Override
        public ContactInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
            ContactInfo contactInfo = new ContactInfo();
            contactInfo.setId(rs.getInt("id"));
            contactInfo.setParentName(rs.getString("parent_name"));
            contactInfo.setYourName(rs.getString("your_name"));
            contactInfo.setEmail(rs.getString("email"));
            contactInfo.setMobile(rs.getString("mobile"));
            contactInfo.setYourQuery(rs.getString("your_query"));
            return contactInfo;
        }
    }
}
