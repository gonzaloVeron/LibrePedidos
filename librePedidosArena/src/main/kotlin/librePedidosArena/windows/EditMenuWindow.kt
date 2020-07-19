import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.tables.Column
import org.uqbar.arena.widgets.tables.Table
import org.uqbar.arena.windows.*
import org.uqbar.commons.applicationContext.ApplicationContext
import org.uqbar.commons.model.exceptions.UserException

open class EditMenuWindow(owner: WindowOwner, model: MenuController) : Dialog<MenuController>(owner, model) {

    override fun createErrorsPanel(mainPanel: Panel?): ErrorsPanel? {
        return null
    }

    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel).setCaption("Cancelar").onClick { this.cancel() }.setWidth(132)

        Button(actionsPanel).setCaption("Aceptar").onClick { this.buildMenu(); this.accept() }.setWidth(132)
    }

    override fun createFormPanel(mainPanel: Panel) {
        this.title = "Restaurante :: Modificar Menú"

        this.titleWindow(mainPanel)

        this.codeNameDescFields(mainPanel)

        this.selectorField(mainPanel)

        this.tables(mainPanel)

        this.checkBoxAndPrice(mainPanel)
    }

    open fun titleWindow(mainPanel: Panel){
        Label(mainPanel).setText("Modificar Menu " + modelObject.code).fontSize = 20
    }

    private fun codeNameDescFields(mainPanel: Panel){
        val panel1 = Panel(mainPanel)

        panel1.setLayout(ColumnLayout(2))

        this.codeField(mainPanel)

        this.nameField(mainPanel)

        this.descField(mainPanel)
    }

    private fun codeField(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Codigo").setWidth(50).alignLeft()
        Label(panel).setText(modelObject.code.toString()).alignRight()
    }

    private fun nameField(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Nombre").setWidth(50).alignLeft()
        TextBox(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("name")
    }

    private fun descField(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Descrip").setWidth(50).alignLeft()
        TextBox(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("description")
    }

    private fun selectorField(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("TipoDesc")
        val selector = Selector<String>(panel)
        selector.setWidth(75)
        selector.bindValueToProperty<String, ControlBuilder>("discountSelected")
        selector.bindItems<String>(ObservableProperty(model, "availableDiscounts"))
        selector.onSelection { modelObject.recalculatePrice() }

        Label(panel).setText("Descuen")
        NumericField(panel).setWidth(49).bindValueToProperty<Int, ControlBuilder>("valDiscount")
    }

    private fun tables(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        this.availableTable(panel)

        this.tableButtons(panel)

        this.selectedTable(panel)
    }

    private fun selectedTable(mainPanel : Panel){
        val panel = Panel(mainPanel)

        Label(panel).setText("Seleccionado")


        val selectedTable = Table<Menu>(panel, Menu::class.java)
        selectedTable.bindItemsToProperty("selected")
        selectedTable.bindValueToProperty<String, ControlBuilder>("selectedProduct")

        Column<Menu>(selectedTable)
                .setTitle("Nombre")
                .setFixedSize(100)
                .bindContentsToProperty("name")
    }


    private fun availableTable(mainPanel : Panel){
        val panel = Panel(mainPanel)

        Label(panel).setText("Disponible")

        val tablaDisponible = Table<Menu>(panel, Menu::class.java)
        tablaDisponible.bindItemsToProperty("availableProducts")
        tablaDisponible.bindValueToProperty<String, ControlBuilder>("availableProduct")

        Column<Menu>(tablaDisponible)
                .setTitle("Nombre")
                .setFixedSize(100)
                .bindContentsToProperty("name")
    }

    private fun tableButtons(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(VerticalLayout())

        Label(panel).setText("")

        Label(panel).setText("")

        Button(panel).setCaption(">>").onClick { modelObject.addAvailableProduct() }.setWidth(40)

        Button(panel).setCaption("<<").onClick { modelObject.removeSelectedProduct() }.setWidth(40)

    }

    open fun buildMenu(){
        try {
            modelObject.buildMenu()
        }
        catch(e : Exception){
            UserException(e.message)
        }
    }

    private fun checkBoxAndPrice(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        this.availableCheckbox(panel)

        Label(panel).setText("").setWidth(75)

        this.menuPrice(panel)
    }

    private fun availableCheckbox(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        val box = CheckBox(panel)
        box.bindValueToProperty<Boolean, ControlBuilder>("state")
        Label(panel).setText("Habilitado")
    }

    private fun menuPrice(mainPanel : Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Precio Menu: ")
        Label(panel).bindValueToProperty<Int, ControlBuilder>("price")
    }

    fun getRestaurantController(): RestaurantController {
        return ApplicationContext.getInstance().getSingleton(RestaurantController::class)
    }

}