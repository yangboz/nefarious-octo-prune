package info.smartkit.eip.cassandra.dao;

import info.smartkit.eip.cassandra.domain.Event;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by yangboz on 10/30/15.
 */
//@Repository
public interface EventRepository extends CassandraRepository<Event> {
    @Query("select * from event where type = ?0 and bucket=?1")
    Iterable findByTypeAndBucket(String type, String bucket);
}
