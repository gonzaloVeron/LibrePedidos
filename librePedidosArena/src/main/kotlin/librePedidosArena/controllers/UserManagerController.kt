import org.uqbar.commons.model.annotations.Observable

@Observable
class UserManagerController(val userManager : UserManager) {
    var username = ""
    var password = ""

    fun verifyUserAdmin() : UserAdmin {
        val user = userManager.verifyUserAdmin(this.username, this.password)
        if(user != null)
            return user
        else
            throw UsernameOrPasswordIncorrect()
    }
}