import Exceptions.UserOrPasswordIncorrectException
import io.mockk.every
import io.mockk.mockk
import kotlin.test.*

class UserManagerTest {

    private val userManager = UserManager()
    private val user1 = mockk<User>()
    private val user2 = mockk<User>()
    private val admin1 = mockk<UserAdmin>()
    private val order1 = mockk<Order>()
    private val order2 = mockk<Order>()
    private val order3 = mockk<Order>()

    @BeforeTest
    fun setUp() {
        every { user1.nameUser } returns "user1"
        every { user2.nameUser } returns "user2"
        every { admin1.nameUser } returns "admin1"
        every { admin1.password } returns "Spore"
        every { user1.ordersPlaced } returns mutableListOf(order1, order2, order3)

        userManager.addUser(user1)
        userManager.addUser(user2)

        userManager.addAdmin(admin1)
    }

    @Test
    fun addUser() {
        assertEquals(2, userManager.users.size)
    }

    @Test
    fun addUserThrowsExeption() {
        assertFailsWith<UsernameNotAvailableException> { userManager.addUser(user1) }
    }

    @Test
    fun addAdmin() {
        assertEquals(1, userManager.admins.size)
    }

    @Test
    fun addAdminThrowsException() {
        assertFailsWith<UsernameNotAvailableException> { userManager.addAdmin(admin1) }
    }

    @Test
    fun verifyUserThrowsException() {
        assertFailsWith<UserOrPasswordIncorrectException> { userManager.verifyUser("test", "123") }
    }

    @Test
    fun verifyUserAdmin(){
        assertEquals(admin1, userManager.verifyUserAdmin("admin1", "Spore"))
    }

    @Test
    fun findUserByName(){
        assertEquals(user1, userManager.findUserByName("user1"))
    }

    @Test (expected = UserNotFoundException::class) fun findUserByNameExpectsUserNotFoundException() {
        assertEquals(user1, userManager.findUserByName("user3"))
    }

}