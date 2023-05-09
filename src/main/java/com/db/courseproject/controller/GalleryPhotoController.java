package com.db.courseproject.controller;

import com.db.courseproject.classes.Album;
import com.db.courseproject.classes.Photo;
import com.db.courseproject.repositry.AlbumRepository;
import com.db.courseproject.repositry.PhotoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/photos")

public class GalleryPhotoController {

	@Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private PhotoRepository photoRepository;
    
    @GetMapping
    public String listAlbums(Model model) {
        List<Album> albums = albumRepository.findAll();
        for (Album album : albums) {
            Photo coverPhoto = photoRepository.findCoverPhotoByAlbumId(album.getCoverPhotoId());
            if (coverPhoto != null) {
                String coverPhotoUrl = coverPhoto.getPhotoUrl();
                album.setCoverPhotoUrl(coverPhotoUrl);
            }
        }
        model.addAttribute("albums", albums);
        return "GalleryPhotos/album_list";
    }
    
    @GetMapping("/{id}")
    public String viewAlbum(@PathVariable("id") int id, Model model) {
        Album album = albumRepository.findById(id);
        List<Photo> photos = photoRepository.findByAlbumId(id);
        model.addAttribute("album", album);
        model.addAttribute("photos", photos);
        return "GalleryPhotos/photo_list";
    }
    
    @GetMapping("/add/{id}")
    public String showAddPhotoForm(@PathVariable("id") int id, Model model) {
        model.addAttribute("albumId", id);
        model.addAttribute("photo", new Photo());
        return "GalleryPhotos/add_photo";
    }
    
    @PostMapping("/add/{id}")
    public String addPhoto(@PathVariable("id") int id, @ModelAttribute Photo photo) {
        photo.setAlbumId(id);
        photoRepository.create(photo);
        return "redirect:/admin/photos/" + id;
    }
    
    @GetMapping("/delete/{id}")
    public String deletePhoto(@PathVariable("id") int id) {
        Photo photo = photoRepository.getPhotoById(id);
        int albumId = photo.getAlbumId();
        photoRepository.delete(id);
        return "redirect:/admin/photos/" + albumId;
    }	
}
