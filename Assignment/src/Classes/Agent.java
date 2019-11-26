
package Classes;

/**
 *
 * @author Poonnamee
 * Date: 27/11/19
 * Agent class
 */
public class Agent {
    public int id;
    public String first;
    public String last;
    public String phone;

    public Agent(int id, String first, String last, String phone) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Agent id=" + id + ", first=" + first + ", last=" + last + ", phone=" + phone;
    }
    
    
}
