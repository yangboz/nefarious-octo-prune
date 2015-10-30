package info.smartkit.eip.cassandra.controllers;

import com.wordnik.swagger.annotations.ApiOperation;
import info.smartkit.eip.cassandra.dao.EventRepository;
import info.smartkit.eip.cassandra.domain.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.cassandra.repository.MapId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.MediaType;
import java.util.Map;

/**
 * Created by yangboz on 9/30/15.
 */
@RestController
@RequestMapping("/v1/events")
public class EventsController {

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON)
    @ApiOperation(httpMethod = "POST", value = "Response a string describing if the Event ino is successfully created or not.")
    public Event create(@RequestBody @Valid Event value) {
        return (Event) eventRepository.save(value);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a list describing all of Events that is successfully get or not.")
    public Iterable<Event> list() {
        return eventRepository.findAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the Event key related value is successfully get or not.")
    public Event get(@PathVariable("id") MapId id) {
        return (Event) eventRepository.findOne(id);
    }

    //
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ApiOperation(httpMethod = "PUT", value = "Response a string describing if the  Event item  is successfully updated or not.")
//    public ResponseEntity<Boolean> update(@PathVariable("id") int id, @RequestBody @Valid Event value) {
//        Event find = (Event) this.eventRepository.findOne(id);
//        return eventRepository.save(find);
//    }

    //
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ApiOperation(httpMethod = "DELETE", value = "Response a string describing if the Event item is successfully delete or not.")
    public ResponseEntity<Boolean> delete(@PathVariable("id") MapId id) {
        this.eventRepository.delete(id);
        return new ResponseEntity<Boolean>(Boolean.TRUE, HttpStatus.OK);
    }

    @RequestMapping(value = "/{type}/{bucket}", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "Response a string describing if the Event by type and bucket is successfully get or not.")
    public Iterable<Event> findByTypeAndBucket(@PathVariable("type") String type, @PathVariable("bucket") String bucket) {
        return eventRepository.findByTypeAndBucket(type, bucket);
    }
}
