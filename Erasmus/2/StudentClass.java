/* UTF-8 codepage: Příliš žluťoučký kůň úpěl ďábelské ódy. ÷ × ¤
 * «Stereotype», Section mark-§, Copyright-©, Alpha-α, Beta-β, Smile-☺
 */

import java.util.*; 


/*******************************************************************************
 * Instance třídy {@code StudentClass} představují ...
 * The {@code StudentClass} class instances represent ...
 *
 * @author  author name
 * @version 0.00.0000 — 20yy-mm-dd
 */
public class StudentClass
{
    private Map<String, Student> students = new HashMap<>();   
    private String UCCode;
    private String information;
    
    public StudentClass(Map<String, Student> students, String UCCode, String information) {
        this.students = students;
        this.UCCode = UCCode;
        this.information = information;
    }
    
    public StudentClass() {
        this.UCCode = "na";
        this.information = "na";
    }
    
    public StudentClass(StudentClass stClass) {
        this.students = stClass.getStudents();
        this.UCCode = stClass.getUCCode();
        this.information = stClass.getInformation();
    }
    
  public Map<String, Student> getStudents() {
        return students;
    }

    public void setStudents(Map<String, Student> students) {
        this.students = students;
    }

    public String getUCCode() {
        return UCCode;
    }

    public void setUCCode(String UCCode) {
        this.UCCode = UCCode;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
  
    
    public int compareTo(StudentClass o) {
        String thisUCCode = this.getUCCode();
        String otherUCCode = o.getUCCode();

        return thisUCCode.compareTo(otherUCCode);
    }    
    @Override
    public String toString(){
        StringBuilder str = new StringBuilder();

        str.append(this.students);
        str.append(this.UCCode);
        str.append(this.information);

        return str.toString();
    }
    
      public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof StudentClass)) return false;
        StudentClass that = (StudentClass) o;
        return getStudents().equals(that.getStudents()) &&
                getUCCode().equals(that.getUCCode()) &&
                getInformation().equals(that.getInformation());
    }
    
    public void insertStudent(Student a){
        students.put(a.getCode(), a);
    }
    
    public Student getStudent(String code){
        return students.get(code);
    }
    
    public void removeStudent(String code){
        students.remove(code);
    }
    
    public Set<String> allCodes(){
        Set<String> codes = new HashSet<String>();
        students.forEach((k,v) -> codes.add(k));
        return codes;
    }
    
    public int countStudents(){
        int count = 0;
        for (String name : students.keySet())  
            count++; 
        return count;
    }
    
    public Set<Student> orderStudents(){
        Set<Student> ordered = new TreeSet<Student>();
        students.forEach((k,v) -> ordered.add(v));
        
        return ordered;
    }
}
