import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.annotations.Observable
import org.uqbar.lacar.ui.model.ControlBuilder

class MenusWithProductWindow(owner: WindowOwner, menusWithProductController: MenusWithProductController) : Dialog<MenusWithProductController>(owner, menusWithProductController) {

    override fun createErrorsPanel(mainPanel: Panel?): ErrorsPanel? {
        return null
    }

    override fun addActions(actionsPanel: Panel?) {
        Label(actionsPanel).setText("").setWidth(150)
        Button(actionsPanel).setCaption("Cerrar").onClick{ accept() }
    }

    override fun createFormPanel(mainPanel: Panel?) {

        this.setMinWidth(200)

        this.setTitle("Productos de menu")

        Label(mainPanel).setText("Menus que tienen " + modelObject.productName).setFontSize(12)

        Label(mainPanel).setText("")

        Label(mainPanel).setFontSize(10).alignLeft().bindValueToProperty<String, ControlBuilder>("menusWithProduct")

        Label(mainPanel).setText("")

    }
}