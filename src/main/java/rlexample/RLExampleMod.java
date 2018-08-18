package rlexample;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import net.minecraft.util.ResourceLocation;

import java.io.InputStream;

@Mod(modid = "rlexample", version = "1.0a")
public class RLExampleMod
{
	private static String getTextByResource (ResourceLocation location)
	{
		final int INPUT_BUFFER = 8192;

		// getResourceAsStream()はClassクラスのstaticメソッドを使用するのでどのクラスのものでもいいっぽい?
		InputStream stream = RLExampleMod.class.getResourceAsStream("/assets/" + location.getResourceDomain() + "/" + location.getResourcePath());
		String ret = "";

		try
		{
			int readLength;
			do
			{
				byte[] readBuffer = new byte[INPUT_BUFFER];
				readLength = stream.read(readBuffer);
				ret += new String(readBuffer);
			} while (readLength >= 0);
		}
		catch (Exception e)
		{
			ret = "";
			e.printStackTrace();
		}

		return ret;
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		final ResourceLocation location = new ResourceLocation("rlexample:exresource/Sample.txt");

		String loadedText = getTextByResource(location);
		if (loadedText.length() > 0)
		{
			System.out.println("Loaded Text >> " + loadedText);
		}
		else
		{
			System.out.println("Error.");
		}
	}
}
