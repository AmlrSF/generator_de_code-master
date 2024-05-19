public class enseignants {

    private int teacherId;
    private String subject;
    private String lastName;
    private String firstName;

    public enseignants(int teacherId, String subject, String lastName, String firstName) {
        this.teacherId = teacherId;
        this.subject = subject;
        this.lastName = lastName;
        this.firstName = firstName;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}