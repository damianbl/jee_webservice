package eu.softelo.integration;

import eu.softelo.Birthday;
import eu.softelo.BirthdayCalendar;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

/**
 * Created by dabl on 2014-09-18.
 */
public class ServiceIntTest {

    private static final String ADDRESS = "http://localhost:9090/birthday";
    private static final String DD_MM_YYYY = "dd-MM-yyyy";
    private static final String NAME = "Damian";
    private static final String DATE = "19-02-1984";

    protected BirthdayCalendar newBirthdayClient() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(BirthdayCalendar.class);
        factory.setAddress(ADDRESS);
        return (BirthdayCalendar) factory.create();
    }

    @Test
    public void shouldReturnProperBirtday() throws ParseException {
        BirthdayCalendar bc = newBirthdayClient();
        SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
        Birthday[] br = bc.getBirthdaysInMonth(2);
        if (br == null || br.length == 0) {
            bc.addBirthday(NAME, sdf.parse(DATE));
        }
        Birthday[] b = bc.getBirthdaysInMonth(2);
        assertEquals(1, b.length);
        assertEquals(NAME, b[0].getName());
        assertEquals(19, b[0].getDayOfMonth());
    }

}