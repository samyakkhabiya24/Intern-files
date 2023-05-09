package com.db.courseproject.repositry;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.db.courseproject.classes.Album;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AlbumRepository {

    private final JdbcTemplate jdbcTemplate;

    public AlbumRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static final RowMapper<Album> ALBUM_ROW_MAPPER = new RowMapper<Album>() {
        @Override
        public Album mapRow(ResultSet resultSet, int rowNum) throws SQLException {
            Album album = new Album();
            album.setId(resultSet.getInt("id"));
            album.setTitle(resultSet.getString("title"));
            album.setCoverPhotoId(resultSet.getInt("cover_photo_id"));
            album.setStartDate(resultSet.getDate("start_date").toLocalDate());
            album.setEndDate(resultSet.getDate("end_date").toLocalDate());
            return album;
        }
    };

    public List<Album> findAll() {
        String sql = "SELECT * FROM albums";
        return jdbcTemplate.query(sql, ALBUM_ROW_MAPPER);
    }
    
    @SuppressWarnings("deprecation")
	public Album findById(int id) {
        String sql = "SELECT * FROM albums WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, ALBUM_ROW_MAPPER);
    }

    
//    public int create(Album album) {
//        String sql = "INSERT INTO albums (title, cover_photo_id, start_date, end_date) VALUES (?, ?, ?, ?)";
//        return jdbcTemplate.update(sql, album.getTitle(), album.getCoverPhotoId(), album.getStartDate(), album.getEndDate());
//    }

    public int create(Album album) {
        String sql = "INSERT INTO albums (title, cover_photo_id, start_date, end_date) VALUES (?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, album.getTitle(), album.getCoverPhotoId(), album.getStartDate(), album.getEndDate());
        
        if (rowsAffected == 1) {
            // retrieve the ID of the newly created album
            String query = "SELECT LAST_INSERT_ID()";
            int id = jdbcTemplate.queryForObject(query, Integer.class);
            return id;
        } else {
            throw new RuntimeException("Failed to create album");
        }
    }


    public int update(Album album) {
        String sql = "UPDATE albums SET title = ?, cover_photo_id = ?, start_date = ?, end_date = ? WHERE id = ?";
        return jdbcTemplate.update(sql, album.getTitle(), album.getCoverPhotoId(), album.getStartDate(), album.getEndDate(), album.getId());
    }
    
    public int delete(int id) {
        String deletePhotosSql = "DELETE FROM photos WHERE album_id = ?";
        jdbcTemplate.update(deletePhotosSql, id);
        
        String deleteAlbumSql = "DELETE FROM albums WHERE id = ?";
        return jdbcTemplate.update(deleteAlbumSql, id);
    }  
}