package respository;

import models.Entity;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public abstract class Repository<T extends Entity> {
    protected ArrayList<T> records;
    private int currentID;

    public Repository() {
        records = new ArrayList<>();
    }

    public T find(int id) {
        for (T record: records) {
            if (record.getId() == id) {
                return record;
            }
        }
        throw new NoSuchElementException("no such element with specified ID");
    }

    public boolean exists(int id) {
        for (T record: records) {
            if (record.getId() == id) {
                return true;
            }
        }
        return false;
    }

    private int nextID() {
        return currentID++;
    }

    public void insert(T newRecord) throws InvalidObjectException {
        for (T record: records) {
            if (record.equals(newRecord)) {
                throw new InvalidObjectException("constraint failed");
            }
        }
        newRecord.setId(nextID());
        records.add(newRecord);
    }
}
