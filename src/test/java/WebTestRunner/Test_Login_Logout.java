package WebTestRunner;

import WebFunctions.wf_Login;
import org.testng.annotations.Test;
import utils.BaseClass;

public class Test_Login_Logout extends BaseClass {

    @Test
    public void testLogin_Logout() throws Exception {
        wf_Login wfl = new wf_Login(webDriver);
        wfl.loginPage();
        wfl.logOut();
    }
}
