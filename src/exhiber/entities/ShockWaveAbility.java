package exhiber.entities;

import arc.Events;
import arc.audio.Sound;
import arc.graphics.Color;
import arc.math.Angles;
import arc.struct.Seq;
import arc.util.Time;
import mindustry.Vars;
import mindustry.entities.Effect;
import mindustry.entities.Units;
import mindustry.entities.abilities.StatusFieldAbility;
import mindustry.game.EventType;
import mindustry.game.Team;
import mindustry.gen.Bullet;
import mindustry.gen.Groups;
import mindustry.gen.Unit;
import mindustry.type.StatusEffect;

public class ShockWaveAbility extends StatusFieldAbility {
    public Seq<Bullet> targets = new Seq<>();
    int shake = 1;
    int bulletDamage = 50;
    int falloffCount = 5;
    public Color waveColor;
    public ShockWaveAbility(StatusEffect effect, float duration, float reload, float range)
    {
        super(effect,duration,reload,range);
    }

    @Override
    public void update(Unit unit){
        timer += Time.delta;

        if(timer >= reload && (!onShoot || unit.isShooting)){
            targets.clear();
            Groups.bullet.intersect(unit.x - range, unit.y - range, range * 2, range * 2, b -> {
                if(b.team != unit.team && b.type.hittable){
                    targets.add(b);
                }
            });

            if(targets.size > 0){
                activeEffect.at(unit.x, unit.y, range, waveColor);
                Effect.shake(shake, shake, unit);
                float waveDamage = Math.min(bulletDamage, bulletDamage * falloffCount / targets.size);

                for(var target : targets){
                    if(target.damage > waveDamage){
                        target.damage -= waveDamage;
                    }else{
                        target.remove();
                    }
                    activeEffect.at(target.x, target.y, effectSizeParam ? range : unit.rotation, parentizeEffects ? unit : null);
                }
            }
        }
    }
}
