package exhiber.content;

import arc.graphics.*;
import arc.struct.*;
import exhiber.entities.EnemyStatusFieldAbility;
import mindustry.ai.types.*;
import mindustry.content.*;
import mindustry.entities.abilities.*;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.*;
import mindustry.entities.pattern.ShootAlternate;
import mindustry.entities.pattern.ShootHelix;
import mindustry.entities.pattern.ShootMulti;
import mindustry.entities.pattern.ShootSpread;
import mindustry.gen.*;
import mindustry.graphics.*;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.type.weapons.*;
import mindustry.content.UnitTypes;
import mindustry.world.meta.Stat;

public class EXUnits{
    public static UnitType
            /*Core units*/cadet,

    /*Missile's*/titaniaMissile,
    /*Stealth Path*/sentry,sentryUp,stalker,heatseeker,
    /*Rampage Path*/loner,lonerUp,rouge,rougeUp,revenger,
    /*Protector Path*/bonfire,bonfireUp,campfire,barrack,
    /*Freezing Path*/polar,polarUp,brainFreeze,frostBite,
    /*Kaon Path (Lava Path)*/cheron,
    /*Extra units*/maxwell,smallTrajectoryCar, largeTrajectoryCar,

            /*Bosses*/titania
            ;
    public static void EXload() {
        titaniaMissile = new MissileUnitType("titania-missle") {{
            lifetime = 1.2f * 60f;
            speed = 4.6f;
            maxRange = 5f;
            health = 70;
            rotateSpeed *= 1.5f;
            lowAltitude = true;
            useUnitCap = false;
            engineSize = 3f;
            deathExplosionEffect = Fx.none;
            weapons.add(new Weapon() {
                {
                    shootCone = 360f;
                    mirror = false;
                    reload = 1f;
                    shootOnDeath = true;
                    bullet = new ExplosionBulletType(100f, 8f) {
                        {
                            suppressionRange = 140f;
                            shootEffect = new ExplosionEffect() {{
                                lifetime = 50f;
                                waveStroke = 5f;
                                waveLife = 8f;
                                waveColor = Color.white;
                                sparkColor = smokeColor = Pal.turretHeat;
                                waveRad = 40f;
                                smokeSize = 4f;
                                smokes = 7;
                                smokeSizeBase = 0f;
                                sparks = 10;
                                sparkRad = 40f;
                                sparkLen = 6f;
                                sparkStroke = 2f;
                            }};
                            damage = 80;
                            status = StatusEffects.burning;
                            statusDuration = 120;
                        }
                    };
                }
            });
        }};

        cadet = new UnitType("cadet") {{
            localizedName = "Cadet";
            health = 350;
            armor = 2;
            speed = 1;
            hitSize = 13f;
            constructor = EntityMapping.map("atrax");
            legCount = 6;
            legGroupSize = 3;
            legSpeed = 0.2f;
            legLength = 10;
            allowLegStep = true;
            mineSpeed = 4;
            mineTier = 2;
            buildSpeed = 0.8f;
            immunities = ObjectSet.with(EXStatusEffects.scabbed);
            weapons.add(new Weapon(name("cadet-builder-mount")) {{
                mirror = false;
                top = true;
                y = 4;
                x = 0;
                reload = 30;
                bullet = new BasicBulletType(2, 40) {{
                    lifetime = 60;
                    width = 12;
                    height = 15;
                    fragRandomSpread = 0;
                    fragAngle = -90;
                    fragSpread = 180;
                    buildingDamageMultiplier = 0.05f;
                    fragBullets = 2;
                    fragVelocityMin = 1;
                    fragVelocityMax = 1;
                    fragLifeMax = 1;
                    fragLifeMin = 1;
                    trailColor = Pal.lightTrail;
                    trailWidth = 2;
                    trailLength = 50;
                    fragBullet = new LiquidBulletType() {{
                        speed = 1;
                        lifetime = 110;
                        damage = 20;
                        drag = 0.1f;
                        status = EXStatusEffects.detained;
                        buildingDamageMultiplier = 0.05f;
                        liquid = Liquids.cryofluid;
                        puddleAmount = 4;
                        puddleLiquid = Liquids.cryofluid;
                        trailColor = Pal.lancerLaser;
                        trailWidth = 2;
                        trailLength = 50;
                    }};
                }};
            }});
        }};
        loner = new UnitType("loner") {{
            localizedName = "Loner";
            health = 350;
            armor = 2;
            speed = 3.8f / 7.5f;
            legCount = 4;
            legLength = 4.5f;
            legGroupSize = 2;
            constructor = EntityMapping.map("atrax");
            immunities = ObjectSet.with(EXStatusEffects.scabbed);
            abilities.add(new ForceFieldAbility(6f, 2f / 60f, 120f, 2f * 60f));
            weapons.add(new Weapon(name("loner-gun")) {{
                mirror = true;
                top = true;
                x = 2;
                y = -2;
                reload = 25;
                bullet = new BasicBulletType(4f, 20) {{
                    lifetime = 34;
                    ammoMultiplier = 4;
                    width = 5f;
                    height = 7f;
                    hitSize = 4;
                    sprite = "missile-large";
                    smokeEffect = Fx.shootBigSmoke;
                    shootEffect = Fx.shootBigColor;
                    hitColor = backColor = trailColor = Pal.ammo;
                    frontColor = Color.white;
                    trailWidth = 1.7f;
                    trailLength = 5;
                    despawnEffect = hitEffect = Fx.hitBulletColor;
                }};
            }});
        }};
        lonerUp = new UnitType("loner-up") {{
            constructor = EntityMapping.map("mace");
            localizedName = "Hitman";
            health = 1260;
            armor = 5;
            speed = 7f / 7.5f;
            canBoost = true;
            boostMultiplier = 8f / 7f;
            range = 19 * 8;
            immunities.add(EXStatusEffects.scabbed);
            weapons.add(
                    new Weapon("") {{
                        reload = 60f * 0.7f;
                        shoot.shotDelay = 60f * 0.1f;
                        shoot.shots = 3;
                        x = 0;
                        y = 0;
                        inaccuracy = 4;
                        bullet = new MissileBulletType(3, 28) {{
                            lifetime = setRange(19, 3);
                            trailLength = 19;
                            weaveRandom = true;
                            weaveScale = 1;
                            weaveMag = 1;
                            trailWidth = 1;
                            trailColor = frontColor = backColor = Pal.engine;
                        }};
                    }}
            );
        }};
        rouge = new UnitType("rouge") {{ //Todo
            constructor = EntityMapping.map("merui");
            localizedName = "Rouge";
            health = 3600;
            legCount = 6;
            legGroupSize = 3;
            armor = 18;
            hitSize = 16;
            speed = 6f / 7.5f;
            abilities.add(new ShieldRegenFieldAbility(20f, 40f, 120, 12));
            immunities.add(EXStatusEffects.scabbed);
            weapons.add(new Weapon(name("rouge-shotgun")) {{
                mirror = false;
                top = false;
                reload = 45;
                layerOffset -= 0.02f;
                engineOffset = -1;
                shoot = new ShootSpread(8, 8f);
                x = 0;
                y = 2;
                inaccuracy = 1;
                bullet = new BasicBulletType(8f, 20) {{
                    knockback = 1f;
                    width = 25f;
                    lifetime = 5f;
                    hitSize = 10f;
                    height = 20f;
                    shootEffect = Fx.shootBigColor;
                    smokeEffect = Fx.shootSmokeSquareSparse;
                    ammoMultiplier = 1;
                    hitColor = backColor = trailColor = Color.valueOf("ea8878");
                    frontColor = Pal.redLight;
                    trailWidth = 6f;
                    trailLength = 3;
                    hitEffect = despawnEffect = Fx.hitSquaresColor;
                    buildingDamageMultiplier = 0.2f;
                }};
            }});
            weapons.add(new Weapon(name("rouge-gun")) {{
                mirror = true;
                rotate = true;
                top = false;
                reload = 15;
                engineOffset = -1;
                x = -6;
                y = 0;
                inaccuracy = 1;
                bullet = new LaserBulletType() {{
                    length = 12 * 8;
                    lifetime = 4;
                    damage = 13;
                    lightColor = Pal.ammo;
                    lightningDamage = 13;
                    lightningColor = Pal.ammo;
                }};
            }});
        }};
        rougeUp = new UnitType("rouge-up") {{
            localizedName = "Paladin";
            constructor = EntityMapping.map("arkyid");
            health = 5100;
            armor = 8;
            speed = 1f / 7.5f;
            legCount = 8;
            legLength = 2.4f * 8f;
            hitSize = 16;
            range = 34 * 8;
            immunities.add(EXStatusEffects.scabbed);
            weapons.add(new Weapon(name("rouge-up-gun")) {{
                reload = 60f;
                mirror = true;
                top = true;
                x = -6;
                y = -4;
                bullet = new BulletType() {{
                    shootEffect = Fx.unitSpawn;
                    hitColor = Pal.suppress;
                    speed = 0f;
                    hitShake = 3;
                    shake = 3;
                    keepVelocity = false;
                    spawnUnit = new MissileUnitType("rouge-up-missle") {{
                        lifetime = setRange(34, 4.6f);
                        speed = 4.6f;
                        maxRange = 5f;
                        health = 70;
                        rotateSpeed *= 1.5f;
                        homingDelay = 10f;
                        homingPower = 0.1f;
                        lowAltitude = true;
                        engineSize = 3f;
                        deathExplosionEffect = Fx.none;
                        weapons.add(new Weapon() {
                            {
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                shootOnDeath = true;
                                bullet = new ExplosionBulletType(40f, 8f) {
                                    {
                                        suppressionRange = 140f;
                                        shootEffect = new ExplosionEffect() {{
                                            lifetime = 50f;
                                            waveStroke = 5f;
                                            waveLife = 8f;
                                            waveColor = Color.white;
                                            sparkColor = smokeColor = Pal.turretHeat;
                                            waveRad = 40f;
                                            smokeSize = 4f;
                                            smokes = 7;
                                            smokeSizeBase = 0f;
                                            sparks = 10;
                                            sparkRad = 40f;
                                            sparkLen = 6f;
                                            sparkStroke = 2f;
                                        }};
                                        status = StatusEffects.burning;
                                        statusDuration = 120;
                                    }
                                };
                            }
                        });
                    }};
                }};
            }});
        }};
        revenger = new UnitType("revenger") {{
            localizedName = "Revenger";
            constructor = EntityMapping.map("toxopid");
            health = 30000;
            armor = 22;
            legCount = 8;
            legLength = 2.6f * 8f;
            hitSize = 24;
            speed = 1.6f / 7.5f;
            immunities.add(EXStatusEffects.scabbed);
            abilities.add(new ForceFieldAbility(20f, 50f / 60f, 2000f, 10f * 60f));
            weapons.add(new Weapon(name("revenger-cannon")) {{
                top = true;
                x = 0;
                y = 0;
                mirror = false;
                recoil = 4.5f;
                legSplashDamage = 30;
                legSplashRange = 10;
                rotate = true;
                reload = 4.5f * 60f;
                shoot = new ShootSpread(8, 8);
                shootStatus = EXStatusEffects.slowing;
                shootStatusDuration = 60;
                bullet = new BulletType() {{
                    shootEffect = Fx.unitSpawn;
                    hitColor = Pal.suppress;
                    speed = 0f;
                    hitShake = 3;
                    shake = 3;
                    keepVelocity = false;
                    spawnUnit = new MissileUnitType("revenger-missle") {{
                        lifetime = 2.2f * 60f;
                        speed = 4.6f;
                        maxRange = 5f;
                        health = 70;
                        rotateSpeed *= 1.5f;
                        homingDelay = 10f;
                        homingPower = 0.1f;
                        lowAltitude = true;
                        engineSize = 3f;
                        deathExplosionEffect = Fx.none;
                        weapons.add(new Weapon() {
                            {
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                shootOnDeath = true;
                                bullet = new ExplosionBulletType(40f, 8f) {
                                    {
                                        suppressionRange = 140f;
                                        shootEffect = new ExplosionEffect() {{
                                            lifetime = 50f;
                                            waveStroke = 5f;
                                            waveLife = 8f;
                                            waveColor = Color.white;
                                            sparkColor = smokeColor = Pal.turretHeat;
                                            waveRad = 40f;
                                            smokeSize = 4f;
                                            smokes = 7;
                                            smokeSizeBase = 0f;
                                            sparks = 10;
                                            sparkRad = 40f;
                                            sparkLen = 6f;
                                            sparkStroke = 2f;
                                        }};
                                        status = StatusEffects.burning;
                                        statusDuration = 120;
                                    }
                                };
                            }
                        });
                    }};
                }};
            }});
            faceTarget = true;
            weapons.add(new Weapon(name("revenger-gun")) {{
                x = 15;
                y = -1;
                top = false;
                mirror = true;
                inaccuracy = 5;
                recoil = 2;
                reload = 2;
                bullet = new BasicBulletType(4, 14) {{
                    shootY = 6;
                    inaccuracy = 20;
                    width = 5;
                    shake = hitShake = 3;
                    height = 5;
                    hitColor = backColor = trailColor = Pal.ammo;
                    frontColor = Color.white;
                    trailWidth = 1.7f;
                    trailLength = 5;
                }};
            }});
        }};
        sentry = new UnitType("sentry") {{
            localizedName = "Sentry";
            health = 120;
            armor = 0;
            flying = true;
            drag = 0.08f;
            speed = 7f / 7.5f;
            engineSize = 1.25f;
            engineOffset = 3f;
            constructor = EntityMapping.map("flare");
            trailScl = 1f;
            trailColor = Pal.lightTrail;
            trailLength = 30;
            weapons.add(new Weapon(name("sentry-mount")) {{
                mirror = true;
                x = 1;
                y = -1;
                reload = 26;
                bullet = new BasicBulletType(2.5f, 12) {{
                    lifetime = 60;
                    ammoMultiplier = 4;
                    weaveScale = 3;
                    status = EXStatusEffects.scabbed;
                    weaveMag = 3;
                    trailChance = 100;
                    trailInterval = 0;
                }};
            }});
        }};
        sentryUp = new UnitType("sentry-up") {{
            localizedName = "Avoid";
            constructor = EntityMapping.map("horizon");
            health = 650;
            armor = 3;
            speed = 4.5f / 7.5f;
            flying = true;
            weapons.add(
                    new Weapon("") {{
                        mirror = false;
                        top = false;
                        rotate = true;
                        reload = 60f;
                        x = 0;
                        y = 0;
                        bullet = new BombBulletType(13f, 2f * 8f) {{
                            width = 10f;
                            height = 14f;
                            hitEffect = Fx.flakExplosion;
                            shootEffect = Fx.none;
                            smokeEffect = Fx.none;

                            status = StatusEffects.blasted;
                            statusDuration = 60f;
                        }};
                    }},
                    new PointDefenseWeapon(name("sentry-up-point-defense")) {{
                        mirror = false;
                        rotate = true;
                        top = true;
                        reload = 60f / 2f;
                        x = 0;
                        y = 0;
                        bullet = new BulletType(2f, 12) {{
                            lifetime = 60;
                            ammoMultiplier = 4;
                            maxRange = 100;
                            pierceArmor = true;
                        }};
                    }}
            );
        }};
        stalker = new UnitType("stalker") {{
            localizedName = "Stalker";
            constructor = EntityMapping.map("horizon");
            health = 1180f;
            armor = 3f;
            hitSize = 14f;
            speed = 8f / 7.5f;
            flying = true;
            immunities.add(EXStatusEffects.scabbed);
            weapons.add(new Weapon(name("stalker-big-gun")) {{
                mirror = true;
                rotate = true;
                top = true;
                reload = 60;
                x = -4;
                y = 3;
                shoot = new ShootSpread(3, 15);
                inaccuracy = 1;
                bullet = new BasicBulletType(2.5f, 24) {{
                    lifetime = 60;
                    ammoMultiplier = 4;
                    weaveScale = 3;
                    status = EXStatusEffects.scabbed;
                    weaveMag = 3;
                    trailChance = 100;
                    trailInterval = 0;
                    pierceArmor = true;
                }};
            }});
            weapons.add(new PointDefenseWeapon(name("stalker-small-gun")) {{
                mirror = true;
                rotate = true;
                top = true;
                reload = 60f / 3f;
                engineOffset = -1;
                x = -4;
                y = -6;
                inaccuracy = 1;
                bullet = new BulletType(2f, 12) {{
                    lifetime = 60;
                    ammoMultiplier = 4;
                    trailChance = 100;
                    trailInterval = 0;
                    maxRange = 100;
                    pierceArmor = true;
                }};
            }});
        }};
        heatseeker = new UnitType("heatseeker") {{
            localizedName = "Heatseeker";
            constructor = EntityMapping.map("antumbra");
            health = 8000;
            armor = 21;
            rotateSpeed = 1;
            lowAltitude = true;
            omniMovement = false;
            flying = true;
            hitSize = 4.4f * 10f;
            drag = 0.04f;
            speed = UnitTypes.antumbra.speed;
            setEnginesMirror(
                    new UnitEngine(15, 14, 4, 0),
                    new UnitEngine(15, -14, 4, 0)
            );
            abilities.add(new SpawnDeathAbility(new MissileUnitType("heatseeker-missile") {{
                        lifetime = 0.6f * 60f;
                        speed = 4.6f;
                        maxRange = 5f;
                        health = 70;
                        rotateSpeed *= 1.5f;
                        homingDelay = 999f * 60f;
                        lowAltitude = true;
                        engineSize = 3f;
                        trailColor = Pal.ammo;
                        trailScl = 1.7f;
                        trailLength = 5;
                        deathExplosionEffect = Fx.none;
                        weapons.add(new Weapon() {
                            {
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                shootOnDeath = true;
                                bullet = new ExplosionBulletType(30f, 12f) {{
                                    suppressionRange = 140f;
                                    shootEffect = new ExplosionEffect() {{
                                        lifetime = 50f;
                                        waveStroke = 5f;
                                        waveLife = 8f;
                                        waveColor = Color.white;
                                        sparkColor = smokeColor = Pal.turretHeat;
                                        waveRad = 40f;
                                        smokeSize = 4f;
                                        smokes = 7;
                                        smokeSizeBase = 0f;
                                        sparks = 10;
                                        sparkRad = 40f;
                                        sparkLen = 6f;
                                        sparkStroke = 2f;
                                    }};
                                    status = StatusEffects.melting;
                                    statusDuration = 120;
                                }};
                            }
                        });
                    }}, 32, 10),
                    new SpawnDeathAbility(new MissileUnitType("heatseeker-missile2") {{
                        lifetime = 1.2f * 60f;
                        speed = 2.3f;
                        maxRange = 5f;
                        health = 70;
                        rotateSpeed *= 1.5f;
                        homingDelay = 999f * 60f;
                        lowAltitude = true;
                        engineSize = 3f;
                        trailColor = Pal.ammo;
                        trailScl = 2f;
                        trailLength = 8;
                        deathExplosionEffect = Fx.none;
                        weapons.add(new Weapon() {
                            {
                                shootCone = 360f;
                                mirror = false;
                                reload = 1f;
                                shootOnDeath = true;
                                bullet = new ExplosionBulletType(30f, 12f) {{
                                    suppressionRange = 140f;
                                    shootEffect = new ExplosionEffect() {{
                                        lifetime = 50f;
                                        waveStroke = 5f;
                                        waveLife = 8f;
                                        waveColor = Color.white;
                                        sparkColor = smokeColor = Pal.turretHeat;
                                        waveRad = 40f;
                                        smokeSize = 4f;
                                        smokes = 7;
                                        smokeSizeBase = 0f;
                                        sparks = 10;
                                        sparkRad = 40f;
                                        sparkLen = 6f;
                                        sparkStroke = 2f;
                                    }};
                                    status = StatusEffects.melting;
                                    statusDuration = 120;
                                }};
                            }
                        });
                    }}, 32, 10)
            );
            weapons.add(
                    new Weapon(name("heatseeker-gun")) {{
                        x = 0;
                        rotate = true;
                        y = 2;
                        recoil = 2;
                        layerOffset += 0.02;
                        top = true;
                        mirror = false;
                        shoot = new ShootAlternate(4.5f);
                        reload = 0.2f * 60f;
                        shootY = 13f;

                        bullet = new BasicBulletType(3, 24) {{
                            lifetime = setRange(20, 3);
                            hitColor = backColor = trailColor = Pal.lancerLaser;
                            frontColor = Color.white;
                            trailWidth = 2.1f;
                            trailLength = 6;
                            fragBullets = 3;
                            despawnEffect = hitEffect = Fx.explosion;
                            shake = 3;
                            hitShake = 3;
                            fragBullet = new BasicBulletType(1, 6) {{
                                hitColor = backColor = trailColor = Pal.lancerLaser;
                                frontColor = Color.white;
                                trailWidth = 1.7f;
                                trailLength = 5;
                                lifetime = setRange(3, 1);
                            }};
                        }};
                    }}
            );

        }};
        polar = new UnitType("polar") {{
            localizedName = "Polar";
            constructor = EntityMapping.map("dagger");
            health = 280;
            speed = 3.4f / 7.5f;
            boostMultiplier = 1.47f;
            immunities.add(StatusEffects.freezing);
            canBoost = true;
            weapons.add(new Weapon(name("polar-weapon")) {{
                mirror = false;
                top = true;
                reload = 15;
                x = 0;
                y = 0;
                inaccuracy = 10;
                bullet = new BasicBulletType(2, 7) {{
                    trailColor = Pal.lancerLaser;
                    trailChance = 100;
                    trailInterval = 0;
                    homingPower = 0.01f;
                    lifetime = 60;
                }};
            }});
        }};
        polarUp = new UnitType("polar-up") {{
            constructor = EntityMapping.map("mace");
            localizedName = "Kelvin";
            health = 840;
            armor = 2;
            speed = 4f / 7.5f;
            boostMultiplier = 1.1f;
            canBoost = true;
            abilities.add(new EnemyStatusFieldAbility(StatusEffects.freezing, 180f, 120f, 12 * 8));
            weapons.add(
                    new Weapon(name("polar-up-gun")) {{
                        reload = 35f;
                        x = 0f;
                        y = 0f;
                        shootY = 4f;
                        shootX = 4f;
                        recoil = 1f;
                        top = false;
                        rotate = false;
                        mirror = true;
                        shoot = new ShootHelix();

                        bullet = new BasicBulletType(5f, 34) {{
                            width = 7f;
                            height = 12f;
                            lifetime = 18f;
                            shootEffect = Fx.sparkShoot;
                            smokeEffect = Fx.shootBigSmoke;
                            hitColor = backColor = trailColor = Pal.lancerLaser;
                            frontColor = Color.white;
                            trailWidth = 1.5f;
                            trailLength = 5;
                            hitEffect = despawnEffect = Fx.hitBulletColor;
                        }};
                    }}
            );
        }};
        brainFreeze = new UnitType("brainFreeze") {{
            health = 2200;
            constructor = EntityMapping.map("fortress");
            localizedName = "Brain Freeze";
            armor = 8;
            speed = 2.2f / 7.5f;
            hitSize = 20;
            canBoost = true;
            boostMultiplier = 2.27f;
            immunities.add(StatusEffects.freezing);
            weapons.add(new Weapon(name("brainFreeze-gun")) {{
                reload = 60f;
                mirror = false;
                top = true;
                x = 0;
                y = 0;
                bullet = new BasicBulletType() {{

                    shootEffect = new MultiEffect(new WaveEffect() {{
                        colorTo = Pal.sapBulletBack;
                        sizeTo = 26f;
                        lifetime = 14f;
                        strokeFrom = 4f;
                    }});
                    hitColor = Pal.lancerLaser;
                    despawnSound = Sounds.spark;

                    sprite = "large-orb";
                    trailInterval = 3f;
                    trailParam = 4f;
                    speed = 3f;
                    damage = 80f;
                    lifetime = 75f;
                    width = height = 15f;
                    backColor = Pal.lancerLaser;
                    frontColor = Pal.lancerLaser;
                    shrinkX = shrinkY = 0f;
                    trailColor = Pal.lancerLaser;
                    trailLength = 12;
                    trailWidth = 2.2f;
                    despawnEffect = hitEffect = new ExplosionEffect() {{
                        waveColor = Pal.lancerLaser;
                        smokeColor = Color.gray;
                        sparkColor = Pal.sap;
                        waveStroke = 4f;
                        waveRad = 40f;
                    }};

                    intervalBullet = new LightningBulletType() {{
                        damage = 18;
                        collidesAir = false;
                        ammoMultiplier = 1f;
                        lightningColor = Pal.lancerLaser;
                        lightningLength = 3;
                        lightningLengthRand = 6;
                        despawnEffect = Fx.none;
                        //for visual stats only.
                        buildingDamageMultiplier = 0.25f;

                        lightningType = new BulletType(0.0001f, 0f) {{
                            status = StatusEffects.shocked;
                            statusDuration = 10f;
                            hittable = false;
                            lightColor = Color.white;
                            despawnEffect = Fx.none;
                            buildingDamageMultiplier = 0.25f;
                        }};
                    }};

                    bulletInterval = 4f;

                    lightningColor = Pal.sapBullet;
                    lightningDamage = 21;
                    lightning = 8;
                    lightningLength = 2;
                    lightningLengthRand = 8;
                }};
            }});
        }};
        frostBite = new UnitType("frost-bite") {{
            localizedName = "Frost Bite";
            constructor = EntityMapping.map("scepter");
            health = 7200;
            armor = 5;
            speed = 2.2f / 7.5f;
            canBoost = true;
            boostMultiplier = 1.7f / 2.2f;
            abilities.add(new EnemyStatusFieldAbility(StatusEffects.freezing, 780f, 720f, 40 * 8f));
            weapons.add(new Weapon(name("frost-bite-gun")) {{
                mirror = false;
                top = true;
                x = 0;
                y = -4;
                shootY = 4;
                reload = 10f;
                inaccuracy = 25f;
                rotate = true;
                rotationLimit = 1;
                recoil = 8;
                recoilPow = 0.5f;
                recoilTime = 120;
                minWarmup = 0.88f;

                shootWarmupSpeed = 0.02f;
                shootStatus = StatusEffects.unmoving;
                shootStatusDuration = 120f;
                bullet = new FlakBulletType(8f, 36f) {{
                    sprite = "missile-large";

                    lifetime = setRange(27, 8);
                    width = 12f;
                    height = 22f;

                    hitSize = 7f;
                    shootEffect = Fx.shootSmokeSquareBig;
                    smokeEffect = Fx.shootSmokeDisperse;
                    ammoMultiplier = 1;
                    hitColor = backColor = trailColor = lightningColor = Pal.lancerLaser;
                    frontColor = Color.white;
                    trailWidth = 3f;
                    trailLength = 12;
                    hitEffect = despawnEffect = Fx.hitBulletColor;
                    buildingDamageMultiplier = 0.3f;

                    trailEffect = Fx.colorSpark;
                    trailRotation = true;
                    trailInterval = 3f;
                    lightning = 1;
                    lightningCone = 15f;
                    lightningLength = 20;
                    lightningLengthRand = 30;
                    lightningDamage = 10f;

                    homingPower = 0.17f;
                    homingDelay = 19f;
                    homingRange = 160f;

                    explodeRange = 160f;
                    explodeDelay = 0f;

                    flakInterval = 20f;
                    despawnShake = 3f;

                    fragBullet = new LaserBulletType(36f) {{
                        colors = new Color[]{Pal.lancerLaser.cpy().a(0.4f), Pal.lancerLaser, Color.white};
                        buildingDamageMultiplier = 0.25f;
                        width = 19f;
                        hitEffect = Fx.hitLancer;
                        sideAngle = 175f;
                        sideWidth = 1f;
                        sideLength = 40f;
                        lifetime = 22f;
                        drawSize = 400f;
                        length = 180f;
                        pierceCap = 2;
                    }};

                    fragSpread = fragRandomSpread = 0f;

                    splashDamage = 0f;
                    hitEffect = Fx.hitSquaresColor;
                    collidesGround = true;
                }};
            }});
        }};
        bonfire = new UnitType("bonfire") {{
            localizedName = "Bonfire";
            constructor = EntityMapping.map("merui");
            abilities.add(new StatusFieldAbility(EXStatusEffects.protectivePlating, 480f, 240f, 5 * 8f));
            abilities.add(new RepairFieldAbility(6f, 120f, 5 * 8f));
            immunities.add(EXStatusEffects.scabbed);
            health = 200;
            speed = 3f / 7.5f;
            hitSize = 5;
            legCount = 4;
            legLength = 6f;
            legGroupSize = 4;
            weapons.add(new Weapon(){{
                reload = 30f;
                mirror = false;
                top = true;
                x = 0;
                y = 0;
                bullet = new LaserBoltBulletType(setRange(1,3.4f),6)
                {{
                    lifetime = setRange(10,speed);
                    damage = 6;
                    backColor = trailColor = lightColor = frontColor = Pal.heal;
                }};
            }});
        }};
        bonfireUp = new UnitType("bonfire-up") {{
            localizedName = "Hover";
            description = "Guardian V0.1";
            constructor = EntityMapping.map("cleroi");
            abilities.add(
                    new ShieldRegenFieldAbility(50f, 400f, 60f * 10, 10f * 8f),
                    new RepairFieldAbility(20, 60, 1)
            );
            immunities.add(EXStatusEffects.scabbed);
            health = 1600;
            armor = 4;
            speed = 3.8f / 7.5f;
            legCount = 4;
            legLength = 8f;
            legGroupSize = 2;
            weapons.add(new Weapon(name("bonfire-up-weapon")) {{
                            shootSound = Sounds.blaster;
                            y = -4f;
                            x = 4f;
                            top = true;
                            mirror = true;
                            reload = 60f / 8f;
                            baseRotation = -35f;
                            shootCone = 360f;

                            shoot = new ShootSpread(1, 11f);

                            bullet = new BasicBulletType(5f, 8) {{
                                homingPower = 0.19f;
                                homingDelay = 4f;
                                width = 7f;
                                height = 12f;
                                lifetime = 30f;
                                shootEffect = Fx.sparkShoot;
                                smokeEffect = Fx.shootBigSmoke;
                                hitColor = backColor = trailColor = Pal.heal;
                                frontColor = Color.white;
                                trailWidth = 1.5f;
                                trailLength = 5;
                                hitEffect = despawnEffect = Fx.hitBulletColor;
                            }};
                        }},
                    new RepairBeamWeapon(name("campfire-repair")) {{
                        x = 0f;
                        y = -4f;
                        shootY = 3f;
                        beamWidth = 0.9f;
                        mirror = false;
                        top = true;
                        repairSpeed = 30f / 60f;

                        bullet = new BulletType() {{
                            maxRange = 13 * 8f;
                        }};
                    }});
        }};
        campfire = new UnitType("campfire") {{
            localizedName = "Campsite";
            constructor = EntityMapping.map("merui");
            abilities.add(new StatusFieldAbility(EXStatusEffects.advancedPlating, 480f, 360f, 5 * 8f));
            abilities.add(new SpawnDeathAbility(loner, 1, 11));
            abilities.add(new SpawnDeathAbility(polar, 1, 11));
            abilities.add(new SpawnDeathAbility(bonfire, 1, 11));
            immunities.add(EXStatusEffects.scabbed);
            health = 4500;
            armor = 9;
            faceTarget = true;
            baseRotateSpeed = 10;
            hitSize = 18;
            speed = 1f / 7.5f;
            legCount = 4;
            legLength = 15f;
            legGroupSize = 4;
            weapons.add(new RepairBeamWeapon(name("campfire-repair")) {{
                x = 4f;
                y = -4f;
                shootY = 3f;
                beamWidth = 0.8f;
                mirror = true;
                repairSpeed = 0.5f;

                bullet = new BulletType() {{
                    maxRange = 11 * 8f;
                }};
            }});
            weapons.add(new RepairBeamWeapon(name("campfire-repair")) {{
                x = 4f;
                y = 4f;
                shootY = 3f;
                beamWidth = 0.8f;
                mirror = true;
                repairSpeed = 0.5f;

                bullet = new BulletType() {{
                    maxRange = 11 * 8f;
                }};
            }});
            weapons.add(new Weapon() {{
                shootOnDeath = true;
                reload = 24f;
                shootCone = 180f;
                shootSound = Sounds.explosion;
                x = shootY = 0f;
                mirror = false;
                bullet = new BulletType() {{
                    collidesTiles = false;
                    collides = false;
                    hitSound = Sounds.explosion;
                    rangeOverride = 30f;
                    speed = 0f;
                    splashDamageRadius = 5 * 8f;
                    instantDisappear = true;
                    splashDamage = 300f;
                    killShooter = true;
                    hittable = false;
                    collidesAir = true;
                }};
            }});
        }};
        barrack = new UnitType("barrack") {{
            localizedName = "Barrack";
            constructor = EntityMapping.map("arkyid");
            abilities.add(new SpawnDeathAbility(campfire, 1, 0));
            abilities.add(new SpawnDeathAbility(loner, 2, 0));
            abilities.add(new UnitSpawnAbility(sentry, 120f * 60f, 18, 0), new UnitSpawnAbility(sentry, 120f * 60f, -18, 0));
            immunities.add(EXStatusEffects.scabbed);
            hitSize = 4f * 10f;
            health = 32000;
            armor = 25;
            faceTarget = true;
            baseRotateSpeed = 10;
            speed = 1.3f / 7.5f;
            legCount = 8;
            legLength = 30f;
            weapons.add(new RepairBeamWeapon(name("barrack-mendb")) {{
                x = 12f;
                y = 12f;
                shootY = 3f;
                beamWidth = 1.2f;
                mirror = true;
                top = true;
                repairSpeed = 0.75f;

                bullet = new BulletType() {{
                    maxRange = 13 * 8f;
                }};
            }});
            weapons.add(new RepairBeamWeapon(name("barrack-mendb")) {{
                x = 12f;
                y = -12f;
                shootY = 3f;
                beamWidth = 1.2f;
                mirror = true;
                top = true;
                repairSpeed = 0.75f;

                bullet = new BulletType() {{
                    maxRange = 13 * 8f;
                }};
            }});
            weapons.add(new Weapon(name("")) {{
                x = 0;
                y = 0;
                mirror = false;
                reload = 240f;

                bullet = new BasicBulletType(1, 120) {{
                    width = 30;
                    height = 30;
                    lifetime = setRange(6, 1);
                    shrinkX = shrinkY = 20f / (lifetime / 2f);
                    trailColor = lightColor = backColor = frontColor = Color.white;
                    trailWidth = 0.4f;
                    trailLength = 15;
                    splashDamage = 120;
                    splashDamageRadius = 24;
                    scaledSplashDamage = true;
                    buildingDamageMultiplier = 3f;
                    despawnEffect = hitEffect = Fx.massiveExplosion;
                    shootEffect = Fx.greenBomb;
                }};
            }});
        }};
        cheron = new UnitType("cheron") {{
            localizedName = "Cheron";
            health = 75;
            armor = 0;
            speed = 30f / 7.5f;
            accel = 1 / 7.5f;
            rotateSpeed = 12;
            outlineColor = Color.valueOf("1c1e1f");
            faceTarget = true;
            drag = 0.02f;
            itemCapacity = 25;
            range = 8 * 8;
            constructor = EntityMapping.map("flare");
            flying = true;
            immunities.add(StatusEffects.burning);
            weapons.add(new Weapon(name("cheron-arm")) {{
                reload = 30f / 2.4f;
                x = -3f;
                y = 2;
                top = false;
                mirror = true;
                shoot = new ShootSpread(2, 1);
                inaccuracy = 5;
                bullet = new LaserBulletType(6) {{
                    lifetime = 12;
                    length = 8 * 8;
                    lightningDamage = 6;
                    pierce = true;
                    lightColor = lightningColor = trailColor = hitColor = Pal.turretHeat;
                    colors = new Color[]{Pal.turretHeat, Pal.lightFlame, Pal.darkFlame};
                    shootEffect = Fx.sparkShoot;
                    despawnEffect = Fx.none;
                    pierceCap = 10;
                    removeAfterPierce = true;
                    status = StatusEffects.burning;
                    shootStatus = EXStatusEffects.cheronHit;
                    shootStatusDuration = 30f / 2.4f + 2;
                    statusDuration = 180;
                }};
            }});
        }};
        titania = new UnitType("titania") {{
            localizedName = "Titania";
            description = "Hell Nah.";
            outlineColor = Color.valueOf("313131");
            health = 12000;
            armor = 5;
            rotateSpeed = 3;
            speed = 12f / 7.5f;
            outlineColor = Color.valueOf("1c1e1f");
            lowAltitude = true;
            itemCapacity = 120;
            hitSize = 40;
            faceTarget = true;
            outlines = true;
            constructor = EntityMapping.map("eclipse");
            abilities.add(
                    new StatusFieldAbility(StatusEffects.overdrive, 300, 180, 8*16)
            );
            flying = true;
            weapons.add(
                    new Weapon(name("titania-gun")) {{
                        x = -18;
                        y = 12.5f;
                        inaccuracy = 8;
                        shootY = 14;
                        reload = 60f/4.8f;
                        rotate = true;
                        alternate = true;
                        mirror = true;
                        rotationLimit = 90;
                        shoot = new ShootSpread(2,2);
                        top = false;
                        bullet = new LaserBulletType(62) {{
                            lifetime = 30;
                            width = 8*2;
                            length = 18 * 8;
                            lightningDamage = 6;
                            pierce = true;
                            lightColor = lightningColor = trailColor = hitColor = Pal.turretHeat;
                            colors = new Color[]{Pal.turretHeat, Pal.lightFlame, Pal.darkFlame};
                            shootEffect = Fx.sparkShoot;
                            despawnEffect = Fx.none;
                            pierceCap = 10;
                            removeAfterPierce = true;
                            status = StatusEffects.burning;
                            shootStatus = EXStatusEffects.cheronHit;
                            shootStatusDuration = 30f / 2.4f + 2;
                            statusDuration = 180;
                        }};
                    }},
                    new Weapon(name("titania-mount")) {{
                        x = 13;
                        y = -16;
                        reload = 60f/1.4f;
                        mirror = true;
                        top = true;
                        rotate = true;
                        recoil = 2f;
                        bullet = new LaserBulletType(200) {
                            {
                                recoil = 1f;
                                lifetime = 120;
                                lightningDamage = 120;
                                chargeEffect = Fx.bigShockwave;
                                length = 36*8;
                                width = 32;
                                damage = 120;
                                hitShake = 2;
                                despawnShake = 2;
                                buildingDamageMultiplier = 0.8f;
                                lightColor = lightningColor = trailColor = hitColor = Pal.turretHeat;
                                colors = new Color[]{Pal.turretHeat, Pal.lightFlame, Pal.darkFlame};
                            }
                        };
                    }}
            );
        }};
        maxwell = new UnitType("maxwell") {{
            controller = u -> new AssemblerAI();
            localizedName = "Maxwell";
            engineOffset = -2;
            constructor = EntityMapping.map("assemblyDrone");
            flying = true;
            drag = 0.06f;
            accel = 0.11f;
            speed = 1.3f;
            health = 90;
            engineSize = 2f;
            engineOffset = 6.5f;
            payloadCapacity = 0f;
            targetable = false;
            isEnemy = false;
            hidden = true;
            useUnitCap = false;
            logicControllable = false;
            playerControllable = false;
            allowedInPayloads = false;
            createWreck = false;
            immunities.add(EXStatusEffects.scabbed);
        }};
        smallTrajectoryCar = new UnitType("small-trajectory-car") {{
            localizedName = "Angstrom";
            description = "a smoll unit slave forced to suffer carrying heavy force pods";
            health = 225;
            armor = 0;
            speed = 8.2f / 7.5f;
            itemCapacity = 200;
            flying = true;
            isEnemy = false;
            useUnitCap = false;
            outlines = false;
            constructor = EntityMapping.map("flare");
        }};
        largeTrajectoryCar = new UnitType("large-trajectory-car") {{
            localizedName = "Nanometer";
            description = "the result of carying heavy force pods on end. Has now become a GigaChad";
            health = 600;
            armor = 2;
            speed = 10f / 7.5f;
            itemCapacity = 500;
            flying = true;
            isEnemy = false;
            useUnitCap = false;
            outlines = true;
            constructor = EntityMapping.map("flare");
        }};
    }
    public static String name(String n){
        return "exhiber-"+n;
    }
    public static float setRange(float range,float speed)
    {
        return range / (speed / 8);
    }
}
