import org.uqbar.arena.layout.ColumnLayout
import org.uqbar.arena.layout.HorizontalLayout
import org.uqbar.arena.layout.VerticalLayout
import org.uqbar.arena.widgets.*
import org.uqbar.arena.windows.*
import org.uqbar.lacar.ui.model.ControlBuilder
import org.uqbar.commons.model.exceptions.UserException

class LoginWindow(owner: WindowOwner, userManagerModel: UserManagerController) : SimpleWindow<UserManagerController>(owner, userManagerModel){

    override fun addActions(actionsPanel: Panel) {}

    override fun createFormPanel(mainPanel: Panel) {}

    override fun createContents(mainPanel: Panel) {
        this.title = "Login"

        Label(mainPanel).setText("Bienvenidos a LibrePedidos").fontSize = 14

        Label(mainPanel).setText("")

        this.userField(mainPanel)

        this.passwordField(mainPanel)

        this.acceptButton(mainPanel)

    }

    fun userField(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Usuario: ").setWidth(60).alignLeft()
        TextBox(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("username")
    }

    fun passwordField(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("Password:").setWidth(60).alignLeft()
        PasswordField(panel).setWidth(200).bindValueToProperty<String, ControlBuilder>("password")
    }

    fun acceptButton(mainPanel: Panel){
        val panel = Panel(mainPanel)

        panel.setLayout(HorizontalLayout())

        Label(panel).setText("").setWidth(173)

        Button(panel).setCaption("Ingresar").onClick{ verifyUser() }.setWidth(100)
    }


    fun verifyUser() {
        try {
            val admin = modelObject.verifyUserAdmin()
            this.close()
            LibrePedidosWindow(this, RestaurantController(admin.restaurant as Restaurant)).open()
        }
        catch(e: Exception) {
            throw UserException(e.message)
        }
    }


}

