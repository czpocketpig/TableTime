package cz.bean;

import cn.bmob.v3.BmobObject;

/**
 * Created by chenzhang on 2017/4/10.
 */

public class courses extends BmobObject {
    public String time;
    public String teacher;
    public String place;
    public String course_name;
    public String class_id;
    public String cixu;
    public String position;

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    public String getClass_id() {
        return class_id;
    }

    public void setClass_id(String class_id) {
        this.class_id = class_id;
    }

    public String getCixu() {
        return cixu;
    }

    public void setCixu(String cixu) {
        this.cixu = cixu;
    }
}
