package cn.tsuki.album.domain;

/**
 * Created by tsuki on 2017/4/9.
 */
public class Photo {
    private int id; //标识属性
    private String title;  //相片的名称
    private String fileName; // 相片在服务器上的文件名
    private User user; // 保存相片所属的用户

    public Photo() {

    }

    public Photo(int id, String title, String fileName, User user) {
        this.id = id;
        this.title = title;
        this.fileName = fileName;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
