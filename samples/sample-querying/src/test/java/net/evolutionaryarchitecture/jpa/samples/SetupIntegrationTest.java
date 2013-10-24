package net.evolutionaryarchitecture.jpa.samples;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@ContextConfiguration(locations = "classpath:itest-system-definition.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SetupIntegrationTest {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void countEntries() {
        assertEquals(15, em.createQuery("from Product p").getResultList().size());
    }
    
//    @Test
//    public void createInsertStatements() {
//        List<Product> products = em.createQuery("select p from Product p", Product.class).getResultList();
//
//        for (Product product : products) {
//            for (Manufacturer manufacturer : product.manufacturers) {
//                printJob(product.getId(), manufacturer.getId());
//            }
//        }
//    }

//    StringBuffer sb = new StringBuffer();
//
//    int jobCount = 1;
//
//    int stateCount = 1;
//
//    private void printJob(Long productId, Long manufacturerId) {
//        int jobsPerProduct = random(20);
//        for (int i = 0; i < jobsPerProduct; i++) {
//            sb.append(
//                    "insert into T_JOBS (ID, AMOUNT, PRODUCT_ID, MANUFACTURER_ID) values (" + jobCount++ + ", "
//                            + random(40) + ", " + productId + ", " + manufacturerId + ")").append("\n");
//            printStateChange();
//        }
//    }
//
//    private void printStateChange() {
//        Timestamp start = Timestamp.valueOf("2011-08-01 12:00:00.0");
//        if (random(10) < 7) {
//            String startTime = dateAddHours(start, random(200), random(60), random(60));
//            sb.append(
//                    "insert into T_STATE_CHANGES (JOB_ID, CHANGED_WHEN, NEW_STATE) values (" + (jobCount-1) + ", '"
//                            + startTime + "', 'STARTED')").append("\n");
//            if (random(10) < 6) {
//                String ongoingTime = dateAddHours(Timestamp.valueOf(startTime), random(50), random(60), random(60));
//                sb.append(
//                        "insert into T_STATE_CHANGES (JOB_ID, CHANGED_WHEN, NEW_STATE) values (" + (jobCount-1) + ", '"
//                                + ongoingTime + "', 'ONGOING')").append("\n");
//                if (random(10) < 5) {
//                    sb.append(
//                            "insert into T_STATE_CHANGES (JOB_ID, CHANGED_WHEN, NEW_STATE) values (" + (jobCount-1) + ", '"
//                                    + dateAddHours(Timestamp.valueOf(ongoingTime), random(60), random(60), random(60))
//                                    + "', 'DONE')").append("\n");
//                }
//            }
//        }
//    }
//
//    private String dateAddHours(Timestamp start, int hours, int minutes, int seconds) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.setTimeInMillis(start.getTime());
//        calendar.add(Calendar.HOUR, hours);
//        calendar.add(Calendar.MINUTE, minutes);
//        calendar.add(Calendar.SECOND, seconds);
//
//        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime());
//    }
//
//    Random random = new Random(System.currentTimeMillis());
//
//    private int random(int i) {
//        return random.nextInt(i - 1) + 1;
//    }
//
//    @After
//    public void print() {
//        System.out.println(sb.toString());
//    }
}
