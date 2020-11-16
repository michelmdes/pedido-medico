package com.dasa.pedidomedico.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {

    public static Date addDias(Date data, int qtdDias) {
        long dateTime = data.getTime() + (1000 * 60 * 60 * 24 * qtdDias);
        return new Date(dateTime);
    }

    public static Date parseDate(String data, String pattern) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.parse(data);
    }

}
