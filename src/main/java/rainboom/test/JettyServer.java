package rainboom.test;


import org.apache.camel.main.Main;

public class JettyServer {

    public static void main ( String[] args ) throws Exception {
        System.out.println (args[0] );
        Long multiplier;
        try {
            multiplier = Long.parseLong ( args[ 0 ] );
        } catch ( Exception e ) {
            throw new RuntimeException ( "Multiplier format is not valid or multiplier not present" );
        }
        var main = new Main ( );
        main.addRouteBuilder ( new JettyRouteBuilder ( multiplier ) );
        main.run ( );
    }

}
