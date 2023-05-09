package com.db.courseproject.controller;

import com.db.courseproject.classes.Album;
import com.db.courseproject.classes.Photo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.db.courseproject.repositry.AlbumRepository;
import com.db.courseproject.repositry.PhotoRepository;

@Controller
@RequestMapping("/admin/albums")

public class GalleryAlbumController {
	
	@Autowired
    private AlbumRepository albumRepository;
	@Autowired
    private PhotoRepository photoRepository;

    @GetMapping
    public String getAllAlbums(Model model) {
        List<Album> albums = albumRepository.findAll();
        model.addAttribute("albums", albums);
        return "GalleryAlbums/albums";
    }

//    @GetMapping("/{id}")
//    public String getAlbumById(@PathVariable int id, Model model) {
//        Album album = albumRepository.findById(id);
//        model.addAttribute("album", album);
//        return "GalleryAlbums/album";
//    }

    @GetMapping("/add")
    public String addAlbum(Model model) {
        model.addAttribute("album", new Album());
        return "GalleryAlbums/add_album";
    }

//    @PostMapping("/add")
//    public String createAlbum(@ModelAttribute Album album) {
//        albumRepository.create(album);
//        return "redirect:/admin/albums";
//    }
    
    @PostMapping("/add")
    public String createAlbum(@ModelAttribute Album album) {
        // first create the album in the database
        int albumId = albumRepository.create(album);

        if (album.getCoverPhotoUrl() != null && !album.getCoverPhotoUrl().isEmpty()) {
            // create cover photo for the album and associate it with the album
            Photo coverPhoto = new Photo();
            coverPhoto.setAlbumId(albumId);
            coverPhoto.setPhotoUrl(album.getCoverPhotoUrl());
            int coverPhotoId = photoRepository.create(coverPhoto);

            // update the album with the cover photo id
            Album newAlbum = new Album();
            newAlbum.setId(albumId);
            newAlbum.setTitle(album.getTitle());
            newAlbum.setStartDate(album.getStartDate());
            newAlbum.setEndDate(album.getEndDate());
            newAlbum.setCoverPhotoId(coverPhotoId);
            albumRepository.update(newAlbum);
        }

        return "redirect:/admin/albums";
    }




    @GetMapping("/edit/{id}")
    public String editAlbum(@PathVariable int id, Model model) {
    	Album album = albumRepository.findById(id);
        List<Photo> photos = photoRepository.findByAlbumId(id);
        model.addAttribute("album", album);
        model.addAttribute("photos", photos);
        return "GalleryAlbums/edit_album";
    }

    @PostMapping("/edit")
    public String updateAlbum(@ModelAttribute Album album) {
        albumRepository.update(album);
        return "redirect:/admin/albums";
    }

    @GetMapping("/delete/{id}")
    public String deleteAlbum(@PathVariable int id) {
        albumRepository.delete(id);
        return "redirect:/admin/albums";
    }
    
    
	
}
