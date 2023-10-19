import java.util.*;
import java.io.*;

class Toy {
    private int id;
    private String name;
    private int quantity;
    private double weight;

    public Toy(int id, String name, int quantity, double weight) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.weight = weight;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}

class ToyStore {
    private List<Toy> toys;

    public ToyStore() {
        toys = new ArrayList<>();
    }

    public void addToy(Toy toy) {
        toys.add(toy);
    }

    public void adjustWeight(int toyId, double newWeight) {
        for (Toy toy : toys) {
            if (toy.getId() == toyId) {
                toy.setWeight(newWeight);
                return;
            }
        }
    }

    public void performGiveaway() {
        double totalWeight = toys.stream().mapToDouble(Toy::getWeight).sum();
        double randomValue = Math.random() * totalWeight;
        double currentWeight = 0.0;

        for (Toy toy : toys) {
            currentWeight += toy.getWeight();
            if (randomValue <= currentWeight) {
                System.out.println("Выиграла игрушка: " + toy.getName());
                return;
            }
        }
    }

    public void listToys() {
        for (Toy toy : toys) {
            System.out.println("ID: " + toy.getId() + ", Название: " + toy.getName() + ", Количество: " + toy.getQuantity() + ", Вес: " + toy.getWeight());
        }
    }
}

public class task2 {
    public static void main(String[] args) {
        ToyStore store = new ToyStore();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить игрушку");
            System.out.println("2. Изменить вес игрушки");
            System.out.println("3. Провести розыгрыш");
            System.out.println("4. Вывести список игрушек");
            System.out.println("5. Выход");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Введите ID игрушки: ");
                    int id = scanner.nextInt();
                    System.out.print("Введите название игрушки: ");
                    scanner.nextLine(); // Очистить буфер после nextInt()
                    String name = scanner.nextLine();
                    System.out.print("Введите количество игрушек: ");
                    int quantity = scanner.nextInt();
                    System.out.print("Введите вес игрушки: ");
                    double weight = scanner.nextDouble();
                    Toy newToy = new Toy(id, name, quantity, weight);
                    store.addToy(newToy);
                    break;
                case 2:
                    System.out.print("Введите ID игрушки, у которой нужно изменить вес: ");
                    int toyId = scanner.nextInt();
                    System.out.print("Введите новый вес игрушки: ");
                    double newWeight = scanner.nextDouble();
                    store.adjustWeight(toyId, newWeight);
                    break;
                case 3:
                    store.performGiveaway();
                    break;
                case 4:
                    store.listToys();
                    break;
                case 5:
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Неверный выбор. Попробуйте снова.");
            }
        }
    }
}
