package es.unex.asee_proyectoprueba;

import static org.junit.Assert.assertNotEquals;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;

@RunWith(RobolectricTestRunner.class)
public class CU09_NavigationTabs_UnitTest {

    @Mock
    Context context;

    @Test
    public void test () throws NoSuchFieldException, IllegalAccessException {

        int testSocial = R.layout.class.getField("fragment_item_detail_social").getInt(null);
        assertNotEquals(testSocial,0);

        int testInfo = R.layout.class.getField("fragment_item_detail_info").getInt(null);
        assertNotEquals(testInfo,0);
    }

    @Before
    public void initTest() {
        MockitoAnnotations.initMocks(this);
        context = ApplicationProvider.getApplicationContext();
    }
}
