package com.db.courseproject.controller;

import com.db.courseproject.classes.Album;
import com.db.courseproject.classes.ContactInfo;
import com.db.courseproject.classes.CourseBatches;
import com.db.courseproject.classes.CourseDetails;
import com.db.courseproject.classes.CreativeCorner;
import com.db.courseproject.classes.Photo;
import com.db.courseproject.repositry.*;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class mainController {
	
	    @Autowired
	    private CourseDetailsRepositoryjdbclmpl courseDetailsRepository;
	    
	    @Autowired
	    private CourseBatchesRepositoryJdbcImpl courseBatchesRepository;
	    
	    @Autowired
	    private AlbumRepository albumRepository;

	    @Autowired
	    private PhotoRepository photoRepository;
	    
	    @Autowired
	    private ContactInfoRepository contactInfoRepository;
	    
	    @Autowired
	    private CreativeCornerRepository CreativeCornerRepository;

	    @GetMapping("/")
	    public String home(Model model) {
	        model.addAttribute("courses", courseDetailsRepository.findAll());
	        return "HomePage/home";
	    }
	    
	    @GetMapping("/admin")
	    public String adminHome() {
	        return "adminHome/adminHome";
	    }
	    
	    @GetMapping("/batches/{id}")
	    public String courseBatches(@PathVariable("id") Long id, Model model) {
	    	List<CourseDetails> course = courseDetailsRepository.findById(id);
	        List<CourseBatches> batches = courseBatchesRepository.findByCourseId(id);
	        List<CreativeCorner> urls = CreativeCornerRepository.getAllUrls();
	        model.addAttribute("urls", urls);
	        model.addAttribute("course", course);
	        model.addAttribute("batches", batches);
	        return "HomePage/courses/batches";
	    }
	    

	    
	    @GetMapping("/gallery")
	    public String showAlbums(Model model) {
	        List<Album> albums = albumRepository.findAll();
	        for (Album album : albums) {
	            Photo coverPhoto = photoRepository.findCoverPhotoByAlbumId(album.getCoverPhotoId());
	            if (coverPhoto != null) {
	                String coverPhotoUrl = coverPhoto.getPhotoUrl();
	                album.setCoverPhotoUrl(coverPhotoUrl);
	            }
	        }
	        model.addAttribute("albums", albums);
	        return "HomePage/gallery/albums";
	    }
	    
	    @GetMapping("/AboutUs")
	    public String AboutUs() {
	        return "HomePage/AboutUs";
	    }
	    
	    @GetMapping("/gallery/{albumId}")
	    public String showPhotos(@PathVariable("albumId") int albumId, Model model) {
	    Album album = albumRepository.findById(albumId);
	    List<Photo> photos = photoRepository.findByAlbumId(albumId);
	    model.addAttribute("album", album);
	    model.addAttribute("photos", photos);
	    return "HomePage/gallery/photos";
	    }
	    
	    @GetMapping("/contact")
	    public String showContactForm(Model model) {
	        model.addAttribute("contactUs", new ContactInfo());
	        return "HomePage/contact_form";
	    }
	    
	    @PostMapping("/contact")
	    public String addInquiry(@ModelAttribute("newInquiry") ContactInfo contactInfo) {
	        contactInfoRepository.insert(contactInfo);
	        return "redirect:/contact";
	    }
	    
}
