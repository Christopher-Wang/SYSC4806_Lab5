package AddressBook.lab;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class AddressController {
    @Autowired
    private AddressBookRepository repository;

    public AddressController(AddressBookRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/createaddressbook")
    public String createAddressBookForm() {
        return "createaddressbook";
    }

    @PostMapping("/createaddressbook")
    public String createAddressBookSuccess(Model model) {
        AddressBook a = new AddressBook();
        model.addAttribute(a);
        this.repository.save(a);
        model.addAttribute("addressbook", a);
        return "addressbooksuccess";
    }

    @GetMapping("/createbuddyinfo")
    public String createBuddyInfoForm(@RequestParam long id, Model model) {
        model.addAttribute("id", id);
        model.addAttribute("buddy", new BuddyInfo());
        return "createbuddyinfo";
    }

    @PostMapping("/createbuddyinfo/{id}")
    public String createAddressBookSuccess(@PathVariable long id,
                                           @ModelAttribute BuddyInfo buddy,
                                           Model model) {
        model.addAttribute("id", id);
        AddressBook a = this.repository.findById(id);
        if (a == null){
            return "buddyinfofailure";
        }
        a.addBuddy(buddy);
        this.repository.save(a);
        return "buddyinfosuccess";
    }

    @GetMapping("/viewaddressbook")
    public String viewaddressbook(@RequestParam long id, Model model) {
        AddressBook a = this.repository.findById(id);
        model.addAttribute("validId", !(a == null));
        model.addAttribute("addressbook", a);
        return "viewaddressbook";
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