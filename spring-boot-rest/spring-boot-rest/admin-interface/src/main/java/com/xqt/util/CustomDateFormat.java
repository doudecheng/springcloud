package com.xqt.util;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Author: Wangwei
 * Date: 2016/7/16
 * Desc:
 */
public class CustomDateFormat extends DateFormat {

    private static final long serialVersionUID = -7895434796585382023L;

    private String pattern ;

    /**
     * Cache NumberFormat instances with Locale key.
     */
    private static final ConcurrentMap<Locale, NumberFormat> cachedNumberFormatData
            = new ConcurrentHashMap<>(3);

   /* public CustomDateFormat() {
    }*/

    public CustomDateFormat(String pattern) {
        this(pattern, Locale.getDefault(Locale.Category.FORMAT));
    }

    public CustomDateFormat(String pattern, Locale locale) {
        if (pattern == null || locale == null) {
            throw new NullPointerException();
        }

        initializeCalendar(locale);
        this.pattern = pattern;
        initialize(locale);
    }

    private void initializeCalendar(Locale loc) {
        if (calendar == null) {
            assert loc != null;
            calendar = Calendar.getInstance(TimeZone.getDefault(), loc);
        }
    }

    /* Initialize compiledPattern and numberFormat fields */
    private void initialize(Locale loc) {
        /* try the cache first */
        numberFormat = cachedNumberFormatData.get(loc);
        if (numberFormat == null) { /* cache miss */
            numberFormat = NumberFormat.getIntegerInstance(loc);
            numberFormat.setGroupingUsed(false);

            /* update cache */
            cachedNumberFormatData.putIfAbsent(loc, numberFormat);
        }
        numberFormat = (NumberFormat) numberFormat.clone();
    }

    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        DateTime dateTime = new DateTime(date);
        String str = dateTime.toString(pattern);
        return toAppendTo.append(str);
    }

    @Override
    public Date parse(String source, ParsePosition pos) {
        DateTimeFormatter format = DateTimeFormat.forPattern(pattern);
        DateTime dateTime = DateTime.parse(source, format);
        return dateTime.toDate();
    }
}
