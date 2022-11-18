package exhiber.type;

import arc.Core;
import arc.graphics.Color;
import mindustry.content.StatusEffects;
import mindustry.ctype.ContentType;
import mindustry.ctype.UnlockableContent;
import mindustry.type.StatusEffect;
import mindustry.world.meta.Stat;

public class Forces extends UnlockableContent {
    public Color color;
    public float temperature;
    public float flammability;
    public float explosiveness;
    public StatusEffect effect = StatusEffects.none;

    public Forces(String name, Color color) {
        super(name);
        this.color = color;
    }

    public Forces(String name) {
        this(name, new Color(Color.black));
    }

    @Override
    public void setStats() {
        stats.addPercent(Stat.temperature, temperature);
        stats.addPercent(Stat.flammability, flammability);
        stats.addPercent(Stat.explosiveness, explosiveness);
    }

    @Override
    public void loadIcon() {
        fullIcon = Core.atlas.find(name);
        uiIcon = Core.atlas.find(localizedName, fullIcon);
    }

    @Override
    public ContentType getContentType() {
        return ContentType.ammo_UNUSED;
    }
}
