package AddressBook.lab;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LabApplicationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper mapper;
    private BuddyInfo buddy  = new BuddyInfo("Pizza Pizza", "613-737-1111", "2301 Navaho Dr");


    @BeforeAll
    public void testAddBuddyInfo() throws Exception {
        //Test Create AddressBook
        mockMvc.perform( MockMvcRequestBuilders
                .post("/addressbook/")
                .content("{}")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        //Test Adding BuddyInfo
        mockMvc.perform( MockMvcRequestBuilders
                .post("/addressbook/1")
                .content(mapper.writeValueAsString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Pizza Pizza")));
    }

    @Test
    public void testGetBuddyInfo() throws Exception {
        this.mockMvc.perform(get("/addressbook/1")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Pizza Pizza")));
    }

    @AfterAll
    public void testRemoveBuddyInfo() throws Exception {
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/addressbook/1")
                .content(mapper.writeValueAsString(buddy))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Pizza Pizza"))));
    }
}