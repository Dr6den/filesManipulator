package com.mycompany.filesmanipulator.dao.nosql;

import com.datastax.driver.core.Session;
import com.mycompany.filesmanipulator.dao.SessionManager;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author andrew
 */
public class CassandraConnectionTest {
    @Test
    public void cassandraConnection() {
        Session session = SessionManager.getCassandraSession();
        assertNotNull(session);
    }
}
