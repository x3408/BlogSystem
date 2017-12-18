package Bean;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.Map;

public class User implements HttpSessionBindingListener{
    private String id;
    private String username;
    private String password;
    private String hobby;
    private String speciality;
    private String path;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public boolean equals(Object o) {
        User user = (User)o;
        if (this.id.equals(user.id)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.username.hashCode()*2 + 31;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {}

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        Map<User, HttpSession> map = (Map<User, HttpSession>) httpSessionBindingEvent.getSession().getServletContext().getAttribute("userMap");
        map.remove(this);
    }
}
