package com.db.courseproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.db.courseproject.classes.CourseDetails;
import com.db.courseproject.repositry.CourseDetailsRepositoryjdbclmpl;

@Controller
public class CourseDetailsController {

    @Autowired
    private CourseDetailsRepositoryjdbclmpl courseDetailsRepository;
    
    
    
    @GetMapping("/admin/courses")
    public String getAllCourses(Model model) {
        List<CourseDetails> courses = courseDetailsRepository.findAll();
        
        
        model.addAttribute("courses", courses);
        return "CourseDetails/admin_panel";
    }

    @GetMapping("/admin/courses/{id}/edit")
    public String getCourseById(@PathVariable(value = "id") Long id, Model model) {
        List<CourseDetails> course = courseDetailsRepository.findById(id);
        if (course.size() > 0) {
            model.addAttribute("course", course.get(0));
        }
        return "CourseDetails/admin_panel_courses_edit";
    }

    @PostMapping("/admin/courses")
    public String addCourse(@ModelAttribute("course") CourseDetails courseDetails) {
        courseDetailsRepository.save(courseDetails);
        return "redirect:/admin/courses";
    }

    @PostMapping("/admin/courses/{id}")
    public String updateCourse(@PathVariable(value = "id") Long id,
                               @ModelAttribute("course") CourseDetails courseDetails) {
        List<CourseDetails> course = courseDetailsRepository.findById(id);
        if (course.size() > 0) {
            courseDetails.setId(id);
            courseDetailsRepository.update(courseDetails);
        }
        return "redirect:/admin/courses";
    }

    @PostMapping("/admin/courses/{id}/delete")
    public String deleteCourse(@PathVariable(value = "id") Long id) {
        courseDetailsRepository.deleteById(id);
        return "redirect:/admin/courses";
    }

}
