package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Product;
import ru.netology.repository.ShopRepository;

public class ShopRepositoryTest {
    Product item1 = new Product(11, "Кастрюля эмалированная", 690);
    Product item2 = new Product(12, "Тарелка", 230);
    Product item3 = new Product(13, "Кружка", 120);
    Product item4 = new Product(14, "Сковорода нержавеющая", 1300);

    @Test
    public void shouldDeleteExistElement() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        repo.remove(12);

        Product[] expected = {item1, item3, item4};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldGenerateExceptionWhenNotExistElement() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.remove(15);
        });
    }

    @Test
    public void shouldAddProduct() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);

        Product[] expected = {item1, item2, item3};
        Product[] actual = repo.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldNotAddProductWithExistId() {
        ShopRepository repo = new ShopRepository();
        repo.add(item1);
        repo.add(item2);
        repo.add(item3);
        repo.add(item4);

        Product item5 = new Product(14, "Миска суповая", 310);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.add(item5);
        });
    }
}
