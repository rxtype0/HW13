/*
 * Kenny Fung
 * Homework 13 (programming)  
 * Octahedron
 */

import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import java.applet.*;
import com.sun.j3d.utils.applet.MainFrame;

public class TestOctahedron extends Applet {
	public static void main(String[] args) {
		new MainFrame(new TestOctahedron(), 640, 480);
	}

	public void init() {
		// create canvas
		GraphicsConfiguration gc = SimpleUniverse.getPreferredConfiguration();
		Canvas3D cv = new Canvas3D(gc);
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
		TransformGroup spin = new TransformGroup();
		ColoringAttributes ca = new ColoringAttributes();
		Appearance ap = new Appearance();
		spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(spin);
		
		ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
	    ap.setColoringAttributes(ca);
		
		//object
		//ap.setPolygonAttributes(new PolygonAttributes(
				//PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
		//polygon appearance
		PolygonAttributes pa = new PolygonAttributes();
	    pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
	    ap.setPolygonAttributes(pa);
	    
		Shape3D shape = new Shape3D(new Octahedron(), ap);
		
		
		//rotating object
		Transform3D tr = new Transform3D();
		tr.setScale(0.25);
		TransformGroup tg = new TransformGroup(tr);
		spin.addChild(tg);
		tg.addChild(shape);
	    	
		Alpha alpha = new Alpha(-1, 12000);
		RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
		BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
		rotator.setSchedulingBounds(bounds);
		

		/*
		Transform3D yAxis = new Transform3D();
	    Transform3D xAxis = new Transform3D();
	    xAxis.rotX(0);
	    yAxis.add(xAxis);
		
	    Alpha rotationAlpha = new Alpha(-1, 12000);
	    RotationInterpolator rotator = new RotationInterpolator
	    		(rotationAlpha, spin, yAxis, 0.0f, (float) Math.PI * 2.0f);
		
	    //bound the object to scene
	    BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
	    rotator.setSchedulingBounds(bounds);
	    */
	    
	    //material
		Material mat = new Material();
		mat.setLightingEnable(true);
		ap.setMaterial(mat);

		Background background = new Background(1.0f, 1.0f, 1.0f);
	    background.setApplicationBounds(bounds);
	    root.addChild(background);
		
		BoundingSphere infinite = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);


		DirectionalLight l1 = new DirectionalLight();
		l1.setInfluencingBounds(infinite);
		l1.setColor(new Color3f(Color.RED));
		l1.setCapability(Light.ALLOW_STATE_WRITE);
		l1.setDirection(new Vector3f(2.0f, 2.0f, 1.0f));
		l1.setEnable(true);

		DirectionalLight l2 = new DirectionalLight();
		l2.setInfluencingBounds(infinite);
		l2.setColor(new Color3f(Color.BLUE));
		l2.setCapability(Light.ALLOW_STATE_WRITE);
		l2.setDirection(new Vector3f(-2.0f, 2.0f, 1.0f));
		l2.setEnable(true);

		DirectionalLight l3 = new DirectionalLight();
		l3.setInfluencingBounds(infinite);
		l3.setColor(new Color3f(Color.GREEN));
		l3.setCapability(Light.ALLOW_STATE_WRITE);
		l3.setDirection(new Vector3f(2.0f, -2.0f, 1.0f));
		l3.setEnable(true);

		DirectionalLight l4 = new DirectionalLight();
		l4.setInfluencingBounds(infinite);
		l4.setColor(new Color3f(Color.YELLOW));
		l4.setCapability(Light.ALLOW_STATE_WRITE);
		l4.setDirection(new Vector3f(-2.0f, -2.0f, 1.0f));
		l4.setEnable(true);

		AmbientLight aL = new AmbientLight();
		aL.setInfluencingBounds(infinite);
		aL.setColor(new Color3f(1.0f, 1.0f, 1.0f));
		aL.setCapability(Light.ALLOW_STATE_WRITE);
		aL.setEnable(true);

		//spin.addChild(shape);
		
		spin.addChild(rotator);
		root.addChild(l1);
		root.addChild(l2);
		root.addChild(l3);
		root.addChild(l4);
		root.addChild(aL);

		return root;
	}
}