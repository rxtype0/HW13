/*
 * Kenny Fung
 * Luis Banegas
 * Homework 15 (programming)  
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

public class TestColorOctahedron extends Applet {
	public static void main(String[] args) {
		new MainFrame(new TestColorOctahedron(), 640, 480);
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

		// Shade Gouraud
		ca.setShadeModel(ColoringAttributes.SHADE_GOURAUD);
		ap.setColoringAttributes(ca);

		/*
		object
		ap.setPolygonAttributes(new PolygonAttributes(
		PolygonAttributes.POLYGON_FILL, PolygonAttributes.CULL_NONE, 0));
		polygon appearance
		PolygonAttributes pa = new PolygonAttributes();
		pa.setPolygonMode(PolygonAttributes.POLYGON_FILL);
		ap.setPolygonAttributes(pa);
		 */

		// Creating shape
		Shape3D shape = new Shape3D(new ColorOctahedron(), ap);


		//rotating object
		Transform3D tr = new Transform3D();
		tr.setScale(0.25);
		TransformGroup tg = new TransformGroup(tr);
		rotate.addChild(tg);
		tg.addChild(shape);

		Alpha alpha = new Alpha(-1, 12000);
		RotationInterpolator rotator = new RotationInterpolator(alpha, rotate);
		BoundingSphere bounds = new BoundingSphere();
		rotator.setSchedulingBounds(bounds);

		// Background color.
		Background background = new Background(1.0f, 1.0f, 1.0f);
		background.setApplicationBounds(bounds);
		root.addChild(background);

		rotate.addChild(rotator);

		return root;
	}

}