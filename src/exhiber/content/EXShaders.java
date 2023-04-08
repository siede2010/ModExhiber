package exhiber.content;

import arc.Core;
import arc.graphics.gl.Shader;
import arc.scene.ui.layout.Scl;
import arc.util.Time;
import mindustry.Vars;

public class EXShaders {
    public static RainShieldShader rainShield;

    public static void init() {
        if (!Vars.headless) {
            rainShield = new RainShieldShader();
        }
    }

    public static class RainShieldShader extends ModShader {
        public RainShieldShader() {
            super("rainshield", "screenspace");
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


    public static class ModShader extends Shader {
        public ModShader(String frag, String vert) {
            super(Vars.tree.get("shaders/" + vert + ".vert"), Vars.tree.get("shaders/" + frag + ".frag"));
        }
    }
}
