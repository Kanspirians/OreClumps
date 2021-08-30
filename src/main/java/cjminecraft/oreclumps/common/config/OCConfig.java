package cjminecraft.oreclumps.common.config;

import com.google.common.collect.Lists;
import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

public class OCConfig {

    // todo language file for config

    public static class Server {
        public final ForgeConfigSpec.ConfigValue<List<String>> ironOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> goldOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> copperOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> leadOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> nickelOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> silverOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> tinOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> uraniumOres;
        public final ForgeConfigSpec.ConfigValue<List<String>> osmiumOres;


        Server(ForgeConfigSpec.Builder builder) {
            builder.comment("Server configuration")
                    .push("server");

            this.ironOres = defineOreList(builder, "iron", Lists.newArrayList("minecraft:iron_ore"));
            this.goldOres = defineOreList(builder, "gold", Lists.newArrayList("minecraft:gold_ore"));
            this.copperOres = defineOreList(builder, "copper", Lists.newArrayList("thermal:copper_ore", "mekanism:copper_ore", "occultism:copper_ore", "create:copper_ore"));
            this.leadOres = defineOreList(builder, "lead", Lists.newArrayList("thermal:lead_ore", "mekanism:lead_ore", "eidolon:lead_ore"));
            this.nickelOres = defineOreList(builder, "nickel", Lists.newArrayList("thermal:nickel_ore"));
            this.silverOres = defineOreList(builder, "silver", Lists.newArrayList("thermal:silver_ore", "occultism:silver_ore"));
            this.tinOres = defineOreList(builder, "tin", Lists.newArrayList("thermal:tin_ore", "mekanism:tin_ore"));
            this.uraniumOres = defineOreList(builder, "uranium", Lists.newArrayList("mekanism:uranium_ore"));
            this.osmiumOres = defineOreList(builder, "osmium", Lists.newArrayList("mekanism:osmium_ore"));

            // todo zinc from create

            builder.pop();
        }

        private ForgeConfigSpec.ConfigValue<List<String>> defineOreList(ForgeConfigSpec.Builder builder, String name, List<String> defaultValue) {
            return builder
                    .comment("Set this to the list of ores that should drop raw " + name)
                    .translation("oreclumps.configgui." + name + "Ores")
                    .worldRestart()
                    .define(name + "Ores", defaultValue);
        }
    }

    public static final ForgeConfigSpec SERVER_SPEC;
    public static final Server SERVER;

    static {
        final Pair<OCConfig.Server, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(OCConfig.Server::new);
        SERVER_SPEC = specPair.getRight();
        SERVER = specPair.getLeft();
    }

}
