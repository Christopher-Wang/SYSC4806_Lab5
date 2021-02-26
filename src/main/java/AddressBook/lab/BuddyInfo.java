package AddressBook.lab;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/** BuddyInfo template for storing buddies in an address
 * @author Christopher Wang 100951354
 */
@Entity(name="BuddyInfo")
public class BuddyInfo  {

    @Id
    @GeneratedValue
    private Long Id;
    private String name, number, address;


    /**
     * Constructor for Buddy info
     * @param name name of the BuddyInfo
     * @param number humber of the BuddyInfo
     * @param address address of the BuddyInfo
     */
    public BuddyInfo(String name, String number, String address){
        this.name = name;
        this.number = number;
        this.address = address;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BuddyInfo otherBuddy = (BuddyInfo) obj;
        return (otherBuddy.address == this.address)
                && (otherBuddy.name == this.name)
                && (otherBuddy.number == this.number);
    }

    @Override
    public String toString() {
        return "BuddyInfo{"+ "name=" + this.name  + ", number=" + this.number + ", address=" + this.address + "}";
    }

    public BuddyInfo(){}

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
