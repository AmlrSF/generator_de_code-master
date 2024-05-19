public class classes {

    private enseignants teacherId;
    private int classId;
    private students studentId;

    public classes(enseignants teacherId, int classId, students studentId) {
        this.teacherId = teacherId;
        this.classId = classId;
        this.studentId = studentId;
    }

    public enseignants getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(enseignants teacherId) {
        this.teacherId = teacherId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public students getStudentId() {
        return studentId;
    }

    public void setStudentId(students studentId) {
        this.studentId = studentId;
    }
}