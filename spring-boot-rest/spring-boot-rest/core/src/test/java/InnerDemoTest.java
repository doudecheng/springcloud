import com.xqt.entity.demo.InnerDemo;
import org.junit.Test;

public class InnerDemoTest {

    @Test
    public void test(){
       InnerDemo innerDemo = InnerDemo.newBuilder().setId("1")
                .setName("nick").build();
        System.out.println(innerDemo);
    }

}
