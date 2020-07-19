import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.widgets.TextBox
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.SimpleWindow
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.applicationContext.ApplicationContext
import org.uqbar.commons.model.Search
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.commons.model.exceptions.UserException
import javax.xml.soap.Text

class LibrePedidosWindow(owner: WindowOwner, restaurantController: RestaurantController) : SimpleWindow<RestaurantController>(owner, restaurantController) {

    override fun addActions(actionsPanel: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {}

    override fun createContents(mainPanel: Panel) {

        ApplicationContext.getInstance().configureSingleton(RestaurantController::class, modelObject)

        this.title = "LibrePedidos"

        mainPanel.setLayout(HorizontalLayout())

        this.productsPanel(mainPanel)

        this.menusPanel(mainPanel)
    }

    fun productsPanel(mainPanel: Panel) {
        val panel = Panel(mainPanel)

        this.productsTable(panel)

        this.productsButtons(panel)
    }

    fun productsTable(mainPanel: Panel){
        val panel = Panel(mainPanel)

        Label(panel).setText("Administrar productos").fontSize = 20

        this.productSearchBar(panel)

        val leftTable = Table<Product>(panel, Product::class.java)
        leftTable.setHeight(150)
        leftTable.bindItemsToProperty("products")
        leftTable.bindValueToProperty<Product, ControlBuilder>("selectedProduct")

        Column<Product>(leftTable)
                .setTitle("Nombre")
                .setFixedSize(150)
                .bindContentsToProperty("name")

        Column<Product>(leftTable)
                .setTitle("Precio")
                .setFixedSize(150)
                .bindContentsToProperty("price")
    }

    fun productSearchBar(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        TextBox(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("productToFind")

        Button(panel).setCaption("Buscar Producto").onClick{ modelObject.searchProductsByName() }.setWidth(97)
    }

    fun productsButtons(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(ColumnLayout(5))

        Button(panel).setCaption("Ver")
                .onClick{ this.openViewProductWindow() }
                .setWidth(60)

        Button(panel).setCaption("Ver menus")
                .onClick{ this.openMenusWithProductWindow() }
                .setWidth(60)

        Button(panel).setCaption("Modificar")
                .onClick{ this.openModifyProductWindow() }
                .setWidth(60)

        Button(panel).setCaption("Agregar")
                .onClick{ this.openCreateNewProductWindow() }
                .setWidth(60)

        Button(panel).setCaption("Eliminar")
                .onClick{ this.removeProduct() }
                .setWidth(60)
    }

    fun menusPanel(mainPanel: Panel) {
        val panel = Panel(mainPanel)

        this.menusTable(panel)

        this.menusButtons(panel)
    }

    fun menusTable(mainPanel: Panel){
        val panel = Panel(mainPanel)

        Label(panel).setText("Administrar menus").fontSize = 20

        this.menuSearchBar(panel)

        val rightTable = Table<Menu>(panel, Menu::class.java)
        rightTable.setHeight(150)
        rightTable.bindItemsToProperty("menus")
        rightTable.bindValueToProperty<Menu, ControlBuilder>("selectedMenu")

        Column<Menu>(rightTable)
                .setTitle("Nombre")
                .setFixedSize(100)
                .bindContentsToProperty("name")

        Column<Menu>(rightTable)
                .setTitle("Precio")
                .setFixedSize(100)
                .bindContentsToProperty("price")

        Column<Menu>(rightTable)
                .setTitle("Habil?")
                .setFixedSize(100)
                .bindContentsToProperty("state")
    }

    fun menuSearchBar(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        TextBox(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("menuToFind")

        Button(panel).setCaption("Buscar Menú").onClick{ modelObject.searchMenuByName() }.setWidth(97)
    }

    fun menusButtons(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(ColumnLayout(4))

        Button(panel).setCaption("Ver")
                .onClick{ this.openProductsMenuWindow() }
                .setWidth(75)

        Button(panel).setCaption("Agregar")
                .onClick{ this.openCreateMenuWindow() }
                .setWidth(75)

        Button(panel).setCaption("Modificar")
                .onClick{ this.openModifyMenuWindow() }
                .setWidth(75)

        Button(panel).setCaption("Eliminar")
                .onClick{ this.removeMenu() }
                .setWidth(75)
    }

    fun openCreateMenuWindow(){
        val menu = MenuController(modelObject.newMenu(), modelObject.products)
        val window = CreateNewMenuWindow(this, menu)
        window.onAccept { modelObject.updateMenus() }
        window.open()
    }

    fun openModifyMenuWindow() {
        try{
            val window = EditMenuWindow(this, MenuController(modelObject.obtainSelectedMenu(), modelObject.products))
            window.onAccept { modelObject.updateMenus() }
            window.open()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

    fun openCreateNewProductWindow(){
        val product = ProductController(modelObject.newProduct())
        val window = CreateNewProductWindow(this, product)
        window.onAccept { modelObject.updateProducts() }
        window.open()
    }

    fun openModifyProductWindow(){
        try {
            val window = EditProductWindow(this, ProductController(modelObject.obtainSelectedProduct()))
            window.onAccept { modelObject.updateProducts() }
            window.open()
        }
        catch(e: java.lang.Exception) {
            throw UserException(e.message)
        }
    }

    fun openProductsMenuWindow() {
        try {
            ProductsInMenuWindow(this, MenuController(modelObject.obtainSelectedMenu(), modelObject.products)).open()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

    fun openMenusWithProductWindow() {
        try {
            MenusWithProductWindow(this, MenusWithProductController(modelObject.obtainSelectedProduct(), modelObject.menus)).open()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

    fun openViewProductWindow() {
        try {
            ViewProductWindow(this, ProductController(modelObject.obtainSelectedProduct())).open()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

    fun removeMenu() {
        try {
            modelObject.removeMenu(modelObject.obtainSelectedMenu())
            modelObject.updateMenus()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

    fun removeProduct() {
        try {
            modelObject.removeProduct(modelObject.obtainSelectedProduct())
            modelObject.updateProducts()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }

}