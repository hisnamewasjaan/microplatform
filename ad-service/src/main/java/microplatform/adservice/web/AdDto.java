package microplatform.adservice.web;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class AdDto {
    private Long id;
    private String name;


    public AdDto() {
        super();
    }

    public AdDto(final long id, final String name) {
        super();

        this.id = id;
        this.name = name;
    }

    //

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("name", name)
                .toString();
    }

}
