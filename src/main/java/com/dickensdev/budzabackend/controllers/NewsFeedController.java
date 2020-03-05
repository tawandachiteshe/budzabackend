package com.dickensdev.budzabackend.controllers;

import com.dickensdev.budzabackend.models.NewsFeed;
import com.dickensdev.budzabackend.services.NewsFeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.io.*;
import java.util.*;

@RestController
@RequestMapping("/api/v1")
public class NewsFeedController {
    private NewsFeedService newsFeed;
    @Autowired
    public NewsFeedController(NewsFeedService newsFeed){
        this.newsFeed = newsFeed;
    }
    @GetMapping("/newsfeed")
    public List<NewsFeed> news(){
        return newsFeed.getAllStories();
    }
    @PostMapping("/p")
    public void post(@RequestBody NewsFeed newsFeed1){
        newsFeed.addStory(newsFeed1);
    }
    @PostMapping("f")
    public void postImage(@RequestBody byte[] bytes) {
        try {
            Date date = new Date();
            Long currentmillis = System.currentTimeMillis();
            Random random = new Random();
            Integer d = random.nextInt();
            Base64.Encoder encoder = Base64.getEncoder();
            String encodedText = encoder.encodeToString(d.toString().getBytes());
            File s = new File(encodedText + ".jpg");
            DataOutputStream dor = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(s)));
            dor.write(bytes);
            dor.close();
            System.out.println(System.currentTimeMillis() - currentmillis + " ms");
            currentmillis = 0l;
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(bytes.length + " bytes" + " " + "megabytes " + (bytes.length / 1000000.0));
    }
}
