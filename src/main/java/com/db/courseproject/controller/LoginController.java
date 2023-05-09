package com.db.courseproject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller
public class LoginController {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @GetMapping("/login")
  public String showLoginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String handleLoginRequest(@RequestParam String username, @RequestParam String password, Model model) {
    String sql = "SELECT * FROM login WHERE username = ? AND password = ?";
    RowMapper<User> rowMapper = new UserRowMapper();
    List<User> users = jdbcTemplate.query(sql, rowMapper, username, password);

    if (!users.isEmpty()) {
      return "redirect:/admin";
    } else {
      model.addAttribute("error", "Invalid username or password");
      return "login";
    }
  }

  

  private class User {
    private String username;
    private String password;

    public User(String username, String password) {
      this.username = username;
      this.password = password;
    }

    @SuppressWarnings("unused")
	public String getUsername() {
      return username;
    }

    @SuppressWarnings("unused")
	public void setUsername(String username) {
      this.username = username;
    }

    @SuppressWarnings("unused")
	public String getPassword() {
      return password;
    }

    @SuppressWarnings("unused")
	public void setPassword(String password) {
      this.password = password;
    }
  }

  private class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      String username = rs.getString("username");
      String password = rs.getString("password");
      return new User(username, password);
    }
  }

}

