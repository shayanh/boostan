package models;

public abstract class Entity {
    private int id;

    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && this.getClass() == object.getClass()) {
            Entity typedObject = (Entity) object;
            return this.getId() == typedObject.getId();
        }
        return false;
    }
}
