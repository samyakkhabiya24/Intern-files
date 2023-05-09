package com.db.courseproject.controller;

import com.db.courseproject.classes.CourseBatches;
import com.db.courseproject.classes.CourseDetails;
//import com.db.courseproject.classes.CourseDetails;
//import com.db.courseproject.repositry.CourseBatchesRepository;
import com.db.courseproject.repositry.CourseBatchesRepositoryJdbcImpl;
//import com.db.courseproject.repositry.CourseDetailsRepository;
import com.db.courseproject.repositry.CourseDetailsRepositoryjdbclmpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/batches")
public class CourseBatchesController {

    @Autowired
    private CourseBatchesRepositoryJdbcImpl courseBatchesRepository;
    
    @Autowired
    private CourseDetailsRepositoryjdbclmpl courseDetailsRepository;
    
    @GetMapping
    public String BatchesHome(Model model) {
        model.addAttribute("courses", courseDetailsRepository.findAll());
        return "CourseBatch/BatchesHome";
    }

    
    @GetMapping("/BatchesHome/{id}")
    public String showBatches(@PathVariable("id") Long id, Model model) {
        List<CourseDetails> course = courseDetailsRepository.findById(id);
        List<CourseBatches> batches = courseBatchesRepository.findByCourseId(id);
        model.addAttribute("course", course);
        model.addAttribute("batches", batches);
        model.addAttribute("courseId", id); 
        return "CourseBatch/admin_panel_batches";
    }

//    @GetMapping("/BatchesHome/{id}/add")
//    public String showAddBatchForm(Model model) {
//        model.addAttribute("batch", new CourseBatches());
//        return "CourseBatch/add-edit-batch";
//    }

    @PostMapping("/BatchesHome/{id}")
    public String addBatch(@PathVariable("id") Long id, @ModelAttribute("batch") CourseBatches batch) {
        batch.setCourseId(id);
        courseBatchesRepository.save(batch);
        return "redirect:/admin/batches/BatchesHome/" + batch.getCourseId();
    }

    @GetMapping("/BatchesHome/edit/{id}")
    public String showEditBatchForm(@PathVariable("id") Long id, Model model) {
        CourseBatches batch = courseBatchesRepository.findById(id);
        model.addAttribute("batch", batch);
        return "CourseBatch/edit_batches";
    }

    @PostMapping("/BatchesHome/edit/{id}")
    public String editBatch(@PathVariable("id") Long id, @ModelAttribute("batch") CourseBatches batch) {
    	
    	batch.setId(id);
        
        courseBatchesRepository.update(batch);
        return "redirect:/admin/batches/BatchesHome/"+ batch.getCourseId();
    }

    @GetMapping("/BatchesHome/delete/{id}")
    public String deleteBatch(@PathVariable("id") Long id) {
    	CourseBatches batch = courseBatchesRepository.findById(id);
        Long val=batch.getCourseId();
    	courseBatchesRepository.deleteById(id);
        return "redirect:/admin/batches/BatchesHome/"+ val;
    }
}

