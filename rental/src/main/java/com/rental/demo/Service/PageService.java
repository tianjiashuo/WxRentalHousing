package com.rental.demo.Service;

import com.rental.demo.Repository.dao.PageDao;
import com.rental.demo.Repository.entity.Swiper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("pageService")
public class PageService {

    @Autowired
    private PageDao pageDao;

    /**
     * 返回主页面的swiper 信息
     * @return
     */
    public List<Swiper> getSwiper(){
       List<Swiper> lists = pageDao.getSwiper();
        return lists;
    }

}
