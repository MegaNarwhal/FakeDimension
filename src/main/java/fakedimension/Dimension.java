package fakedimension;

import com.comphenix.protocol.utility.MinecraftReflection;
import org.bukkit.World.Environment;

public enum Dimension {

	NORMAL("OVERWORLD"),
	NETHER("THE_NETHER"),
	END("THE_END");

	private final Object keyDimension;
	private final Object keyWorld;

	Dimension(String fieldName){
		try{
			this.keyDimension = getKeyDimension(fieldName);
			this.keyWorld = getKeyWorld(fieldName);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}

	public Object getKeyDimension(){
		return keyDimension;
	}

	public Object getKeyWorld(){
		return keyWorld;
	}

	private static Object getKeyDimension(String fieldName) throws Exception{
		return getStaticField(MinecraftReflection.getMinecraftClass("DimensionManager"),fieldName);
	}

	private static Object getKeyWorld(String fieldName) throws Exception{
		return getStaticField(MinecraftReflection.getMinecraftClass("World"),fieldName);
	}

	private static Object getStaticField(Class<?> clazz,String fieldName) throws Exception{
		return clazz.getField(fieldName).get(null);
	}

	public static Dimension getByBukkit(Environment env) {
		switch (env) {
			case NORMAL: {
				return NORMAL;
			}
			case THE_END: {
				return END;
			}
			case NETHER: {
				return NETHER;
			}
			default: {
				throw new IllegalArgumentException("Unknown dimension " + env);
			}
		}
	}

}
