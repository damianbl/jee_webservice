package eu.softelo;

import java.util.Date;

/**
 * Created by dabl on 2014-09-18.
 */

public interface BirthdayCalendar {

    /**
     * Holds a single birthday.
     */
    public interface Birthday {
        String getName();

        int getDayOfMonth();
    }

    /**
     * Adds a birthday.
     *
     * @param name the name of the person.
     * @param date the birthday.
     */
    void addBirthday(String name, Date date);

    /**
     * returns all the birthdays in one month.
     *
     * @param month the month (1..12).
     * @return all the birthdays in that month.
     */
    Birthday[] getBirthdaysInMonth(int month);

}
