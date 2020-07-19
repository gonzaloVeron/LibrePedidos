import org.uqbar.arena.widgets.Label
import org.uqbar.arena.widgets.Panel
import org.uqbar.arena.windows.WindowOwner

class CreateNewMenuWindow(owner: WindowOwner, menuController: MenuController) : EditMenuWindow(owner, menuController) {

    override fun createContents(mainPanel: Panel) {
        super.createContents(mainPanel)
        this.title = "Restaurante :: Nuevo Menú"
    }

    override fun titleWindow(mainPanel: Panel) {
        Label(mainPanel).setText("Nuevo Menú").fontSize = 20
    }

    override fun buildMenu(){
        getRestaurantController().addMenu(modelObject.buildMenu())
    }

}