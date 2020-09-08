package com.rental.demo.Service;

import com.rental.demo.Repository.dao.NewsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("NewsService")
public class NewsService {
    @Autowired
    private NewsDao newsDao;

    /**
     * 增加一条信息
     * @param userId
     * @param content
     * @return
     */
    public boolean addNews(int userId, String content){
       return  newsDao.addNews(String.valueOf(userId),content);
    }

}
