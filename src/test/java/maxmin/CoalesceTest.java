package maxmin;

import static org.junit.Assert.assertTrue;

import java.util.function.BinaryOperator;

import org.junit.Rule;
import org.junit.Test;
import org.neo4j.harness.junit.Neo4jRule;

import maxmin.util.BasicTest;

public class CoalesceTest {
	@Rule
	public Neo4jRule neo4j = new Neo4jRule().withFunction( Coalesce.class );
	    
	@Test
	public void findTheCoalescence() throws Throwable {
		BinaryOperator<Double> f=(Double x,Double y) -> (x==null) ? y : x;
		BasicTest tester=new BasicTest("RETURN maxmin.coalesce(%f,%f) AS result",f);
        assertTrue(tester.allTests(neo4j));
	}
	
}
