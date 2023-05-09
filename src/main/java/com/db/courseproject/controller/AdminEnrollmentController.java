package com.db.courseproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.db.courseproject.classes.Student;
import com.db.courseproject.repositry.AdminStudentForm;



import java.util.List;

@Controller
@RequestMapping("/admin/enrollment")
public class AdminEnrollmentController {
	
	
	private final AdminStudentForm studentRepository;

    
    public AdminEnrollmentController(AdminStudentForm studentRepository) {
        this.studentRepository = studentRepository;
    }

    
    

    @GetMapping
    public String showStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "EnrollmentForm/admin_panel";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "EnrollmentForm/add";
    }

    @PostMapping("/new")
    public String addStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/admin/enrollment";
    }
    
    @GetMapping("reciept/{id}")
    public String showReciept(@PathVariable int id, Model model) {
        Student student = studentRepository.findById(id);
        model.addAttribute("student", student);
        return "EnrollmentForm/reciept";
    }


//    @GetMapping("/edit/{id}")
//    public String showEditForm(@PathVariable("id") int id, Model model) {
//        Student student = studentRepository.findById(id);
//        model.addAttribute("student", student);
//        return "EnrollmentForm/edit";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateStudent(@PathVariable("id") int id, @ModelAttribute Student student) {
//        studentRepository.update(id, student);
//        return "redirect:/admin/enrollment";
//    }
    

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") int id) {
        studentRepository.deleteById(id);
        return "redirect:/admin/enrollment";
    }
}




