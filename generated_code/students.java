public class students {

    private groupes groupId;
    private int studentId;
    private String lastName;
    private String firstName;
    private int age;
    private String email;

    public students(groupes groupId, int studentId, String lastName, String firstName, int age, String email) {
        this.groupId = groupId;
        this.studentId = studentId;
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.email = email;
    }

    public groupes getGroupId() {
        return groupId;
    }

    public void setGroupId(groupes groupId) {
        this.groupId = groupId;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}