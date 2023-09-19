package aop;

import org.springframework.stereotype.Component;

@Component
public class UniLibrary extends AbstractLibrary{

    @Override
    public void getBook() {
        System.out.println("UniLibrary: We getting book");
        System.out.println("--------------------------------");
    }

    public void getBook(Book book) {
        System.out.println("UniLibrary: We getting book " + book.getName());
        System.out.println("--------------------------------");
    }

    public void returnBook() {
        System.out.println("UniLibrary: We returning book");
        System.out.println("--------------------------------");
    }

    public void getMagazine(int a) {
        System.out.println("UniLibrary: We getting magazine");
        System.out.println("--------------------------------");
    }

    public void returnMagazine(int a) {
        System.out.println("UniLibrary: We returning magazine");
        System.out.println("--------------------------------");
    }

    public void addBook(String personName, Book book) {
        System.out.println("UniLibrary: We adding book");
        System.out.println("--------------------------------");
    }

    public void addMagazine() {
        System.out.println("UniLibrary: We adding magazine");
        System.out.println("--------------------------------");
    }
}
