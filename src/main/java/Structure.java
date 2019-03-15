import java.util.List;

//The structure class has the properties which are given
public class Structure {

    private Long id;
    private String title;
    private Long level;
    private List<Structure> children;
    private Long parent_id;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public List<Structure> getChildren() {
        return children;
    }

    public void setChildren(List<Structure> children) {
        this.children = children;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }
}
