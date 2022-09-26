package com.virtualpairprogrammers.isbntools;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class StockManagementTest {

    ExternalIsbnDataService dataService;
    ExternalIsbnWebService webService;
    StockManager stockManager;

    @BeforeEach
    public void setup() {
        dataService = mock(ExternalIsbnDataService.class);
        webService = mock(ExternalIsbnWebService.class);
        stockManager = new StockManager();
        stockManager.setDataService(dataService);
        stockManager.setWebService(webService);
    }

    @Test
    void testCanGetACorrectLocatorCode() {
        String isbn = "0198526636";
        when(dataService.lookUp(anyString())).thenReturn(null);
        when(webService.lookUp(anyString())).thenReturn(new Book(isbn, "Of Mice And Men", "J. Steinbeck"));

        String locatorCode = stockManager.getLocatorCode(isbn);

        assertEquals(locatorCode, "6636J4");
    }

    @Test
    void checkDatabaseIsUsedIfDataIsPresent() {
        when(dataService.lookUp("0198526636")).thenReturn(new Book("0198526636", "abc", "abc"));

        String isbn = "0198526636";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(dataService).lookUp("0198526636");
        verify(webService, never()).lookUp(anyString());
    }

    @Test
    void checkWebServiceIsUsedIfDataNotPresent() {
        when(dataService.lookUp("0198526636")).thenReturn(null);
        when(webService.lookUp("0198526636")).thenReturn(new Book("0198526636", "abc", "abc"));

        String isbn = "0198526636";
        String locatorCode = stockManager.getLocatorCode(isbn);

        verify(dataService).lookUp("0198526636");
        verify(webService).lookUp(anyString());
    }
}
