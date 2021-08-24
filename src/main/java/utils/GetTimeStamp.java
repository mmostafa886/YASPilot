package utils;

import java.text.SimpleDateFormat;

public class GetTimeStamp {

    public SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    public String getCurrentTimeStamp(){
        return sDateFormat.format(System.currentTimeMillis());
    }

}
