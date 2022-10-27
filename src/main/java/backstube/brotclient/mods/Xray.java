package backstube.brotclient.mods;

import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import java.util.Arrays;
import java.util.HashSet;

public class Xray {


    private static boolean enabled;
    public KeyBinding keybind;
    public SimpleOption defaultGamma;

    private static HashSet<String> xRayBlocks = new HashSet<String>(Arrays.asList(
        "Block{minecraft:water}",
        "Block{minecraft:lava}",
        "Block{minecraft:chest}",
        "Block{minecraft:trapped_chest}",
        "Block{minecraft:shulker_box}",
        "Block{minecraft:white_shulker_box}",
        "Block{minecraft:orange_shulker_box}",
        "Block{minecraft:magenta_shulker_box}",
        "Block{minecraft:light_blue_shulker_box}",
        "Block{minecraft:yellow_shulker_box}",
        "Block{minecraft:lime_shulker_box}",
        "Block{minecraft:pink_shulker_box}",
        "Block{minecraft:gray_shulker_box}",
        "Block{minecraft:light_gray_shulker_box}",
        "Block{minecraft:cyan_shulker_box}",
        "Block{minecraft:purple_shulker_box}",
        "Block{minecraft:blue_shulker_box}",
        "Block{minecraft:brown_shulker_box}",
        "Block{minecraft:green_shulker_box}",
        "Block{minecraft:red_shulker_box}",
        "Block{minecraft:black_shulker_box}",
        "Block{minecraft:furnace}",
        "Block{minecraft:blast_furnace}",
        "Block{minecraft:smoker}",
        "Block{minecraft:dropper}",
        "Block{minecraft:dispenser}",
        "Block{minecraft:hopper}",
        "Block{minecraft:ancient_debris}",
        "Block{minecraft:glowstone}",
        "Block{minecraft:nether_quartz_ore}",
        "Block{minecraft:nether_gold_ore}",
        "Block{minecraft:coal_ore}",
        "Block{minecraft:deepslate_coal_ore}",
        "Block{minecraft:iron_ore}",
        "Block{minecraft:deepslate_iron_ore}",
        "Block{minecraft:gold_ore}",
        "Block{minecraft:deepsalte_gold_ore}",
        "Block{minecraft:diamond_ore}",
        "Block{minecraft:deepslate_diamond_ore}",
        "Block{minecraft:redstone_ore}",
        "Block{minecraft:deepslate_redstone_ore}",
        "Block{minecraft:lapis_ore}",
        "Block{minecraft:deepslate_lapis_ore}",
        "Block{minecraft:emerald_ore}",
        "Block{minecraft:deepslate_emerald_ore}"
        ));




    public static boolean shouldSideBeDrawn(BlockState pstate) {
        if(xRayBlocks.contains(pstate.getBlock().toString())) {return true;}
        return false;
    }


    public void onEnable() {

    }

    public void onDisable() {
        //MinecraftClient.getInstance().options.gamma = defaultGamma;
    }

    public void onToggle() {
        MinecraftClient.getInstance().player.sendMessage(Text.literal("X-Ray " + isEnabled()), false);
        MinecraftClient.getInstance().worldRenderer.reload();
    }

    public static boolean isEnabled() {return enabled;}

    public void toggle() {
        enabled = !enabled;
        onToggle();
        if(enabled) {onEnable();}
        else {onDisable();}
    }
}