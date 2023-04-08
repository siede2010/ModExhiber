package exhiber.world.block.effect;

import arc.Core;
import arc.graphics.g2d.Draw;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.graphics.g2d.TextureRegion;
import arc.math.Mathf;
import mindustry.content.Fx;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.gen.Building;
import mindustry.gen.Groups;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.logic.Ranged;
import mindustry.type.StatusEffect;
import mindustry.world.Block;

public class StatusEffectBlock extends Block {
    public float radius = 80;
    public StatusEffect statusEffect = StatusEffects.none;
    public float statusEffectDuration = 60;
    public Effect unitEffect = Fx.none;
    public TextureRegion glowRegion;
    public float interval = 30;
    public float consumeTime = 60;
    public Effect consumeEffect = Fx.none;
    public StatusEffectBlock(String name)
    {
        super(name);
        update = true;

    }
    @Override
    public void drawPlace(int x,int y,int rotation,boolean placeable)
    {
        super.drawPlace(x,y,rotation,placeable);
        Drawf.dashCircle(x*8,y*8,radius, Pal.lighterOrange);
    }
    @Override
    public void init()
    {
        updateClipRadius(radius);
        super.init();
        glowRegion = Core.atlas.find(name+"-glow");
    }

    public class StatusEffectBuild extends Building implements Ranged {
        float waveEffect = 0;
        float prog = 0;
        float consume = 0;
        @Override
        public void updateTile()
        {
            waveEffect = Mathf.lerpDelta(waveEffect,1.1f,0.02f);
            if (efficiency > 0)
            {
                waveEffect %= 1;
                prog = Mathf.approachDelta(prog,interval,1);
                consume = Mathf.approachDelta(consume,consumeTime,1);
                if (interval <= prog) {
                    Groups.unit.intersect(x - radius, y - radius, radius * 2, radius * 2, u -> {
                        if (u.team == team && Mathf.len(u.x - x, u.y - y) <= radius) {
                            u.apply(statusEffect, statusEffectDuration);
                            unitEffect.at(u.x, u.y);
                        }});
                    prog %= interval;
                }
                if (consumeTime <= consume)
                {
                    consume();
                    consumeEffect.at(x,y);
                    consume %= consumeTime;
                }
            }
        }

        @Override
        public void draw()
        {
            super.draw();
            if (efficiency > 0) {
                Draw.z(Layer.blockBuilding + 1);
                Lines.stroke((1 - waveEffect) * 2, Pal.lighterOrange);
                Lines.circle(x, y, radius * waveEffect);
                Draw.color(team.color);
                Draw.alpha(Math.abs((waveEffect-0.5f)*2));
                Draw.rect(glowRegion, x, y);
                Draw.color(team.color);
                Draw.z(Layer.shields+2.5f);
                Fill.circle(x, y, radius);
            }
        }

        @Override
        public float range() {
            return radius*efficiency;
        }
    }
}
