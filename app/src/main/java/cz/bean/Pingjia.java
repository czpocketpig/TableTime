package cz.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by chenzhang on 2017/4/12.
 */

public class Pingjia extends BmobObject {
    public  String student_id;
    public  String content;

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
