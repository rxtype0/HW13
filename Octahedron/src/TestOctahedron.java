/*
 * Kenny Fung
 * Luis Banegas
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
		TransformGroup rotate = new TransformGroup();
		ColoringAttributes ca = new ColoringAttributes();
		Appearance ap = new Appearance();
		rotate.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		root.addChild(rotate);

		ca.setShadeModel(ColoringAttributes.SHADE_FLAT);
		ap.setColoringAttributes(ca);

		//object
		//ap.setPolygonAttributes(new PolygonAttributes(
		//PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));

		//polygon appearance
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		ap.setPolygonAttributes(pa);

		// Create shape
		Shape3D shape = new Shape3D(new Octahedron(), ap);


		//rotating object
		Transform3D tr = new Transform3D();
		tr.setScale(0.25);
		TransformGroup tg = new TransformGroup(tr);
		rotate.addChild(tg);
		tg.addChild(shape);

		Alpha alpha = new Alpha(-1, 12000);
		RotationInterpolator rotator = new RotationInterpolator(alpha, rotate);
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

		// Background color
		Background background = new Background(1.0f, 1.0f, 1.0f);
		background.setApplicationBounds(bounds);
		root.addChild(background);

		BoundingSphere infinite = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), Double.MAX_VALUE);

		// Directional Lights
		
		// Top-Right Light
		DirectionalLight l1 = new DirectionalLight();
		l1.setInfluencingBounds(infinite);
		l1.setColor(new Color3f(Color.RED));
		l1.setCapability(Light.ALLOW_STATE_WRITE);
		l1.setDirection(new Vector3f(3.0f, 3.0f, 5.0f));
		l1.setEnable(true);

		// Top-Left Light
		DirectionalLight l2 = new DirectionalLight();
		l2.setInfluencingBounds(infinite);
		l2.setColor(new Color3f(Color.BLUE));
		l2.setCapability(Light.ALLOW_STATE_WRITE);
		l2.setDirection(new Vector3f(-3.0f, 3.0f, 5.0f));
		l2.setEnable(true);

		// Bottom-Right Light
		DirectionalLight l3 = new DirectionalLight();
		l3.setInfluencingBounds(infinite);
		l3.setColor(new Color3f(Color.GREEN));
		l3.setCapability(Light.ALLOW_STATE_WRITE);
		l3.setDirection(new Vector3f(3.0f, -3.0f, 5.0f));
		l3.setEnable(true);

		// Bottom-Left Light
		DirectionalLight l4 = new DirectionalLight();
		l4.setInfluencingBounds(infinite);
		l4.setColor(new Color3f(Color.YELLOW));
		l4.setCapability(Light.ALLOW_STATE_WRITE);
		l4.setDirection(new Vector3f(-3.0f, -3.0f, 5.0f));
		l4.setEnable(true);

		// Point Lights
		
		// Center-Bottom Light
		PointLight p1 = new PointLight(new Color3f(Color.CYAN),
				new Point3f(0f, -3.0f, 1f), new Point3f(0.4f, 0.3f, 0.3f));
		p1.setInfluencingBounds(bounds);

		// Center-Top Light
		PointLight p2 = new PointLight(new Color3f(Color.MAGENTA),
				new Point3f(0f, 3.0f, 1f), new Point3f(0.4f, 0.3f, 0.3f));
		p2.setInfluencingBounds(bounds);
		
		// Right-Top Light
		PointLight pl1 = new PointLight(new Color3f(Color.GREEN),
				new Point3f(5.0f, 5.0f, 1.0f), new Point3f(0.1f, 0.1f, 0.1f));
		pl1.setInfluencingBounds(bounds);
		
		// Left-Top Light
		PointLight pl2 = new PointLight(new Color3f(Color.CYAN),
				new Point3f(-5.0f, 5.0f, 1.0f), new Point3f(0.1f, 0.1f, 0.1f));
		pl2.setInfluencingBounds(bounds);
		
		// Right-Bottom Light
		PointLight pl3 = new PointLight(new Color3f(Color.BLUE),
				new Point3f(5.0f, -5.0f, 1.0f), new Point3f(0.1f, 0.1f, 0.1f));
		pl3.setInfluencingBounds(bounds);
		
		// Left-Bottom Light
		PointLight pl4 = new PointLight(new Color3f(Color.RED),
				new Point3f(-5.0f, -5.0f, 1.0f), new Point3f(0.1f, 0.1f, 0.1f));
		pl4.setInfluencingBounds(bounds);

		// Ambient Lights
	    AmbientLight aL = new AmbientLight(true, new Color3f(Color.PINK));
	    aL.setInfluencingBounds(bounds);


		/*
		PointLight ptlight = new PointLight(new Color3f(Color.green),
				new Point3f(3f,3f,3f), new Point3f(1f,0f,0f));
		ptlight.setInfluencingBounds(bounds);
		root.addChild(ptlight);

		PointLight ptlight2 = new PointLight(new Color3f(Color.yellow),
				new Point3f(-2f,2f,2f), new Point3f(1f,0f,0f));
		ptlight2.setInfluencingBounds(bounds);
		root.addChild(ptlight2);
		 */

		//spin.addChild(shape);

		rotate.addChild(rotator);
		root.addChild(aL);
		root.addChild(l1);
		root.addChild(l2);
		root.addChild(l3);
		root.addChild(l4);
		root.addChild(p1);
		root.addChild(p2);
		root.addChild(pl1);
		root.addChild(pl2);
		root.addChild(pl3);
		root.addChild(pl4);

		return root;
	}
}