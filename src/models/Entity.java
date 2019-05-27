package models;

public abstract class Entity {
    private Integer id;

    public Entity(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object object) {
        if (object != null && this.getClass() == object.getClass()) {
            Entity typedObject = (Entity) object;
            if (this.getId() == null || typedObject.getId() == null) {
                return false;
            }
            return this.getId().equals(typedObject.getId());
        }
        return false;
    }
}
