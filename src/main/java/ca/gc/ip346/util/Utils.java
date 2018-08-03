package ca.gc.ip346.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import org.apache.commons.lang.StringUtils;


public class Utils {
	public static String toHtml( String string )
	  {
	    if( StringUtils.isEmpty(string ) )
	      return "";

	    BufferedReader st = new BufferedReader( new StringReader( string ) );
	    StringBuffer buf = new StringBuffer( "" );

	    try
	    {
	      String str = st.readLine();

	      while( str != null )
	      {
	        if( str.equalsIgnoreCase( "<br/>" ) )
	        {
	          str = "<br>";
	        }

	        buf.append( str );

	        if( !str.equalsIgnoreCase( "<br>" ) )
	        {
	          buf.append("<br>");
	        }

	        str = st.readLine();
	      }
	    }
	    catch( IOException e )
	    {
	      e.printStackTrace();
	    }

	    
	    string = buf.toString();
	    return string;
	  }

}
