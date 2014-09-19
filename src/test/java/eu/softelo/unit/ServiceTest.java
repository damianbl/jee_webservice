package eu.softelo.unit;

/**
 * Created by dabl on 2014-09-18.
 */

import eu.softelo.BirthdayCalendar;
import eu.softelo.DefaultBirthdayCalendar;
import junit.framework.TestCase;

import org.apache.cxf.frontend.ClientProxyFactoryBean;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.aegis.databinding.AegisDatabinding;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;

public class ServiceTest {

    private static final String ADDRESS = "http://localhost:8081/Birthday";
    private static final String DD_MM_YYYY = "dd-MM-yyyy";
    private static final String DAMIAN = "Damian";
    private static final String BIRTDAY = "19-02-1984";

    static {
        ServerFactoryBean serverFactoryBean = new ServerFactoryBean();
        serverFactoryBean.getServiceFactory().setDataBinding(new AegisDatabinding());
        serverFactoryBean.setServiceClass(BirthdayCalendar.class);
        serverFactoryBean.setAddress(ADDRESS);
        serverFactoryBean.setServiceBean(new DefaultBirthdayCalendar());
        serverFactoryBean.create();

    }

    @Test
    public void shouldReturnProperBirthday() throws ParseException {
        BirthdayCalendar bc = newBirthdayClient();
        SimpleDateFormat sdf = new SimpleDateFormat(DD_MM_YYYY);

        bc.addBirthday(DAMIAN, sdf.parse(BIRTDAY));
        BirthdayCalendar.Birthday[] b = bc.getBirthdaysInMonth(2);

        assertEquals(1, b.length);
        assertEquals(DAMIAN, b[0].getName());
        assertEquals(19, b[0].getDayOfMonth());
    }

    protected BirthdayCalendar newBirthdayClient() {
        ClientProxyFactoryBean clientProxyFactoryBean = new ClientProxyFactoryBean();
        clientProxyFactoryBean.setServiceClass(BirthdayCalendar.class);
        clientProxyFactoryBean.getServiceFactory().setDataBinding(new AegisDatabinding());
        clientProxyFactoryBean.setAddress(ADDRESS);
        return (BirthdayCalendar) clientProxyFactoryBean.create();
    }
}