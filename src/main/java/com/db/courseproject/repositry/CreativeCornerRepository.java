package com.db.courseproject.repositry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.CreativeCorner;

@Repository
public class CreativeCornerRepository {

    private final JdbcTemplate jdbcTemplate;

    public CreativeCornerRepository(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<CreativeCorner> getAllUrls() {
        String sql = "SELECT * FROM creative_corner";
        return jdbcTemplate.query(sql, new CreativeCornerRowMapper());
    }

    public void addUrl(CreativeCorner creativeCorner) {
        String sql = "INSERT INTO creative_corner (url) VALUES (?)";
        jdbcTemplate.update(sql, creativeCorner.getUrl());
    }

    public void editUrl(CreativeCorner creativeCorner) {
        String sql = "UPDATE creative_corner SET url=? WHERE id=?";
        jdbcTemplate.update(sql, creativeCorner.getUrl(), creativeCorner.getId());
    }

    public void deleteUrl(int id) {
        String sql = "DELETE FROM creative_corner WHERE id=?";
        jdbcTemplate.update(sql, id);
    }
    
    public CreativeCorner getUrlById(int id) {
        String sql = "SELECT * FROM creative_corner WHERE id = ?";
        @SuppressWarnings("deprecation")
		CreativeCorner creativeCorner = jdbcTemplate.queryForObject(sql, new Object[]{id}, new CreativeCornerRowMapper());
        return creativeCorner;
    }

    private static final class CreativeCornerRowMapper implements RowMapper<CreativeCorner> {

        @Override
        public CreativeCorner mapRow(ResultSet rs, int rowNum) throws SQLException {
            int id = rs.getInt("id");
            String url = rs.getString("url");
            return new CreativeCorner(id, url);
        }
    }
}
