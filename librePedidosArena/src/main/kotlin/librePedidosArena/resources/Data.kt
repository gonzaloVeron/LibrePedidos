object Data {

    var userManager : UserManager = UserManager()

    var app : App = App(userManager)

    /* Restaurant 1 */

    var userAdmin1 : UserAdmin = UserAdmin("Admin1", "1234")
    var coordinates1 = Coordinates(10.toDouble(), 10.toDouble(), "Mitre 2000")
    var rest1 = Restaurant(app.generateRestaurantCode(), "Restaurant 1", "Comida rapida", userAdmin1, "Mitre", coordinates1)

    var product1 : Product = Product(rest1.generateProductCode(), "Coca cola", "gaseosa", 80, Category.SOFT_DRINK)
    var product2 : Product = Product(rest1.generateProductCode(), "Agua", "sin gas", 70, Category.SOFT_DRINK)
    var product3 : Product = Product(rest1.generateProductCode(), "Rabas", "calamares fritos", 100, Category.ENTRY)
    var product4 : Product = Product(rest1.generateProductCode(), "Pizza", "napolitana", 140, Category.MAIN_DISH)

    var menu1 = Menu(rest1.generateMenuCode(), "Pizza + Gaseosa", "Gaseosa: coca cola", mutableListOf(), DiscountType.STATIC, 50, true)
    var menu2 = Menu(rest1.generateMenuCode(), "Entrada + Pizza + Bebida", "Bebida: Gaseosa o Agua", mutableListOf(), DiscountType.PERCENTEGE, 5, true)

    /* Restaurant 2 */

    var userAdmin2 : UserAdmin = UserAdmin("Admin2", "1234")
    var coordinates2 = Coordinates(20.toDouble(), 20.toDouble(), "Obelisco")
    var rest2 = Restaurant(app.generateRestaurantCode(), "Restaurant 2", "Restobar", userAdmin2, "9 de Julio", coordinates2)

    var product5 : Product = Product(rest1.generateProductCode(), "Fernet con coca", "50% - 50%", 95, Category.SOFT_DRINK)
    var product6 : Product = Product(rest1.generateProductCode(), "Cerveza", "Palermo", 70, Category.SOFT_DRINK)
    var product7 : Product = Product(rest1.generateProductCode(), "Papas fritas", "con cheddar", 120, Category.ENTRY)
    var product8 : Product = Product(rest1.generateProductCode(), "Hamburguesa", "completa", 140, Category.MAIN_DISH)

    var menu3 = Menu(rest2.generateMenuCode(), "Hamburguesa + Cerveza", "Hamburguesa completa y cerveza Palermo", mutableListOf(), DiscountType.PERCENTEGE, 5, true)
    var menu4 = Menu(rest2.generateMenuCode(), "Papas fritas + Fernet", "Papas fritas con cheddar", mutableListOf(), DiscountType.PERCENTEGE, 10, false)

    fun generateApp() : App {
        generateRestaurants()
        generateMenus()
        generateUsers()
        return app
    }

    fun generateUsers() {
        userAdmin1.restaurant = rest1
        userAdmin2.restaurant = rest2
        userManager.addAdmin(userAdmin1)
        userManager.addAdmin(userAdmin2)
    }

    fun generateMenus() {
        menu1.addProduct(product1)
        menu1.addProduct(product4)
        menu2.addProduct(product1)
        menu2.addProduct(product2)
        menu2.addProduct(product3)
        menu2.addProduct(product4)

        menu3.addProduct(product8)
        menu3.addProduct(product6)
        menu4.addProduct(product7)
        menu4.addProduct(product5)
    }

    fun generateRestaurants() {
        rest1.addProduct(product1)
        rest1.addProduct(product2)
        rest1.addProduct(product3)
        rest1.addProduct(product4)
        rest1.addMenu(menu1)
        rest1.addMenu(menu2)

        rest2.addProduct(product5)
        rest2.addProduct(product6)
        rest2.addProduct(product7)
        rest2.addProduct(product8)
        rest2.addMenu(menu3)
        rest2.addMenu(menu4)

        app.addRestaurant(rest1)
        app.addRestaurant(rest2)
    }

}