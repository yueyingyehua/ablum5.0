package cn.tsuki.album.vo;

/**
 * Created by tsuki on 2017/4/9.
 */
public class PhotoHolder {
    //相片的名称
    private String title;
    //相片在服务器上的文件名
    private String fileName;

    public PhotoHolder() {

    }

    public PhotoHolder(String title, String fileName) {
        this.title = title;
        this.fileName = fileName;
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
}
