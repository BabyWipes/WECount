package code.husky;

import java.util.HashMap;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public class WECount extends JavaPlugin implements Listener {

	HashMap<String, Location> lc = new HashMap<String, Location>();
	HashMap<String, Location> lc2 = new HashMap<String, Location>();
	int x,y,z,x1,y1,z1;

	public void onEnable() {
		this.getServer().getPluginManager().registerEvents(this,this);
	}

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack iteminhand = p.getItemInHand();
		if(iteminhand.getType() == Material.WOOD_AXE) {
			if(p.hasPermission("admin.select")) {
				if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
					lc.put(p.getName(), e.getClickedBlock().getLocation());
					e.setCancelled(true);
				} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					lc2.put(p.getName(), e.getClickedBlock().getLocation());
					e.setCancelled(true);
				}
				if(lc.get(p.getName()) != null && lc2.get(p.getName()) != null) {
					x = lc.get(p.getName()).getBlockX();
					y = lc.get(p.getName()).getBlockY();
					z = lc.get(p.getName()).getBlockZ();
					x1 = lc2.get(p.getName()).getBlockX();
					y1 = lc2.get(p.getName()).getBlockY();
					z1 = lc2.get(p.getName()).getBlockZ();
					p.sendMessage(ChatColor.GREEN + "[WECount] " + ChatColor.RED + "Amount of blocks " + getBlocks());
					p.sendMessage(ChatColor.GREEN + "[WECount] " + ChatColor.RED + "Amount to remove " + (getBlocks() * 0.20));
					p.sendMessage(ChatColor.GREEN + "[WECount] " + ChatColor.RED + "Amount to add " + (getBlocks() * 0.50));
				}
			}
		}
	}

	public int getMaxX() {
		return Math.max(x,x1);
	}

	public int getMinX() {
		return Math.min(x,x1);
	}

	public int getMaxY() {
		return Math.max(y, y1);
	}

	public int getMinY() {
		return Math.min(y,y1);
	}

	public int getMaxZ() {
		return Math.max(z,z1);
	}

	public int getMinZ() {
		return Math.min(z,z1);
	}

	public int getBlocks() {
		int fina = 0;
		for (int x = this.getMinX(); x <= this.getMaxX(); x++) {
			for (int y = this.getMinY(); y <= this.getMaxY(); y++) {
				for (int z = this.getMinZ(); z <= this.getMaxZ(); z++) {
					fina++;
				}
			}
		}
		return fina;
	}

}
