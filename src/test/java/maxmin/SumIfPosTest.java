package maxmin;

import org.junit.Rule;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.neo4j.harness.junit.Neo4jRule;
import java.util.List;
import java.util.stream.Collectors;


import maxmin.util.Randomise;

import org.neo4j.driver.v1.*;

public class SumIfPosTest {
	public static double bound=100.0;
	public static double error=1.0e-9;
	
	@Rule
    public Neo4jRule neo4j = new Neo4jRule().withAggregationFunction( SumIfPositive.class );

	public static boolean equal(double x,double y) {
		double diff=Math.abs(x-y);
		return diff<error*bound;
	}
	
	@Test
	public void doesTheSumRight() throws Throwable {
		try( Driver driver = GraphDatabase.driver( neo4j.boltURI() , Config.build().withEncryptionLevel( Config.EncryptionLevel.NONE ).toConfig() ) )
        {
            Session session = driver.session();
            List<Double> inputs=Randomise.makeSymmetricList(100, 1000.0);
            List<String> strings=inputs.stream().map(n -> n.toString()).collect(Collectors.toList());
            String list=String.join(",", strings);
            String command=String.format("UNWIND [%s] AS number RETURN maxmin.sumIfPos(number) AS result", list);
            Double result = session.run(command).single().get("result").asDouble();
            
            double predicted=inputs.stream().filter(d -> d>0).reduce(0.0, (s,e) -> s+e);

            // Then
            assertTrue(equal(predicted,result));
        }
	}
}
