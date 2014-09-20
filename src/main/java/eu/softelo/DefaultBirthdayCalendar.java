package eu.softelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dabl on 2014-09-18.
 */
public class DefaultBirthdayCalendar implements BirthdayCalendar {
    private List<Birthday> birthdays = new ArrayList<>();

    @Override
    public void addBirthday(String name, Date date) {
        birthdays.add(new Birthday(name, date));
    }

    @Override
    public Birthday[] getBirthdaysInMonth(int month) {
        List<Birthday> results = new ArrayList<>();
        for (Birthday b : birthdays) {
            if (b.getMonth() == month) {
                results.add(b);
            }
        }
        return results.toArray(new Birthday[results.size()]);
    }
}
