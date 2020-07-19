import org.uqbar.arena.widgets.Button
import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.Dialog
import org.uqbar.arena.windows.ErrorsPanel
import org.uqbar.arena.windows.WindowOwner


class ViewProductWindow(owner: WindowOwner, productController: ProductController) : Dialog<ProductController>(owner, productController) {

    override fun createErrorsPanel(mainPanel: Panel?): ErrorsPanel? {
        return null
    }

    override fun addActions(actionsPanel: Panel?) {
        Label(actionsPanel).setText("").setWidth(150)
        Button(actionsPanel).setCaption("Cerrar").onClick{ close() }
    }

    override fun createFormPanel(mainPanel: Panel?) {

        this.setMinWidth(200)

        this.setTitle("Ver producto")

        Label(mainPanel).setText("Producto " + modelObject.code).setFontSize(12).alignCenter()

        Label(mainPanel).setText("")

        Label(mainPanel).setText("- Nombre: " + modelObject.name).setFontSize(10).alignLeft()

        Label(mainPanel).setText("- Descripcion: " + modelObject.description).setFontSize(10).alignLeft()

        Label(mainPanel).setText("- Categoria: " + modelObject.category).setFontSize(10).alignLeft()

        Label(mainPanel).setText("- Precio: " + modelObject.price).setFontSize(10).alignLeft()

        Label(mainPanel).setText("")
    }

}