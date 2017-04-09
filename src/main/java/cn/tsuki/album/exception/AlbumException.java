package cn.tsuki.album.exception;

/**
 * Created by tsuki on 2017/4/9.
 */
public class AlbumException extends RuntimeException {
    //提供一个无参数的构造器
    public AlbumException()
    {

    }
    //提供一个待字符串参数的构造器
    public AlbumException(String message)
    {
        super(message);
    }
}
