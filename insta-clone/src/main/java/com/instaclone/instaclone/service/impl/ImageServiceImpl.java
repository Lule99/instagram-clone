package com.instaclone.instaclone.service.impl;

import com.instaclone.instaclone.service.ImageService;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

@Service
public class ImageServiceImpl implements ImageService {

    @Override
    public String uploadImage(String picture, Long entityId, String entityType) {
        String images = System.getProperty("user.dir")+ "\\insta-clone\\src\\main\\resources\\static\\"+entityType+"\\";
        String images2 = System.getProperty("user.dir")+ "\\insta-clone\\target\\classes\\static\\"+entityType+"\\";
        String imageDataBytes = picture.substring(picture.indexOf(",")+1);

        byte[] data = Base64.getDecoder().decode(imageDataBytes);

        try(OutputStream stream = new FileOutputStream(images + entityId + ".jpg")) {
            stream.write(data);
        } catch (Exception e) {
            System.out.println("Error on attempt to save picture!");
        }
        try(OutputStream stream = new FileOutputStream(images2 + entityId +".jpg")) {
            stream.write(data);
        } catch (Exception e) {
            System.out.println("Error on attempt to save picture!");
        }
        return "/static/"+entityType+"/"+entityId+".jpg";

    }
}
