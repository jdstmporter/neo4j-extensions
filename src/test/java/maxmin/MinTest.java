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
public class MinTest 
{
	@Rule
	public Neo4jRule neo4j = new Neo4jRule().withFunction( Min.class );
	
	@Test
	public void findTheMinimum() throws Throwable {
		BinaryOperator<Double> f=(Double x,Double y) -> Math.min(x, y);
		BasicTest tester=new BasicTest("RETURN maxmin.min(%f,%f) AS result",f);
        assertTrue(tester.allTests(neo4j));
	}
	

	
}
