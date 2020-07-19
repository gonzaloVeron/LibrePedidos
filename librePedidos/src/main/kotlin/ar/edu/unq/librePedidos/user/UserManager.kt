import Exceptions.UserOrPasswordIncorrectException
import org.uqbar.commons.model.annotations.Observable

@Observable
class UserManager {

    val users : MutableList<User> = mutableListOf()
    val admins : MutableList<UserAdmin> = mutableListOf()

    fun addUser(newUser: User): User {
        if(usernameIsAvailable(newUser.nameUser)) {
            users.add(newUser)
            return newUser
        }
        else
            throw UsernameNotAvailableException(newUser.nameUser)
    }

    fun addAdmin(newAdmin: UserAdmin) {
        if(adminUsernameIsAvailable(newAdmin.nameUser)) admins.add(newAdmin) else throw UsernameNotAvailableException(newAdmin.nameUser)
    }

    fun usernameIsAvailable(username: String) : Boolean {
        return ! users.any { u -> u.nameUser == username }
    }

    fun adminUsernameIsAvailable(username: String) : Boolean {
        return ! admins.any { a -> a.nameUser == username }
    }

    fun verifyUser(username: String, password: String): User {
        return users.find { user -> user.nameUser == username && user.password == password } ?: throw UserOrPasswordIncorrectException()
    }

    fun verifyUserAdmin(username: String, password: String) : UserAdmin? {
        return admins.find { admin -> admin.nameUser == username && admin.password == password }
    }

    fun findUserByName(name : String): User{
        return users.find { user -> user.nameUser == name } ?: throw UserNotFoundException()
    }

}