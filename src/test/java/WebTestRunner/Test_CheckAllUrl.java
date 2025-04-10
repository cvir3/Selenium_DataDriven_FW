package WebTestRunner;

import WebFunctions.wf_CheckAllUrl;
import org.testng.annotations.Test;
import utils.BaseClass;

public class Test_CheckAllUrl extends BaseClass {

    @Test
    public void testCheckAllUrl() throws Exception {
        wf_CheckAllUrl wfu = new wf_CheckAllUrl(webDriver);
        wfu.checkAllUrl();
    }
}
