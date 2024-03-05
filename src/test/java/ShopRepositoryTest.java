import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    ShopRepository repo = new ShopRepository();

    Product product1 = new Product(1, "Кепка", 1500);
    Product product2 = new Product(2, "Юбка", 3000);
    Product product3 = new Product(3, "Пуховик", 4000);

    @Test
    public void successDeleting() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        repo.removeById(3);

        Product[] expected = {product1, product2};
        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void NotFoundExceptionTest() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repo.removeById(-100);
        });
    }

    @Test
    public void successExists() {
        Product product4 = new Product(4, "Носки", 500);
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);
        repo.add(product4);

        Product[] expected = {product1, product2, product3, product4};
        Assertions.assertArrayEquals(expected, repo.findAll());
    }

    @Test
    public void alreadyExistsExceptionTest() {
        repo.add(product1);
        repo.add(product2);
        repo.add(product3);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repo.alreadyNoProduct(product1);
        });
    }
}