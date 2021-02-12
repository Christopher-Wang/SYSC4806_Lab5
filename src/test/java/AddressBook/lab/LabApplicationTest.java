package AddressBook.lab;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class LabApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private BuddyInfo buddy  = new BuddyInfo("Pizza Pizza", "613-737-1111", "2301 Navaho Dr");

    @Test
    public void testAddBuddyInfo() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/addressbook/0")
                .content(mapper.writeValueAsString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddressBookForm() throws Exception {
        this.mockMvc.perform(get("/addressbook/0")).andExpect(status().isOk());
    }

    @Test
    public void testRemoveBuddyInfo() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .post("/addressbook/0")
                .content(mapper.writeValueAsString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}