package com.db.courseproject.repositry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.time.LocalTime;

import com.db.courseproject.classes.Student;
import com.db.courseproject.classes.Student.Gender;
import com.db.courseproject.classes.Student.PaymentMethod;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AdminStudentForm {

	@Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM students", new StudentRowMapper());
    }

    @SuppressWarnings("deprecation")
	public Student findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM students WHERE id=?", new Object[]{id}, new StudentRowMapper());
    }

    public void save(Student student) {
        jdbcTemplate.update("INSERT INTO students (name, date_of_birth, gender, res_address, office_address, phone_res, phone_off, mobile, email, qualification, course_enrolled, start_date, start_time, payment_method, amount, bank_name, cheque_dd_no, cheque_dd_date, agreement, date_applied) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                student.getName(), student.getDateOfBirth(), student.getGender().getValue(), student.getResAddress(), student.getOfficeAddress(),
                student.getPhoneRes(), student.getPhoneOff(), student.getMobile(), student.getEmail(), student.getQualification(), student.getCourseEnrolled(),
                student.getStartDate(), student.getStartTime(), student.getPaymentMethod().name(), student.getAmount(), student.getBankName(),
                student.getChequeDdNo(), student.getChequeDdDate(), student.isAgreement(), student.getDateApplied());
    }

    public void update(int id, Student student) {
        jdbcTemplate.update("UPDATE students SET name=?, date_of_birth=?, gender=?, res_address=?, office_address=?, phone_res=?, phone_off=?, mobile=?, email=?, qualification=?, course_enrolled=?, start_date=?, start_time=?, payment_method=?, amount=?, bank_name=?, cheque_dd_no=?, cheque_dd_date=?, agreement=?, date_applied=? WHERE id=?",
                student.getName(), student.getDateOfBirth(), student.getGender().getValue(), student.getResAddress(), student.getOfficeAddress(),
                student.getPhoneRes(), student.getPhoneOff(), student.getMobile(), student.getEmail(), student.getQualification(), student.getCourseEnrolled(),
                student.getStartDate(), student.getStartTime(), student.getPaymentMethod().name(), student.getAmount(), student.getBankName(),
                student.getChequeDdNo(), student.getChequeDdDate(), student.isAgreement(), student.getDateApplied(), id);
    }

    public void deleteById(int id) {
        jdbcTemplate.update("DELETE FROM students WHERE id=?", id);
    }
    
    public class StudentRowMapper implements RowMapper<Student> {

        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setDateOfBirth(rs.getObject("date_of_birth", LocalDate.class));
            student.setGender(Gender.fromValue(rs.getString("gender")));
            student.setResAddress(rs.getString("res_address"));
            student.setOfficeAddress(rs.getString("office_address"));
            student.setPhoneRes(rs.getString("phone_res"));
            student.setPhoneOff(rs.getString("phone_off"));
            student.setMobile(rs.getString("mobile"));
            student.setEmail(rs.getString("email"));
            student.setQualification(rs.getString("qualification"));
            student.setCourseEnrolled(rs.getString("course_enrolled"));
            student.setStartDate(rs.getObject("start_date", LocalDate.class));
            student.setStartTime(rs.getObject("start_time", LocalTime.class));
            
            student.setPaymentMethod(PaymentMethod.fromValue(rs.getString("payment_method")));
            student.setAmount(rs.getBigDecimal("amount"));
            student.setBankName(rs.getString("bank_name"));
            student.setChequeDdNo(rs.getString("cheque_dd_no"));
            student.setChequeDdDate(rs.getDate("cheque_dd_date"));
            student.setAgreement(rs.getBoolean("agreement"));
            student.setDateApplied(rs.getDate("date_applied"));
            return student;
        }
    }
    
}
