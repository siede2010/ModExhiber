package exhiber.content;

import arc.graphics.Color;
import arc.graphics.g2d.Fill;
import arc.graphics.g2d.Lines;
import arc.math.Angles;
import arc.math.Mathf;
import arc.struct.EnumSet;
import arc.struct.Seq;
import exhiber.world.Icon;
import exhiber.world.SquareBaseShiled;
import exhiber.world.block.*;
import exhiber.world.block.crafter.*;
import exhiber.world.block.distribution.*;
import exhiber.world.block.effect.StatusEffectBlock;
import exhiber.world.block.energy.*;
import exhiber.world.block.logic.*;
import exhiber.world.block.unit.*;
import exhiber.world.block.unit.ComponentUnitFactory;
import exhiber.world.block.unit.RepairFactory;
import mindustry.content.*;
import mindustry.entities.Effect;
import mindustry.entities.bullet.*;
import mindustry.entities.effect.ExplosionEffect;
import mindustry.entities.part.DrawPart;
import mindustry.entities.part.RegionPart;
import mindustry.entities.pattern.*;
import mindustry.gen.Sounds;
import mindustry.graphics.CacheLayer;
import mindustry.graphics.Drawf;
import mindustry.graphics.Layer;
import mindustry.graphics.Pal;
import mindustry.type.*;
import mindustry.type.unit.MissileUnitType;
import mindustry.world.Block;
import mindustry.world.blocks.defense.*;
import mindustry.world.blocks.defense.turrets.*;
import mindustry.world.blocks.distribution.*;
import mindustry.world.blocks.environment.*;
import mindustry.world.blocks.liquid.Conduit;
import mindustry.world.blocks.payloads.PayloadConveyor;
import mindustry.world.blocks.power.*;
import mindustry.world.blocks.production.*;
import mindustry.world.blocks.storage.*;
import mindustry.world.blocks.units.*;
import mindustry.world.draw.*;
import mindustry.world.meta.*;

import static arc.graphics.g2d.Draw.color;
import static arc.graphics.g2d.Lines.stroke;
import static exhiber.content.EXItems.*;
import static mindustry.type.ItemStack.with;

public class EXBlocks{
    public static Block
            //Enviroment
            chalkFloor,chalkWall,clayFloor,nitrogenPool,ammoniaPool,
            ClaySolidWall,marmatiteFloor,marmatiteWall,patroniteFloor,patroniteWall,
            zincNitrateFloor,zincNitrateWall,chalkClogs,marmatiteRocks,
            vanadiumFloor,vantraxPlating1,vantraxPlating2,vantraxPlating3,vantraxPlating4,
            metalFloorB,metalFloorH,metalFloorP,corePlate,
            bauxiteFloor,bauxiteWall,feldsparFloor,feldsparWall,graniteFloor,graniteRocks,graniteWall,
            /*Ore's*/clayOre,diamondOre,radiumOre,vanadiumOre,zincOre,copperOre,
            //Effects
            /*Cores*/corePad,coreVessel,coreHeaven,

            vanadiumMender,isolatorVault,smallRainShield,
            //Liquids
            zincConduit,mixUp,
            //Distribution
            zincGutter,zincDispatcher,zincJunction,
            //Forces - Distribution
            smallTrajectoryTower,trajectoryTower,smallTrajectoryCarFabricator,trajectoryCarFabricator,smallTrajectoryUnloader,trajectoryUnloader,
            //Power
            zincWires,copperCoil,kitePad,watermill,powerRouter,windmill,
            //Drills
            chalkScraper,turboDrill,powerDrill,nitroDrill,
            //Crafters
            magnifyingGlass,rainCollector,brassForge,quartzRecrystallizer,tartarusRadiator,tartarusImpulator,
            tunnelExtractor,superfluxForge,diamondCompressor,
            //Turrets
            swift,dispel,reflect,tensor,flow,scourge,effervescence,descent,
            polish,conflict,bind,drumicade,sinusoidal,overfreight,pitchfork,relegation,smithery,
            //Modules
            moduleMK1,moduleMK2,electricModuleEM1,
            //Units
            moduleAssembler,moduleAssemblerLarge,payloadGutter,magnumAssembler,eliteRefabricator,
                    switcher,freezingSwitcher,protectiveSwitcher,fiberRepairStation,componentAssembler,
            //Walls
            brassWall,brassWallLarge,vanadiumWall,vanadiumWallLarge,clayWall,clayWallLarge,

            //Logics
            heatProcessor,

            //Sandbox
            worldBarrier,siegeProducer
            ;

        public static void EXload() {
            vanadiumMender = new MendProjector("vanadium-mender"){{
                requirements(Category.effect,with(EXItems.vanadium,20,EXItems.brass,18,EXItems.rawClay,8));
                localizedName = "Vanadium Mender";
                health = 100;
                healPercent = 10;
                baseColor = Pal.redderDust;
                lightColor = Pal.redderDust;
                phaseColor = Pal.redDust;
                phaseBoost = 2;
                consumePower(30f/60f);
                consumeItem(EXItems.brass).boost();
                size = 1;
                range = 80f;
                useTime = 240;
            }};
            brassWall = new Wall("brass-wall"){{
                requirements(Category.defense,with(EXItems.brass,12));
                localizedName = "Brass Wall";
                size = 1;
                health = 640;

            }};
            brassWallLarge = new Wall("brass-wall-large"){{
                requirements(Category.defense,with(EXItems.brass,48));
                localizedName = "Large Brass Wall";
                size = 2;
                health = 640*4;
            }};
            vanadiumWall = new Wall("vanadium-wall"){{
                requirements(Category.defense,with(EXItems.vanadium,20));
                localizedName = "Vanadium Wall";
                size = 1;
                health = 1200;
            }};
            vanadiumWallLarge = new Wall("vanadium-wall-large"){{
                requirements(Category.defense,with(EXItems.vanadium,80));
                localizedName = "Large Vanadium Wall";
                size = 2;
                health = 1200*4;
            }};
            clayWall = new Wall("clay-wall"){{
                requirements(Category.defense,with(EXItems.rawClay,8,EXItems.quartz,4));
                localizedName = "Clay Wall";
                size = 1;
                health = 120;
                absorbLasers = true;
            }};
            clayWallLarge = new Wall("clay-wall-large"){{
                requirements(Category.defense,with(EXItems.rawClay,32,EXItems.quartz,16));
                localizedName = "Large Clay Wall";
                size = 2;
                health = 120*4;
                absorbLasers = true;
            }};
            moduleMK1 = new Wall("module-mk-1"){{
                requirements(null,with(zinc,20,EXItems.tenorite,10));
                localizedName = "Module-Mk 1";
                health = 1;
                armor = -10;
            }};
            moduleMK2 = new Wall("module-mk-2"){{
                requirements(null,with(EXItems.brass,30,EXItems.fiberGlass,10,EXItems.vanadium,10));
                localizedName = "Module-Mk 2";
                health = 1;
                armor = -10;
            }};
            electricModuleEM1 = new Wall("electric-module-em-1"){{
                requirements(null,with(EXItems.quartz,20,EXItems.tenorite,20));
                localizedName = "Electric Module-EM 1";
                health = 1;
                armor = -10;
            }};
            zincOre = new OreBlock("zinc-ore"){{
                localizedName = "Zinc Ore";
                variants = 3;
                itemDrop = zinc;
                envEnabled = 5;
            }};
            copperOre = new OreBlock("copper-ore"){{
                localizedName = "Copper Ore";
                variants = 3;
                itemDrop = EXItems.tenorite;
                envEnabled = 5;
            }};
            clayOre = new OreBlock("clay-ore"){{
                localizedName = "Clay Ore";
                variants = 3;
                itemDrop = EXItems.rawClay;
                envEnabled = 5;
            }};
            diamondOre = new OreBlock("diamond-ore"){{
                localizedName = "Diamond Ore";
                variants = 3;
                itemDrop = EXItems.diamond;
                envEnabled = 5;
            }};
            radiumOre = new OreBlock("radium-ore"){{
                localizedName = "Radium Ore";
                variants = 3;
                itemDrop = EXItems.radium;
                envEnabled = 5;
            }};
            chalkClogs = new Prop("chalk-clogs"){{
                variants = 2;
                localizedName = "Chalk Clog";
            }};
            chalkFloor = new Floor("chalk-floor",3){{
                localizedName = "Chalk Floor";
                itemDrop = EXItems.chalk;
                decoration = chalkClogs;
                playerUnmineable = true;
                attributes.set(EXAttributes.tunnel,1f/25f);
            }};
            chalkWall = new StaticWall("chalk-wall"){{
                localizedName = "Chalk Wall";
                variants = 2;
            }};
            vanadiumFloor = new Floor("vanadium-floor",3){{
                localizedName = "Vanadium Powder";
            }};
            clayFloor = new Floor("clay-floor",3){{
                localizedName = "Clay Floor";
                attributes.set(EXAttributes.tunnel,1.2f/25f);
            }};
            ClaySolidWall = new StaticWall("clay-solid-wall"){{
                localizedName = "Clay Wall";
                variants = 2;
            }};
            marmatiteRocks = new Prop("marmatite-rocks"){{
                variants = 2;
                localizedName = "Marmatite Rock";
            }};
            marmatiteFloor = new Floor("marmatite-floor",1){{
                localizedName = "Marmatite Floor";
                decoration = marmatiteRocks;
                attributes.set(EXAttributes.tunnel,0.4f/25f);
            }};
            marmatiteWall = new StaticWall("marmatite-wall"){{
                localizedName = "Marmatite Wall";
                variants = 2;
            }};
            patroniteFloor = new Floor("patronite-floor",3){{
                localizedName = "Patronite Floor";
                attributes.set(EXAttributes.tunnel,0.2f/25f);
            }};
            patroniteWall = new StaticWall("patronite-wall"){{
                localizedName = "Patronite Wall";
                variants = 2;
            }};
            zincNitrateFloor = new Floor("zinc-nitrate-floor",3){{
                localizedName = "Zinc Nitrate Floor";
                attributes.set(EXAttributes.tunnel,0.4f/25f);
            }};
            zincNitrateWall = new StaticWall("zinc-nitrate-wall"){{
                localizedName = "Zinc Nitrate Wall";
                variants = 2;
            }};
            vanadiumOre = new OreBlock("vanadium-ore"){{
                localizedName = "Vanadium Ore";
                variants = 3;
                itemDrop = EXItems.vanadium;
                envEnabled = 5;
            }};
            vantraxPlating1 = new Floor("vantrax-plating-1",0){{localizedName = "Vantrax Plating 1";}};
            vantraxPlating2 = new Floor("vantrax-plating-2",0){{localizedName = "Vantrax Plating 2";}};
            vantraxPlating3 = new Floor("vantrax-plating-3",0){{localizedName = "Vantrax Plating 3";}};
            vantraxPlating4 = new Floor("vantrax-plating-4",0){{localizedName = "Vantrax Plating 4";}};
            metalFloorB = new Floor("metal-floor-b",0)
            {{
                localizedName = "Metal Floor B";
                status = StatusEffects.overclock;
                statusDuration = 30f;
            }};
            metalFloorH = new Floor("metal-floor-h",0)
            {{
                localizedName = "Metal Floor B";
                damageTaken = -30f/60f;

            }};
            metalFloorP = new Floor("metal-floor-p",0)
            {{
                localizedName = "Metal Floor P";
                status = EXStatusEffects.antiScabbed;
                statusDuration = 120f;
            }};
            corePlate = new Floor("core-plate",0)
            {{
                allowCorePlacement = true;
                localizedName = "Core Plate";
                status = EXStatusEffects.antiScabbed;
                statusDuration = 120f;
            }};

            bauxiteFloor = new Floor("bauxite-floor",3){{localizedName = "Bauxite Floor";}};
            bauxiteWall = new StaticWall("bauxite-wall"){{localizedName = "Bauxite Wall";variants = 2;}};
            feldsparFloor = new Floor("feldspar-floor",2){{localizedName = "Bauxite Wall";}};
            feldsparWall = new StaticWall("feldspar-wall"){{localizedName = "Feldspar Wall";variants = 2;}};
            graniteFloor = new Floor("granite-floor",3){{localizedName = "Granite Floor";}};
            graniteRocks = new Prop("granite-rocks"){{localizedName = "Granite Rock";variants = 3;}};
            graniteWall = new StaticWall("granite-wall"){{localizedName = "Granite Wall";variants = 2;}};

            nitrogenPool = new Floor("nitrogen-pool",0){{
                attributes.set(EXAttributes.watermill,1f/4f);
                localizedName = "Nitrogen Pool";
                status = StatusEffects.freezing;
                damageTaken = 5f/60f;
                statusDuration = 180f;
                liquidDrop = Liquids.nitrogen;
                isLiquid = true;
                cacheLayer = CacheLayer.water;
                albedo = 0.9f;
            }};

            ammoniaPool = new Floor("ammonia-pool",0){{
                localizedName = "Ammonia Pool";
                status = StatusEffects.corroded;
                damageTaken = 5f/60f;
                statusDuration = 120f;
                mapColor = EXLiquids.ammonia.color;
                liquidDrop = EXLiquids.ammonia;
                isLiquid = true;
                cacheLayer = CacheLayer.water;
                albedo = 0.9f;
            }};

            //Enviorment ends here
            //---------------------
            //Blocks start here

            zincJunction = new Junction("zinc-junction"){{
                requirements(Category.distribution,with(zinc,3,EXItems.chalk,1));
                localizedName = "Zinc Junction";
                capacity = 6;
                size = 1;
                speed = 0.2f;
                health = 65;
                alwaysUnlocked = true;
                envEnabled = 5;
            }};
            zincGutter = new StaticConveyor("zinc-gutter"){{
                requirements(Category.distribution,with(zinc,1));
                speed = 0.05f;
                displayedSpeed = 0.05f;
                size = 1;
                localizedName = "Zinc Gutter";
                health = 110;
                hasShadow = false;
                junctionReplacement = zincJunction;
                envEnabled = 5;
                alwaysUnlocked = true;
            }};
            zincDispatcher = new Router("zinc-dispatcher"){{
                requirements(Category.distribution,with(zinc,6,EXItems.chalk,2));
                localizedName = "Zinc Dispatcher";
                speed = 0.2f;
                size = 1;
                health = 80;
                alwaysUnlocked = true;
                envEnabled = 5;
            }};
            corePad = new CoreBlock("core-pad"){{
                requirements(Category.effect,with(zinc,200,EXItems.diamond,100,EXItems.tenorite,20));
                localizedName = "Core: Pad";
                size = 2;
                health = 780;
                itemCapacity = 2000;
                alwaysUnlocked = true;
                unitCapModifier = 3;
                unitType = EXUnits.cadet;
                envEnabled = 5;
            }};
            coreVessel = new CoreBlock("core-vessel"){{
                requirements(Category.effect,with(zinc,1000,EXItems.quartz,400,EXItems.brass,200));
                localizedName = "Core: Vessel";
                size = 3;
                health = 1520;
                itemCapacity = 6000;
                unitType = EXUnits.cadet;
                alwaysUnlocked = true;
                unitCapModifier = 9;
                envEnabled = 5;
            }};
            chalkScraper = new Drill("chalk-scraper"){{
                requirements(Category.production,with(zinc,20));
               localizedName = "Chalk Scraper";
                size = 2;
               health = 240;
               itemCapacity = 20;
                envEnabled = 5;
                itemDrop = EXItems.chalk;
                drillTime = 100;
                liquidBoostIntensity = 1;
                tier = 1;
            }};
            diamondCompressor = new GenericCrafter("diamond-compressor"){{
                requirements(Category.crafting,with(zinc,80,EXItems.chalk,200,EXItems.tenorite,50));
                localizedName = "Diamond Compressor";
                size = 1;
                craftTime = 150;
                consumeItems(with(EXItems.chalk,8));
                outputItem = new ItemStack(EXItems.diamond,2);
                craftEffect = Fx.smoke;
            }};
            zincConduit = new Conduit("zinc-conduit"){{
                requirements(Category.liquid,with(zinc,2,EXItems.diamond,2));
               localizedName = "Zinc Conduit";
               health = 40;
                envEnabled = 5;
            }};
            zincWires = new PowerCable("zinc-wire"){{
                requirements(Category.power,with(zinc,2,EXItems.tenorite,1));
                localizedName = "Zinc Wire";
                health = 65;
                hasShadow = false;
            }};
            copperCoil = new Battery("copper-coil"){{
                requirements(Category.power,with(zinc,20,EXItems.tenorite,20,EXItems.rawClay,10));
                localizedName = "Tenorite Coil";
                consumePowerBuffered(5000);
                emptyLightColor = Color.valueOf("e0b38d");
                fullLightColor = Color.valueOf("d99e6b");
                size = 2;
                health = 420;
            }};
            magnifyingGlass = new MultiLiquifier("magnifying-glass",Seq.with(zinc,EXItems.brass,EXItems.tenorite,EXItems.vanadium),Seq.with(EXLiquids.liquidZinc,EXLiquids.liquidBrass,EXLiquids.liquidTenorite,EXLiquids.liquidVanadium)){{
                requirements(Category.crafting,with(zinc,80,EXItems.tenorite,40,EXItems.diamond,80));
                localizedName = "Magnifying Glass";
                liquidCapacity = 300;
                itemCapacity = 10;
                craftTime = 60;
                liquidAmount = 10f/60f;
                size = 3;
                craftEffect = Fx.absorb;
            }};
            rainCollector = new WeatherCrafter("rain-collector", EXWeather.diamondRain){{
                requirements(Category.crafting,with(zinc,30,EXItems.chalk,20));
                localizedName = "Rain Collector";
                size = 2;
                craftTime = 160;
                outputItem = new ItemStack(EXItems.diamond,1);
                craftEffect = ExEffects.collectRain;
            }};
            swift = new ItemTurret("swift"){{
                requirements(Category.turret,with(zinc,12,EXItems.diamond,8));
                localizedName = "Swift";
                range = 18*8;
                reload = 80;
                shoot.shots = 2;
                shoot.shotDelay = 10f;
                health = 320;
                ammo(
                        EXItems.chalk, new BasicBulletType(4,18){{
                            reloadMultiplier = 1.5f;
                            lifetime = setRange(18,4);
                            ammoMultiplier = 4;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailWidth = 2;
                            trailLength = 10;
                            trailColor = backColor = lightColor = Color.white;
                            hitEffect = despawnEffect = ExEffects.chalkAoe;
                            splashDamage = 14;
                            splashDamageRadius = 8;
                        }},
                        EXItems.diamond, new BasicBulletType(4,45){{
                            lifetime = setRange(18,4);
                            reloadMultiplier = 1.2f;
                            ammoMultiplier = 2;
                            weaveScale = 4;
                            weaveMag = 4;
                            trailWidth = 2;
                            trailLength = 10;
                            trailColor = backColor = lightColor = Pal.lancerLaser;
                            status = EXStatusEffects.scabbed;
                            statusDuration = 180;
                        }}
                );
                drawer = new DrawTurret(name("gas"));
            }};
            dispel = new ItemTurret("dispel"){{
                requirements(Category.turret,with(zinc,20,EXItems.tenorite,8));
                localizedName = "Dispel";
                description = "Rapidly fires homing pellets. With devistation damage.";
                health = 320;
                range = 120;
                targetAir = false;
                reload = 20;
                inaccuracy = 10;
                ammo(
                        zinc, new BasicBulletType(2.6f,18){{
                            reloadMultiplier = 1.5f;
                            lifetime = 60f;
                            frontColor = Color.valueOf("eeeeff");
                            backColor = Color.valueOf("ddddee");
                            ammoMultiplier = 1;
                            trailWidth = 2;
                            trailLength = 20;
                            trailColor = Color.valueOf("eeeeff");
                            homingPower = 0.05f;
                            intervalAngle = -180;
                            intervalBullets = 2;
                            intervalRandomSpread = 0;
                            intervalSpread = 90;
                            bulletInterval = 10;
                            intervalBullet = new BasicBulletType(1,18){{
                                backColor = lightColor = trailColor = Color.valueOf("eeeeff");
                                lifetime = 10;
                                trailWidth = 1;
                                trailLength = 20;
                            }};
                            collidesAir = false;
                        }},
                        EXItems.tenorite, new BasicBulletType(5.2f,46){{
                            reloadMultiplier = 0.5f;
                            lifetime = 30f;
                            ammoMultiplier = 2;
                            trailWidth = 2;
                            trailLength = 20;
                            homingPower = 0.05f;
                            intervalAngle = -180;
                            intervalBullets = 2;
                            intervalRandomSpread = 0;
                            intervalSpread = 90;
                            bulletInterval = 5;
                            intervalBullet = new BasicBulletType(1,32){{
                                lifetime = 30;
                                drag = 0.05f;
                                trailWidth = 1;
                                trailLength = 20;
                            }};
                            collidesAir = false;
                        }},
                        EXItems.brass, new BasicBulletType(5.2f,26){{
                            reloadMultiplier = 1.6f;
                            lifetime = 31f;
                            frontColor = Color.valueOf("ffff11");
                            backColor = Color.valueOf("eeee00");
                            ammoMultiplier = 2;
                            trailColor = Color.valueOf("eeee00");
                            trailWidth = 2;
                            trailLength = 20;
                            homingPower = 0.05f;
                            intervalAngle = -180;
                            intervalBullets = 2;
                            intervalRandomSpread = 0;
                            intervalSpread = 90;
                            bulletInterval = 10;
                            pierce = true;
                            pierceCap = 2;
                            intervalBullet = new BasicBulletType(1,24){{
                                backColor = lightColor = trailColor = Color.valueOf("eeee00");
                                lifetime = 60;
                                drag = 0.1f;
                                trailWidth = 1;
                                trailLength = 20;
                            }};
                            collidesAir = false;
                        }}
                );
                drawer = new DrawTurret(name("gas"));
            }};
            reflect = new ContinuousTurret("reflect"){{
                requirements(Category.turret,with(zinc,16,EXItems.quartz,8,EXItems.diamond,12));
                localizedName = "Reflect";
                range = 110;
                reload = 40;
                consumePower(6f/60f);
                shootType = new PointLaserBulletType(){{
                    damage = 14f;
                    status = StatusEffects.burning;
                    statusDuration = 120f;
                    maxRange = range;
                }};
                drawer = new DrawTurret(name("gas"));
            }};
            tensor = new ItemTurret("tensor"){{
                requirements(Category.turret,with(EXItems.brass,14,EXItems.rawClay,10,EXItems.vanadium,10));
                localizedName = "Tensor";
                description = "[WHO LEFT THE TODO'S OUT]";
                health = 320;
                targetAir = false;
                range = 250;
                reload = 150;
                ammo(
                        EXItems.rawClay, new ArtilleryBulletType(){{
                            damage = 14;
                            speed = 2;
                            splashDamage = 25;
                            splashDamageRadius = 8f;
                            trailChance = 100;
                            trailInterval = 0;
                            trailColor = Pal.redDust;
                            collidesAir = false;
                        }},
                        EXItems.vanadium, new ArtilleryBulletType(){{
                            damage = 25;
                            speed = 2;
                            splashDamage = 9;
                            splashDamageRadius = 16f;
                            trailChance = 100;
                            trailInterval = 0;
                            trailColor = Pal.redderDust;
                            collidesAir = false;
                        }}
                );
                drawer = new DrawTurret(name("gas"));
            }};
            flow = new ItemTurret("flow"){{
                requirements(Category.turret,with(zinc,40,EXItems.rawClay,20,EXItems.vanadium,20));
                localizedName = "Flow";
                size = 2;
                health = 1200;
                reload = 50;
                shoot = new ShootSpread(3,10);
                shootCone = 10;
                range = 19*8;
                ammo(
                        EXItems.chalk,new BasicBulletType(2,20){{
                            reloadMultiplier = 1.5f;
                            lifetime = 60f;
                            ammoMultiplier = 4;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailChance = 100;
                            trailInterval = 0;
                        }},
                        EXItems.diamond,new BasicBulletType(2,26){{
                            reloadMultiplier = 1.3f;
                            lifetime = 60f;
                            ammoMultiplier = 4;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailChance = 100;
                            trailInterval = 0;
                        }},
                        EXItems.quartz,new BasicBulletType(2,34){{
                            lifetime = 60f;
                            ammoMultiplier = 4;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailChance = 100;
                            trailInterval = 0;
                        }}
                );
                drawer = new DrawTurret(name("gas"));
            }};
            scourge = new ItemTurret("scourge"){{
                requirements(Category.turret,with(EXItems.brass,20,EXItems.rawClay,10,EXItems.fiberGlass,4));
                localizedName = "Scourge";
                size = 2;
                health = 1200;
                reload = 60f/1.8f;
                recoil = 4;
                targetAir = false;
                minWarmup = 0.85f;
                shootWarmupSpeed = 0.1f;
                shoot = new ShootSpread(4,10);
                shootCone = 10;
                range = 9*8;
                ammo(
                    EXItems.rawClay,new BasicBulletType(3,10){{
                            lifetime = setRange(9,3);
                            reloadMultiplier = 2f;
                            knockback = 2f;
                            width = 25f;
                            hitSize = 7f;
                            height = 20f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            ammoMultiplier = 1;
                            hitColor = backColor = trailColor = Color.valueOf("ea8878");
                            frontColor = Pal.redLight;
                            trailWidth = 6f;
                            trailLength = 3;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                            collidesAir = false;
                        }},
                        EXItems.quartz,new BasicBulletType(4,26){{
                            lifetime = setRange(9,4);
                            status = EXStatusEffects.crunched;
                            statusDuration = 7f*60f;
                            knockback = 2f;
                            width = 25f;
                            hitSize = 7f;
                            height = 20f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            ammoMultiplier = 1;
                            hitColor = backColor = trailColor = Color.valueOf("ea8878");
                            frontColor = Pal.redLight;
                            trailWidth = 6f;
                            trailLength = 3;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                            collidesAir = false;
                        }},
                        EXItems.fiberGlass,new BasicBulletType(5,62){{
                            lifetime = setRange(9,5);
                            reloadMultiplier = 0.6f;

                            status = EXStatusEffects.crunched;
                            statusDuration = 18f*60f;
                            knockback = 2f;
                            width = 25f;
                            hitSize = 7f;
                            height = 20f;
                            shootEffect = Fx.shootBigColor;
                            smokeEffect = Fx.shootSmokeSquareSparse;
                            ammoMultiplier = 1;
                            hitColor = backColor = trailColor = Color.valueOf("ea8878");
                            frontColor = Pal.redLight;
                            trailWidth = 6f;
                            trailLength = 3;
                            hitEffect = despawnEffect = Fx.hitSquaresColor;
                            collidesAir = false;
                        }}
                );
                drawer = new DrawTurret(name("gas")){{
                    parts.addAll(
                            new RegionPart("-barrel1"){{
                                progress = PartProgress.warmup;
                                mirror = false;
                                moveX = -2f * 4f / 3f;
                                moveY = -0.5f;
                                moveRot = 40f;
                                under = true;
                            }},
                            new RegionPart("-barrel2"){{
                                progress = PartProgress.warmup;
                                mirror = false;
                                moveX = 2f * 4f / 3f;
                                moveY = -0.5f;
                                moveRot = -40f;
                                under = true;
                            }}
                    );
                }};
            }};
            effervescence = new ItemTurret("effervescence"){{
                requirements(Category.turret,with(EXItems.vanadium,20,EXItems.radium,8,EXItems.fiberGlass,4));
                localizedName = "Effervescence";
                size = 2;
                health = 1200;
                reload = 60f/12f;
                recoil = 4;
                shootWarmupSpeed = 0.01f;
                targetGround = false;
                inaccuracy = 5;
                consumePower(24f/60f);
                range = 190f;
                ammo(
                        EXItems.vanadium,new LaserBoltBulletType(8f,9){{
                            lifetime = setRange(23.75f,8f);
                            ammoMultiplier = 1;
                            status = EXStatusEffects.infrafluxed;
                            statusDuration = 4f*60f;
                            backColor = Pal.heal;
                            frontColor = Color.white;
                            collidesGround = false;
                        }}
                );
                drawer = new DrawTurret(name("gas")){{
                            parts.add(new RegionPart("-gun"){{
                                    recoil = 2;
                                  mirror = false;
                                  under = false;
                              }});
                            parts.add(
                            new RegionPart("-r2"){{
                                          mirror = false;
                                          under = true;
                                          moveX = 1.75f;
                                          moveY = -0.5f;
                            }},
                            new RegionPart("-l2"){{
                                          mirror = false;
                                          under = true;
                                          moveX = -1.75f;
                                          moveY = -0.5f;
                            }},
                                    new RegionPart("-r1"){{
                                        mirror = false;
                                        under = true;
                                        moveY = 1f;
                                        moveX = 1.5f;
                                    }},
                                    new RegionPart("-l1"){{
                                        mirror = false;
                                        under = true;
                                        moveY = 1f;
                                        moveX = -1.5f;
                                    }}
                    );
                }};
            }};
            descent = new PowerTurret("descent")
            {{
                requirements(Category.turret,with(EXItems.vanadium,12,EXItems.radium,8,EXItems.hyperfluxMatter,4));
                localizedName = "Descent";
                size = 2;
                health = 1600;
                reload = 60f/4f;
                recoil = 1f;
                shake = 0.4f;
                targetAir = false;
                consumePower(30f/60f);
                range = 12f*8f;
                shootType = new SapBulletType()
                {{
                    shootY = 1;
                    sapStrength = 0;
                    length = 12f*8f;
                    damage = 16;
                    color = lightColor = trailColor = Pal.ammo;
                    shootEffect = Fx.shootSmall;
                    hitColor = color = Color.valueOf("bf92f9");
                    despawnEffect = Fx.none;
                    status = StatusEffects.sapped;
                    statusDuration = 240f;
                    width = 0.55f;
                    lifetime = 30f;
                    knockback = -1f;
                    collidesAir = false;
                }};
                var heatp = DrawPart.PartProgress.warmup.blend(p -> Mathf.absin(2f, 1f) * p.warmup, 0.2f);
                drawer = new DrawTurret(name("gas")){{
                    parts.addAll(

                    parts.add(new RegionPart("-l1"){{
                                  progress = PartProgress.warmup;
                                  heatProgress = PartProgress.warmup;
                                  heatColor = Color.valueOf("ff6214");
                                  mirror = false;
                                  under = true;
                                  moveX = 1f;
                                  moveRot = -7f;
                                  moves.add(new PartMove(PartProgress.warmup, 0f, -2f, 3f));
                              }},
                            new RegionPart("-r1"){{
                                progress = PartProgress.warmup;
                                heatProgress = PartProgress.warmup;
                                heatColor = Color.valueOf("ff6214");
                                mirror = false;
                                under = true;
                                moveX = -1f;
                                moveRot = 7f;
                                moves.add(new PartMove(PartProgress.warmup, 0f, -2f, -3f));
                            }},
                            new RegionPart("-gun"){{
                                heatProgress = heatp;
                                progress = PartProgress.warmup;
                                heatColor = Color.valueOf("ff6214");
                                moveY = -3f;
                                mirror = false;
                                under = true;
                            }}));
                }};
            }};
            polish = new TractorBeamTurret("polish"){{
                requirements(Category.turret,with(EXItems.vanadium,30,EXItems.fiberGlass,20,EXItems.hyperfluxMatter,6));
                localizedName = "Polish";
                size = 2;
                health = 1600;
                targetGround = true;
                damage = 27f/60f;
                range = 32*8;
                force = 2;
                scaledForce = 3;
                status = EXStatusEffects.polishHit;
                statusDuration = 60f;
                rotateSpeed = 10;
                consumePower(60f/60f);
                consumeLiquid(EXLiquids.ammonia,16f/60f);
            }};
            conflict = new ItemTurret("conflict"){{
                requirements(Category.turret,with(zinc,100,EXItems.vanadium,35,EXItems.brass,30,EXItems.chalk,30));
                localizedName = "Conflict";
                size = 2;
                health = 1800;
                reload = 60f/8f;
                drawer = new DrawTurret(name("gas"));
                recoil = 1;
                range = 22*8;
                itemCapacity = 10;
                inaccuracy = 8;
                ammo(
                        zinc,new BasicBulletType(4,15)
                        {{
                            lifetime = setRange(22,4);
                            trailWidth = 2;
                            trailColor = frontColor = backColor = lightColor = zinc.color;
                            trailLength = 22;
                            ammoMultiplier = 2f;
                        }},
                        EXItems.diamond,new BasicBulletType(6,12)
                        {{
                            reloadMultiplier = 1.4f;
                            lifetime = setRange(22,6);
                            trailWidth = 2;
                            trailColor = frontColor = backColor = lightColor = EXItems.diamond.color;
                            trailLength = 22;
                            ammoMultiplier = 3f;
                        }},
                        EXItems.quartz,new BasicBulletType(8,25)
                        {{
                            reloadMultiplier = 0.9f;
                            lifetime = setRange(22,8);
                            trailWidth = 2;
                            trailColor = frontColor = backColor = lightColor = EXItems.quartz.color;
                            trailLength = 22;
                            ammoMultiplier = 1f;
                        }}
                );
            }};
            drumicade = new ItemTurret("drumicade")
            {{
                requirements(Category.turret,with(EXItems.tenorite,120,EXItems.brass,80,EXItems.fiberGlass,20,EXItems.vanadium,20));
                localizedName = "Drumicade";
                shoot = new ShootSpread(4,10);
                recoil = 6;
                range = 4.6f * (0.8f * 60f);
                reload = 60f/1.4f;
                size = 3;
                health = 2100;
                ammo(
                    EXItems.vanadium,new BulletType()
                        {{
                                shootEffect = Fx.unitSpawn;
                                hitColor = Pal.suppress;
                                speed = 0f;
                                hitShake = 3;
                                shake = 3;
                                keepVelocity = false;
                                spawnUnit = new MissileUnitType("drumicade-missile-van") {{
                                    lifetime = 0.8f * 60f;
                                    speed = 4.6f;
                                    maxRange = 5f;
                                    health = 70;
                                    rotateSpeed *= 1.5f;
                                    homingDelay = 20f;
                                    homingPower = 0.02f;
                                    trailColor = lightColor = EXItems.vanadium.color;
                                    lowAltitude = true;
                                    engineSize = 3f;
                                    deathExplosionEffect = Fx.none;
                                    weapons.add(new Weapon() {
                                        {
                                            shootCone = 360f;
                                            mirror = false;
                                            reload = 1f;
                                            shootOnDeath = true;
                                            bullet = new ExplosionBulletType(63f, 8f) {
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
                                                    statusDuration = 180;
                                                }
                                            };
                                        }
                                    });
                                }};
                        }},
                        EXItems.fiberGlass,new BulletType()
                        {{
                                shootEffect = Fx.unitSpawn;
                                hitColor = Pal.suppress;
                                speed = 0f;
                                hitShake = 3;
                                shake = 3;
                                keepVelocity = false;
                                spawnUnit = new MissileUnitType("drumicade-missile-fiber") {{
                                    lifetime = 0.8f * 60f;
                                    speed = 4.6f;
                                    maxRange = 5f;
                                    health = 70;
                                    rotateSpeed *= 1.5f;
                                    homingDelay = 20f;
                                    homingPower = 0.02f;
                                    trailColor = lightColor = EXItems.fiberGlass.color;
                                    lowAltitude = true;
                                    engineSize = 3f;
                                    deathExplosionEffect = Fx.none;
                                    weapons.add(new Weapon() {
                                        {
                                            shootCone = 360f;
                                            mirror = false;
                                            reload = 1f;
                                            shootOnDeath = true;
                                            bullet = new ExplosionBulletType(48f, 8f*3f) {
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
                                                    status = EXStatusEffects.crunched;
                                                    statusDuration = 12*60f;
                                                    fragBullets = 3;
                                                    fragBullet = new BasicBulletType(4,20)
                                                    {{
                                                        lifetime = setRange(6,4);
                                                        trailColor = frontColor = backColor = lightColor = EXItems.fiberGlass.color;
                                                    }};
                                                }
                                            };
                                        }
                                    });
                                }};
                        }},
                        zinc,new BulletType()
                        {{
                            reloadMultiplier = 1.25f;
                                shootEffect = Fx.unitSpawn;
                                hitColor = Pal.suppress;
                                speed = 0f;
                                hitShake = 3;
                                shake = 3;
                                keepVelocity = false;
                                spawnUnit = new MissileUnitType("drumicade-missile") {{
                                    lifetime = 0.8f * 60f;
                                    speed = 4.6f;
                                    maxRange = 5f;
                                    health = 70;
                                    rotateSpeed *= 1.5f;
                                    homingDelay = 20f;
                                    homingPower = 0.02f;
                                    trailColor = lightColor = zinc.color;
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
                        }}
                );
                drawer = new DrawTurret(name("gas")){{
                    parts.addAll(
                            new RegionPart("-barrels"){{
                                progress = PartProgress.recoil;
                                moveY = -4f;
                            }}
                    );
                }};
            }};
            sinusoidal = new ItemTurret("sinusoidal"){{
                requirements(Category.turret,with(zinc,200,EXItems.vanadium,70,EXItems.brass,80,EXItems.fiberGlass,10));
                localizedName = "Sinusoidal";
                size = 3;
                recoil = 4f;
                reload = 60f/0.8f;
                inaccuracy = 1f;
                liquidCapacity = 10f;
                shootEffect = Fx.shootLiquid;
                shoot = new ShootSpread(12,7.5f);
                health = 2300;
                flags = EnumSet.of(BlockFlag.turret, BlockFlag.extinguisher);
                range = 140;
                shootY = 1;
                ammo(
                        EXItems.chalk,new BasicBulletType(2,20){{
                            reloadMultiplier = 1.5f;
                            lifetime = setRange(140f/8f,2f);
                            ammoMultiplier = 4;
                            weaveRandom = false;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailWidth = 2;
                            trailLength = 20;
                        }},
                        EXItems.diamond,new BasicBulletType(2,26){{
                            reloadMultiplier = 1.5f;
                            lifetime = setRange(140f/8f,2f);
                            ammoMultiplier = 4;
                            weaveRandom = false;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailWidth = 2;
                            trailLength = 20;
                        }},
                        EXItems.quartz,new BasicBulletType(2,34){{
                            reloadMultiplier = 1.5f;
                            lifetime = setRange(140f/8f,2f);
                            ammoMultiplier = 4;
                            weaveRandom = false;
                            weaveScale = 3;
                            weaveMag = 3;
                            trailWidth = 2;
                            trailLength = 20;
                        }}
                );
                drawer = new DrawTurret(name("gas")){{
                    parts.addAll(
                            new RegionPart("-l"){{
                                progress = PartProgress.warmup;
                                moveRot = 10;
                                moveX = -1;
                                moveY = -1;
                            }},
                            new RegionPart("-r"){{
                                progress = PartProgress.warmup;
                                moveRot = -10;
                                moveX = 1;
                                moveY = -1;
                            }}
                    );
                }};
            }};
            overfreight = new ItemTurret("overfreight")
            {{
                requirements(Category.turret,with(EXItems.tenorite,150,EXItems.quartz,40,EXItems.radium,40));
                localizedName = "Overfreight";
                size = 3;
                reload = 180;
                range = 8*32;
                targetAir = false;
                consumePower(100f/60f);
                ammo(
                        EXItems.radium,new EmpBulletType(){{
                            float rad = 100f;

                            scaleLife = true;
                            lightOpacity = 0.7f;
                            unitDamageScl = 0.8f;
                            healPercent = 20f;
                            //timeIncrease = 3f;
                            timeIncrease = 0;
                            //timeDuration = 60f * 20f;
                            timeDuration = 0;
                            powerDamageScl = 3f;
                            damage = 120;
                            hitColor = lightColor = Pal.heal;
                            lightRadius = 70f;
                            clipSize = 250f;
                            shootEffect = Fx.hitEmpSpark;
                            smokeEffect = Fx.shootBigSmoke2;
                            lifetime = 60f;
                            sprite = "circle-bullet";
                            backColor = Pal.heal;
                            frontColor = Color.white;
                            width = height = 12f;
                            shrinkY = 0f;
                            speed = 5f;
                            trailLength = 20;
                            trailWidth = 6f;
                            trailColor = Pal.heal;
                            trailInterval = 3f;
                            splashDamage = 120f;
                            splashDamageRadius = rad;
                            hitShake = 4f;
                            trailRotation = true;
                            status = StatusEffects.electrified;
                            hitSound = Sounds.plasmaboom;
                            collidesAir = false;

                            trailEffect = new Effect(16f, e -> {
                                color(Pal.heal);
                                for(int s : Mathf.signs){
                                    Drawf.tri(e.x, e.y, 4f, 30f * e.fslope(), e.rotation + 90f*s);
                                }
                            });

                            hitEffect = new Effect(50f, 100f, e -> {
                                e.scaled(7f, b -> {
                                    color(Pal.heal, b.fout());
                                    Fill.circle(e.x, e.y, rad);
                                });

                                color(Pal.heal);
                                stroke(e.fout() * 3f);
                                Lines.circle(e.x, e.y, rad);

                                int points = 10;
                                float offset = Mathf.randomSeed(e.id, 360f);
                                for(int i = 0; i < points; i++){
                                    float angle = i* 360f / points + offset;
                                    //for(int s : Mathf.zeroOne){
                                    Drawf.tri(e.x + Angles.trnsx(angle, rad), e.y + Angles.trnsy(angle, rad), 6f, 50f * e.fout(), angle/* + s*180f*/);
                                    //}
                                }

                                Fill.circle(e.x, e.y, 12f * e.fout());
                                color();
                                Fill.circle(e.x, e.y, 6f * e.fout());
                                Drawf.light(e.x, e.y, rad * 1.6f, Pal.heal, e.fout());
                            });
                        }}
                );
                drawer = new DrawTurret(name("gas"));
            }};
            pitchfork = new ItemTurret("pitchfork")
            {{
                //requirements(Category.production,with(EXItems.zinc,1));
                localizedName = "Pitchfork";
                size = 3;
                drawer = new DrawTurret(name("gas"));
            }};
            turboDrill = new Drill("turbo-drill"){{
                requirements(Category.production,with(zinc,30,EXItems.chalk,10));
                localizedName = "Turbo Drill";
                tier = 4;
                size = 2;
                drillTime = 300;
                liquidBoostIntensity = 1;
            }};
            powerDrill = new Drill("power-drill"){{
                requirements(Category.production,with(EXItems.tenorite,40,EXItems.brass,20,EXItems.chalk,20));
                localizedName = "Power Drill";
                tier = 5;
                consumesPower = true;
                consumePower(30f/60f);
                size = 2;
                drillTime = 180;
                liquidBoostIntensity = 2.5f;
                consumeLiquid(Liquids.nitrogen,0.1f).boost();
            }};
            nitroDrill = new Drill("nitro-drill"){{
                requirements(Category.production,with(EXItems.tenorite,40,EXItems.brass,20,EXItems.chalk,20,EXItems.vanadium,40)); //todo
                localizedName = "Nitro Drill";
                tier = 8;
                consumesPower = true;
                consumePower(200f/60f);
                size = 3;
                drillTime = 160;
                consumeLiquid(Liquids.nitrogen,12f/60f);
            }};
            moduleAssembler = new SelectiveConstructor("module-assembler",new Block[]{moduleMK1,swift,dispel,brassWall,vanadiumWall,vanadiumMender,clayWall,electricModuleEM1,reflect,tensor}){{
                requirements(Category.units,with(zinc,100,EXItems.diamond,20,EXItems.chalk,80));
                localizedName = "Module Assembler";
                maxBlockSize = 3;
                minBlockSize = 0;
                size = 3;

            }};
            payloadGutter = new PayloadConveyor("payload-gutter"){{
                requirements(Category.units,with(zinc,20,EXItems.chalk,8,EXItems.tenorite,10));
                localizedName = "Payload Gutter";
                size = 3;
            }};
            brassForge = new GenericCrafter("brass-forge"){{
                requirements(Category.crafting,with(zinc,60,EXItems.chalk,40,EXItems.diamond,40));
                localizedName = "Brass Forge";
                size = 3;
                consumeLiquid(EXLiquids.liquidZinc,6f/60f);
                consumeLiquid(EXLiquids.liquidTenorite,8f/60f);
                outputItem = new ItemStack(EXItems.brass,1);
                craftTime = 120;
                updateEffectChance = 0.1f;
                hasLiquids = true;
                craftEffect = ExEffects.brassCraft;
                drawer = new DrawMulti(
                        new DrawMulti(
                            new DrawRegion("-bottom1"),
                            new DrawLiquidTile(EXLiquids.liquidZinc, 2f),
                            new DrawBubbles(Color.valueOf(EXLiquids.liquidZinc.color.toString())){{
                                sides = 10;
                                recurrence = 3f;
                                spread = 6;
                                radius = 1.5f;
                                amount = 20;
                            }}
                        ),
                        new DrawMulti(
                            new DrawRegion("-bottom2"),
                            new DrawLiquidTile(EXLiquids.liquidTenorite, 2f),
                            new DrawBubbles(Color.valueOf(EXLiquids.liquidTenorite.color.toString())){{
                                sides = 10;
                                recurrence = 3f;
                                spread = 6;
                                radius = 1.5f;
                                amount = 20;
                            }},
                            new DrawLiquidOutputs()
                        ),
                        new DrawRegion("-bottom3"),
                        new DrawRegion()
                );
            }};
            kitePad = new WeatherGenerator("kite-pad", Weathers.fog){{
                requirements(Category.power,with(EXItems.tenorite,20,EXItems.diamond,10,EXItems.brass,6));
                localizedName = "Kite Pad";
                size = 3;
                powerProduction = 1f;
                outputsPower = true;
            }};
            watermill = new ThermalGenerator("water-mill"){{
                requirements(Category.power,with(EXItems.diamond,20,EXItems.vanadium,20));
                localizedName = "Water Mill";
                size = 2;
                attribute = EXAttributes.watermill;
                outputLiquid = new LiquidStack(Liquids.nitrogen,6f/60f);
                powerProduction = 12f/60f;
            }};
            tartarusRadiator = new GenericCrafter("tartarus-radiator"){{
                requirements(Category.crafting,with(zinc,400,EXItems.vanadium,400,EXItems.brass,200,EXItems.quartz,200));
                localizedName = "Tartarus Radiator";
                health = 500;
                size = 4;
                consumeItems(with(EXItems.quartz,4,EXItems.chalk,2));
                craftTime = 180;
                consumePower(100f/60f);
                outputItem = new ItemStack(EXItems.fiberGlass,1);
                craftEffect = Fx.fireHit;
                updateEffect = Fx.smoke;
                updateEffectChance = 0.1f;
            }};
            tartarusImpulator = new GenericCrafter("tartarus-impulator"){{
                requirements(Category.crafting,with(zinc,650,EXItems.vanadium,820,EXItems.brass,560,EXItems.quartz,420,EXItems.fiberGlass,50));
                localizedName = "Tartarus Impulator";
                itemCapacity = 16;
                health = 1400;
                size = 5;
                consumeItems(with(EXItems.quartz,6,EXItems.chalk,6,EXItems.diamond,2));
                craftTime = 100;
                consumePower(240f/60f);
                outputItem = new ItemStack(EXItems.fiberGlass,3);
                craftEffect = Fx.ballfire;
                updateEffect = Fx.producesmoke;
                updateEffectChance = 0.1f;
            }};
            magnumAssembler = new UnitAssembler("magnum-assembler"){{
                requirements(Category.units,with( zinc,100,EXItems.tenorite,60,EXItems.brass,60));
                 localizedName = "Magnum Assembler";
                 size = 3;
                 consumePower(20f/60f);
                 areaSize = 3;
                droneType = EXUnits.maxwell;
                plans.add(
                        new AssemblerUnitPlan(EXUnits.loner, 60f * 20f, PayloadStack.list( brassWall,2,dispel,1)),
                        new AssemblerUnitPlan(EXUnits.sentry, 60f * 15f, PayloadStack.list( brassWall,1,swift,1)),
                        new AssemblerUnitPlan(EXUnits.polar, 60f * 18f, PayloadStack.list( clayWall,1,reflect,1)),
                        new AssemblerUnitPlan(EXUnits.bonfire, 60f * 20f, PayloadStack.list( vanadiumWall, 1,vanadiumMender,2))
                );
            }};
            switcher = new UnitAssemblerModule("switcher"){{
                requirements(Category.units,with());
                description = "Switches the assembler to produce a Stealth unit";
                localizedName = "Stealth Switcher";
                size = 1;
            }};
            freezingSwitcher = new UnitAssemblerModule("freezing-switcher"){{
                requirements(Category.units,with());
                description = "Switches the assembler to produce a Freezing unit";
                localizedName = "Freezing Switcher";
                size = 1;
                tier = 2;
            }};
            protectiveSwitcher = new UnitAssemblerModule("protective-switcher"){{
                requirements(Category.units,with());
                description = "Switches the assembler to produce a Protective unit";
                localizedName = "Protective Switcher";
                size = 1;
                tier = 3;
            }};
            powerRouter = new PowerNode("power-router"){{
                requirements(Category.power,with(EXItems.brass,4,EXItems.rawClay,2));
                localizedName = "Power Router";
                laserRange = 2;
                consumePowerBuffered(1);
            }};
            quartzRecrystallizer = new GenericCrafter("quartz-recrystallizer"){{
                requirements(Category.crafting,with(EXItems.brass,50,EXItems.chalk,20,EXItems.diamond,20));
                localizedName = "Quartz Recrystallizer";
                size = 3;
                craftTime = 0.8f*60f;
                consumePower(20f/60f);
                consumeItems(with(EXItems.chalk,2,EXItems.rawClay,1));
                outputItem = new ItemStack(EXItems.quartz,1);
                craftEffect = Fx.producesmoke;
            }};
            windmill = new ConsumeGenerator("wind-mill"){{
                requirements(Category.power,with(zinc,20,EXItems.tenorite,12,EXItems.chalk,12));
                localizedName = "Wind Mill";
                powerProduction = 8f/60f;
                size = 2;

            }};
            eliteRefabricator = new UnitAssembler("elite-fabricator"){{
                requirements(Category.units,with( zinc,200,EXItems.quartz,60,EXItems.brass,100,EXItems.rawClay,100,EXItems.vanadium,50));
                localizedName = "Elite Fabricator";
                size = 5;
                health = 2800;
                consumePower(120f/60f);
                areaSize = 5;
                droneType = EXUnits.maxwell;
                consumeLiquid(EXLiquids.liquidBrass,8f/60f);
                plans.add(
                        new AssemblerUnitPlan(EXUnits.rouge, 60f * 20f, PayloadStack.list( vanadiumWallLarge,2,EXUnits.loner,2,scourge,1)),
                        new AssemblerUnitPlan(EXUnits.stalker, 60f * 15f, PayloadStack.list( flow,1,brassWallLarge,2,EXUnits.sentry,2)),
                        new AssemblerUnitPlan(EXUnits.brainFreeze,60f*30f,PayloadStack.list(EXUnits.polar,2,clayWallLarge,2,effervescence,1)),
                        new AssemblerUnitPlan(EXUnits.campfire,60f*30f,PayloadStack.list(vanadiumWallLarge,2,EXUnits.loner,1,EXUnits.bonfire,1,EXUnits.polar,1))
                );
            }};
            moduleAssemblerLarge = new SelectiveConstructor("large-module-assembler",new Block[] {moduleMK2,clayWallLarge,brassWallLarge,vanadiumWallLarge,flow,scourge,effervescence}){{
                requirements(Category.units,with(zinc,1200,EXItems.vanadium,200,EXItems.fiberGlass,200));
                localizedName = "Large Module Assembler";
                maxBlockSize = 3;
                minBlockSize = 0;
                size = 5;
            }};
            isolatorVault = new StorageBlock("isolator-vault"){{
                requirements(Category.effect,with(EXItems.rawClay,40,EXItems.vanadium,20));
                localizedName = "Isolator Vault";
                itemCapacity = 1200;
                size = 2;
            }};
            fiberRepairStation = new RepairFactory("fiber-repair-station"){{
                requirements(Category.units,with(EXItems.fiberGlass,25, zinc,120,EXItems.brass,80));
                localizedName = "Fiber Repair Station";
                description = "Heals both Tier 1 and Tier 2 Units";
                consumeItems(with(EXItems.brass,5,EXItems.chalk,20));
                itemCapacity = 40;
                size = 3;
                constructTime = 300;
                addUpgrade(EXUnits.loner);
                addUpgrade(EXUnits.campfire);
                addUpgrade(EXUnits.polar);
                addUpgrade(EXUnits.sentry);

                addUpgrade(EXUnits.bonfire);
                addUpgrade(EXUnits.rouge);
                addUpgrade(EXUnits.stalker);
                addUpgrade(EXUnits.brainFreeze);

                addUpgrade(EXUnits.cadet);
            }};
            componentAssembler = new ComponentUnitFactory("component-assembler")
            {{
                requirements(Category.units,with(EXItems.chalk,500, zinc,250,EXItems.tenorite,180,EXItems.brass,120));
                localizedName = "Component Assembler";
                size = 3;
                addComponent(EXStatusEffects.componentBrute,with(EXItems.brass,40,EXItems.tenorite,50,EXItems.rawClay,10));
                addComponent(EXStatusEffects.componentFreeze,with(EXItems.chalk,100,EXItems.diamond,20,EXItems.dryIce,4));
                addComponent(EXStatusEffects.componentShield,with(EXItems.diamond,80,EXItems.vanadium,50));
                addComponent(EXStatusEffects.componentSpeed,with(EXItems.vanadium,40,EXItems.radium,20, zinc,40));
            }};
            heatProcessor = new ExhiberLogicBlock("heat-processor"){{
                requirements(Category.logic,with(EXItems.rawClay,60, zinc,120,EXItems.quartz,40));
                localizedName = "Heat Processor";

                consumePower(24f/60f);

                description = "Uses energy to create Heat to withstand the harsh climat of Exhiber.";
                health = 110;
                instructionsPerTick = 3;
                range = 0;
                updateEffect = ExEffects.heatEffect;
                updateEffectInterval = 30;
            }};
            mixUp = new ItemLiquidCrossover("mix-up"){{
                requirements(Category.liquid,with(EXItems.diamond,2,EXItems.rawClay,1));
                localizedName = "Mix Up";
            }};
            tunnelExtractor = new AttributeCrafter("tunnel-extractor"){{
                requirements(Category.crafting,with(zinc,1000));
                localizedName = "Tunnel Extractor";
                size = 5;
                attribute = EXAttributes.tunnel;
                baseEfficiency = 0;
                consumeLiquid(EXLiquids.liquidTenorite,20f/60f);
                outputItem = new ItemStack(EXItems.dryIce,1);
            }};
            superfluxForge = new GenericCrafter("superflux-forge"){{
                requirements(Category.crafting,with(EXItems.vanadium,200,EXItems.brass,200,EXItems.fiberGlass,120));
                localizedName = "Superflux Forge";
                size = 3;
                consumePower(200f/60f);
                craftTime = 150;
                consumeLiquid(Liquids.nitrogen,12f/60f);
                consumeItems(with(EXItems.vanadium,2, zinc,2,EXItems.radium,1));
                outputItem = new ItemStack(EXItems.hyperfluxMatter,2);
                craftEffect = ExEffects.superfluxeffect;
            }};
            /*
            TODO section - Testing
             */
            smallTrajectoryTower = new CarPoint("small-trajectory-tower")
            {{
                requirements(Category.distribution,with(EXItems.vanadium,200));
                localizedName = "Small Trajectory Tower";
                size = 3;
                range = 25;
                itemCapacity = 500;
                railId = 1;
            }};
            smallTrajectoryCarFabricator = new CarCenter("small-trajectory-car-fabricator"){{
                requirements(Category.distribution,with(EXItems.vanadium,400));
                localizedName = "Small Trajectory Car Fabricator";
                unitType = EXUnits.smallTrajectoryCar;
                size = 3;
                range = 25;
                itemCapacity = 100;
                consumeItems(with(zinc,24,EXItems.rawClay,10));
                railId = 1;
            }};
            smallTrajectoryUnloader = new CarDeposit("small-trajectory-unloader"){{
                requirements(Category.distribution,with(EXItems.vanadium,400,EXItems.tenorite,150));
                localizedName = "Small Trajectory Unloader";
                size = 3;
                range = 25;
                itemCapacity = 400;
                railId = 1;
            }};

            trajectoryTower = new CarPoint("trajectory-tower")
            {{
                requirements(Category.distribution,with(EXItems.vanadium,800));
                localizedName = "Trajectory Tower";
                size = 5;
                range = 46;
                itemCapacity = 1250;
                railId = 2;
                railSize = 2;
            }};
            trajectoryCarFabricator = new CarCenter("trajectory-car-fabricator"){{
                requirements(Category.distribution,with(EXItems.vanadium,1600));
                localizedName = "Trajectory Car Fabricator";
                unitType = EXUnits.largeTrajectoryCar;
                size = 5;
                range = 46;
                itemCapacity = 200;
                consumeItems(with(zinc,120,EXItems.rawClay,50));
                railId = 2;
                railSize = 2;
            }};
            trajectoryUnloader = new CarDeposit("trajectory-unloader")
            {{
                requirements(Category.distribution,with(EXItems.vanadium,1000,EXItems.tenorite,400));
                localizedName = "Trajectory Unloader";
                size = 5;
                range = 46;
                itemCapacity = 1000;
                railId = 2;
                railSize = 2;
            }};
            smallRainShield = new StatusEffectBlock("small-rain-shield")
            {{
                requirements(Category.effect,with(zinc,320,vanadium,40,radium,10));
                localizedName = "Small Rain Shield";
                size = 3;
                radius = 8*28;
                consumeTime = 120;
                consumeItem(tenorite);
                consumeLiquid(Liquids.nitrogen,5f/60f);
                consumePower(10f/60f);
                statusEffect = EXStatusEffects.advancedPlating;
                statusEffectDuration = 121;
                unitEffect = Fx.shieldApply;
            }};
            /*
            Extra Down Here
            */

            loadSandbox();
        }
        public static void loadSandbox()
        {
            worldBarrier = new SquareBaseShiled("world-barrier")
            {{
                requirements(Category.effect,BuildVisibility.sandboxOnly,with());
                localizedName = "World Barrier";
                consumePower(1f/60f);
                health = 1000;
                armor = 10;
                radius = 8*2.5f;
                forceDark = true;
                maxSize = 7;
            }};
            siegeProducer = new UnitFactory("siege-producer")
            {{
                requirements(Category.units,BuildVisibility.sandboxOnly, with(Items.copper, 60, Items.lead, 70));
                localizedName = "Siege Production Center";
                plans = Seq.with(
                        new UnitPlan(EXUnits.probe, 60f * 45, with(zinc,20,EXItems.tenorite,10)),
                        new UnitPlan(EXUnits.voyager, 60f * 60, with(EXItems.brass,35,EXItems.vanadium,10)),
                        new UnitPlan(EXUnits.satellite, 60f * 90, with(EXItems.brass,50,EXItems.fiberGlass,10)),
                        new UnitPlan(EXUnits.barricade, 60f * 90, with(zinc,20,EXItems.tenorite,10))
                );
                size = 3;
                consumePower(1f/60f);
            }};
            coreHeaven = new CoreBlock("core-heaven"){{
                requirements(Category.effect, BuildVisibility.sandboxOnly,with(zinc,200,EXItems.diamond,100,EXItems.tenorite,20));
                localizedName = "Core: Heaven";
                size = 2;
                health = 10000000;
                itemCapacity = 1000000;
                alwaysUnlocked = true;
                unitCapModifier = 99;
                unitType = EXUnits.seraphim;
                envEnabled = 5;
            }};
        }
    public static float setRange(float range,float speed)
    {
        return range / (speed / 8);
    }

    public static String name(String n){
        return "exhiber-"+n;
    }
}
