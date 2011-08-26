package com.travel.mentor.dao.general;

import com.travel.mentor.dao.dto.general.IslandDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class IslandDAOUnitTest {

    @Mock
    private IslandDAO mockedIslandDAO;

    @Before
    public void setup() {
        mockedIslandDAO = mock(IslandDAO.class);
    }

    @Test
    public void testBasicListManipulation() {
        List mockedList = mock(List.class);

        //using mock object
        mockedList.add("one");
        mockedList.clear();

        //verify the behaviour
        verify(mockedList).add("one");
        verify(mockedList).clear();
    }


    @Test
    public void testFindAll_NoWhens() {
        List<IslandDTO> mockedIslandDTOList = mock(List.class);
        mockedIslandDTOList = mockedIslandDAO.findAll();

        // verify the behaviour
        verify(mockedIslandDAO).findAll();

        System.out.println("here");
    }

    @Test
    public void testFindAll_HasWhens() {
        List<IslandDTO> mockedIslandDTOList = mock(List.class);

        Mockito.when(mockedIslandDAO.findAll()).thenReturn(mockedIslandDTOList);


        mockedIslandDTOList = mockedIslandDAO.findAll();

        Assert.assertTrue(mockedIslandDTOList.size() == 0);

        // verify the behaviour
        verify(mockedIslandDAO).findAll();

        System.out.println("here");
    }

    @Test
    public void testFind_Whens() {
        IslandDTO mockedIslandDTO = mock(IslandDTO.class);

        Mockito.when(mockedIslandDAO.find(1L)).thenReturn(mockedIslandDTO);


        IslandDTO islandDTO = mockedIslandDAO.find(1L);

        // verify the behaviour
        verify(mockedIslandDAO).find(1L);

        System.out.println("here");

        Assert.assertNotNull(islandDTO);
    }


}
