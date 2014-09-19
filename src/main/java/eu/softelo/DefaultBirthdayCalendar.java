package eu.softelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by dabl on 2014-09-18.
 */
public class DefaultBirthdayCalendar implements BirthdayCalendar {
    public static class DefaultBirthday implements Birthday {
        String name;
        int dayOfMonth;
        int month;

        public DefaultBirthday(String name, Date birthDay) {
            this.name = name;
            Calendar c = Calendar.getInstance();
            c.setTime(birthDay);
            dayOfMonth = c.get(Calendar.DAY_OF_MONTH);
            month = c.get(Calendar.MONTH) + 1;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public int getDayOfMonth() {
            return dayOfMonth;
        }

        public int getMonth() {
            return month;
        }
    }

    private List<DefaultBirthday> birthdays = new ArrayList<DefaultBirthday>();

    @Override
    public void addBirthday(String name, Date date) {
        birthdays.add(new DefaultBirthday(name, date));
    }

    @Override
    public Birthday[] getBirthdaysInMonth(int month) {
        List<Birthday> results = new ArrayList<Birthday>();
        for (DefaultBirthday b : birthdays) {
            if (b.getMonth() == month) {
                results.add(b);
            }
        }
        return results.toArray(new Birthday[results.size()]);
    }
}
