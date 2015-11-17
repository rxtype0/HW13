/*
 * Kenny Fung
 * Homework 13 (programming)  
 * Octahedron
 */

import java.awt.*;
import java.applet.*;
import java.awt.event.*;

import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.applet.MainFrame;

public class NotForAbhiTest extends Applet {
  public static void main(String s[]) {
    new MainFrame(new NotForAbhiTest(), 900, 900);
  }
  
  public void init() {
    GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
    Canvas3D cv = new Canvas3D(gc);
    cv.setBackground(Color.white);
    setLayout(new BorderLayout());
    add(cv, BorderLayout.CENTER);
    BranchGroup bg = createSceneGraph();
    bg.compile();
    SimpleUniverse su = new SimpleUniverse(cv);
    su.getViewingPlatform().setNominalViewingTransform();
    su.addBranchGraph(bg);
  }

  private BranchGroup createSceneGraph() {
    BranchGroup root = new BranchGroup();
    
    Appearance ap = new Appearance();
    ColoringAttributes ca = new ColoringAttributes();
    TransformGroup tg = new TransformGroup();
    
    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    root.addChild(tg);
    
    //object
    // create objects

    
    Point3f[] vertices = new Point3f[6];
    
    vertices[0] = new Point3f(0.0f, 0.0f, 1.0f);
    vertices[1] = new Point3f(0.0f, 0.0f, -1.0f);
    vertices[2] = new Point3f(1.0f, 0.0f, 0.0f);
    vertices[3] = new Point3f(-1.0f, 0.0f, 0.0f);
    vertices[4] = new Point3f(0.0f, 1.0f, 0.0f);
    vertices[5] = new Point3f(0.0f, -1.0f, 0.0f);
    
    int[] faces = {0,2,4,0,4,3,0,3,5,0,5,2,1,2,5,1,5,3,1,3,4,1,4,2};
    
    GeometryInfo g = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);
    g.setCoordinates(vertices);
    g.setCoordinateIndices(faces);
    NormalGenerator ng = new NormalGenerator();
    ng.setCreaseAngle(Math.PI / 4);
    ng.generateNormals(g);
    GeometryArray octahedron = g.getGeometryArray();
    
    ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
    ap.setColoringAttributes(ca);
    
    //polygon appearance
    PolygonAttributes pa = new PolygonAttributes();
    pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
    ap.setPolygonAttributes(pa);
    
    //material
    Material mat = new Material();
    mat.setLightingEnable(true);
    ap.setMaterial(mat);
    
    
    
    //transformation
    // create transform groups
    
    Transform3D yAxis = new Transform3D();
    Transform3D xAxis = new Transform3D();
    xAxis.rotX(0);
    yAxis.add(xAxis);
    
    //rotational object
    Alpha rotationAlpha = new Alpha(-1, 12000);
    RotationInterpolator rotator = new RotationInterpolator(rotationAlpha, tg, yAxis, 0.0f, (float) Math.PI * 2.0f);
    
    //bound the object to scene
    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
    rotator.setSchedulingBounds(bounds);
    
    // add transform groups to root
    // add objects to transform groups
    
    //light
    // create light sources, and influencing bounds
    
    BoundingSphere infinite = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);
    
    
    DirectionalLight l1 = new DirectionalLight();
    l1.setInfluencingBounds(infinite);
    l1.setColor(new Color3f(1.0f, 0.2f, 0.3f));
    l1.setCapability(Light.ALLOW_STATE_WRITE);
    l1.setDirection(new Vector3f(5.0f, 5.0f, 1.0f));
    l1.setEnable(true);
    
    DirectionalLight l2 = new DirectionalLight();
    l2.setInfluencingBounds(infinite);
    l2.setColor(new Color3f(0.0f, 0.8f, 1.0f));
    l2.setCapability(Light.ALLOW_STATE_WRITE);
    l2.setDirection(new Vector3f(-5.0f, 5.0f, 1.0f));
    l2.setEnable(true);
    
    DirectionalLight l3 = new DirectionalLight();
    l3.setInfluencingBounds(infinite);
    l3.setColor(new Color3f(0.0f, 1.0f, 0.0f));
    l3.setCapability(Light.ALLOW_STATE_WRITE);
    l3.setDirection(new Vector3f(5.0f, -5.0f, 1.0f));
    l3.setEnable(true);
    
    DirectionalLight l4 = new DirectionalLight();
    l4.setInfluencingBounds(infinite);
    l4.setColor(new Color3f(1.0f, 1.0f, 0.0f));
    l4.setCapability(Light.ALLOW_STATE_WRITE);
    l4.setDirection(new Vector3f(-5.0f, -5.0f, 1.0f));
    l4.setEnable(true);
    
    AmbientLight aL = new AmbientLight();
    aL.setInfluencingBounds(infinite);
    aL.setColor(new Color3f(1.0f, 1.0f, 1.0f));
    aL.setCapability(Light.ALLOW_STATE_WRITE);
    aL.setEnable(true);
    
    // add light to root
    
    tg.addChild(new Shape3D(octahedron, ap));
    root.addChild(rotator);
    root.addChild(l1);
    root.addChild(l2);
    root.addChild(l3);
    root.addChild(l4);
    //root.addChild(aL);
    
    return root;
  }
}