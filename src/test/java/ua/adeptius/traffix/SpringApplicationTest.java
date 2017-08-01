package ua.adeptius.traffix;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringApplicationTest {

    @Autowired
    ApplicationContext ctx;

    @Test
    public void testContextLoads() throws Exception {
        assertThat(this.ctx).isNotNull();

        assertThat(this.ctx.containsBean("postProxyInvokerContextListener")).isTrue();
        assertThat(this.ctx.containsBean("application")).isTrue();
        assertThat(this.ctx.containsBean("backgroundManager")).isTrue();
    }

}