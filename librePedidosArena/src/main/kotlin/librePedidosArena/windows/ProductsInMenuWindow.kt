import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.lacar.ui.model.ControlBuilder

class ProductsInMenuWindow(owner: WindowOwner, menuController: MenuController) : Dialog<MenuController>(owner, menuController) {

    override fun createErrorsPanel(mainPanel: Panel?): ErrorsPanel? {
        return null
    }

    override fun addActions(actionsPanel: Panel?) {
        Label(actionsPanel).setText("").setWidth(150)
        Button(actionsPanel).setCaption("Cerrar").onClick{ close() }
    }

    override fun createFormPanel(mainPanel: Panel?) {

        this.setMinWidth(200)

        this.setTitle("Productos de menu")

        Label(mainPanel).setText("Productos del menu " + modelObject.code).setFontSize(12)

        Label(mainPanel).setText("")

        Label(mainPanel).setFontSize(10).alignLeft().bindValueToProperty<String, ControlBuilder>("nameProducts")

        Label(mainPanel).setText("")

        Label(mainPanel).setText("Total: " + modelObject.price).setFontSize(10).alignLeft()

    }
}