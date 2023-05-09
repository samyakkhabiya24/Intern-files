package com.db.courseproject.repositry;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.CourseDetails;

@Repository
public class CourseDetailsRepositoryjdbclmpl  {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<CourseDetails> findAll() {
		String query = "SELECT * FROM course_details";
		return jdbcTemplate.query(query, new CourseDetailsRowMapper());
	}

	@SuppressWarnings("deprecation")
	public List<CourseDetails> findById(Long id) {
		String query = "SELECT * FROM course_details WHERE id = ?";
		return jdbcTemplate.query(query, new Object[] { id }, new CourseDetailsRowMapper());
	}

	public int save(CourseDetails courseDetails) {
		String query = "INSERT INTO course_details (course_title, youtube_link, course_description, age_group, course_duration, course_fee) VALUES (?, ?, ?, ?, ?, ?)";
		return jdbcTemplate.update(query, courseDetails.getCourseTitle(), courseDetails.getYoutubeLink(),
				courseDetails.getCourseDescription(), courseDetails.getAgeGroup(), courseDetails.getCourseDuration(),
				courseDetails.getCourseFee());
	}

	public int update(CourseDetails courseDetails) {
		String query = "UPDATE course_details SET course_title=?, youtube_link=?, course_description=?, age_group=?, course_duration=?, course_fee=? WHERE id=?";
		return jdbcTemplate.update(query, courseDetails.getCourseTitle(), courseDetails.getYoutubeLink(),
				courseDetails.getCourseDescription(), courseDetails.getAgeGroup(), courseDetails.getCourseDuration(),
				courseDetails.getCourseFee(), courseDetails.getId());
	}

	public int deleteById(Long id) {
		String batchesquery = "DELETE FROM course_batches WHERE course_id=?";
		jdbcTemplate.update(batchesquery, id);
		
		String query = "DELETE FROM course_details WHERE id=?";
		return jdbcTemplate.update(query, id);
	}

	public class CourseDetailsRowMapper implements RowMapper<CourseDetails> {

		@Override
		public CourseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
			CourseDetails course = new CourseDetails();
			course.setId(rs.getLong("id"));
			course.setCourseTitle(rs.getString("course_title"));
			course.setYoutubeLink(rs.getString("youtube_link"));
			course.setCourseDescription(rs.getString("course_description"));
			course.setAgeGroup(rs.getString("age_group"));
			course.setCourseDuration(rs.getInt("course_duration"));
			course.setCourseFee(rs.getBigDecimal("course_fee"));
			return course;
		}
	}
}

