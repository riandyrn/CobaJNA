import java.util.HashMap;
import java.util.Map;

import com.sun.jna.Library;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Platform;
import com.sun.jna.Pointer;
import com.sun.jna.win32.StdCallFunctionMapper;
import java.lang.reflect.Method;

/* JNA example by Michal Wrobel
 * go to www.blog.mwrobel.eu to find out more
 */




/** Simple example of native library declaration and usage. */
public class Main 
{
    public interface ITTS_DLL extends Library {
    	
    	void IndoTTS_Say(String x);
    	
    	void IndoTTS_IsPlaying();
    	
    	void IndoTTS_ProsodyON();
    	
    }

    public static void main(String[] args) {
    	
    	Map<String, StdCallFunctionMapper> options = new HashMap<>();
    	
    	options.put(Library.OPTION_FUNCTION_MAPPER, new StdCallFunctionMapper(){
    		public String getFunctionName(NativeLibrary library, Method method)
    		{
    			if(method.getName().equals("findDevices"))
    			{
    				String name = super.getFunctionName(library, method);
    				
    				if(name.equalsIgnoreCase("IndoTTS_Say"))
    					return "IndoTTS_Say@4";
    				if(name.equalsIgnoreCase("IndoTTS_IsPlaying"))
    					return "IndoTTS_IsPlaying@4";
    				if(name.equalsIgnoreCase("IndoTTS_ProsodyON"))
    					return "IndoTTS_ProsodyON@0";
    			}
    			
				return super.getFunctionName(library, method);
    			
    		}
    	});
    	
    	ITTS_DLL itts = (ITTS_DLL) Native.loadLibrary("ITTS_DLL", ITTS_DLL.class, options);
    	
    }      
}