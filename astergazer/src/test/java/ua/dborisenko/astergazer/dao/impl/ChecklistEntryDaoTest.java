package ua.dborisenko.astergazer.dao.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import ua.dborisenko.astergazer.domain.Checklist;
import ua.dborisenko.astergazer.domain.ChecklistEntry;
import ua.dborisenko.astergazer.exception.DaoException;
import ua.dborisenko.astergazer.exception.RecordNotFoundException;

@RunWith(MockitoJUnitRunner.class)
public class ChecklistEntryDaoTest {

    @Mock
    private EntityManager mockEm;
    @InjectMocks
    private ChecklistEntryDao entryDao;
    @InjectMocks
    @Spy
    private ChecklistEntryDao spyEntryDao;

    @Test
    public void getTest() throws DaoException {
        ChecklistEntry expectedEntry = new ChecklistEntry();
        long id = 1;
        expectedEntry.setId(id);

        when(mockEm.find(ChecklistEntry.class, id)).thenReturn(expectedEntry);

        assertThat(entryDao.get(id), is(expectedEntry));
    }

    @SuppressWarnings("unchecked")
    @Test(expected = DaoException.class)
    public void getExceptionTest() throws DaoException {
        long id = 1;
        
        when(mockEm.find(ChecklistEntry.class, id)).thenThrow(Exception.class);
        entryDao.get(id);
    }

    @Test(expected = RecordNotFoundException.class)
    public void getNotFoundTest() throws DaoException {
        long id = 1;
        
        when(mockEm.find(Checklist.class, id)).thenReturn(null);
        entryDao.get(id);
    }

    @Test
    public void addTest() throws DaoException {
        ChecklistEntry entry = new ChecklistEntry();
        entryDao.add(entry);

        verify(mockEm).persist(entry);
    }

    @Test(expected = DaoException.class)
    public void addExceptionTest() throws DaoException {
        doThrow(Exception.class).when(mockEm).persist(any());
        entryDao.add(new ChecklistEntry());
    }

    @Test
    public void getCountTest() throws DaoException {
        long expectedResult = 2;
        long id = 1;
        int checkListId = 1;
        String controlValue = "";
        Query mockQuery = mock(Query.class);

        when(mockEm.createNamedQuery("ChecklistEntry.getCount")).thenReturn(mockQuery);
        when(mockQuery.getSingleResult()).thenReturn(expectedResult);

        assertThat(entryDao.getCount(id, checkListId, controlValue), is(expectedResult));
    }

    @Test(expected = DaoException.class)
    public void getCountExceptionTest() throws DaoException {
        long id = 1;
        int checkListId = 2;
        String controlValue = "";
                
        doThrow(Exception.class).when(mockEm).createNamedQuery("ChecklistEntry.getCount");
        entryDao.getCount(id, checkListId, controlValue);
    }

    @Test
    public void updateTest() throws DaoException {
        ChecklistEntry entry = new ChecklistEntry();
        entryDao.update(entry);

        verify(mockEm).merge(entry);
    }

    @Test(expected = DaoException.class)
    public void updateExceptionTest() throws DaoException {
        doThrow(Exception.class).when(mockEm).merge(any());
        entryDao.update(new ChecklistEntry());
    }

    @Test
    public void deleteTest() throws DaoException {
        long id = 1;
        ChecklistEntry entry = mock(ChecklistEntry.class);

        doReturn(entry).when(spyEntryDao).get(id);
        spyEntryDao.delete(id);

        verify(mockEm).remove(entry);
    }

    @Test(expected = DaoException.class)
    public void deleteExceptionTest() throws DaoException {
        int id = 1;
        ChecklistEntry entry = mock(ChecklistEntry.class);

        doReturn(entry).when(spyEntryDao).get(id);
        doThrow(Exception.class).when(mockEm).remove(any());
        spyEntryDao.delete(id);
    }

    
    @Test
    public void getReturnValueTest() throws DaoException {
        String controlValue = "";
        String expectedValue = "test";
        int checklistId = 1;
        List<String> resultList = new ArrayList<>();
        resultList.add(expectedValue);
        Query mockQuery = mock(Query.class);

        when(mockEm.createNamedQuery("ChecklistEntry.getReturnValue")).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(resultList);

        assertThat(entryDao.getReturnValue(checklistId, controlValue), is(expectedValue));
    }
    
    @Test
    public void getReturnValueEmptyTest() throws DaoException {
        List<String> resultList = new ArrayList<>();
        int checklistId = 1;
        String controlValue = "";
        Query mockQuery = mock(Query.class);

        when(mockEm.createNamedQuery("ChecklistEntry.getReturnValue")).thenReturn(mockQuery);
        when(mockQuery.getResultList()).thenReturn(resultList);

        assertThat(entryDao.getReturnValue(checklistId, controlValue), nullValue());
    }
    
    @Test(expected = DaoException.class)
    public void getReturnValueExceptionTest() throws DaoException {
        int checklistId = 1;
        String controlValue = "test";

        doThrow(Exception.class).when(mockEm).createNamedQuery("ChecklistEntry.getReturnValue");
        entryDao.getReturnValue(checklistId, controlValue);
    }
    
}
