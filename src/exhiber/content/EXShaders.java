package exhiber.content;

import arc.Core;
import arc.graphics.Texture;
import arc.graphics.gl.Shader;
import arc.scene.ui.layout.Scl;
import arc.util.Time;
import mindustry.Vars;

import static mindustry.Vars.renderer;
import static mindustry.graphics.Shaders.*;

public class EXShaders {
    public static RainShieldShader rainShield;
    public static ModSurfaceShader nitroPool;
    public static void init() {
        if (!Vars.headless) {
            rainShield = new RainShieldShader("rainshield", "screenspace");
        }
        nitroPool = new ModSurfaceShader("nitropool");
    }

    public static class RainShieldShader extends ModShader {
        public RainShieldShader(String frag, String vert) {
            super(frag, vert);
        }

        @Override
        public void apply() {
            setUniformf("u_time", Time.time / Scl.scl(1f));
            setUniformf("u_offset",
                    Core.camera.position.x - Core.camera.width / 2,
                    Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_texsize", Core.camera.width, Core.camera.height);
            setUniformf("u_invsize", 1f / Core.camera.width, 1f / Core.camera.height);
        }
    }

    public static class ModSurfaceShader extends ModShader{
        Texture noiseTex;

        public ModSurfaceShader(String frag) {
            super(frag);
            loadNoise();
        }

        public String textureName(){
            return "noise";
        }

        public void loadNoise(){
            Core.assets.load("sprites/" + textureName() + ".png", Texture.class).loaded = t -> {
                t.setFilter(Texture.TextureFilter.linear);
                t.setWrap(Texture.TextureWrap.repeat);
            };
        }

        @Override
        public void apply(){
            setUniformf("u_campos", Core.camera.position.x - Core.camera.width / 2, Core.camera.position.y - Core.camera.height / 2);
            setUniformf("u_resolution", Core.camera.width, Core.camera.height);
            setUniformf("u_time", Time.time);

            if(hasUniform("u_noise")){
                if(noiseTex == null){
                    noiseTex = Core.assets.get("sprites/" + textureName() + ".png", Texture.class);
                }

                noiseTex.bind(1);
                renderer.effectBuffer.getTexture().bind(0);

                setUniformi("u_noise", 1);
            }
        }
    }


    public static class ModShader extends Shader {
        public ModShader(String frag) {
            super(Vars.tree.get("shaders/screenspace.vert"),Vars.tree.get("shaders/" + frag + ".frag"));
        }
        public ModShader(String frag, String vert) {
            super(Vars.tree.get("shaders/" + vert + ".vert"), Vars.tree.get("shaders/" + frag + ".frag"));
        }
    }
}
