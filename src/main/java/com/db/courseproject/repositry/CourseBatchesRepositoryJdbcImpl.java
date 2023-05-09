package com.db.courseproject.repositry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.CourseBatches;

@Repository
public class CourseBatchesRepositoryJdbcImpl  {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

//    @Override
    public List<CourseBatches> findAll() {
        String query = "SELECT * FROM course_batches";
        return jdbcTemplate.query(query, new CourseBatchesRowMapper());
    }

    @SuppressWarnings("deprecation")
//	@Override
    public List<CourseBatches> findByCourseId(Long courseId) {
        String query = "SELECT * FROM course_batches WHERE course_id = ?";
        return jdbcTemplate.query(query, new Object[]{courseId}, new CourseBatchesRowMapper());
    }

//    @Override
    public CourseBatches findById(Long id) {
        String query = "SELECT * FROM course_batches WHERE id = ?";
        @SuppressWarnings("deprecation")
		List<CourseBatches> courseBatches = jdbcTemplate.query(query, new Object[]{id}, new CourseBatchesRowMapper());
        return courseBatches.isEmpty() ? null : courseBatches.get(0);
    }

//    @Override
    public void save(CourseBatches courseBatch) {
        String query = "INSERT INTO course_batches (course_id, batch_name, batch_start_date, batch_end_date, batch_timing) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(query, courseBatch.getCourseId(), courseBatch.getBatchName(), courseBatch.getBatchStartDate(), courseBatch.getBatchEndDate(), courseBatch.getBatchTiming());
    }

//    @Override
    public void update(CourseBatches courseBatch) {
    	
        String query = "UPDATE course_batches SET course_id = ?, batch_name = ?, batch_start_date = ?, batch_end_date = ?, batch_timing = ? WHERE id = ?";
        
        jdbcTemplate.update(query, courseBatch.getCourseId(), courseBatch.getBatchName(), courseBatch.getBatchStartDate(), courseBatch.getBatchEndDate(), courseBatch.getBatchTiming(), courseBatch.getId());
    }


//    @Override
    public void deleteById(Long id) {
        String query = "DELETE FROM course_batches WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

    private class CourseBatchesRowMapper implements RowMapper<CourseBatches> {

        @Override
        public CourseBatches mapRow(ResultSet rs, int rowNum) throws SQLException {
            CourseBatches courseBatch = new CourseBatches();
            courseBatch.setId(rs.getLong("id"));
            courseBatch.setCourseId(rs.getLong("course_id"));
            courseBatch.setBatchName(rs.getString("batch_name"));
            courseBatch.setBatchStartDate(rs.getDate("batch_start_date").toLocalDate());
            courseBatch.setBatchEndDate(rs.getDate("batch_end_date").toLocalDate());
            courseBatch.setBatchTiming(rs.getString("batch_timing"));
            return courseBatch;
        }

    }

}
