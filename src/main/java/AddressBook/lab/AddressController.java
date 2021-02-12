package AddressBook.lab;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AddressController {
    private AddressBookRepository repository;

    public AddressController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/addressbook/{id}")
    public String addressBookForm(@PathVariable long id, Model model) {
        AddressBook a = this.repository.findById(id);
        model.addAttribute("validId", !(a == null));
        model.addAttribute("addressbook", a);
        return "addressbook";
    }

    @PostMapping("/addressbook/{id}")
    @ResponseBody
    public AddressBook addBuddyInfo(@PathVariable long id,
                         @RequestBody BuddyInfo buddy) {
        AddressBook a = this.repository.findById(id);
        if (a == null){
            return null;
        }
        a.addBuddy(buddy);
        this.repository.save(a);
        return a;
    }

    @DeleteMapping("/addressbook/{id}")
    @ResponseBody
    public AddressBook removeBuddyInfo(@PathVariable long id,
                               @RequestBody BuddyInfo buddy) {
        AddressBook a = this.repository.findById(id);
        if (a == null){
            return null;
        }
        int index = 0, i = 0;
        for(BuddyInfo b : a.getBuddyList()){
            if (b.equals(buddy)) index = i;
            i++;
        }
        a.getBuddyList().remove(index);
        this.repository.save(a);
        return a;
    }
}