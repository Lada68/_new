package com.amr.project;

import com.amr.project.dao.abstracts.*;
import com.amr.project.model.entity.*;
import com.amr.project.model.enums.Gender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.*;

@Component
public class DatabaseInitializer {

    private final RoleDao roleDao;
    private final UserDao userDao;
    private final AddressDao addressDao;
    private final CountryDao countryDao;
    private final CityDao cityDao;
    private final CategoryDao categoryDao;
    private final ItemDao itemDao;
    private final ImageDao imageDao;
    private final ShopDao shopDao;

    private List<Category> categories;
    private List<Country> countries;
    private List<City> cities;
    private Set<Role> roles;
    private List<User> users;
    private List<Item> items;
    private List<Shop> shops;

    @Autowired
    public DatabaseInitializer(RoleDao roleDao, UserDao userDao, AddressDao addressDao,
                               CountryDao countryDao, CityDao cityDao, CategoryDao categoryDao,
                               ItemDao itemDao, ImageDao imageDao, ShopDao shopDao) {

        this.roleDao = roleDao;
        this.userDao = userDao;
        this.addressDao = addressDao;
        this.countryDao = countryDao;
        this.cityDao = cityDao;
        this.categoryDao = categoryDao;
        this.itemDao = itemDao;
        this.imageDao = imageDao;
        this.shopDao = shopDao;
    }

    @PostConstruct
    public void init() {
        roles = getRoles();
        roles.forEach(roleDao::persist);

        countries = getCountries();
        countries.forEach(countryDao::persist);

        cities = getCities();
        cities.forEach(cityDao::persist);

        categories = getCategories();
        categories.forEach(categoryDao::persist);

        users = getUsers();
        //USER Можно пока одного юзера сделать фискированным чтобы время сэкономить?
        users.get(0).setUsername("user");
        users.get(0).setPassword("user");
        users.get(0).setRoles(null);
        users.get(0).addRole(roles.stream().filter(r -> r.getName() == "USER").findFirst().orElse(null));
        //USER Можно пока одного юзера сделать фискированным чтобы время сэкономить?
        users.forEach(user -> {
            user.getAddress().forEach(addressDao::persist);
            user.getImages().forEach(imageDao::persist);
            userDao.persist(user);
        });

        shops = getShops();
        shops.forEach(shop -> {
            imageDao.persist(shop.getLogo());
            shopDao.persist(shop);
        });

        items = getItems();
        items.forEach(item -> {
            item.getImages().forEach(imageDao::persist);
            itemDao.persist(item);
        });

        Optional<Item> item = itemDao.getByKey(Item.class, 1L);
        System.out.println();

    }

    private Set<Role> getRoles() {
        Set<Role> roles = new HashSet<>();

        roles.add(new Role("USER"));
        roles.add(new Role("ADMIN"));

        return roles;
    }

    private List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(getUser("Ivan", "Ivanov", Gender.MALE));
        users.add(getUser("Vasily", "Vasiliev", Gender.MALE));
        users.add(getUser("Piter", "Petrov", Gender.MALE));
        users.add(getUser("Irina", "Irinova", Gender.FEMALE));
        users.add(getUser("Sveta", "Svetova", Gender.FEMALE));
        users.add(getUser("Alex", "Alexeev", Gender.MALE));
        users.add(getUser("Kira", "Kireeva", Gender.FEMALE));
        users.add(getUser("Dmitry", "Dmitrov", Gender.MALE));
        users.add(getUser("Kiril", "Kirilov", Gender.MALE));
        users.add(getUser("Pavel", "Pavlov", Gender.MALE));

        return users;
    }

    private User getUser(String firstName, String lastName, Gender gender) {
        User user = new User();

        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setActivate(true);
        user.setActivationCode("1");
        user.setEmail(user.getFirstName().toLowerCase() + user.getLastName().toLowerCase() + "@mail.com");
        user.setAge(Integer.parseInt(randomNumberString(2)));
        user.setUsername(user.getFirstName().toLowerCase() + user.getAge());
        user.setPassword(randomNumberString(4));
        user.addAddress(getRandomAddress());
        user.addAddress(getRandomAddress());
        user.addAddress(getRandomAddress());
        user.addRole(randomListElement(new ArrayList<>(roles)));
        user.setGender(gender);
        user.addImages(getRandomImage());
        user.addImages(getRandomImage());
        user.addImages(getRandomImage());
        user.setPhone(randomPhone());

        return user;
    }

    private List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();

        categories.add(new Category("Транспорт"));
        categories.add(new Category("Недвижимость"));
        categories.add(new Category("Работа"));
        categories.add(new Category("Услуги"));
        categories.add(new Category("Личные вещи"));
        categories.add(new Category("Электроника"));
        categories.add(new Category("Инструменты"));
        categories.add(new Category("Животные"));
        categories.add(new Category("Мебель"));

        return categories;
    }

    private Address getRandomAddress() {
        City city = randomListElement(cities);

        return new Address(
                randomNumberString(6),
                city.getCountry(),
                city,
                "Street " + randomNumberString(3),
                randomNumberString(2)
        );
    }

    private List<Country> getCountries() {
        List<Country> countries = new ArrayList<>();

        countries.add(new Country("USA"));
        countries.add(new Country("Russia"));
        countries.add(new Country("Kazakhstan"));
        countries.add(new Country("Belarus"));
        countries.add(new Country("Ukraine"));
        countries.add(new Country("France"));
        countries.add(new Country("Spain"));

        return countries;
    }

    private List<City> getCities() {
        List<City> cities = new ArrayList<>();

        cities.add(new City("Atlanta", countries.get(0)));
        cities.add(new City("Boston", countries.get(0)));
        cities.add(new City("Chicago", countries.get(0)));
        cities.add(new City("Houston", countries.get(0)));
        cities.add(new City("Moscow", countries.get(1)));
        cities.add(new City("Kolomna", countries.get(1)));
        cities.add(new City("Tula", countries.get(1)));
        cities.add(new City("Tomsk", countries.get(1)));
        cities.add(new City("Ekaterinburg", countries.get(1)));
        cities.add(new City("Nursultan", countries.get(2)));
        cities.add(new City("Astana", countries.get(2)));
        cities.add(new City("Turkestan", countries.get(2)));
        cities.add(new City("Karaganda", countries.get(2)));
        cities.add(new City("Minsk", countries.get(3)));
        cities.add(new City("Brest", countries.get(3)));
        cities.add(new City("Gomel", countries.get(3)));
        cities.add(new City("Vitebsk", countries.get(3)));
        cities.add(new City("Kiev", countries.get(4)));
        cities.add(new City("Odessa", countries.get(4)));
        cities.add(new City("Donetsk", countries.get(4)));
        cities.add(new City("Paris", countries.get(5)));
        cities.add(new City("Lion", countries.get(5)));
        cities.add(new City("Marcel", countries.get(5)));
        cities.add(new City("Barcelona", countries.get(6)));
        cities.add(new City("Madrid", countries.get(6)));

        return cities;
    }

    private List<Shop> getShops() {
        String path = "src/main/resources/static/img/shops/";

        List<Shop> shops = new ArrayList<>();
        shops.add(getShop("M-Group", "Мебель для дома и сада", path + "mgroup.jpg"));
        shops.add(getShop("Vesco", "Элитная загородная, городская и коммерческая недвижимость", path + "vesco.jpg"));
        shops.add(getShop("BigTV", "Проверенная временем цифровые устройства", path + "bigtv.jpg"));
        shops.add(getShop("MskServer", "Постройте свой бизнес без переплат", path + "mskserver.jpg"));
        shops.add(getShop("SereverGate", "Восстановленное IT оборудование", path + "servergate.jpg"));
        shops.add(getShop("iBatt", "Аккумуляторные батареи для ноутбуков, блоки питания для ноутбуков", path + "ibatt.jpg"));
        shops.add(getShop("FotoCCCP", "Фототехника и оптика. Переходники, адаптеры и аксессуары для фото", path + "fotocccp.jpg"));
        shops.add(getShop("RedKey", "Сеть салонов Hi-Fi техники", path + "redkey.jpg"));

        return shops;
    }

    private Shop getShop(String name, String description, String logoUrl) {
        Shop shop = new Shop();

        shop.setName(name);
        shop.setDescription(description);
        shop.setLogo(new Image(logoUrl));
        shop.setEmail(shop.getName().toLowerCase() + "@mail.com");
        shop.setPhone(randomPhone());
        shop.setRating(randomRating());
        shop.setLocation(randomListElement(countries));
        shop.setUser(randomListElement(users));

        return shop;
    }

    private List<Item> getItems() {
        String path = "src/main/resources/static/img/items/";

        Item item1 = new Item();
        item1.setName("Harman kardon mini");
        item1.setPrice(BigDecimal.valueOf(500));
        item1.addCategory(categories.get(5));
        item1.setDescription("Состояние на четверочку. На большой громкости начинает хрипеть, на умеренной можно слушать. В комплекте aux кабель. Продаю как есть, в качестве запчасти, без гарантий, проверка на месте.");
        item1.setRating(randomRating());
        item1.addImage(new Image(path + "carbon/1.jpg"));
        item1.addImage(new Image(path + "carbon/2.jpg"));
        item1.addImage(new Image(path + "carbon/3.jpg"));
        item1.setShop(randomListElement(shops));

        Item item2 = new Item();
        item2.setName("iPhone 4 16gb");
        item2.setPrice(BigDecimal.valueOf(1000));
        item2.addCategory(categories.get(5));
        item2.setDescription("Эта модель давно не поддерживается Apple, поэтому из приложений кроме телеграмм ничего поставить не удастся. Кабель usb в комплекте. Продаю как есть, без гарантий, так как давно им не пользовался, сколько держит батарею без понятия. Любые проверки на месте.");
        item2.setRating(randomRating());
        item2.addImage(new Image(path + "iphone4/1.jpg"));
        item2.addImage(new Image(path + "iphone4/2.jpg"));
        item2.setShop(randomListElement(shops));

        Item item3 = new Item();
        item3.setName("Зеркало правое Renault Logan 1");
        item3.setPrice(BigDecimal.valueOf(600));
        item3.addCategory(categories.get(0));
        item3.setDescription("Механическое правое зеркало на первый Логан. На зеркальном элементе трещины. Снято с собственного автомобиля.");
        item3.setRating(randomRating());
        item3.addImage(new Image(path + "mirror/1.jpg"));
        item3.addImage(new Image(path + "mirror/2.jpg"));
        item3.setShop(randomListElement(shops));

        Item item4 = new Item();
        item4.setName("Дом каркасно щитовой");
        item4.setPrice(BigDecimal.valueOf(590000));
        item4.addCategory(categories.get(1));
        item4.setDescription("Деревянный каркасный дом 6х8 для полноценного проживания с окнами ПВХ, двери ДФ, отделка внутри и снаружи евровагонка камерной сушки");
        item4.setRating(randomRating());
        item4.addImage(new Image(path + "house/1.jpg"));
        item4.setShop(randomListElement(shops));

        Item item5 = new Item();
        item5.setName("Котенок Курильский бобтейл");
        item5.setPrice(BigDecimal.valueOf(25000));
        item5.addCategory(categories.get(7));
        item5.setDescription("Очень ласковый и невероятно мурчащий котёнок курильского бобтейла");
        item5.setRating(randomRating());
        item5.addImage(new Image(path + "cat/1.jpg"));
        item5.addImage(new Image(path + "cat/2.jpg"));
        item5.setShop(randomListElement(shops));

        Item item6 = new Item();
        item6.setName("Стол обеденный");
        item6.setPrice(BigDecimal.valueOf(17750));
        item6.addCategory(categories.get(8));
        item6.setDescription("Стоимость указана за обеденный стол в базовой комплектации без сборки. У нас вы можете купить обеденный стол и получить стулья в подарок.");
        item6.setRating(randomRating());
        item6.addImage(new Image(path + "table/1.jpg"));
        item6.setShop(randomListElement(shops));

        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);
        items.add(item6);

        return items;
    }

    /* Utilities */

    private String randomNumberString(int length) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < length; i++) {
            sb.append((int)(Math.random()*10));
        }
        return sb.toString();
    }

    private <T> T randomListElement(List<T> list) {
        int randomIndex = new Random().nextInt(list.size());
        return list.get(randomIndex);
    }

    private Double randomRating() {
        return Math.random()*5;
    }

    private Image getRandomImage() {
        Image image = new Image();
        return image.ImageFromURL("https://thispersondoesnotexist.com/image");
    }

    private String randomPhone() {
        return new StringBuilder().append("(").append(randomNumberString(3)).append(") ")
                .append(randomNumberString(3)).append("-")
                .append(randomNumberString(2)).append("-")
                .append(randomNumberString(2)).toString();
    }

}
