package com.db.courseproject.repositry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.Photo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PhotoRepository {

    private final JdbcTemplate jdbcTemplate;

    public PhotoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Photo> PHOTO_ROW_MAPPER = new RowMapper<Photo>() {
        @Override
        public Photo mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Photo photo = new Photo();
            photo.setId(resultSet.getInt("id"));
            photo.setAlbumId(resultSet.getInt("album_id"));
            photo.setPhotoUrl(resultSet.getString("photo_url"));
            return photo;
        }
    };

    public List<Photo> findAll() {
        String sql = "SELECT * FROM photos";
        return jdbcTemplate.query(sql, PHOTO_ROW_MAPPER);
    }
    
    @SuppressWarnings("deprecation")
	public Photo getPhotoById(int id) {
        String sql = "SELECT * FROM photos WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, PHOTO_ROW_MAPPER);
    }
    
    @SuppressWarnings("deprecation")
	public Photo findCoverPhotoByAlbumId(int CoverPhotoId) {
        String sql = "SELECT * FROM photos WHERE id = ? ";
        return jdbcTemplate.queryForObject(sql, new Object[]{CoverPhotoId}, PHOTO_ROW_MAPPER);
    }
    
    @SuppressWarnings("deprecation")
	public List<Photo> findByAlbumId(int albumId) {
        String sql = "SELECT * FROM photos WHERE album_id = ?";
        return jdbcTemplate.query(sql, new Object[]{albumId}, PHOTO_ROW_MAPPER);
    }
    
//    public int create(Photo photo) {
//        String sql = "INSERT INTO photos (album_id, photo_url) VALUES (?, ?)";
//        return jdbcTemplate.update(sql, photo.getAlbumId(), photo.getPhotoUrl());
//    }
    public int create(Photo photo) {
        String sql = "INSERT INTO photos (album_id, photo_url) VALUES (?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, photo.getAlbumId(), photo.getPhotoUrl());
        
        if (rowsAffected == 1) {
            // retrieve the ID of the newly created photo
            String query = "SELECT LAST_INSERT_ID()";
            int id = jdbcTemplate.queryForObject(query, Integer.class);
            return id;
        } else {
            throw new RuntimeException("Failed to create photo");
        }
    }

    public int update(Photo photo) {
        String sql = "UPDATE photos SET album_id = ?, photo_url = ? WHERE id = ?";
        return jdbcTemplate.update(sql, photo.getAlbumId(), photo.getPhotoUrl(), photo.getId());
    }

    public int delete(int id) {
        String sql = "DELETE FROM photos WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }
}