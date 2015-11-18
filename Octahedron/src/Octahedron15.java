/*
 * Kenny Fung
 * Luis Banegas
 * Homework 15 (programming)  
 * Octahedron
 */

import javax.vecmath.*;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import javax.media.j3d.*;

public class Octahedron15 extends IndexedTriangleArray {
	public Octahedron15() {

		super(8, GeometryArray.COORDINATES | 
				GeometryArray.NORMALS | GeometryArray.COLOR_3, 24);
		
		// Vertices.
		setCoordinate(0, new Point3f(0,0,1));
		setCoordinate(1, new Point3f(-1,0,0));
		setCoordinate(2, new Point3f(0,-1,0));
		setCoordinate(3, new Point3f(1,0,0));
		setCoordinate(4, new Point3f(0,1,0));
		setCoordinate(5, new Point3f(0,0,-1));

		// Coordinates Indices
		int[] coords = {0,1,2,0,2,3,0,3,4,0,4,1,
				5,1,4,5,4,3,5,3,2,5,2,1};

		float n = 1f/(float)Math.sqrt(3);
		
		// Normals
		setNormal(0, new Vector3f(-n,-n,n));
		setNormal(1, new Vector3f(n,-n,n));
		setNormal(2, new Vector3f(n,n,n));
		setNormal(3, new Vector3f(-n,n,n));
		setNormal(4, new Vector3f(-n,n,-n));
		setNormal(5, new Vector3f(n,n,-n));
		setNormal(6, new Vector3f(n,-n,-n));
		setNormal(7, new Vector3f(-n,-n,-n));
		
		// Normal Indices
		int[] norms = {0,0,0,1,1,1,2,2,2,3,3,3,
				4,4,4,5,5,5,6,6,6,7,7,7};

		// Setting Coordinates and Normal Indices
		setCoordinateIndices(0, coords);
		setNormalIndices(0,norms);

		// Setting Color and the indice for color.
		setColor(0, new Color3f(0,0,1));
		setColor(1, new Color3f(0,1,0));
		setColor(2, new Color3f(1,0,0));
		setColor(3, new Color3f(1,0,1));
		setColor(4, new Color3f(0,1,1));
		setColor(5, new Color3f(1,0,1));
		this.setColorIndices(0, coords);
	}
}