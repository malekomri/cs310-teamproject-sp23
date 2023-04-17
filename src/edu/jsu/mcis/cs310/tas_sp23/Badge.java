package edu.jsu.mcis.cs310.tas_sp23;

/**
* <p>Model class for creating and handling Badge objects.</p>
*/
public class Badge {

    private String id;
    private String description;
    
    /**
    * <p>Constructor for creating a Badge object.</p>
    * @param id
    * @param description
    */
    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

   
    /**
    * <p>This method is used to get the id variable.</p>
    * @return id
    */
    public String getId() {
        return id;
    }
    
    /**
    * <p>This method is used to get the description variable.</p>
    * @return description
    */
    public String getDescription() {
        return description;
    }
    
    /**
    * <p>This method is used to set the id variable.</p>
    * @param id
    */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
    * <p>This method is used to set the description variable.</p>
    * @param description
    */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
    * <p>This method is used to format the information contained in a Badge object as a string.</p>
    * @return constructed string
    */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();

    }

}
