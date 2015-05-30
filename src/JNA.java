import com.sun.jna.*;
import com.sun.jna.win32.StdCallFunctionMapper;

import java.util.Map;
import java.util.HashMap;
import java.lang.reflect.Method;

public class JNA {
// This is the standard, stable way of mapping, which supports extensive
// customization and mapping of Java to native types.

public interface CLibrary extends Library {

	void IndoTTS_Say(String x);
	
	void IndoTTS_IsPlaying();
	
	void IndoTTS_ProsodyON();
}

public static void main(String[] args) {
// CLibrary.INSTANCE.printf("Hello, World\n", new Object[0]);
Map<String, StdCallFunctionMapper> options = new HashMap<String, StdCallFunctionMapper>();

options.put(Library.OPTION_FUNCTION_MAPPER, new StdCallFunctionMapper() 
{
	public String getFunctionName(NativeLibrary library, Method method) 
	{
		if (method.getName().equals("findDevices")) {
			String name = super.getFunctionName(library, method);

			if (name.equalsIgnoreCase("IndoTTS_Say"))
				return "IndoTTS_Say@4";
			if (name.equalsIgnoreCase("IndoTTS_IsPlaying"))
				return "IndoTTS_IsPlaying@4";

			if (name.equalsIgnoreCase("IndoTTS_ProsodyON"))
				return "IndoTTS_ProsodyON@0";

		}

		// do any others
		return super.getFunctionName(library, method);
	}
});

CLibrary lib = (CLibrary) Native.loadLibrary("ITTS_DLL", CLibrary.class, options);
//lib.IndoTTS_Say("Assalamu alaikum ");
while (true) {
//lib.IndoTTS_IsPlaying();
lib.IndoTTS_ProsodyON();
try {
Thread.currentThread().sleep(1500);
}
catch (InterruptedException ex) {
ex.printStackTrace();
}
if (1 == 1)
break;
}

}

}