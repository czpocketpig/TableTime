package cz.bean;

/**
 * Created by chenzhang on 2017/4/7.
 */

public class LocalUser {

    public   String  object_id;
    public   String student_id;
    public   String password;
    public  String stu_name;
    public  String  monday,tuesday,wednesday,thursday,friday;



    public LocalUser(String object_id, String student_id, String password, String stu_name) {
        this.object_id = object_id;
        this.student_id = student_id;
        this.password = password;
        this.stu_name = stu_name;
    }



}
