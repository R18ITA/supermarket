package net.togogo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonController {
    public Date createtime(){
        Date date=new Date();//创建时间
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        String nowTime=sdf.format(date);
        Date time= null;
        try {
            time = sdf.parse(nowTime);
        } catch (ParseException e) {
            System.out.println("时间错误");
        }
        return time;
    }
}
