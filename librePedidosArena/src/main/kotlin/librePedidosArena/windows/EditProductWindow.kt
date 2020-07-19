import org.uqbar.arena.bindings.ObservableProperty
import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.commons.applicationContext.ApplicationContext
import org.uqbar.commons.model.exceptions.UserException
import org.uqbar.lacar.ui.model.ControlBuilder

open class EditProductWindow(owner:WindowOwner, productController: ProductController) : Dialog<ProductController>(owner,productController){

    override fun createErrorsPanel(mainPanel: Panel?): ErrorsPanel? {
        return null
    }


    override fun addActions(actionsPanel: Panel) {
        Button(actionsPanel).setCaption("Aceptar").onClick { this.buildProduct(); this.accept() }
        Button(actionsPanel).setCaption("Cancelar").onClick{ this.cancel() }
    }

    override fun createFormPanel(mainPanel: Panel) {
        this.title = "Modificar producto"

        this.titleWindow(mainPanel)

        this.createFirstPanel(mainPanel)

        this.createSecondPanel(mainPanel)
    }

    open fun titleWindow(mainPanel: Panel){
        Label(mainPanel).setText("Modificar producto " + modelObject.code).fontSize = 20
    }

    fun createFirstPanel(mainPanel: Panel) : Unit {
        val firstPanel = Panel(mainPanel)

        firstPanel.setLayout(ColumnLayout(2))

        Label(firstPanel).setText("Código: ")
        Label(firstPanel).setText(modelObject.code.toString()).alignRight()
        Label(firstPanel).setText("Nombre:")
        TextBox(firstPanel).setWidth(120).bindValueToProperty<String,ControlBuilder>("name")
        Label(firstPanel).setText("Descrip:")
        TextBox(firstPanel).setWidth(120).bindValueToProperty<String,ControlBuilder>("description")
    }

    fun createSecondPanel(mainPanel: Panel) : Unit {
        val secondPanel = Panel(mainPanel)
        secondPanel.setLayout(HorizontalLayout())

        Label(secondPanel).setText("Precio:")
        NumericField(secondPanel).setWidth(50).bindValueToProperty<Int,ControlBuilder>("price")

        Label(secondPanel).setText("Categoria")
        val selector = Selector<String>(secondPanel)
        selector.bindValueToProperty<Category,ControlBuilder>("category")
        selector.bindItems<String>(ObservableProperty(model, "categories"))
    }

    open fun buildProduct() : Unit {
        try{
            modelObject.buildProduct()
        }
        catch(e:Exception){
            UserException(e.message)
        }
    }


    fun getRestaurantController() : RestaurantController {
        return ApplicationContext.getInstance().getSingleton(RestaurantController::class)
    }

}
