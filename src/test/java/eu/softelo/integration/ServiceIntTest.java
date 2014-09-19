package eu.softelo.integration;

import eu.softelo.BirthdayCalendar;
import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.apache.cxf.frontend.ClientProxyFactoryBean;
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
    private static final String DAMIAN = "Damian";
    private static final String BIRTDAY = "19-02-1984";

    protected BirthdayCalendar newBirthdayClient() {
        ClientProxyFactoryBean factory = new ClientProxyFactoryBean();
        factory.setServiceClass(BirthdayCalendar.class);
        factory.getServiceFactory().setDataBinding(new AegisDatabinding());
        factory.setAddress(ADDRESS);
        return (BirthdayCalendar) factory.create();
    }

    @Test
    public void shouldReturnProperBirthday() throws ParseException {
        BirthdayCalendar bc = newBirthdayClient();
        SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);
        if (bc.getBirthdaysInMonth(2).length == 0) {
            bc.addBirthday(DAMIAN, sdf.parse(BIRTDAY));
        }
        BirthdayCalendar.Birthday[] b = bc.getBirthdaysInMonth(2);
        assertEquals(1, b.length);
        assertEquals(DAMIAN, b[0].getName());
        assertEquals(19, b[0].getDayOfMonth());
    }

}
