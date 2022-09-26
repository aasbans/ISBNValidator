package com.virtualpairprogrammers.isbntools;

public class StockManager {

    private ExternalIsbnDataService dataService;
    private ExternalIsbnWebService webService;

    public void setDataService(ExternalIsbnDataService dataService) {
        this.dataService = dataService;
    }

    public void setWebService(ExternalIsbnWebService webService) {
        this.webService = webService;
    }

    public String getLocatorCode(String isbn) {
        Book book = dataService.lookUp(isbn);
        if (book == null)
            book = webService.lookUp(isbn);
        StringBuilder locatorCode = new StringBuilder();
        locatorCode.append(isbn.substring(isbn.length() - 4));
        locatorCode.append(book.getAuthor().charAt(0));
        locatorCode.append(book.getTitle().split(" ").length);
        return locatorCode.toString();
    }
}
