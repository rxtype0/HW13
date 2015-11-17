

import javax.vecmath.*;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
import javax.media.j3d.*;

public class Octahedron extends IndexedTriangleArray {
	public Octahedron() {

	    super(6, TriangleArray.COORDINATES | TriangleArray.NORMALS, 24);
	    setCoordinate(0, new Point3f(0.0f, 0.0f, 1.0f));
	    setCoordinate(1, new Point3f(0.0f, 0.0f, -1.0f));
	    setCoordinate(2, new Point3f(1.0f, 0.0f, 0.0f));
	    setCoordinate(3, new Point3f(-1.0f, 0.0f, 0.0f));
	    setCoordinate(4, new Point3f(0.0f, 1.0f, 0.0f));
	    setCoordinate(5, new Point3f(0.0f, -1.0f, 0.0f));
	    int[] coords = {0,2,4,0,4,3,0,3,5,0,5,2,1,2,5,1,5,3,1,3,4,1,4,2};
	    float n = (float)(Math.PI / 4);
	    
	    GeometryInfo g = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);
	    NormalGenerator ng = new NormalGenerator();
	    ng.setCreaseAngle(Math.PI / 4);
	    ng.generateNormals(g);
	    
	    setNormal(0, new Vector3f(n,n,-n));
	    setNormal(1, new Vector3f(n,-n,n));
	    setNormal(2, new Vector3f(-n,-n,-n));
	    setNormal(3, new Vector3f(-n,n,n));
	    setNormal(4, new Vector3f(n,n,-n));
	    setNormal(5, new Vector3f(n,-n,n));
	    int[] norms = {0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,1,1,1,2,2,2};
	    setCoordinateIndices(0, coords);
	    setNormalIndices(0, norms);

	}
}