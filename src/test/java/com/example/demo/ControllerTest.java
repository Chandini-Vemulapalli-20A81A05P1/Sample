package com.example.demo;

import com.example.demo.Test.Controller;
import com.example.demo.Test.Service;
import com.example.demo.Test.Student;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
public class ControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @MockBean
    private Service service;

    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testSelect() throws Exception {
        // Mock data returned by the service
        List<Map<String, Object>> mockData = Arrays.asList(
                createStudentMap(1, "Alice", "Math"),
                createStudentMap(2, "Bob", "Physics")
        );
        when(service.SelAll()).thenReturn(mockData);

        // Perform GET request and verify
        mockMvc.perform(get("/path/s"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Alice"))
                .andExpect(jsonPath("$[0].dep").value("Math"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].name").value("Bob"))
                .andExpect(jsonPath("$[1].dep").value("Physics"));
    }

    @Test
    public void testInsert() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setName("Alice");
        student.setDep("Math");

        when(service.insertStudent(any(Student.class))).thenReturn(true);

        mockMvc.perform(post("/path/i")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    public void testInsertFail() throws Exception {
        Student student = new Student();
        student.setName("Alice");
        student.setDep("Math");

        when(service.insertStudent(any(Student.class))).thenReturn(true);

        mockMvc.perform(post("/path/i")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                 .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("Data not inserted"));
    }

    @Test
    public void testUpdate() throws Exception {
        Student student = new Student();
        student.setId(1);
        student.setName("Alice");
        student.setDep("Math");

        when(service.updateStudent(any(Student.class))).thenReturn(true);

        mockMvc.perform(put("/path/P")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }
    @Test
    public void testUpdateFail() throws Exception {
        Student student = new Student();
        student.setName("Alice");
        student.setDep("Math");

        when(service.updateStudent(any(Student.class))).thenReturn(true);

        mockMvc.perform(put("/path/P")
                        .contentType("application/json")
                        .content(new ObjectMapper().writeValueAsString(student)))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(content().string("Data not updated"));
    }
    @Test
public void testUpdateFail_IdNotFound() throws Exception {
    Student student = new Student();
    student.setId(1); // Set an ID not present in the table (assumption)
    student.setName("Alice");
    student.setDep("Math");

    // Mock updateStudent to return false for specific ID
    when(service.updateStudent(student)).thenReturn(false); // Update fails

    mockMvc.perform(put("/path/P")
            .contentType("application/json")
            .content(new ObjectMapper().writeValueAsString(student)))
            .andExpect(status().isNotFound()) // Expect 404 Not Found
            .andDo(MockMvcResultHandlers.print())
            .andExpect(content().string("Student not found")); // Optional: Expected message
}


    @Test
public void testDelete() throws Exception {
    int studentId = 1;

    when(service.deleteStudent(studentId)).thenReturn(true);

    String studentJson = "{ \"id\": " + studentId + " }";

    mockMvc.perform(delete("/path/d")
                        .contentType("application/json")
                        .content(studentJson))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
}
@Test
    public void testNoDelete() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.delete("/d")
                .param("id", "10")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("Value not deleted"));
    }


    private Map<String, Object> createStudentMap(int id, String name, String dep) {
        Map<String, Object> studentMap = new HashMap<>();
        studentMap.put("id", id);
        studentMap.put("name", name);
        studentMap.put("dep", dep);
        return studentMap;
    }
}
