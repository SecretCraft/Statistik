package de.secretcraft.statistik;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Helper {
		
	public static void playerMessageNoPermission( Player player, String permission ) {
		player.sendMessage( ChatColor.GOLD + "Du hast keine Berechtigung dies zu tun. Permission : " + permission );
	}
	
	public static void log( String message ) {
		
		Logger.getLogger("Minecraft").info( "[Statistik] " + message );
		
	}
	
	public static List<String> fromArray(String... values)
    {
        List<String> results = new ArrayList<String>();
        Collections.addAll(results, values);
        results.remove("");
        return results;
    }
	
	public static String[] toArray(List<String> list)
    {
        return list.toArray(new String[list.size()]);
    }
	
	public static String[] removeFirst(String[] args)
    {
        List<String> out = fromArray(args);

        if (!out.isEmpty())
        {
            out.remove(0);
        }
        return toArray(out);
    }
	
	public static String format(String string) {
	    String s = string;
	    for (ChatColor color : ChatColor.values()) {
	        s = s.replaceAll("(?i)<" + color.name() + ">", "" + color);
	    }
	    return s;
	}
	
}
