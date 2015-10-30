package info.smartkit.eip.cassandra;

import info.smartkit.eip.cassandra.configs.CassandraConfiguration;
import info.smartkit.eip.cassandra.domain.Event;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cassandra.core.cql.CqlIdentifier;
import org.springframework.data.cassandra.core.CassandraAdminOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = CassandraConfiguration.class)
public abstract class BaseIntegrationTest {

    @Autowired
    private CassandraAdminOperations adminTemplate;

    @Before
    public void resetKeySpace() {
        adminTemplate.dropTable(CqlIdentifier.cqlId("event"));
        adminTemplate.createTable(true, CqlIdentifier.cqlId("event"), Event.class, new HashMap<String, Object>());
    }
}
