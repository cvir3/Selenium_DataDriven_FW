package WebTestRunner;

import WebFunctions.wf_Register;
import org.testng.annotations.Test;
import utils.BaseClass;

public class Test_RegisterForm extends BaseClass {

    @Test()
    public void testRegister() throws Exception {
        wf_Register wfr = new wf_Register(webDriver);
        wfr.registerForm();
    }
}
