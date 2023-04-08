package exhiber.content;

import arc.Core;
import arc.graphics.Color;
import exhiber.entities.EnemyStatusFieldAbility;
import exhiber.world.statusEffect.AlterStatusEffect;
import exhiber.world.statusEffect.ComponentEffect;
import exhiber.world.statusEffect.StatusEffectStack;
import mindustry.content.StatusEffects;
import mindustry.entities.Effect;
import mindustry.entities.abilities.ForceFieldAbility;
import mindustry.type.StatusEffect;

public class EXStatusEffects {
    public static StatusEffect
        scabbed,detained,ballistic,protectivePlating,advancedPlating,crunched,infrafluxed
            , slowing , polishHit,cheronHit,antiScabbed,leaderShip,
            componentShield,componentFreeze,componentBrute,componentSpeed
            ;

        public static void EXload(){
            scabbed = new StatusEffect("scabbed"){{
                localizedName = "Scabbed";
                description = "The rain of the Diamond debris has Gotten into your inner circuitry. Ouch!";
                reloadMultiplier = 0.8f;
                damage = 0.0666667f;
                speedMultiplier = 0.9f;
                fullIcon = Core.atlas.find("scabbed");
                uiIcon = Core.atlas.find("scabbed");
                init(() -> {
                    opposite(protectivePlating);
                });
            }};
            detained = new StatusEffectStack("detained"){{
                localizedName = "Detained";
                description = "Being Detained Will lower the movement speed the more often it happends.";
                speedMultiplier = 0.9f;
                reloadMultiplier = 0.9f;
                buildSpeedMultiplier = 0.9f;
                damage = 1.11111111111112f/60f;
                charges = 9;
                staticStat();
            }};
            ballistic = new StatusEffect("ballistic"){{
                localizedName = "Ballistic";
                description = "The Ammunition has the Side effect of boosting the inner circuitry. in addition to damaging it greatly.";
                reloadMultiplier = 1.2f;
                damageMultiplier = 1.2f;
                damage = 0.025f;
            }};
            protectivePlating = new StatusEffect("protective-plating"){{
                localizedName = "Protecting Plating";
                description = "small temporary Plating that Protects from minor injury's and Diamond Rain";
                healthMultiplier = 1.1f;
                init(() -> {
                    opposite(scabbed);
                });
            }};
            advancedPlating = new AlterStatusEffect("advanced-plating"){{
                localizedName = "Advanced Plating";
                description = "More advanced Plating that Also Protects the unit From Freezing and Burning";
                healthMultiplier = 1.25f;
                buildSpeedMultiplier = 1.2f;
                speedMultiplier = 1.1f;
                afterStatusEffect = protectivePlating;
                afterStatusEffectDuration = 5*60;
                init(() -> {
                    opposite(scabbed,StatusEffects.burning,StatusEffects.freezing);
                });
            }};
            crunched = new StatusEffect("crunched"){{
                localizedName = "Crunched";
                healthMultiplier = 1f/1.6f;
                init(() -> {
                    affinity(StatusEffects.blasted,(unit,result,time) -> {
                        unit.damagePierce(120);
                    });
                        });
            }};
            slowing = new StatusEffect("slowing"){{
                localizedName = "Slowing";
                show = false;
                speedMultiplier = 0.3333f;
                dragMultiplier = 0.01f;
            }};
            infrafluxed = new StatusEffect("infrafluxed"){{
                localizedName = "Infrafluxed";
                speedMultiplier = 0.2f;
                reloadMultiplier = 0.2f;
            }};
            polishHit = new StatusEffect("polish-hit"){{
                localizedName = "Polish Hit";
                show = false;
                speedMultiplier = 0.2f;
                dragMultiplier = 0.02f;
            }};
            cheronHit = new StatusEffect("cheron-hit"){{
                localizedName = "Cheron Hit";
                show = false;
                speedMultiplier = 0.2f;
                dragMultiplier = 2;
            }};
            antiScabbed = new StatusEffect("anti-scabbed"){{
                localizedName = "Anti Scabbed";
                show = false;
                init(() -> {
                    opposite(scabbed);
                });
            }};
            leaderShip = new StatusEffectStack("leader-ship")
            {{
                localizedName = "Leader Ship";
                description = "Commanded by a Commanding unit capable of stopping anything in their tracks boosts the functionality of any unit near it.";
                speedMultiplier = 1.01f;
                reloadMultiplier = 1.01f;
                buildSpeedMultiplier = 1.01f;
                healthMultiplier = 1.01f;
                damage = -0.1f/60f;
                charges = 20;
                staticStat();
            }};
            componentShield = new ComponentEffect("component-shield")
            {{
                localizedName = "Component: Shield";
                description = ("an addon to any unit that gives it a small and compact shield");
                abilities.add(new ForceFieldAbility(8*3,5f/60f,100,10*60));
            }};
            componentFreeze = new ComponentEffect("component-freeze")
            {{
                localizedName = "Component: Freeze";
                description = ("an addon to any unit that freezes nearby enemy units and gives the unit host extra armor");
                healthMultiplier = 2f;
                speedMultiplier = 0.8f;
                reloadMultiplier = 0.8f;
                abilities.add(new EnemyStatusFieldAbility(StatusEffects.freezing,180,120,8*12));
            }};
            componentBrute = new ComponentEffect("component-brute")
            {{
                localizedName = "Component: Brute";
                description = ("an addon to any unit that sharpens their armor and weapons but makes them more vulnerable.");
                damageMultiplier = 1.5f;
                healthMultiplier = 0.8f;
            }};
            componentSpeed = new ComponentEffect("component-speed")
            {{
                localizedName = "Component: Speed";
                description = ("an addon to any unit that prioritizes speed than making shure less damage is taken. harder time slowing down due to this.");
                speedMultiplier = 1.4f;
                healthMultiplier = 0.8f;
                dragMultiplier = 0.5f;
            }};
        }
}
