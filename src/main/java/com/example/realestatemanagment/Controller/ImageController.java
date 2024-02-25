package com.example.realestatemanagment.Controller;

import com.example.realestatemanagment.Models.ImageData;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.ImageRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.example.realestatemanagment.Service.ImageService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;
    private final ImageRepository imageRepo;
    private final PropertyRepository propertyRepo;

    public ImageController(ImageService imageService, ImageRepository imageRepo, PropertyRepository propertyRepo) {
        this.imageService = imageService;
        this.imageRepo = imageRepo;
        this.propertyRepo = propertyRepo;
    }

    @PostMapping
    public ResponseEntity<String> uploadImage(@RequestParam("file")MultipartFile multipartFile, @RequestParam Long id) throws IOException {
        String image = imageService.uploadImage(multipartFile,id);
        return ResponseEntity.ok("file");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> downloadImage(@PathVariable("id") Long id) throws IOException {
        byte[] image =  imageService.downloadImage(id);
        Optional<Property> property = propertyRepo.findById(id);
        Optional<ImageData> dbImageData = imageRepo.findById(property.get().getImageData().getId());
        MediaType mediaType = MediaType.valueOf(dbImageData.get().getType());

        return ResponseEntity.ok().contentType(mediaType).body(image);

    }
}
