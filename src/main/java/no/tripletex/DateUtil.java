package no.tripletex;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by runar on 16.02.16.
 */
public class DateUtil {

    public static final String formatDate(LocalDate date , String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return  date.format(formatter);
    }

    public static final LocalDate parseToDate(String date, String pattern){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse( date , formatter);
    }

}
