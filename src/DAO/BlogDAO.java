package DAO;

import Bean.Blog;
import Bean.User;

import java.util.List;

public interface BlogDAO {
    List<Blog> findPage(int page, int limit);

    int getTotalCount();

    int getTotalCount(User user);

    List<Blog> findPage(User user, int page, int limit);

    boolean addEssay(Blog blog, User user);
}
