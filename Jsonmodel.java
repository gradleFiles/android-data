package proveb.gk.com.foresightsqlite.model;

/**
 * Created by Nehru on 25-07-2016.
 */
public class Jsonmodel {
    String degree="",specialty="";

    public Jsonmodel(String degree, String specialty) {
        this.degree = degree;
        this.specialty = specialty;
    }
    public Jsonmodel() {
        this.degree = new String();
        this.specialty = new String();
    }

    public String getDegree() {
        return degree;
    }

    public Jsonmodel setDegree(String degree) {
        this.degree = degree;
        return this;
    }

    public String getSpecialty() {
        return specialty;
    }

    public Jsonmodel setSpecialty(String specialty) {
        this.specialty = specialty;
        return this;
    }
}
