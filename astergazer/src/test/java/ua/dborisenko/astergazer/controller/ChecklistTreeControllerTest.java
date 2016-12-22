package ua.dborisenko.astergazer.controller;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import ua.dborisenko.astergazer.domain.Checklist;
import ua.dborisenko.astergazer.domain.ChecklistEntry;
import ua.dborisenko.astergazer.dto.JsTreeNodeDynamicDto;
import ua.dborisenko.astergazer.service.IChecklistService;

@RunWith(MockitoJUnitRunner.class)
public class ChecklistTreeControllerTest {

    private static final String CONTROLLER_PATH = "/checklists/tree";

    @InjectMocks
    ChecklistTreeController controller;

    @Mock
    IChecklistService mockChecklistService;

    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

    }

    @Test
    public void testGetCheckLists() throws Exception {
        List<JsTreeNodeDynamicDto> dtoList = new ArrayList<>();
        int expectedId = 1;
        Checklist checklist = new Checklist();
        checklist.setId(expectedId);
        dtoList.add(new JsTreeNodeDynamicDto(checklist));

        when(mockChecklistService.getChecklistsTreeDto()).thenReturn(dtoList);

        mockMvc.perform(post(CONTROLLER_PATH + "/getchecklists")).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is("checklist" + expectedId)));
        verify(mockChecklistService).getChecklistsTreeDto();
    }

    @Test
    public void testGetEntries() throws Exception {
        List<JsTreeNodeDynamicDto> dtoList = new ArrayList<>();
        int expectedEntryId = 1;
        int checklistId = 2;
        ChecklistEntry entry = new ChecklistEntry();
        entry.setId(expectedEntryId);
        entry.setChecklist(new Checklist());
        dtoList.add(new JsTreeNodeDynamicDto(entry));

        when(mockChecklistService.getEntriesTreeDto(checklistId)).thenReturn(dtoList);

        mockMvc.perform(post(CONTROLLER_PATH + "/getentries/" + checklistId)).andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"))
                .andExpect(jsonPath("$[0].id", is("entry" + expectedEntryId)));
        verify(mockChecklistService).getEntriesTreeDto(checklistId);
    }

}