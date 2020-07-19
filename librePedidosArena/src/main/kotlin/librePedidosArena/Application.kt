import org.uqbar.arena.Application
import org.uqbar.arena.windows.Window
import org.uqbar.commons.applicationContext.ApplicationContext

class ApplicationWindow : Application() {

    override fun createMainWindow(): Window<*>? {
        val app : App = Data.generateApp()
        return LoginWindow(this, UserManagerController(app.userManager))
    }
}

fun main() {
    ApplicationWindow().start()
}

