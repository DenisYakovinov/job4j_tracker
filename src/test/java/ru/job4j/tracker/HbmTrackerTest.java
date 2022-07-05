package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HbmTrackerTest {

    @Test
    void whenReplaceItem() {
        HbmTracker tracker = new HbmTracker();
        Item item = tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        Item item1 = tracker.add(new Item("item1", "description1", Timestamp.from(Instant.now())));
        assertTrue(tracker.replace(item.getId(), item1));
    }

    @Test
    void whenAddItem() {
        HbmTracker tracker = new HbmTracker();
        tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        assertThat(tracker.findAll().size(), is(1));
    }

    @Test
    public void whenFindById() {
        HbmTracker tracker = new HbmTracker();
        Item item = tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        assertEquals(item, tracker.findById(item.getId()));
    }

    @Test
    void whenFindByName() {
        HbmTracker tracker = new HbmTracker();
        tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        tracker.add(new Item("item1", "description1", Timestamp.from(Instant.now())));
        List<Item> list = tracker.findByName("item");
        assertEquals("item", list.get(0).getName());
    }

    @Test
    void whenFindAllItems() {
        HbmTracker tracker = new HbmTracker();
        tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        tracker.add(new Item("item1", "description1", Timestamp.from(Instant.now())));
        assertThat(tracker.findAll().size(), is(2));
    }

    @Test
    void whenDeleteItem() {
        HbmTracker tracker = new HbmTracker();
        Item item = tracker.add(new Item("item", "description", Timestamp.from(Instant.now())));
        assertTrue(tracker.delete(item.getId()));
        assertTrue(tracker.findAll().isEmpty());
    }
}
