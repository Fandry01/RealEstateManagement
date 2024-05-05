package com.example.realestatemanagment.Service;

import com.example.realestatemanagment.Models.ImageData;
import com.example.realestatemanagment.Models.Property;
import com.example.realestatemanagment.Repository.ImageRepository;
import com.example.realestatemanagment.Repository.PropertyRepository;
import com.example.realestatemanagment.Utils.ImageUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class ImageService {

    private final ImageRepository imageRepo;
    private final PropertyRepository propertyRepo;
    private final PropertyService propertyService;


    public ImageService(ImageRepository imageRepo, PropertyRepository propertyRepo, PropertyService propertyService) {
        this.imageRepo = imageRepo;
        this.propertyRepo = propertyRepo;
        this.propertyService = propertyService;
    }


    public String uploadImage(MultipartFile multipartFile, Long id) throws IOException {
        Optional<Property> property = propertyRepo.findById(id);
        Property property1 = property.get();

        ImageData imgdata = new ImageData();

        imgdata.setName(multipartFile.getName());
        imgdata.setType(multipartFile.getContentType());
        imgdata.setImageData(ImageUtil.compressImage(multipartFile.getBytes()));

        imgdata.setProperty(property1);
        ImageData savedImage = imageRepo.save(imgdata);

        property1.setImageData(imgdata);
        propertyRepo.save(property1);
        return savedImage.getName();
    }

    public byte[] downloadImage(Long id) throws IOException {
        Optional<Property> property = propertyRepo.findById(id);
        Property property1 = property.get();
        if (property.isPresent()) {
            property1 = property.get();
        }

        ImageData imageData = property1.getImageData();

        return ImageUtil.decompressImage(imageData.getImageData());
    }
}
