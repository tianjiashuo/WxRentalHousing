package com.rental.demo.Controller;

import com.rental.demo.Repository.entity.Swiper;
import com.rental.demo.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PageController {

    @Autowired
    private PageService pageService;

    @GetMapping("/page/swiper")
    public List<Swiper> showSwiper(){
        return pageService.getSwiper();
    }

}
