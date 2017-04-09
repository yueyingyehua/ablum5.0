package cn.tsuki.album.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by tsuki on 2017/4/9.
 */
public class User {
    private Integer id;
    private String name;
    private String password;
    private Set<Photo> photos = new HashSet<Photo>();

    public User() {

    }

    public User(int id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(Set<Photo> photos) {
        this.photos = photos;
    }
}
