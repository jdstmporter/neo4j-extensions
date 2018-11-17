package maxmin;

import org.junit.Rule;
import org.junit.Test;

import org.neo4j.harness.junit.Neo4jRule;
import maxmin.util.BasicTest;
import static org.junit.Assert.assertTrue;
import java.util.function.BinaryOperator;


/**
 * Unit test for simple App.
 */
public class MaxTest 
{
	@Rule
	public Neo4jRule neo4j = new Neo4jRule().withFunction( Max.class );
	    
	@Test
	public void findTheMaximum() throws Throwable {
		BinaryOperator<Double> f=(Double x,Double y) -> Math.max(x, y);
		BasicTest tester=new BasicTest("RETURN maxmin.max(%f,%f) AS result",f);
        assertTrue(tester.allTests(neo4j));
	}
	
}
