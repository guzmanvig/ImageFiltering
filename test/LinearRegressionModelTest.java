import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import model.KClusteringModel;
import model.KClusteringModel.Cluster;
import model.Model;
import model.Point2D;
import org.junit.Test;

public class KClusteringModelTest {

  @Test
  public void testCluster() {
    // TODO: use interface.
    KClusteringModel model = new KClusteringModel(4);
    ArrayList<Point2D> list = new ArrayList<>();

    Point2D p0 = new Point2D(0,0);
    Point2D p1 = new Point2D(1,0);
    Point2D p2 = new Point2D(0,1);
    Point2D p3 = new Point2D(1,1);
    list.add(p0);
    list.add(p1);
    list.add(p2);
    list.add(p3);

    Point2D p4 = new Point2D(100,0);
    Point2D p5 = new Point2D(101,0);
    Point2D p6 = new Point2D(100,1);
    Point2D p7 = new Point2D(101,1);
    list.add(p4);
    list.add(p5);
    list.add(p6);
    list.add(p7);

    Point2D p8 = new Point2D(0,100);
    Point2D p9 = new Point2D(1,100);
    Point2D p10 = new Point2D(0,101);
    Point2D p11 = new Point2D(1,101);
    list.add(p8);
    list.add(p9);
    list.add(p10);
    list.add(p11);

    Point2D p12 = new Point2D(100,100);
    Point2D p13 = new Point2D(101,100);
    Point2D p14 = new Point2D(100,101);
    Point2D p15 = new Point2D(101,101);
    list.add(p12);
    list.add(p13);
    list.add(p14);
    list.add(p15);

    List<Cluster> a = model.calculateTest(list);
    int b = 0;

  }

}