package com.evan.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PubIdGenerator {
    public static int generatePubId() {

        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyMMddS");
        String pubIdString = simpleDateFormat.format(currentTime);
        return Integer.parseInt(pubIdString);
    }
}
