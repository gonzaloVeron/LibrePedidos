import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.WindowOwner
import org.uqbar.commons.model.exceptions.UserException

class CreateNewProductWindow(owner: WindowOwner, productController : ProductController) : EditProductWindow(owner, productController) {

    override fun createContents(mainPanel: Panel) {
        super.createContents(mainPanel)
        this.title = "Create New Product"
    }

    override fun titleWindow(mainPanel: Panel) {
        Label(mainPanel).setText("Nuevo producto").fontSize = 20
    }

    override fun buildProduct(){
        try {
            getRestaurantController().addProduct(modelObject.buildProduct())
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }
}