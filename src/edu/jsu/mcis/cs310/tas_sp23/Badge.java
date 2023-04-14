package edu.jsu.mcis.cs310.tas_sp23;

public class Badge {

    private String id;
    private String description;

    public Badge(String id, String description) {
        this.id = id;
        this.description = description;
    }

   

    
    /** 
     * @return String
     */
    public String getId() {
        return id;
    }

    
    /** 
     * @return String
     */
    public String getDescription() {
        return description;
    }

    
    /** 
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }
    
    
    /** 
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
    

    
    /** 
     * @return String
     */
    @Override
    public String toString() {

        StringBuilder s = new StringBuilder();

        s.append('#').append(id).append(' ');
        s.append('(').append(description).append(')');

        return s.toString();

    }


}
